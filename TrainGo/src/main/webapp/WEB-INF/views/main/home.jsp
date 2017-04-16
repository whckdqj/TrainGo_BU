<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row_content">
    
    <div class="col-xs-5">
    	<p>미니예매 : 출발역, 도착역, 출발일</p>
    </div>
    <div class="col-xs-7">
    	<p>FAQ 5개내외</p>
    </div>
    <div class="col-xs-12">
    	<p>관광지 3~5개만 보임</p>
    </div>

    <%-- Revoltion Slider --%>
    <div class="col-xs-12 tp-banner-container" id="div_caracell">
        <div id="rev_caracell" class="tp-banner" style="display: none;">
            <ul>
                <li data-transition="fade" data-slotamount="7" data-masterspeed="1500">
                    <%-- <img src="${pageContext.request.contextPath}/resources/img/milkyway.jpg"  alt="slidebg1"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat"> --%>
                    <img src="${pageContext.request.contextPath}/resources/img/milkyway.jpg"  alt="slidebg1"  data-fullwidthcentering="on">
                    
                    <div class="tp-caption lfr stb stl" 
                        data-x="600"
                        data-y="390" 
                        >
                        <img src="${pageContext.request.contextPath}/resources/img/train4.gif" alt="Image 1" width="600" height="125">
                    </div>
                    
                    <div class="tp-caption lft stb stl" 
                        data-x="30"
                        data-y="30" 
                        data-speed="1000" 
                        data-start="500"
                        data-easing="easeOutExpo" 
                        data-end="5000"
                        data-endspeed="1000">
                        <img class="present" src="${pageContext.request.contextPath}/resources/img/romance_brg.jpg" alt="Image 1" style="border: 2px solid red;">
                    </div>

                    <div class="tp-caption lfb stb stl" 
                        data-x="30"
                        data-y="30" 
                        data-speed="1000" 
                        data-start="5500"
                        data-easing="easeOutExpo" 
                        data-end="10000"
                        data-endspeed="1000">
                        <img class="present" src="${pageContext.request.contextPath}/resources/img/kyoungbok.jpg" alt="Image 1" style="border: 2px solid pink;">
                    </div>

                    <div class="tp-caption lfr stb stl" 
                        data-x="30"
                        data-y="30" 
                        data-speed="1000" 
                        data-start="10500"
                        >
                        <img class="present" src="${pageContext.request.contextPath}/resources/img/ecobridge.jpg" alt="Image 1" style="border: 2px solid skyblue;">
                    </div>

                    <div class="tp-caption slider-text-title sft str"
                        data-x="675" 
                        data-y="75" 
                        data-speed="300"
                        data-start="800" 
                        >차분한 밤하늘</div>


                    <div
                        class="tp-caption slider-text-description sft str"
                        data-x="675" 
                        data-y="150" 
                        data-start="1000"
                        >
                        경남 고성군 하일면 동화리, 동화 어촌 체험마을어귀 황매산<br/>
                        황매산은 철쭉으로도 유명한 관광지로<br/>
                        겨울을 제외한 모든 계절에 은하수를 구경 할 수 있다. 물론...
                    </div>
                    <div
                        class="tp-caption slider-text-description sft str"
                        data-x="675" 
                        data-y="225" 
                        data-start="1500"
                        >
                        <a href="#" role="button" class="btn btn-primary">More Info</a>
                    </div></li>
            </ul>
        </div>
    </div>
</div><%-- row_content --%>

<!-- CustomScript -->
<script src="${pageContext.request.contextPath}/resources/js/main_hdl.js"></script>