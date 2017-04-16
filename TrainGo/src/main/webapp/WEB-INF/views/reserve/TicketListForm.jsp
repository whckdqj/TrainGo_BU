<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- Contents Area --%>
	<table class="table">
		<thead>
			<tr>
				<th>열차번호</th>
				<th>시트번호</th>
				<th>출발역</th>
				<th>출발시간</th>
				<th>도착역</th>
				<th>도착시간</th>
				<th>요금</th>
				<th>취소여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.trainnum}</td>
					<td>${list.seatnum}</td>
					<td>${list.departsta}</td>
					<td>${list.departtime}</td>
					<td>${list.arrivalsta}</td>
					<td>${list.arrivaltime}</td>
					<td>${list.charge}</td>




					 <c:choose>
					 
						<c:when test="${list.cancel eq 'cancelP' and list.nowme<list.dptme}">
										<td><a role="button" class="btn btn-info btn-sm"
									href="${pageContext.request.contextPath}/cancelticket.do?id=${user_id}&trainnum=${list.trainnum}&seatnum=${list.seatnum}">취소</a></td>
								</c:when>			
						<c:when test="${list.cancel eq 'cancelP'}">
										<td>차떠남</td>
						</c:when>
						<c:otherwise>
							<td>취소 완료</td>
						</c:otherwise>	
					</c:choose> 
					
					
					
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="col-xs-1">
		<a role="button" class="btn btn-info" id="toHome"
			href="${pageContext.request.contextPath}/home.do">메인으로</a>
	</div>


	<!-- jQuery, BootStrap Area -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>





</body>
</html>