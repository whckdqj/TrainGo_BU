<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 formation">
    <form class="form-horizontal" id="admin_form" action="${pageContext.request.contextPath}/pageAdmin.do" method="post">
    <fieldset>
        <div class="form-group">
            <label for="table_update" class="col-xs-2 control-label">업데이트</label>
            <div class="col-xs-10">
                <select class="form-control" id="table_update" name="table_update">
                    <option selected="selected" value="0">선택해주세요!</option>
                    <option value="CTYCODELIST">도시코드</option>
                    <option value="VHCLEKNDLIST">차량종류코드</option>
                    <option value="CTYACCTOTRAINSTTNLIST">기차역ID</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-10"></div>
            <div class="col-xs-1"><input type="submit" class="btn btn-info" id="runCheck" value="Check"></div>
            <div class="col-xs-1"><input type="button" class="btn btn-warning" id="Cancle" value="Cancle" disabled="disabled"></div>
        </div>
        </fieldset>
    </form>
</div>

<div class="col-xs-12 tables" id="updateTable">
<table class="table table-hover">
    <thead>
        <tr>
            <th>테이블명</th>
            <th>생성일</th>
            <th>수정일</th>
            <th>최종호출일</th>
            <th>최종접근</th>
            <th>비고</th>
        </tr>
    </thead>
    <tbody id="upTargetTable">
        <tr>
            <td>${adminInfos["CTYCODELIST"].tableName}</td>
            <td>${adminInfos["CTYCODELIST"].created}</td>
            <td>${adminInfos["CTYCODELIST"].modified}</td>
            <td>${adminInfos["CTYCODELIST"].last_called}</td>
            <td>${adminInfos["CTYCODELIST"].access_object}</td>
            <td id="CTYCODELIST_update"></td>
        </tr>
        <tr>
            <td>${adminInfos["VHCLEKNDLIST"].tableName}</td>
            <td>${adminInfos["VHCLEKNDLIST"].created}</td>
            <td>${adminInfos["VHCLEKNDLIST"].modified}</td>
            <td>${adminInfos["VHCLEKNDLIST"].last_called}</td>
            <td>${adminInfos["VHCLEKNDLIST"].access_object}</td>
            <td id="VHCLEKNDLIST_update"></td>
        </tr>
        <tr>
            <td>${adminInfos["CTYACCTOTRAINSTTNLIST"].tableName}</td>
            <td>${adminInfos["CTYACCTOTRAINSTTNLIST"].created}</td>
            <td>${adminInfos["CTYACCTOTRAINSTTNLIST"].modified}</td>
            <td>${adminInfos["CTYACCTOTRAINSTTNLIST"].last_called}</td>
            <td>${adminInfos["CTYACCTOTRAINSTTNLIST"].access_object}</td>
            <td id="CTYACCTOTRAINSTTNLIST_update"></td>
        </tr>
    </tbody>
</table>
</div>