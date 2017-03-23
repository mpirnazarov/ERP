<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="todo"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<style>
    body :not(a) {
        color: inherit;
    }

    .statusTd {
        font-weight: 800;
    }

</style>
<spring:url value="/resources/core/css/paginationsStyle.css" var="paginationCss"/>
<link rel="stylesheet" href="${paginationCss}"/>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="page-header" style="border-bottom: 1px solid #FFFFFF; padding-top: 6%"><span class="glyphicon glyphicon-check" aria-hidden="true"></span> To Do</h2>

        <div class="w3-container">

            <%--????????????????????????--%>


            <div class="searchtoolstyle" style="height: 20%">

                <%--p1--%>
                <div style="display: flex; margin-bottom: 1%;">
                    <div style="margin-left: auto; width: 20%">
                        <label>FormType</label>
                        <select class="form-control" id="formTypeId">
                            <c:forEach var="type" items="${typeList}" varStatus="i">
                                <option id="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="margin-left: 2%; width: 20%">
                        <label>Current status</label>
                        <select class="form-control" id="statusId">
                            <c:forEach var="status" items="${statusList}" varStatus="i">
                                <option id="${status.key}">${status.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="margin-left: 2%; width: 20%">
                        <label>From</label>
                        <input type="text" class="form-control" id="sandbox-container-from" maxlength="10">
                    </div>
                    <div style="margin-left: 2%; width: 20%">
                        <label>To</label>
                        <input type="text" class="form-control" id="sandbox-container-to" maxlength="10">
                    </div>
                </div>

                <%--p2--%>
                <div class="row">
                    <div class="input-group col-md-5 col-md-offset-7 " style="margin-top: 10px; margin-bottom: 2%">
                        <span class="input-group-addon" id="search-addon1"
                              style="background-color: white; color: #337ab7; font-weight: 800">Attribute:</span>
                        <select class="form-control" aria-describedby="search-addon1" id="attributeId">
                            <option id="0"></option>
                            <option id="1">Author</option>
                            <option id="2">Title</option>
                        </select>
                        <input type="text" placeholder="Search for ..." class="form-control" id="searchInputId"
                               aria-describedby="search-addon1">
                        <div class="input-group-addon btn" onclick="pagedList(this.id)" id="btnFilter"
                             style="background-color: white; color: #337ab7; font-weight: 800"><span
                                class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                        </div>
                    </div>
                </div>
            </div>


        </div>


        <table class="table sarTable sarTableBoxShadow" id="tablecha">
            <thead>
            <tr>
                <%--<th>#</th>--%>
                <th>Form type</th>
                <th>Title</th>
                <th>Author</th>
                <th>Request date</th>
                <th>Current status</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="tbodycha">

            </tbody>

        </table>
        <ul class="sagination modal-1" id="pagedListContainer"></ul>
    </div>
</div>

</div>
</div>

<script>
    $("#searchInputId").keyup(function (event) {
        if (event.keyCode == 13) {
            $("#btnFilter").click();
        }
    });
    $('#sandbox-container-from').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
    $('#sandbox-container-to').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

<script type="text/javascript">
    function initialize() {
        pagedList(0);
    }
    var currentPage = 0;
    var page = 0;


    function pagedList(id) {
        //Filter attributes
        var selectedformType = $('#formTypeId option:selected').prop('id');
        var selectedStatus = $('#statusId option:selected').prop('id');
        var selectedAttribute = $('#attributeId option:selected').prop('id');
        var sandBoxcontainerfrom = $('#sandbox-container-from').datepicker({format: "yyyy-mm-dd"}).val();
        var sandBoxcontainerto = $('#sandbox-container-to').datepicker({format: "yyyy-mm-dd"}).val();
        var attrValue = $('#searchInputId').val();

        //Page buttons container
        var container = $('#pagedListContainer');
        var numberOfPages = 0;
        //table for todo list
        var table = $('#tablecha');
        var tbody = $('#tbodycha');

        //if next
        if (id == -1) {
            page = currentPage + 1;
        }
        //if previous
        else if (id == -2) {
            page = currentPage - 1;
        }
        //if filter
        else if (id == "btnFilter") {
            id = 0;
        }
        //if page number is clicked
        else {
            page = id;
        }

        $.ajax({
            type: "POST",
            processData: false,
            data: 'selectedformType=' + selectedformType +
            '&selectedStatus=' + selectedStatus +
            '&selectedAttribute=' + selectedAttribute +
            '&selectedDateFrom=' + sandBoxcontainerfrom +
            '&attrValue=' + attrValue +
            '&selectedDateTo=' + sandBoxcontainerto,
            url: '${pageContext.request.contextPath}/Workflow/MyForms/todo/list/' + page,
            success: function (data) {

                //table
                tbody.appendTo(table);
                tbody.empty();

                //set current page
                currentPage = data.page;

                if (data.models.length == 0) {
                    $('#notFoundDiv').show();

                }
                else {
                    $('#notFoundDiv').hide();
                    $(data.models).each(function (i, req) {
                        //generate table
                        $('<tr/>').appendTo(tbody)
                        /*.append($('<td/>').text(i+1))*/
                            .append($('<td/>').text(req.form_type))
                            .append($('<td/>').text(req.request_subject))
                            .append($('<td/>').text(req.user_name))
                            .append($('<td/>').text(req.date_created))
                            .append($('<td/>').text(req.status).addClass("statusTd"))
                            .append($('<td/>').append($('<div class="btn btn-blue" onclick="location.href=\'/Workflow/MyForms/details/1/' + req.request_id + '\'">View</div>')));
                    });
                }

                $('.statusTd:contains("In progress")').css('color', '#339abf');
                $('.statusTd:contains("Revision")').css('color', '#edb81f');
                $('.statusTd:contains("Rejected")').css('color', '#ff0000');
                $('.statusTd:contains("Draft")').css('color', '#edb81f');
                $('.statusTd:contains("Approved")').css('color', '#0bbf15');
                $('.statusTd:contains("Terminated")').css('color', '#ff0000');
                $('.statusTd:contains("Finished")').css('color', '#59feef');
                $('.statusTd:contains("Deleted")').css('color', '#000000');

                numberOfPages = data.maxPages;
                container.empty();
                container.append($('<li><a href="#" id="-2" onclick="pagedList(this.id); return false">Previous</a></li>'));

                //generate pegination buttons
                for (count = 1; count < numberOfPages + 1; count++) {
                    if (count == currentPage) {
                        container.append($('<li><a href="#" id="' + count + '" class="active" id="' + count + '" onclick="pagedList(this.id); return false">' + count + '</a></li>'));
                    }
                    else {
                        container.append($('<li><a href="#" id="' + count + '" onclick="pagedList(this.id); return false">' + count + '</a></li>'));
                    }
                }
                container.append($('<li><a href="#" id="-1" onclick="pagedList(this.id); return false">Next</a></li>'));
            },


            error: function () {
                alert("error");
            }
        });
    }


</script>

<script type="text/javascript">
    var loading_gif = $('#loading_img');
    $(document).ready(function () {

        $('#notFoundDiv').hide();
        initialize();

    });

    var source = new EventSource()
</script>