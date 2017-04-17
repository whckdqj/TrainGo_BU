<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12">
<table class="table table-hover">
	<thead>
		<tr>
			<th>도시코드</th>
			<th>도시이름</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="ctyCodeList" items="${list}">
        <tr>
            <td>${ctyCodeList.cityCode}</td>
            <td>${ctyCodeList.cityName}</td>
        </tr>
        </c:forEach>
	</tbody>
</table>
</div>