<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-main-style">
	<h2>회원상세정보</h2>
	<div class="form-group">
	<ul>
		<li>아이디 :  ${member.id}</li>
		<li>이름 :  ${member.name}</li>
		<li>전화번호 :  ${member.phone}</li>
		<li>이메일 :  ${member.email}</li>
		<li>우편번호 :  ${member.zipcode}</li>
		<li>주소 :  ${member.address1}</li>
		<li>상세주소 :  ${member.address2}</li>
		<li>가입일 :  ${member.reg_date}</li>
	</ul>
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<input type="button" value="수정" onclick="location.href='memberModify.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='memberDelete.do'">
	</div>
</div>