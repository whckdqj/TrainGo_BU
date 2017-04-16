$(function(){
	var clickseat="";
	var clickseat2="";
	var clickseat3="";
	var output="";

	$('.seats').css('color','#0000ff');
	$(document).on('click','.seats',function(){   
		output="";

		if(clickseat==""){    
			clickseat=$(this).text()+", "; 
			$(this).css('color','red');
		}else if(clickseat2==""){
			clickseat2=$(this).text()+", ";
			$(this).css('color','red');
		}else if(clickseat3==""){
			clickseat3=$(this).text();
			$(this).css('color','red');
		}else{
			alert("더 이상 선택이 불가합니다.");
			return false;
		}
		output=clickseat+clickseat2+clickseat3; 
		$("#seatnum").val(output);

		$(this).attr('class','off');   
	});


	$(document).on('click','.off',function(){
		output="";

		if(clickseat==$(this).text()+", "){    
			clickseat="";     
		}else if(clickseat2==$(this).text()+", "){
			clickseat2="";
		}else if(clickseat3==$(this).text()){
			clickseat3=""; } 

		output=clickseat+clickseat2+clickseat3; 
		$("#seatnum").val(output); 
		$(this).css('color','#0000ff') 
		$(this).attr('class','seats');

	});



	$('#select_seat').submit(function(){  
		var ser = jQuery("#select_seat").serialize();
		console.log(ser);
		alert(ser);
		if($("#seatnum").val()==""){
			alert("좌석을 선택하세요!!");   
			return false;  }  });
});		