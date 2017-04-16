/**
 * Table Handler
 */
jQuery(document).ready(function(){
	// Vhcle Kind Loading
	getTrKndList();
	// Get City
	getCityNodes();
	
	// Selected Element Influence to StationCode
	jQuery(document).on("change", "#area_dep", function(){
		var vals = jQuery("#area_dep option:selected").val();
		console.log(vals);
		if(jQuery("#area_dep option:selected").val()=="선택하세요"){
			jQuery("#stn_dep").empty().append('<option selected="selected">출발지를 선택해 주세요</option>');
			return;
		}
		getStaionCodes(jQuery("#stn_dep"), jQuery("#area_dep option:selected").val());
	});
	jQuery(document).on("change", "#area_arr", function(){
		/*var vals = jQuery("#area_arr option:selected").val();
		console.log(vals);*/
		if(jQuery("#area_arr option:selected").val()=="선택하세요"){
			jQuery("#stn_arr").empty().append('<option selected="selected">도착지를 선택해 주세요</option>');
			return;
		}
		getStaionCodes(jQuery("#stn_arr"), jQuery("#area_arr option:selected").val());
	});
	
	jQuery("#dept_time").datepicker({
		showMonthAfterYear:true
		,dateFormat: "yy-mm-dd"
		,minDate:0, maxDate:"+2D"
		,defaultDate: +0
	});
	
	jQuery("#table_form").on("submit", function(event){
		// Not Select - Error
		if(jQuery("#area_dep option:selected").val()=="선택하세요"){
			jQuery("#area_dep").focus();
			event.preventDefault();
			return false;
		}
		if(jQuery("#area_arr option:selected").val()=="선택하세요"){
			jQuery("#area_arr").focus();
			event.preventDefault();
			return false;
		}
		
		var ser = jQuery("#table_form").serialize();
		console.log(ser);
		jQuery("#timeTable").show();
		jQuery.ajax({
			url: getContextPath()+"/table/getStationInfoTable.do",
			type: "post",
			data: ser,
			dataType: "json",
			cache: false,
			timeout: 50000,
			success: function(data){
			    // List-Up Map
	            var theList = data.list;
	            
				jQuery("#targetTable").show().empty();
				
				if(data == null){
					alert("목록 호출 오류 발생!");
					return false;
				}
				else{
					jQuery(theList).each(function(index, item){
						var output = "<tr>";
						output += "<td>"+item.depPlaceName+"</td>";
						output += "<td>"+item.arrPlaceName+"</td>";
						output += "<td>"+item.trainGradeName+"</td>";
						output += "<td>"+item.depPlandTime+"</td>";
						output += "<td>"+item.arrPlandTime+"</td>";
						output += "<td>"+item.adultCharge+"</td>";
						output += "</tr>";
						
						// 문서 객체에 추가
						jQuery("#targetTable").append(output);
					});
				}
			},
			error: function(){
				jQuery("#timeTable").show().empty();
				jQuery("#timeTable").text("네트워크 오류");
			}
		});
		
		event.preventDefault();
	});
	
	// Admin Update
	jQuery("#admin_form").on("submit", function(event){
        // Not Select - Error
        if(jQuery("#table_update option:selected").val()=="0"){
            jQuery("#table_update").focus();
            event.preventDefault();
            return false;
        }
        
        var ser = jQuery("#admin_form").serialize();

        jQuery.ajax({
            url: getContextPath()+"/pageAdmin.do",
            type: "post",
            data: ser,
            dataType: "json",
            cache: false,
            timeout: 50000,
            success: function(data){
                // Get Bean
                var theMap = data.adminInfo;                
                
                if(data == null){
                    alert("목록 호출 오류 발생!");
                    return false;
                }
                else{
                    var context = "#"+theMap.tableName+"_update";
                    
                    // 문서 객체에 추가
                    jQuery("[id*=LIST_up]").text("");
                    jQuery(context).text("업뎃!");
                }
            },
            error: function(){
                jQuery("#updateTable").append("<div>(네트워크 오류)</div>");
            }
        });
        
        event.preventDefault();
    });
});

function getStaionCodes(context, vals){
	// Get Table for Station
	// vals : CityCode
	// context : select Tag context
	// Main Do : Generate Select Option Tags
	
	// Tag Construction : <option>2</option>
	
	// Post Data Set
	
	// Call Station Codes.
	jQuery.ajax({
		url: getContextPath()+"/table/getTrainSttnList.do",
		type: "post",
		data: {ctyCode:vals},
		dataType: "json",
		cache: false,
		timeout: 50000,
		success: function(data){
		    // List-Up Map
		    var theList = data.list;
			
			context.empty();
			
			if(data == null){
				alert("목록 호출 오류 발생!");
				return false;
			}
			else{
				jQuery(theList).each(function(index, item){
					var output = '<option value="'+item.nodeId+'" id="node'+index+'">';
					output += item.nodeName;
					output += '</option>';
					
					// 문서 객체에 추가
					context.append(output);
				});
			}
		},
		error: function(){
			context.empty();
			var err_out="<option>에러가 터졌네요!!</option>";
			context.append(err_out);
		}
	});
	
}

function getCityNodes(){
	// Get City Code
	// Main Do : Generate City Names
	// Tag Construction : <option>2</option>
	
	jQuery.ajax({
		url: getContextPath()+"/table/getCtyCode.do",
		type: "post",
		dataType: "json",
		cache: false,
		timeout: 50000,
		success: function(data){
			// List-Up the HashTable
		    var theList = data.list;
		    
			jQuery("#area_dep").empty();
			jQuery("#area_arr").empty();
			
			if(theList == null){
				alert("목록 호출 오류 발생!");
				return false;
			}
			else{
				var output = "<option>선택하세요</option>"
				jQuery(theList).each(function(index, item){
					output += "<option value='"+item.cityCode;
					output += "'>"+item.cityName;
					output += "</option>";
				});
				// 문서 객체에 추가
				jQuery("#area_dep").append(output);
				jQuery("#area_arr").append(output);
			}
		},
		error: function(){
			jQuery("#area_dep").empty();
			jQuery("#area_arr").empty();
			
			var out_err="<option>지역정보 로딩실패</option>";
			
			jQuery("#area_dep").append(out_err);
			jQuery("#area_arr").append(out_err);
		}
	});
	
}

function getTrKndList(){
	// Get Train Kind List
	// Main Do : Generate CheckBox Elements
	
	// Tag Construction : <label class="checkbox-inline"> <input type="checkbox" id="inlineCheckbox1" value="option1" checked="checked"> 1</label>
	
	jQuery.ajax({
		url: getContextPath()+"/table/getVhcleKnd.do",
		type: "post",
		dataType: "json",
		cache: false,
		timeout: 50000,
		success: function(data){
		    // List-Up the Map
		    var theList = data.list;
			
			jQuery("#trKnd").empty();
			
			if(data == null){
				alert("목록 호출 오류 발생!");
				return false;
			}
			else{
				var output="";
				jQuery(theList).each(function(index, item){
					output += "<label class='radio-inline'>";
					output += "<input type='radio' name='vehicle' value='"+item.vehicleKndId+"'>"+item.vehicleKndNm;
					output += "</label>";
				});
				output+="<label class='radio-inline'><input type='radio' name='vehicle' checked>전체</lable>";
				// 문서 객체에 추가
				jQuery("#trKnd").append(output);
			}
		},
		error: function(){
			jQuery("#trKnd").empty();
			jQuery("#trKnd").text("네트워크 오류");
		}
	});
}

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}