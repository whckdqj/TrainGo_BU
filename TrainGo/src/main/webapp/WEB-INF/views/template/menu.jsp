<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Navigation Bar --%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/index.do">TrainGo!</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<c:if test="${empty user_id}">
				<li><a href="${pageContext.request.contextPath}/member/memberWrite.do">회원가입</a></li>
				</c:if>
				<c:if test="${!empty user_id}">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">회원메뉴
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath}/member/memberDetail.do">마이페이지</a></li>
						<li><a href="${pageContext.request.contextPath}/member/memberModify.do">회원정보수정</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/member/memberDelete.do">회원탈퇴</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
					</ul></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/contents/reserve/resv_main.do">예매</a></li>
				<li><a href="${pageContext.request.contextPath}/contents/table/table.do">시간표</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">게시판
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath}/contents/board/list.do?code=1">맛집 정보</a></li>
						<li><a href="${pageContext.request.contextPath}/contents/board/list.do?code=2">축제 정보</a></li>
						<li><a href="${pageContext.request.contextPath}/contents/board/list.do?code=3">여행 정보</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/contents/qna/qnaList.do">Q&nbsp;&amp;&nbsp;A</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" style="">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">관리자
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath}/admin.do">초기설정</a></li>
						<li><a href="#">예약관리</a></li>
						<li><a href="#">자료관리</a></li>
						<li><a href="#">게시판관리</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>