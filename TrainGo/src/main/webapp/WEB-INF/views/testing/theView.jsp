<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Contents Area --%>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1">번호</span>
        <input type="text" class="form-control" aria-describedby="basic-addon1" value="${files.seq}" disabled="disabled">
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1">제목</span>
        <input type="text" class="form-control" aria-describedby="basic-addon1" value="${files.title}" disabled="disabled">
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1">작성</span>
        <input type="text" class="form-control" aria-describedby="basic-addon1" value="${files.id}" disabled="disabled">
    </div>
    <div class="input-group">
        <span class="input-group-addon" id="basic-addon1">등록일</span>
        <input type="text" class="form-control" aria-describedby="basic-addon1" value="${files.regdate}" disabled="disabled">
    </div>
    <hr size="1" noshade width="100%">
    <div class="jumbotron">
        <p>${files.content}</p>
    </div>
    <hr size="1" noshade width="100%">
    <div class="row">
        <c:if test="${files.filename.endsWith('.jpg')
                || files.filename.endsWith('.JPG')
                || files.filename.endsWith('.gif')
                || files.filename.endsWith('.GIF')
                || files.filename.endsWith('.png')
                || files.filename.endsWith('.PNG')
                }">
        <div class="col-xs-2">
            <a href="${pageContext.request.contextPath}/fileView.do" class="thumbnail"> <img src="${pageContext.request.contextPath}/pictureView.do" alt="No.01"></a>
        </div>
        </c:if>
    </div>
    <hr size="1" noshade width="100%">
    <div class="col-xs-3 col-xs-offset-9">
        <div class="btn-group btn-group-justified" role="group">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/home.do'">홈으로</button>
            </div>
        </div>
    </div>
    <br><br>
    <hr size="1" noshade width="100%">
    
    <div class="panel panel-success" id="reply_area">
        <div class="panel-heading">
            <h3 class="panel-title">댓글 달기</h3>
        </div>
        <div class="panel-body">
            <form id="re_form">
            <div class="form-group">
            <div class="col-xs-10">
            <textarea class="form-control rep-content" rows="3" name="re_content" id="re_content"
                    <c:if test="${empty user_id}">disabled="disabled" value="로그인해야 작성할 수 있습니다."</c:if>></textarea>
            </div>
            <label for="re_content" class="col-xs-2 control-label" id="re_second" style="margin-top: 10px;">
                <button class="btn btn-success btn-block">전송</button>
            </label>
            </div>
            <c:if test="${!empty user_id}">
                <div id="re_first" class="form-group text-center">
                    <span class="letter-count"><strong>300/300</strong></span>
                </div>
            </c:if>
            </form>
        </div>
    </div>
    <!-- 목록 출력 -->
    <div id="output"></div>
    <div class="paging_button" style="display:none;">
        <input type="button" value="다음 글 보기">
    </div>
    <div id="loading" style="display:none;">
        <img src="${pageContext.request.contextPath}/images/ajax-loader.gif">
    </div>
    <!-- 수정 폼 -->
    <div class="panel panel-success" id="modify_div" style="display:none;">
        <div class="panel-heading">
            <h3 class="panel-title">댓글 수정</h3>
        </div>
        <div class="panel-body">
            <form id="mre_form">
            <input type="hidden" name="re_num" id="mre_num">
            <input type="hidden" name="id" id="muser_id">
            <div class="form-group">
            <div class="col-xs-11">
                <textarea class="form-control rep-content" rows="3" name="re_content" id="mre_content"></textarea>
            </div>
            <div class="col-xs-1">
                <div class="btn-group-vertical" role="group" id="mre_second">
                    <div class="btn-group" role="group"><button type="submit" class="btn btn-primary">수정</button></div>
                    <div class="btn-group" role="group"><button type="button" class="re-reset btn btn-primary">취소</button></div>
                </div>
            </div>
            </div>
            <c:if test="${!empty user_id}">
                <div id="mre_first" class="form-group text-center">
                    <span class="letter-count"><strong>300/300</strong></span>
                </div>
            </c:if>
            </form>
        </div>
    </div>