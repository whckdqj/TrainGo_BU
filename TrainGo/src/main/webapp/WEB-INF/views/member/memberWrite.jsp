<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<form:form action="${pageContext.request.contextPath}/member/memberWrite.do" cssClass="form-horizontal" commandName="command" id="register_form">
			<legend>회원가입</legend>
			<form:errors element="div" cssClass="error-color"/>
				<div class="form-group">
					<label for="id" class="col-xs-2 control-label">아이디</label>
					<form:input path="id"/>
					<input type="button" class="btn btn-default" id="confirmId" value="ID중복체크">
					<img src="${pageContext.request.contextPath}/resources/img/ajax-loader.gif" 
					     width="16" height="16" style="display:none;">
					     <span id="message_id"></span>
					<form:errors path="id" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">이름</label>
					<form:input path="name"/><br>
					<form:errors path="name" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="passwd" class="col-xs-2 control-label">비밀번호</label>
					<form:password path="passwd" /><br>
					<form:errors path="passwd" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="phone" class="col-xs-2 control-label">전화번호</label>
					<form:input path="phone"/><br>
					<form:errors path="phone" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="email" class="col-xs-2 control-label">이메일</label>
					<form:input path="email"/><br>
					<form:errors path="email" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="zipcode" class="col-xs-2 control-label">우편번호</label>
					<form:input path="zipcode"/><br>
					<form:errors path="zipcode" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="address1" class="col-xs-2 control-label">주소</label>
					<form:input path="address1"/><br>
					<form:errors path="address1" cssClass="error-color"/>
				</div>
				<div class="form-group">
					<label for="address2" class="col-xs-2 control-label">상세주소</label>
					<form:input path="address2"/><br>
					<form:errors path="address2" cssClass="error-color"/>
				</div>
				<div class="form-group">
       				<div class="col-xs-10 col-xs-offset-2">
           				<button type="submit" class="btn btn-default">등록</button>
           				<button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/home.do'">홈으로</button>
       				</div>
   				</div>
			
	</form:form>
<script>
$(document).on("keydown", function(event){
	console.log(event.keyCode);
	if((event.which || event.keyCode) == 116){
		e.preventDefault();
	}
	}
});
</script>