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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Business trip"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>
<!-- JS file of autocomplete -->
<script src="/resources/core/js/jquery.easy-autocomplete.min.js"></script>

<!-- CSS file of autocomplete -->
<link rel="stylesheet" href="/resources/core/css/easy-autocomplete.min.css" />

<%--<script src="/resources/core/js/jquery-1.12.4.min.js"></script>--%>

<style>
    body :not(a) {
        color: inherit;
    }

    .sarvar {
        width: 200px;
    }


</style>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Business trip</h2>


        <form:form modelAttribute="businessTripVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form">
            <div class="form-header">
                <div class="input-group" style="width: 100%">
                    <span class="input-group-addon" id="subject-addon" style="width: 25%">Subject:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon"
                           style="width: 40%" path="subject" />
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"
                          style="width: 25%">Type of business trip:</span>
                    <form:select class="form-control" aria-describedby="saerchtype-addon" style="width: 40%" path="tripType" items="${tripTypeList}">

                    </form:select>
                    <label class="radio-inline" style="margin-left: 1%; margin-top: 1%">
                        <form:radiobutton value="true" path="domestic" /> Domestic
                    </label>
                    <label class="radio-inline" style="margin-top: 1%">
                        <form:radiobutton value="false" path="domestic" /> Overseas
                    </label>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="destination-addon" style="width: 25%">Destination:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="destination-addon"
                           style="width: 40%" path="destination" />
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon"
                          style="width: 25%">Purpose of Business trip:</span>
                    <form:textarea class="form-control" rows="3" id="comment" aria-describedby="purpose-addon"
                              style="width: 40%" path="purpose"></form:textarea>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        List of Business Trip members:
                    </label>
                    <div id="myTablecha" style="overflow: auto; width: 100%">
                    <table class="table order-list table-bordered" style="background-color: #2b669a; color: inherit" id="myTable">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <%--<th>Department</th>--%>
                            <th>From</th>
                            <th>To</th>
                            <th>Transportation</th>
                            <th>Daily Allowance</th>
                            <th>Accommodation</th>
                            <th>Currency for accomodation</th>
                            <th>Other</th>
                        </tr>
                        </thead>
                        <tbody id="membersDynamicBody" style="color: #0f0f0f">
                        <c:forEach items="${businessTripVM.membersEntityList}" varStatus="i">
                            <tr style="color: black">
                                <td><form:select path="membersEntityList[${i.index}].userId" items="${users}" cssClass="sarvar" id="userId"/></td>
                                <%--<td> <form:input type='text' name = "membersEntityList[0].organizationName" path="membersEntityList[0].organizationName"/> </td>--%>
                                <td><form:input type='date' path="membersEntityList[${i.index}].dateFrom" id="dateFrom"/> </td>
                                <td><form:input type='date' path="membersEntityList[${i.index}].dateTo" id="dateTo"/> </td>
                                <td><form:input type='number' min="0" path="membersEntityList[${i.index}].expenseTransportation" id="expenseTransportation"/></td>
                                <td><form:input type='number' min="0" path="membersEntityList[${i.index}].dailyAllowance" id="dailyAllowance"/></td>
                                <td><form:input type='number' min="0" path="membersEntityList[${i.index}].expenseAccommodation" id="expenseAccomodation"/></td>
                                <td><form:select path="membersEntityList[${i.index}].accomodationCurrency" items="${currency}" cssClass="sarvar" id="accomodationCurrency"/></td>
                                <td><form:input type='number' min="0" path="membersEntityList[${i.index}].expenseOther" id="expenseOther"/></td>
                                <td><input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                    <input type="button" class="btn btn-normal" value="Add Row" id="addrow"/>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        Detail scheadule and To-do list:
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: black"
                           id="toDoDynamicHead">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody id="toDoDynamicBody" />
                        <c:forEach items="${businessTripVM.toDoEntityList}" varStatus="i">
                            <tr>
                                <td><form:input type='date' min="0"  path="toDoEntityList[${i.index}].date"/></td>
                                <td><form:input type='text' min="0"  path="toDoEntityList[${i.index}].description"/></td>
                                <td><input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input type="button" class="btn btn-normal" value="Add Row" id="addrowToDo"/>
                </div>

            </div>
            <div class="form-footer" style="margin-bottom: 5%">

                <%-- Approvals, references, executors shouldn't be edited --%>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="attach-addon" style="width: 35%">Attachment:</span>
                    <c:forEach items="${unformattedVM.attachments}" var="attachment">
                        <div id="attachmentDiv"><span class="glyphicon glyphicon-list-alt"  aria-hidden="false"></span>
                            <a href="/Workflow/EditForm/files/delete/${attachment.id}"><span class="glyphicon glyphicon-remove-sign" aria-hidden="false" ></span></a>
                            <a href="/Workflow/EditForm/files/${attachment.id}">${attachment.fileName}</a></div>
                    </c:forEach>
                </div>

                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open
                          style="width: 25%">Attachment:</span>
                    <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="date-addon"
                          style="width: 25%">Date(Start/End):</span>
                    <form:input type="date" class="form-control" style="width:36%" name="start"  path="start"/>
                    <form:input type="date" class="form-control" style="width:36%" name="end"  path="end"/>
                </div>
                <div class="btn-group" role="group" aria-label="..." style="margin-left: 40%; margin-top: 3%">
                    <input id="tv" type="submit" name="submitBusinessTrip" value="Save" class="btn btn-success"/>
                    <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger" />
                </div>
            </div>
        </div>
        </form:form>
    </div>

</div>




<script type="text/javascript">


    /* Send input from approval list to controller by AJAX */
    $(document).ready(function() {
        /*$("input[type=submit]").click(function ()*/
        $("#tv").click(function (){
            var a=[];
            var b=[];
            var c=[];

            a = $("#approvals").children().siblings("input[type=text]").val();
            b = $("#references").children().siblings("input[type=text]").val();
            c = $("#executives").children().siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/NewForm/BusinessTripFormAjax",
                data :'approvals='+a+'&references='+b+'&executives='+c,
                success : function(response) {
//                    window.location.href = "/Workflow/NewForm/BusinessTripForm"
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });
        });
    });

    /* Send json data for approvals list*/

    $("#demo-input-local").tokenInput(${jsonData});
    $("#demo-input-local2").tokenInput(${jsonData});
    $("#demo-input-local3").tokenInput(${jsonData});


    var doc = document.getElementById("myTablecha");
        var counter = 4;

        $("#addrow").on("click", function () {
            var ab = document.getElementById('membersDynamicBody');
            var tr = document.createElement('tr');

            var td = document.createElement('td');
            var select = document.createElement('select');
            select.name = "membersEntityList["+counter+"].userId";
            /*elect.className = "form-control text-box single-line";*/
            select.className = 'sarvar';
            var opt;

            $.ajax({
                type : "GET",
                url : "/Workflow/Users",
                success: function(data){
                    $.each(data, function (i, user) {
                        opt = document.createElement('option');
                        opt.value = user.id;
                        opt.innerHTML = user.firstName;
                        select.appendChild(opt);
                    })

                }
            });
            td.appendChild(select);
            tr.appendChild(td);


            /*Input field for organization Name*/
            /*var td = document.createElement('td');
            var input = document.createElement('input');
            input.name = "membersEntityList["+counter+"].organizationName";
            td.appendChild(input);
            tr.appendChild(td);*/

            /*Input field for from date*/
            var td = document.createElement('td');
            var date = document.createElement('input');
            date.setAttribute('type', 'date');
            date.name = "membersEntityList["+counter+"].dateFrom";
            td.appendChild(date);
            tr.appendChild(td);

            /*Input field for to date*/
            var td = document.createElement('td');
            var date = document.createElement('input');
            date.setAttribute('type', 'date');
            date.name = "membersEntityList["+counter+"].dateTo";
            td.appendChild(date);
            tr.appendChild(td);

            /*Input field for expence transportation*/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseTransportation";
            td.appendChild(num);
            tr.appendChild(td);

            /*Input field for dailyAllowance*/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].dailyAllowance";
            td.appendChild(num);
            tr.appendChild(td);

            /*Input field for expenseAccommodation*/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseAccommodation";
            td.appendChild(num);
            tr.appendChild(td);

            /*Input field for select currency*/
            var td = document.createElement('td');
            var select2 = document.createElement('select');
            select2.name = "membersEntityList["+counter+"].accomodationCurrency";
            /*elect.className = "form-control text-box single-line";*/
            select2.className = 'sarvar';

            opt2 = document.createElement('option');
            opt2.value = "0";
            opt2.innerHTML = "";
            select2.appendChild(opt2);

            opt2 = document.createElement('option');
            opt2.value = "1";
            opt2.innerHTML = "UZS";
            select2.appendChild(opt2);

            opt2 = document.createElement('option');
            opt2.value = "2";
            opt2.innerHTML = "USD";
            select2.appendChild(opt2);

            td.appendChild(select2);
            tr.appendChild(td);

            /*Input field for expenseOther*/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseOther";
            td.appendChild(num);
            tr.appendChild(td);

            /*Delete button*/
            var td = document.createElement('td');
            var input = document.createElement('input');

            input.setAttribute('type', 'button');
            input.className = "ibtnDel btn btn-md btn-danger";
            input.value="Delete";
            td.appendChild(input);
            tr.appendChild(td);
            ab.appendChild(tr);
            counter++;






        $("table.order-list.table-bordered").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();

        });


    });

    $(document).ready(function () {
        var counter = 1;

        $("#addrowToDo").on("click", function () {
            var ab = document.getElementById('toDoDynamicBody');
            var tr = document.createElement('tr');

            /*Input field for date*/
            var td = document.createElement('td');
            var date = document.createElement('input');
            date.setAttribute('type', 'date');
            date.name = "toDoEntityList["+counter+"].date";
            td.appendChild(date);
            tr.appendChild(td);

             /*Input field for description*/
            var td = document.createElement('td');
            var input = document.createElement('input');
            input.name = "toDoEntityList["+counter+"].description";
            td.appendChild(input);
            tr.appendChild(td);

            /*Delete button*/
            var td = document.createElement('td');
            var input = document.createElement('input');

            input.setAttribute('type', 'button');
            input.className = "ibtnDel btn btn-md btn-danger";
            input.value="Delete";
            td.appendChild(input);
            tr.appendChild(td);
            ab.appendChild(tr);
            counter++;
        });

     $("table.table-bordered").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();

        });


    });

</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
