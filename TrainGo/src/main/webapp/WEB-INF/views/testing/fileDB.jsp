<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<div class="page-main-style">
    <h2>파일 시험</h2>
    <form:form action="fileDB.do" cssClass="form-horizontal" enctype="multipart/form-data" commandName="command" id="write_form">
        <form:errors element="div" cssClass="error-color"/>
        <div class="form-group">
            <label for="title" class="col-xs-2 control-label">제목</label>
            <div class="col-xs-10">
                <form:input cssClass="form-control" path="title" maxlength="50"/>
            </div>
            <form:errors path="title" cssClass="error-color"/>
        </div>
        <div class="form-group">
            <label for="content" class="col-xs-2 control-label">내용</label>
            <div class="col-xs-10">
                <form:textarea cssClass="form-control" rows="5" path="content"/>
            </div>
            <form:errors path="content" cssClass="error-color"/>
        </div>
        <div class="form-group">
            <label for="upload" class="col-xs-2 control-label">파일</label>
            <div class="col-xs-3">
                <input type="file" class="form-control" name="upload" id="upload">
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-10 col-xs-offset-2">
                <div class="btn-group btn-group-justified" role="group">
                    <div class="btn-group" role="group">
                        <button type="submit" class="btn btn-primary">등록</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/home.do'">홈으로</button>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>