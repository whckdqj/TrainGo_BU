<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page_error" class="page-main-style">
	<h2 class="align-center">오류 발생</h2>
	<table class="content-list">
		<tr>
			<td>
				오류가 발생했습니다. 관리자에게 문의바랍니다.
			</td>
		</tr>
		<tr>
			<td class="align-center">
				<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
			</td>
		</tr>
	</table>
</div>
<%
	Throwable e = (Throwable)request.getAttribute("exception");
	e.printStackTrace();
%>














