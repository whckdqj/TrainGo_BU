<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12">
<table class="table table-hover">
    <thead>
        <tr>
            <th>스키마</th>
            <th>테이블명</th>
            <th>생성일</th>
            <th>갱신일</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${dbaInfos.owner}</td>
            <td>${dbaInfos.object_name}</td>
            <td>${dbaInfos.created}</td>
            <td>${dbaInfos.last_ddl_time}</td>
        </tr>
    </tbody>
</table>
</div>