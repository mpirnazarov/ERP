<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: DS
  Date: 15.08.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<%--CSS--%>
<spring:url value="/resources/core/css/fullcalendar.css" var="fullcalendarCSS"/>
<spring:url value="/resources/core/css/jquery-ui.min.css" var="jqueryUiCSS"/>
<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="dateTimePickerCSS"/>

<div class="mainBodyBlock">
    <%--<h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span> Calendar</h2>--%>
    <div class="w3-container" style="padding-top: 2%">

        <button class="btn btn-green" onclick="test()">Run</button>

        <table class="table sarTable table-bordered">
            <!-- here should go some titles... -->
            <tr>
                <th>id</th>
                <th>Category Name</th>
            </tr>
            <c:forEach items="${categoryList}" var="category">
                <tr>
                    <td>
                        <c:out value="${category.id}"/>
                    </td>
                    <td>
                        <c:out value="${category.assetCategoryName}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<%--Modal--%>
<div class="modal fade" id="AssetCategoryModal" tabindex="-1" role="dialog" aria-labelledby="CalendarModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
            </div>
            <form:form modelAttribute="AssetCategory" method="post" id="categoryForm" enctype="multipart/form-data">
                <div class="modal-body">
                    <form:input class="hidden" id="assetCategoryId" path="id"></form:input>
                    <label for="categoryName">Category Name</label>
                    <form:input id="categoryName" path="assetCategoryName" type="text" class="form-control"/>

                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal" style="float: left;">Close</button>
                    <button id="catSaveBtn" class="btn btn-green" type="submit" name="submit">Add</button>
                    <button id="catUpdateBtn" class="btn btn-blue" type="submit" name="update">Update</button>
                </div>
            </form:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<script>
    /*$(document).ready(function () {
        var modal = $('#AssetListModal')
    })*/

    var modal = $('#AssetListModal')

    function test() {
        modal.modal('show');
    }

</script>
