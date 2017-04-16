<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Custom Style -->
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
<!-- BootStrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- jQuery UI Component -->
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css"
	rel="stylesheet">
<!-- Plugins-CSS -->
<link href="${pageContext.request.contextPath}/resources/css/slick-theme.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/slick.css"
	rel="stylesheet">
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<!-- Plugins-JS -->
<script src="${pageContext.request.contextPath}/resources/js/slick.js"></script>
<!-- CustomScript -->
<script src="${pageContext.request.contextPath}/resources/js/seats_hdl.js"></script>
</head>
<body>
		<!-- <div class="col-xs-12 text-center"> -->
		 <div class="form-group">		
			<h2>예매 현황</h2>
			<h3 class="text-right">${user_id}님환영합니다.</h3>			
		</div>


		<div class="bus">

			<!-- <table class="table table-bordered" -->
			<%-- <c:set var="num" value="0"/> --%>
			<%-- <c:forEach items="${seatlist}" begin="0" end="39" step="4"  var="seatlist" varStatus="status" > --%>
              
              <c:forEach var="seatlist" items="${seatlist}" varStatus="status">
                
                
               <%-- <c:set var="num" value="0"/> --%>
                
                <c:if test="${status.count%4 eq 1}">
              
                 <ul class='seats'>
				</c:if> 
					 		
								               
					<c:choose>
						<c:when test="${seatlist.booker eq null}">							
							<li class="seat">${seatlist.seatnum}</li>								
						</c:when>
						<c:otherwise>
							<li class="full">X</li>
						</c:otherwise>
					</c:choose>
					
				<c:if test="${status.count%2 eq 0 and status.count%4 ne 0}">	
				<li class='tonglo'></li>		
				</c:if>
					
				
				 <c:if test="${status.count%4 eq 0}">				
		         	 </ul> 
				</c:if> 
				

			</c:forEach>




		

		</div>



		<div class="col-xs-12 text-center">${pagingHtml}</div>

		<div class="col-xs-12">
			<hr>
			<form class="form-horizontal" id="select_seat" commandName="traincommand" action="seatselected.do" method="post">
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
								href="${pageContext.request.contextPath}/resv_main.do">취소</a>
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