<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>예매-확인</title>
<!-- Custom Style -->
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet">
<!-- BootStrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery UI Component -->
<link href="${pageContext.request.contextPath}/css/jquery-ui.min.css"
	rel="stylesheet">
<!-- Plugins-CSS -->
<link href="${pageContext.request.contextPath}/css/slick-theme.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/slick.css"
	rel="stylesheet">
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<!-- Plugins-JS -->
<script src="${pageContext.request.contextPath}/js/slick.js"></script>
<!-- CustomScript -->
<script src="${pageContext.request.contextPath}/js/seats_hdl.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">
			<img class="headimg"
				src="${pageContext.request.contextPath}/images/m-ktx-left.jpg">T<small>rain</small>
			G<small>o!!</small><img class="headimg"
				src="${pageContext.request.contextPath}/images/m-ktx-right.jpg">
		</h1>

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
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/index.do">TrainGo!</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<c:if test="${empty user_id}">
							<li><a
								href="${pageContext.request.contextPath}/contents/member/registerUserForm.do">회원가입</a></li>
						</c:if>
						<c:if test="${!empty user_id}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">회원메뉴
									<span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a
										href="${pageContext.request.contextPath}/contents/member/myPage.do">마이페이지</a></li>
									<li><a
										href="${pageContext.request.contextPath}/contents/member/modifyUserForm.do">회원정보수정</a></li>
									<li class="divider"></li>
									<li><a
										href="${pageContext.request.contextPath}/contents/member/deleteUserForm.do">회원탈퇴</a></li>
									<li class="divider"></li>
									<li><a
										href="${pageContext.request.contextPath}/contents/member/logout.do">로그아웃</a></li>
								</ul></li>
						</c:if>
						<li><a
							href="${pageContext.request.contextPath}/contents/reserve/resv_main.do">예매</a></li>
						<li><a
							href="${pageContext.request.contextPath}/contents/table/table.do">시간표</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">게시판
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a
									href="${pageContext.request.contextPath}/contents/board/list.do?code=1">맛집
										정보</a></li>
								<li><a
									href="${pageContext.request.contextPath}/contents/board/list.do?code=2">축제
										정보</a></li>
								<li><a
									href="${pageContext.request.contextPath}/contents/board/list.do?code=3">여행
										정보</a></li>
								<li class="divider"></li>
								<li><a
									href="${pageContext.request.contextPath}/contents/qna/qnaList.do">Q&nbsp;&amp;&nbsp;A</a></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a
							href="${pageContext.request.contextPath}/admin.do"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">관리자 <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">회원관리</a></li>
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

		<%-- Contents Area --%>
		<div class="col-xs-12 text-center">
			<h2>예매 현황</h2>
			<h3 class="text-right">${user_id}님환영합니다.</h3>
		</div>


		<div class="bus">

			<!-- <table class="table table-bordered" -->
			<c:set var="num" value="0"/>
			<c:forEach items="${seatlist}" begin="0" end="39" step="4"  var="seatlist" varStatus="status" >
              
				<ul class='seats'>
								               
					<c:choose>
						<c:when test="${seatlist.booker eq null}">
							<c:set var="num" value="${seatlist.seatnum}"/>
							<li class="seat">${num}</li>								
						</c:when>
						<c:otherwise>
							<li class="full">X</li>
						</c:otherwise>
					</c:choose>
					
						<c:choose>
						<c:when test="${seatlist.booker eq null}">
						<c:set var="num" value="${seatlist.seatnum+1}"/>
							<li class="seat">${num}</li>
						</c:when>
						<c:otherwise>
							<li class="full">X</li>
						</c:otherwise>
					</c:choose>
					
					<li class='tonglo'></li>					
					
						<c:choose>
						<c:when test="${seatlist.booker eq null}">
						<c:set var="num" value="${seatlist.seatnum+2}"/>
							<li class="seat">${num}</li>
						</c:when>
						<c:otherwise>
							<li class="full">X</li>
						</c:otherwise>
					</c:choose>
					
						<c:choose>
						<c:when test="${seatlist.booker eq null}">
						<c:set var="num" value="${seatlist.seatnum+3}"/>
							<li class="seat">${num}</li>
						</c:when>
						<c:otherwise>
							<li class="full">X</li>
						</c:otherwise>
					</c:choose>


				</ul>

			</c:forEach>




			<!-- </table> -->

		</div>



		<div class="col-xs-12 text-center">${pagingHtml}</div>

		<div class="col-xs-12">
			<hr>
			<form class="form-horizontal" id="select_seat"
				action="${pageContext.request.contextPath}/contents/reserve/seatselected.do"
				method="post">
				<fieldset>
					<div class="form-group">
						<label for="id" class="col-xs-2 control-label">ID</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="id" name="id"
								value="${user_id}" readonly>
						</div>

					</div>
					<div class="form-group">
						<label for="trainnum" class="col-xs-2 control-label">열차번호</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="trainnum"
								name="trainnum" value="${train.trainnum}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="trainname" class="col-xs-2 control-label">열차이름</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="trainname"
								name="trainname" value="${train.trainname}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="seatnum" class="col-xs-2 control-label">좌석번호</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="seatnum"
								name="seatnum" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="departsta" class="col-xs-2 control-label">출발역</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="departsta"
								name="departsta" value="${train.departsta}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="departtime" class="col-xs-2 control-label">출발시간</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="departtime"
								name="departtime" value="${train.departtime}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="arrivalsta" class="col-xs-2 control-label">도착역</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="arrivalsta"
								name="arrivalsta" value="${train.arrivalsta}" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="arrivaltime" class="col-xs-2 control-label">도착시간</label>
						<div class="col-xs-10">
							<input class="form-control" type="text" id="arrivaltime"
								name="arrivaltime" value="${train.arrivaltime}" readonly>
						</div>
					</div>


					<!-- 총 비용 추가 3.29 -->
					<div class="form-group">
						<label for="" class="col-xs-2 control-label">비용</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" id="cost" name="cost"
								value="${train.charge}" readonly>
						</div>
						<label class="col-xs-2 control-label">총 비용:</label><label
							class="col-xs-2 control-label" id="totalcost">0원</label>
						<div class="col-xs-2"></div>
						<div class="col-xs-1">
							<input type="submit" class="btn btn-info" id="runCheck"
								value="예매">
						</div>
						<div class="col-xs-1">
							<a role="button" class="btn btn-info" id="toHome"
								href="${pageContext.request.contextPath}/contents/reserve/resv_main.do">취소</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<%-- Footer Area --%>
		<footer class="footer">
			<div class="col-xs-12">
				<hr>
			</div>
			<div class="col-xs-12" id="footer-contents">
				<div class="collapse" id="thePrivacy">
					<div class="well">
						<div class="embed-responsive embed-responsive-4by3">
							<iframe class="embed-responsive-item"
								src="https://www.youtube.com/embed/qJBIrEhL95E"></iframe>
						</div>
					</div>
				</div>
				<div class="col-xs-12">
					<div class="col-xs-6">
						<ul class="list-inline">
							<li><a href="${pageContext.request.contextPath}/index.do">홈으로</a></li>
							<li><a data-g-label="about"
								href="${pageContext.request.contextPath}/contents/table/table.do">열차시간표</a></li>
							<li><a data-g-label="privacy" data-toggle="collapse"
								href="#thePrivacy" aria-expanded="false"
								aria-controls="thePrivacy">저작권</a></li>
							<li><a data-g-label="help"
								href="${pageContext.request.contextPath}/contents/qna/qnaList.do">Q&amp;A</a>
							</li>
						</ul>
					</div>
					<div class="col-xs-6">
						<p class="text-right">Copyright 2017 Team-TrainGo!™ All Right
							Reserved</p>
					</div>
				</div>
			</div>
		</footer>

	</div>
	<!-- jQuery, BootStrap Area -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>