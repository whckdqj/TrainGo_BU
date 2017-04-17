<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-main-style">
	<h2>관리자용 회원목록 보기</h2>
	
	<c:if test="${count == 0}">
		<div class="align-center">출력할 리스트가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="table table-hover">
    <tbody>
    <tr>
       	<th>아이디</th>	
		<th>이름</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>우편번호</th>
		<th>주소</th>
		<th>상세주소</th>
		<th>가입일</th>
    </tr>
    <c:forEach var="member" items="${list}">
	<tr>
	 	<td><a href="detailList.do?id=${member.id}">${member.id}</a></td>
	 	<td>${member.name}</td>
	 	<td>${member.phone}</td>
	 	<td>${member.email}</td>
	 	<td>${member.zipcode}</td>
	 	<td>${member.address1}</td>
	 	<td>${member.address2}</td>
	 	<td>${member.reg_date}</td>
	</tr>
	</c:forEach>
</tbody>
</table>
<div class="align-center">${pagingHtml}</div>
</c:if>
</div>

