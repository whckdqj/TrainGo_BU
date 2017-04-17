<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<div class="page-main-style">
	<h2>회원탈퇴</h2>
	<form:form action="memberDelete.do" commandName="command" id="delete_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/><br>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<li class="align-center">
				<input type="submit" value="탈퇴">
				<input type="button" value="회원상세정보" onclick="location.href='memberDetail.do'">
			</li>
		</ul>
	</form:form>
</div>