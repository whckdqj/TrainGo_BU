<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="page-main-style">
	<h1>회원 삭제</h1>
	<form action="deleteListPro.do">
			<input type="hidden" name="id" value="${param.id}">
			<input type="submit" value="삭제할까요?">
			<input type="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/list.do'">
	</form>
</div>
