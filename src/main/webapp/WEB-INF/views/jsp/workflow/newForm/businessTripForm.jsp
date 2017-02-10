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
                    <form:select class="form-control" aria-describedby="saerchtype-addon" style="width: 40%" path="tripType" items="${triptypeList}">

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
                    <form:textarea class="form-control" rows="5" id="comment" aria-describedby="purpose-addon"
                              style="width: 40%" path="purpose"></form:textarea>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        List of Business Trip members:
                    </label>
                    <div id="myTablecha" style="overflow: auto; width: 100%">
                    <table class="table order-list table-bordered" style="background-color: #2b669a; color: inherit"
                           id="myTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Name of (Organization)</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Transportation</th>
                            <th>Daily Allowance</th>
                            <th>Accommodation</th>
                            <th>Other</th>
                            <th>Overall</th>
                        </tr>
                        </thead>
                        <tbody id="membersDynamicBody" style="color: #0f0f0f">
                            <tr style="color: black">
                                <td> 1 <form:hidden value="0" name = "membersEntityList[0].id" min="0" path="membersEntityList[0].id"/></td>
                                <td><form:select path="membersEntityList[0].userId" items="${users}" cssClass="sarvar"/></td>
                                <td> <form:input type='text' name = "membersEntityList[0].organizationName" path="membersEntityList[0].organizationName"/> </td>
                                <td> <input type='date' name = "membersEntityList[0].dateFrom" /> </td>
                                <td> <input type='date' name = "membersEntityList[0].dateTo" /> </td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseTransportation" min="0" path="membersEntityList[0].expenseTransportation"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].dailyAllowance" min="0" path="membersEntityList[0].dailyAllowance"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseAccommodation" min="0" path="membersEntityList[0].expenseAccommodation"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseOther" min="0" path="membersEntityList[0].expenseOther"/></td>
                                <td><a class="deleteRow"/></td>
                            </tr>
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
                            <td>ID</td>
                            <th>Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody id="toDoDynamicBody" />
                        <tr>
                            <td>1</td>
                            <td><input type='date' name = "toDoEntityList[0].date" min="0" /></td>
                            <td><input type='text' name = "toDoEntityList[0].description" min="0" /></td>
                            <td><a class="deleteRow"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="button" class="btn btn-normal" value="Add Row" id="addrowToDo"/>
                </div>

            </div>
            <div class="form-footer" style="margin-bottom: 5%">
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"
                          style="width: 25%">Approvals:</span>
                    <div class="tab-content" id="approvals">
                        <input type="text" id="demo-input-local"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon"
                          style="width: 25%">Executive:</span>
                    <div class="tab-content" id="executives">
                        <input type="text" id="demo-input-local2"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon"
                          style="width: 25%">Reference:</span>
                    <div class="tab-content" id="references">
                        <input type="text" id="demo-input-local3"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open
                          style="width: 25%">Attachment:</span>
                    <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="date-addon"
                          style="width: 25%">Date(Start/End):</span>
                    <input type="date" class="form-control" style="width:36%" placeholder="Start" name="start" />
                    <input type="date" class="form-control" style="width:36%" placeholder="End" name="end" />
                </div>
                <div class="btn-group" role="group" aria-label="..." style="margin-left: 40%; margin-top: 3%">
                    <input id="tv2" type="submit" name="Save" value="Save" class="btn btn-success"/>
                    <input id="tv" type="submit" name="Submit" value="Submit" class="btn btn-success"/>
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
    $(document).ready(function () {
        $("#demo-input-local").tokenInput(${jsonData});
    });
    $(document).ready(function () {
        $("#demo-input-local2").tokenInput(${jsonData});
    });
    $(document).ready(function () {
        $("#demo-input-local3").tokenInput(${jsonData});
    });




    $(document).ready(function () {
        var counter = 1;

        $("#addrow").on("click", function () {
            var ab = document.getElementById('membersDynamicBody');
            var tr = document.createElement('tr');
            var td = document.createElement('td');
            td.innerHTML = counter+1;
            tr.appendChild(td);
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

            var td = document.createElement('td');
            td.appendChild(select);
            tr.appendChild(td);


            /*Input field for organization Name*/
            var td = document.createElement('td');
            var input = document.createElement('input');
            input.name = "membersEntityList["+counter+"].organizationName";
            td.appendChild(input);
            tr.appendChild(td);

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

            /*Input field for expenseOther*/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseOther";
            td.appendChild(num);
            tr.appendChild(td);

            /*Input field for expenceTotal*/
            /*var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('value', 100);
            num.setAttribute('disabled', true);
            td.appendChild(num);
            tr.appendChild(td);*/


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



        $("table.order-list.table-bordered").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();

        });


    });

    $(document).ready(function () {
        var counter = 1;

        $("#addrowToDo").on("click", function () {
            var ab = document.getElementById('toDoDynamicBody');
            var tr = document.createElement('tr');
            var td = document.createElement('td');
            td.innerHTML = counter+1;
            tr.appendChild(td);

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
