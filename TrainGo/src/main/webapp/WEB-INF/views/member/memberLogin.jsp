<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<div class="page-main-style">
	<form:form action="memberLogin.do" cssClass="form-horizontal" commandName="command" id="login_form">
		<form:errors element="div" cssClass="error-color"/>
			<div class="form-group">
				<label for="id" class="col-xs-2 control-label">아이디</label>
				<form:input path="id"/>
				<form:errors path="id" cssClass="error-color"/>
			</div>
			<div class="form-group">
				<label for="passwd" class="col-xs-2 control-label">비밀번호</label>
				<form:password path="passwd"/><br>
				<form:errors path="passwd" cssClass="error-color"/>
			</div>
			
			<div class="form-group">
       				<div class="col-xs-10 col-xs-offset-2">
       					<input type="submit" class="btn btn-default" value="로그인">
						<input type="button" class="btn btn-default" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/home.do'">
       				</div>
   			</div>
	</form:form>
</div>