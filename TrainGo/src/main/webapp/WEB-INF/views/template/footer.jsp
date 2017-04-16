<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Footer Area --%>
<footer class="footer">
<div class="col-xs-12"><hr></div>
<div class="col-xs-12" id="footer-contents">
	<div class="collapse" id="thePrivacy">
		<div class="well">
			<div class="embed-responsive embed-responsive-4by3">
				<iframe class="embed-responsive-item" src="https://www.youtube.com/embed/qJBIrEhL95E"></iframe>
			</div>
		</div>
	</div>
	<div class="col-xs-12">
	<div class="col-xs-6">
	<ul class="list-inline">
		<li><a href="${pageContext.request.contextPath}/index.do">홈으로</a></li>
		<li><a data-g-label="about"	href="${pageContext.request.contextPath}/contents/table/table.do">열차시간표</a></li>
		<li><a data-g-label="privacy" data-toggle="collapse" href="#thePrivacy" aria-expanded="false" aria-controls="thePrivacy">저작권</a></li>
		<li><a data-g-label="help" href="${pageContext.request.contextPath}/contents/qna/qnaList.do">Q&amp;A</a>
		</li>
	</ul>
	</div>
	<div class="col-xs-6"><p class="text-right">Copyright 2017 Team-TrainGo!™ All Right Reserved</p></div>
	</div>
</div>
</footer>