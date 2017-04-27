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
<c:set var="pageTitle" scope="request" value="Request"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>


<style>
    body :not(a) {
        /*color: inherit;*/
    }

    .statusTd {
        font-weight: 800;
    }

    .newRequestLabel {
        color: #fff;
        font-size: 13px;
        font-weight: 100;
        position: absolute;
        margin-top: -39px;
        margin-left: -41px;
        background-color: #39d04a;
        width: 36px;
        border-radius: 23%;
        text-align: center;
    }


</style>
<spring:url value="/resources/core/css/paginationsStyle.css" var="paginationCss"/>
<link rel="stylesheet" href="${paginationCss}"/>


    <div class="mainBodyBlock">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="headerText"><span
                class="glyphicon glyphicon-edit" aria-hidden="true"></span> Request</h2>

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
                        <select class="form-control" id="reqStatusId">
                            <c:forEach var="status" items="${statusList}" varStatus="i">
                                <option id="${status.key}">${status.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="margin-left: 1%; width: 20%">
                        <label>From</label>
                        <input placeholder="yyyy-mm-dd" type="text" class="form-control" id="reqsandbox-container-from">
                    </div>

                    <div style="margin-left: 1%; width: 20%">
                        <label>To</label>
                        <input placeholder="yyyy-mm-dd" type="text" class="form-control" id="reqsandbox-container-to">
                    </div>
                </div>

                <%--p2--%>
                <div style="width: 41%; margin-left: auto">
                    <div class="input-group " style="margin-top: 25px">
                        <span class="input-group-addon" id="search-addon1"
                              style="color: #4285f4; font-weight: 800">Title:</span>
                        <input type="text" placeholder="Search for ..." class="form-control" id="reqsearchId"
                               aria-describedby="search-addon1">
                        <div class="input-group-addon btn" onclick="pagedList(this.id)" id="btnReqFilter"
                             style="background-color: white; color: #4285f4; font-weight: 800"><span
                                class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                        </div>
                    </div>
                </div>
            </div>

            <table class="table sarTable table-bordered" id="dynamicHead">
                <thead>
                <tr>
                    <th>Form type</th>
                    <th>Title</th>
                    <th>Request date</th>
                    <th>Current status</th>
                    <th>Details</th>
                </tr>
                </thead>
                <tbody id="dynamicBody">
                </tbody>

            </table>
            <ul class="sagination modal-1" id="pagedListContainer"></ul>
        </div>
    </div>



<script>


    $('#reqsandbox-container-from').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
    $('#reqsandbox-container-to').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});


    $("#reqsearchId").keyup(function (event) {
        if (event.keyCode == 13) {
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
        var reqsandBoxcontainerFrom = $('#reqsandbox-container-from').datepicker({format: "yyyy-mm-dd"}).val();
        var reqsandBoxcontainerTo = $('#reqsandbox-container-to').datepicker({format: "yyyy-mm-dd"}).val();


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
            data: 'typeId=' + typeId +
            '&statusId=' + statusId +
            '&reqsandBoxcontainerFrom=' + reqsandBoxcontainerFrom +
            '&reqsandBoxcontainerTo=' + reqsandBoxcontainerTo +
            '&searchInput=' + searchInput,
            url: '${pageContext.request.contextPath}/Workflow/list/' + page,
            success: function (data) {

                //table
                tbody.appendTo(table);
                tbody.empty();

                //set current page
                currentPage = data.page;


                /*var d = new Date();
                 var todayDay = d.getDate();
                 var todayMonth = d.getMonth();
                 var todayYear = d.getFullYear();
                 var todayString = todayYear + "-" + todayMonth + "-" + todayDay;*/


                var today = new Date();
                var month = today.getMonth().toString().length == 1 ? "0" + today.getMonth().toString() : today.getMonth().toString();
                var dayOfMonth = today.getDate().toString().length == 1 ? "0" + today.getDate().toString() : today.getDate().toString();
                var todayString = today.getFullYear() + "-" + month + "-" + dayOfMonth;


                var x = 0;

                if (data.models == null || data.models == ' ') {
                    $('#notFoundDiv').show();


                }
                else {
                    $('#notFoundDiv').hide();
                    $(data.models).each(function (i, req) {

                        x++;
                        //generate table
                        $('<tr/>').appendTo(tbody)
                            .append($('<td/>').text(req.form_type).addClass("formTypeTd"))
                            .append($('<td/>').text(req.request_subject).addClass("titleTd"))
                            .append($('<td/>').text(req.date_created).addClass("requestDateTd"))
                            .append($('<td/>').text(req.status).addClass("statusTd"))
                            .append($('<td/>').append($('<div class="btn btn-blue" onclick="location.href=\'/Workflow/MyForms/details/2/' + req.request_id + '\'">View</div>')));

                        /*$('<div id="newdiv' + x + '" class="newRequestLabel">New</div>').appendTo(tbody)*/

                        var nowDate = parseInt(todayString.slice(-2));
                        var targetDate = parseInt(req.date_created.slice(-2));


                        /*$(this).prev("div").attr("newdiv", "newdiv" + x);

                         if (targetDate >= nowDate){

                         $(this).prev("div").attr("newdiv" + x).show();
                         }else {

                         $(this).prev("div").attr("newdiv" + x).hide();

                         }*/

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
                container.append($('<li><a href="#" id="-1"  onclick="pagedList(this.id); return false">Next</a></li>'));
            },

            error: function () {
                alert("Table not loaded");
            }


        });
    }


</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('#notFoundDiv').hide();
        initialize();
    });


</script>
