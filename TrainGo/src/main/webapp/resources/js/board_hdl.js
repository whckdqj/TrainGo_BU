$(document).ready(function(){
	// CityCodes
	getCityNodes();
	
	// Select Refresh
	// Selected Element Influence to StationCode
	jQuery(document).on("change", "#areaCode", function(){
		/*var vals = jQuery("#area_dep option:selected").val();
		console.log(vals);*/
		if(jQuery("#areaCode option:selected").val()=="선택하세요"){
			jQuery("#nodeId").empty().append('<option selected="selected">지역을 선택해주십시요.</option>');
			return;
		}
		getStaionCodes(jQuery("#nodeId"), jQuery("#areaCode option:selected").val());
	});
	
	// 2017.02.22 15:50 JCB Add For Search A Tag -> Button
	jQuery('li.search_key').on("click", function(event){
		var vals;
		if(jQuery(this).find("h5").text()=="제목"){
			// Change Button Value
			jQuery("#remarkSearch").text("제목");
			// Set Input Value For Send
			jQuery("#keyfield").val("content_title");
		}
		else if(jQuery(this).find("h5").text()=="ID"){
			// Change Button Value
			jQuery("#remarkSearch").text("ID");
			// Set Input Value For Send
			jQuery("#keyfield").val("id");
		}
		else if(jQuery(this).find("h5").text()=="내용"){
			// Change Button Value
			jQuery("#remarkSearch").text("내용");
			// Set Input Value For Send
			jQuery("#keyfield").val("content_introduction");
		}
		else{
			// Change Button Value
			jQuery("#remarkSearch").text("검색항목");
			// Set Input Value Clear
			jQuery("#keyfield").val("");
		}
	});
	
	//검색 유효성 체크
	$('#search_form').submit(function(){
		if($('#keyfield').val()==''){
			alert('검색항목을 선택하세요')
			$('#remarkSearch').focus();
			return false;
		}
		if($('#keyword').val()==''){
			alert('검색어를 입력하세요')
			$('#keyword').focus();
			return false;
		}
	});
	
	//글 등록, 수정 유효성 체크
	$('#write_form,#update_form').submit(function(){
		if($('#content_title').val()==''){
			alert('제목을 입력하세요!');
			$('#content_title').focus();
			return false;
		}
		if($('#content_introduction').val()==''){
			alert('내용을 입력하세요!');
			$('#content_introduction').focus();
			return false;
		}
		if($('#qna_title').val()==''){
			alert('제목을 입력하세요!');
			$('#qna_title').focus();
			return false;
		}
		if($('#qna_content').val()==''){
			alert('내용을 입력하세요!');
			$('#qna_content').focus();
			return false;
		}
		// 2017.02.23 17:24 JCB Add For Check Staion
		if(jQuery("#areaCode option:selected").val()=="선택하세요"){
			alert("지역을 선택하셔야 합니다.");
			jQuery("#areaCode").focus();
			return false;
		}
		if(jQuery("#nodeId option:selected").val()=="지역을 선택해 주십시요."){
			alert("역을 선택하셔야 합니다.");
			jQuery("#nodeId").focus();
			return false;
		}
	});
	
	
	
});

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
			
			jQuery("#areaCode").empty();
			
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
				jQuery("#areaCode").append(output);
			}
		},
		error: function(){
			jQuery("#areaCode").empty();
			
			var out_err="<option>지역정보 로딩실패</option>";
			
			jQuery("#areaCode").append(out_err);
		}
	});
	
}

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

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}