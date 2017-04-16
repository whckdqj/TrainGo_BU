$(document).ready(function(){
	var checkIdDuplicated = 0;
	
	//아이디 중복 체크
	$('#id_check').click(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요!');
			$('#id').focus();
			return;
		}
		
		//메시지 초기화
		$('#message_id').html('');
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading').hide();//로딩 이미지 감추기
				if(data.result == 'idNotFound'){
					$('#message_id').css('color','green')
					                .text('등록 가능');
					checkIdDuplicated = 1;
				}else if(data.result == 'idDuplicated'){
					$('#message_id').css('color','red')
					                .text('중복된 ID');
					$('#id').val('').focus();
					checkIdDuplicated = 0;
				}else{
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				$('#loading').hide();//로딩 이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
		
	});
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #id').keyup(function(){
		checkIdDuplicated = 0;
		$('#message_id').text('');
	});
	
	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요');
			$('#id').focus();
			return false;
		}
		if(checkIdDuplicated==0){
			alert('아이디 중복 체크 필수');
			$('#id_check').focus();
			return false;
		}
		if($('#passwd').val()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').focus();
			return false;
		}
		if($('#name').val()==''){
			alert('이름을 입력하세요');
			$('#name').focus();
			return false;
		}
		if($('#phone').val()==''){
			alert('전화번호를 입력하세요');
			$('#phone').focus();
			return false;
		}
		if($('#email').val()==''){
			alert('이메일을 입력하세요');
			$('#email').focus();
			return false;
		}
		if($('#zipcode').val()==''){
			alert('우편번호를 입력하세요');
			$('#zipcode').focus();
			return false;
		}
		if($('#address1').val()==''){
			alert('주소를 입력하세요');
			$('#address1').focus();
			return false;
		}
	});
	
	//회원정보수정 유효성 체크
	$('#modify_form').submit(function(){
		if($('#passwd').val()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').focus();
			return false;
		}
		if($('#name').val()==''){
			alert('이름을 입력하세요');
			$('#name').focus();
			return false;
		}
		if($('#phone').val()==''){
			alert('전화번호를 입력하세요');
			$('#phone').focus();
			return false;
		}
		if($('#email').val()==''){
			alert('이메일을 입력하세요');
			$('#email').focus();
			return false;
		}
		if($('#zipcode').val()==''){
			alert('우편번호를 입력하세요');
			$('#zipcode').focus();
			return false;
		}
		if($('#address1').val()==''){
			alert('주소를 입력하세요');
			$('#address1').focus();
			return false;
		}
	});
	
	//로그인 유효성 체크
	$('#login_form').submit(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요');
			$('#id').focus();
			return false;
		}
		if($('#passwd').val()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').focus();
			return false;
		}
	});
	
	//회원탈퇴 유효성 체크
	$('#delete_form').submit(function(){
		if($('#passwd').val()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').focus();
			return false;
		}
		if($('#cpasswd').val()==''){
			alert('비밀번호 확인을 입력하세요');
			$('#cpasswd').focus();
			return false;
		}
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('비밀번호와 비밀번호 확인이 불일치합니다.');
			$('#cpasswd').val('').focus();
			return false;
		}
	});
});