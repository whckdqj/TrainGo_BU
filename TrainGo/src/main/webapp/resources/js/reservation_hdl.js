// 2017.02.23 19:25 JCB Add For StaionInfo
jQuery(document).ready(function(){
	// Get City
	getCityNodes();
	
	// Selected Element Influence to StationCode
	jQuery(document).on("change", "#area_dep", function(){
		/*var vals = jQuery("#area_dep option:selected").val();
		console.log(vals);*/
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
	
	jQuery("#startdate").datepicker({
		showMonthAfterYear:true
		,dateFormat: "yy-mm-dd"
		,minDate:0, maxDate:"+2D"
		,defaultDate: +0
	});
	
	$("#reservation").submit(function(event){
		// 2017.02.23 JCB Add For Check Station - Available Only Seoul/Busan
		if(jQuery("#area_dep option:selected").val()=="선택하세요" ||jQuery("#area_arr option:selected").val()=="선택하세요"){
			alert("지역을 선택해주셔야 예매가 가능합니다.");
			return false;
		}
		if(jQuery("#stn_dep option:selected").val()!="NAT010000"||jQuery("#stn_arr option:selected").val()!="NAT014445"){
			alert("죄송합니다. 현재 서비스 가능 지역이 아닙니다." +
					"현재 가능지역 : 서울(출발) - 부산(도착)");
			console.log(jQuery("#stn_dep option:selected").val()+":"+jQuery("#stn_arr option:selected").val());
			return false;
		}
		jQuery("#resertable").show().empty();
		
		var ser = jQuery("#reservation").serialize();
		jQuery("#reservationcar").show();	
		
		 jQuery.ajax({
			url: getContextPath()+"/contents/reserve/trainseat.do",
			type: "post",
			data: ser,
			dataType: "json",
			cache: false,
			timeout: 50000,
			success: function(data){	
				if(data == null){
					alert("데이터가 안옴");
					return false;
					}			
				else{
					jQuery(data).each(function(index, item){
						var output = "<tr>";
						output += "<td>"+item.trainnum+"</td>";
						output += "<td>"+item.trainname+"</td>";
						output += "<td>"+item.departsta+"</td>";
						output += "<td>"+item.departtime+"</td>";
						output += "<td>"+item.arrivalsta+"</td>";
						output += "<td>"+item.arrivaltime+"</td>";
						output += "<td>"+item.charge+"</td>";
						output += "<td>"+item.seats+"</td>";
						output += "<td><a href='"+getContextPath()+"/contents/reserve/selectseat.do?id="+item.trainnum+"'>예매</a></td>";
						output += "</tr>";												
								
						
						
						// 문서 객체에 추가
						jQuery("#resertable").append(output);
					});
				}
			},
			error: function(){
				jQuery("#resertable").show().empty();
				jQuery("#resertable").text("에러");
		
		       
			}
		});
		
		event.preventDefault();  //에이태크가 폼이랑 서밋의 기본작동및 태그에 명시해둔 각종 기능을 막는 거..예를 들어 display none 기능과 같은..
		
	});
});

//2017.02.23 19:25 JCB Add For StaionInfo
function getStaionCodes(context, vals){
	// Get Table for Station
	// vals : CityCode
	// context : select Tag context
	// Main Do : Generate Select Option Tags
	
	// Tag Construction : <option>2</option>
	
	// Post Data Set
	
	// Call Station Codes.
	jQuery.ajax({
		url: getContextPath()+"/contents/table/getSttn.do",
		type: "post",
		data: {ctyCode:vals},
		dataType: "json",
		cache: false,
		timeout: 50000,
		success: function(data){
			
			context.empty();
			
			if(data == null){
				alert("목록 호출 오류 발생!");
				return false;
			}
			else{
				jQuery(data).each(function(index, item){
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
		url: getContextPath()+"/contents/table/getCities.do",
		type: "post",
		dataType: "json",
		cache: false,
		timeout: 50000,
		success: function(data){
			
			jQuery("#area_dep").empty();
			jQuery("#area_arr").empty();
			
			if(data == null){
				alert("목록 호출 오류 발생!");
				return false;
			}
			else{
				var output = "<option>선택하세요</option>"
				jQuery(data).each(function(index, item){
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

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}