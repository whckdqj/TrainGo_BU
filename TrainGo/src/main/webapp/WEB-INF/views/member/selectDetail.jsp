<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-main-style">
	<h2>관리자용 회원정보 상세</h2>

<div class="table-wrapper">
	<table class="table table-hover">
		<tbody>
			<tr>
				<td><h4>회원 아이디</h4></td>
				<td><h4>${member.id}</h4></td>
			</tr>
			<tr>
				<td><h4>이름</h4></td>
				<td><h4>${member.name}</h4></td>
			</tr>
			<tr>
				<td><h4>전화번호</h4></td>
				<td><h4>${member.phone}</h4></td>
			</tr>
			<tr>
				<td><h4>이메일</h4></td>
				<td><h4>${member.email}</h4></td>
			</tr>
			<tr>
				<td><h4>우편번호</h4></td>
				<td><h4>${member.zipcode}</h4></td>
			</tr>
			<tr>
				<td><h4>주소</h4></td>
				<td><h4>${member.address1}</h4></td>
			</tr>
			<tr>
				<td><h4>나머지 주소</h4></td>
				<td><h4>${member.address2}</h4></td>
			</tr>
		</tbody>
	</table>
</div>

<%-- <div class="col-xs-12">
<label class="col-xs-4 control-label">아이디 : ${member.id}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">비밀번호 : ${member.passwd}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">이름 : ${member.name}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">전화번호 : ${member.phone}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">이메일 : ${member.email}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">우편번호 : ${member.zipcode}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">주소 : ${member.address1}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">상세주소 : ${member.address2}</label>				
</div><br>
<div class="col-xs-12">
<label class="col-xs-4 control-label">가입일 : ${member.reg_date}</label>				
</div><br>
 --%>
	<div class="align-center">
		<input type="button" value="수정" onclick="location.href='member/updateList.do?id=${member.id}'">
		<input type="button" value="삭제" onclick="location.href='member/deleteList.do?id=${member.id}'">
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>
