<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="col-xs-12 formation">
    <form class="form-horizontal" id="table_form" action="${pageContext.request.contextPath}/table/callbackTable.do" method="post">
    <fieldset>
        <div class="form-group">
            <label for="area_dep" class="col-xs-2 control-label">출발지</label>
            <div class="col-xs-2">
                <select class="form-control" id="area_dep" name="dep_citycode">
                    <!-- <option selected="selected" value="0">출발지 로딩중...</option> -->
                </select>
            </div>
            <label for="stn_dep" class="col-xs-2 control-label">출발역</label>
            <div class="col-xs-3">
                <select class="form-control" id="stn_dep" name="depplaceid">
                    <option selected="selected">출발지를 선택해 주세요</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="area_arr" class="col-xs-2 control-label">도착지</label>
            <div class="col-xs-2">
                <select class="form-control" id="area_arr" name="arr_citycode">
                    <!-- <option selected="selected" value="0">도착지 로딩중...</option> -->
                </select>
            </div>
            <label for="stn_arr" class="col-xs-2 control-label">도착역</label>
            <div class="col-xs-3">
                <select class="form-control" id="stn_arr" name="arrplaceid">
                    <option selected="selected">도착지를 선택해 주세요</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="dept_time" class="col-xs-2 control-label">출발일</label>
            <div class="col-xs-4">
                <input type="text" class="form-control col-xs-2" id="dept_time" name="depplandtime" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="trKnd" class="col-xs-2 control-label">차종</label>
            <div class="col-xs-8" id="trKnd">
            </div>
            <div class="col-xs-1"><input type="submit" class="btn btn-info" id="runCheck" value="Check"></div>
            <div class="col-xs-1"><input type="button" class="btn btn-warning" id="Cancle" value="Cancle" disabled="disabled"></div>
        </div>
        </fieldset>
    </form>
</div>

<div class="col-xs-12 tables" id="timeTable" style="display: none;">
<table class="table table-hover">
    <thead>
        <tr>
            <th>출발역</th>
            <th>도착역</th>
            <th>차량종류</th>
            <th>출발시간</th>
            <th>도착시간</th>
            <th>운임</th>
        </tr>
    </thead>
    <tbody id="targetTable">
        
    </tbody>
</table>
</div>
<!-- CustomScript -->
<script src="${pageContext.request.contextPath}/resources/js/table_hdl.js"></script>