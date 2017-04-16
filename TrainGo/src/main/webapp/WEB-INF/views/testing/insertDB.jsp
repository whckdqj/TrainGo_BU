<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<form:form action="insertDB.do" commandName="command" class="form-horizontal" id="register_form">
    <legend>입력시험</legend>
    <form:errors element="div" cssClass="error-color"/>
    <div class="form-group">
        <label for="id" class="col-xs-2 control-label">ID</label>
        <div class="col-xs-8">
            <form:input path="id" cssClass="form-control col-xs-8" placeholder="아이디" size="8" maxlength="12"/>
        </div>
        <div class="col-xs-2">
            <input type="button" class="btn btn-default" value="ID중복체크" id="id_check"/><span id="message_id"></span>
            <img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
        </div>
        <form:errors path="id" cssClass="error-color"/>
    </div>
    <div class="form-group">
        <label for="passwd" class="col-xs-2 control-label">PW</label>
        <div class="col-xs-10">
            <form:password cssClass="form-control" path="passwd" placeholder="비밀번호" maxlength="12"/>
        </div>
        <form:errors path="passwd" cssClass="error-color"/>
    </div>
    <div class="form-group">
        <label for="name" class="col-xs-2 control-label">Name</label>
        <div class="col-xs-10">
            <form:input cssClass="form-control" path="name" placeholder="이름" maxlength="10"/>
        </div>
        <form:errors path="name" cssClass="error-color"/>
    </div>
    <div class="form-group">
        <label for="email" class="col-xs-2 control-label">E-Mail</label>
        <div class="col-xs-10">
            <form:input cssClass="form-control" path="email" placeholder="이메일" maxlength="30"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-10 col-xs-offset-2">
            <button type="submit" class="btn btn-default">등록</button>
            <button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/home.do'">홈으로</button>
        </div>
    </div>
</form:form>