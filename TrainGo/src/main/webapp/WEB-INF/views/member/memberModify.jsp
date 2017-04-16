<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<div class="page-main-style">
	<form:form action="memberModify.do" commandName="command" id="modify_form">
			<legend>회원정보 수정</legend>
			<form:errors element="div" cssClass="error-color"/>
				<%-- <div class="col-xs-12">
				<label for="id" class="col-xs-4 control-label">${command.id}</label>				
				</div><br> --%>
				
				<div class="form-group">
					<label for="name" class="col-xs-1 control-label">ID</label>
					${command.id}<br>
				</div>
				<div class="form-group">
					<label for="name" class="col-xs-1 control-label">이름</label>
					<form:input path="name"/><br>
					<form:errors path="name" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="passwd" class="col-xs-1 control-label">비밀번호</label>
					<form:password path="passwd" /><br>
					<form:errors path="passwd" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="phone" class="col-xs-1 control-label">전화번호</label>
					<form:input path="phone"/><br>
					<form:errors path="phone" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="email" class="col-xs-1 control-label">이메일</label>
					<form:input path="email"/><br>
					<form:errors path="email" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="zipcode" class="col-xs-1 control-label">우편번호</label>
					<form:input path="zipcode"/><br>
					<form:errors path="zipcode" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="address1" class="col-xs-1 control-label">주소</label>
					<form:input path="address1"/><br>
					<form:errors path="address1" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="address2" class="col-xs-1 control-label">상세주소</label>
					<form:input path="address2"/><br>
					<form:errors path="address2" cssClass="error-color"/>
				</div>
				<div class="form-group">
       				<div class="col-xs-10 col-xs-offset-1">
           				<button type="submit" class="btn btn-default">수정</button>
           				<button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/home.do'">홈으로</button>
       				</div>
   				</div>
	</form:form>
</div>