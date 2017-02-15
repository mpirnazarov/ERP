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
<c:set var="pageTitle" scope="request" value="Requset"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>


<style>
    body :not(a) {
        color: inherit;
    }


</style>
<spring:url value="/resources/core/css/paginationsStyle.css" var="paginationCss"/>
<link rel="stylesheet" href="${paginationCss}"/>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Request</h2>

        <div class="w3-container">
            <div class="searchtoolstyle" style="height: 20%">

                <%--p1--%>
                <div style="display: flex; margin-bottom: 1%;">
                    <div style="margin-left: auto; width: 20%">
                        <label>FormType</label>
                        <select class="form-control" id="reqTypeId">
                            <c:forEach var="type" items="${typeList}" varStatus="i">
                                <option id="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="margin-left: 1%; width: 20%">
                        <label>Current status</label>
                        <select class="form-control"  id="reqStatusId">
                            <c:forEach var="status" items="${statusList}" varStatus="i">
                                <option id="${status.key}">${status.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="margin-left: 1%; width: 20%">
                        <label>Request date</label>
                        <input type="text" class="form-control" id="reqsandbox-container">
                    </div>



                </div>

                    <%--p2--%>
                <div class="row">

                    <div class="input-group col-md-5 col-md-offset-7 " style="margin-top: 25px">
                        <span class="input-group-addon" id="search-addon1" style="background-color: white; border: none; color: black">Title:</span>
                        <input type="text" placeholder="Search for ..." class="form-control" id="reqsearchId" aria-describedby="search-addon1" style="border: none">
                        <div class="input-group-addon btn" onclick="pagedList(this.id)" id="btnReqFilter" style="background-color: white; color: #1e7ee2"><span
                                class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                        </div>
                    </div>


                    <%--<div class="input-group">
                        <span class="input-group-addon" id="saerchtype-addon">Title:</span>

                        <input type="text" class="form-control" id="reqsearchId">
                    </div>--%>

                    <%--<button type="button" class="btn btn-success" onclick="pagedList(this.id)" id="btnReqFilter"
                            style="margin-left: 20%; border-radius: 0; width: 25%"><span
                            class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                    </button>--%>
                </div>
            </div>

            <table class="table sarTable" id="dynamicHead">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Form type</th>
                    <th>Title</th>
                    <th>Request date</th>
                    <th>Current status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="dynamicBody">
                </tbody>

            </table>
            <ul class="sagination modal-5" id="pagedListContainer" style="margin-left: 37%"></ul>

        </div>
    </div>
</div>
</div>

<script>


    $('#reqsandbox-container').datepicker({format: "yyyy-mm-dd"});

    $("#reqsearchId").keyup(function(event){
        if(event.keyCode == 13){
            $("#btnReqFilter").click();
        }
    });


</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
<script type="text/javascript">
    function initialize() {
        pagedList(0);
    }

    var currentPage = 1;
    var page = 0;


    function pagedList(id) {

        var typeId = $('#reqTypeId option:selected').prop('id');
        var statusId = $('#reqStatusId option:selected').prop('id');
        var searchInput = $('#reqsearchId').val();
        var reqsandBoxcontainer = $('#reqsandbox-container').datepicker({format: "yyyy-mm-dd"}).val();


        var container = $('#pagedListContainer');
        var numberOfPages = 0;

        var table = $('#dynamicHead');
        var tbody = $('#dynamicBody');

        //if next
        if (id == -1) {
            page = currentPage + 1;
        }
        //if previous
        else if (id == -2) {
            page = currentPage - 1;
        }
        //if filter
        else if (id == "btnReqFilter") {
            id = 0;
        }
        //if page number is clicked
        else {
            page = id;
        }

        $.ajax({
            type: "POST",
            processData: false,
            data: 'typeId=' + typeId + '&statusId=' + statusId + '&reqsandBoxcontainer=' + reqsandBoxcontainer + '&searchInput=' + searchInput,
            url: '${pageContext.request.contextPath}/Workflow/list/' + page,
            success: function (data) {

                //table
                tbody.appendTo(table);
                tbody.empty();

                //set current page
                currentPage = data.page;

                $(data.models).each(function (i, req) {
                    //generate table
                    $('<tr/>').appendTo(tbody)
                        .append($('<td/>').text(i + 1))
                        .append($('<td/>').text(req.form_type))
                        .append($('<td/>').text(req.request_subject))
                        .append($('<td/>').text(req.date_created))
                        .append($('<td/>').text(req.status))
                        .append($('<td/>').append($('<div class="btn" onclick="location.href=\'/Workflow/MyForms/details/2/' + req.request_id + '\'"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></div>')));
                });

                numberOfPages = data.maxPages;
                container.empty();
                container.append($('<li><a href="#" id="-2" class="prev fa fa-arrow-left" onclick="pagedList(this.id)"></a></li>'));

                //generate pegination buttons
                for (count = 1; count < numberOfPages + 1; count++) {
                    if (count == currentPage) {
                        container.append($('<li><a href="#" id="' + count + '" class="active" id="' + count + '" onclick="pagedList(this.id)">' + count + '</a></li>'));
                    }
                    else {
                        container.append($('<li><a href="#" id="' + count + '" onclick="pagedList(this.id)">' + count + '</a></li>'));
                    }
                }
                container.append($('<li><a href="#" id="-1" class="prev fa fa-arrow-right" onclick="pagedList(this.id)"></a></li>'));
            },

            error: function () {
                alert("Table not loaded");
            }

        });
    }
</script>

<script type="text/javascript">
    $(document).ready(function () {
        initialize();
    });
</script>
