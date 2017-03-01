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
        width: 100%;
    }

    .w3-container {
        background-color: #FFFFFF;

        width: 115%;
        margin-left: -3%;

        -webkit-box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.62);
        -moz-box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.62);
        box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.62);
    }

    .w3-container input {
        width: 98%;

        border: 1px solid #999999;

    }

    .w3-container select {
        border: 1px solid #999999;
    }

    .w3-container span {
        width: 26%;
        font-style: italic;
        font-size: 17px;
        border: none;
        background-color: #FFFFFF;
    }

    .paper {
        background: #fff;
        box-shadow:
            /* The top layer shadow */
                0 -1px 1px rgba(0,0,0,0.15),
                    /* The second layer */
                0 -10px 0 -5px #eee,
                    /* The second layer shadow */
                0 -10px 1px -4px rgba(0,0,0,0.15),
                    /* The third layer */
                0 -20px 0 -10px #eee,
                    /* The third layer shadow */
                0 -20px 1px -9px rgba(0,0,0,0.15);
        /* Padding for demo purposes */
        padding: 30px;
    }

    div .input-group {
        width: 98%;
    }

    #buttonGroupcha {
        margin-left: 45%;
        margin-top: 2%;
    }

    #buttonGroupcha input {
        width: 71px;
        margin-left: 8px;
    }

    .reqfield:before {
        content: "*";
        color: #ff0000;
    }

    .w3-container span.warningIcon {

        position: absolute;
        color: #ff0000;
        width: 0%;
        font-style: normal;
        margin-left: 6px;

    }



    label.radio-inline {
        color: #000;
    }

    label.radio-inline input {
        width: 25%;
    }

    .form-group, .form-horizontal {
        margin-left: 0px; !important;
        margin-right: 0px; !important;
    }

    .table {
        margin-bottom: 0px;
    }

    thead {
        background-color: #b1beca;

    }

    thead th {
        text-align: center;
        color: #0c0c0c;
    }

    td {
        background-color: #FFFFFF;
    }

    .descInput {
        width: 50%;
    }

    .tableLabel {

        width: 100%;
        color: black;
        font-style: italic;
        font-size: 17px;
        margin-left: 2%;
        margin-top: 2%;
    }

    #addrow, #addrowToDo  {
        width: 100%;
        color: #000;
    }




</style>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="page-header"style="border: none; margin-left: -3%; padding-top: 6%;"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Business trip</h2>


        <form:form modelAttribute="businessTripVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form">
            <div class="form-header" style="padding-top: 1%">
                <div class="input-group" style="">
                    <span class="input-group-addon" id="subject-addon">Subject:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon" path="subject" />
                </div>
                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon">Type of business trip:</span>
                    <form:select class="form-control" aria-describedby="saerchtype-addon" path="tripType" items="${triptypeList}">
                    </form:select>

                    <div class="input-group">
                        <label class="radio-inline">
                            <form:radiobutton value="true" path="domestic" /> Domestic
                        </label>
                        <label class="radio-inline">
                            <form:radiobutton value="false" path="domestic" /> Overseas
                        </label>
                    </div>

                </div>
                <div class="input-group" style=" margin-top: 1%">
                    <span class="input-group-addon" id="destination-addon">Destination:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="destination-addon" path="destination" />
                </div>
                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon">Purpose of Business trip:</span>
                    <form:textarea class="form-control" rows="3" id="comment" aria-describedby="purpose-addon"
                              style="width: 100%; border-color:#999999" path="purpose"></form:textarea>
                </div>
                <div class="form-group" style="margin-left: 0px; margin-right: 0px">
                    <label class="tableLabel"><span class="glyphicon glyphicon-user" aria-hidden="true" style="width: 2%; font-style: normal"></span>
                        Business Trip members:
                    </label>
                    <div id="myTablecha" style="overflow: auto; width: 100%">
                    <table class="table order-list table-bordered" style="background-color: #2b669a; color: inherit">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <%--<th>Department</th>--%>
                            <th>From</th>
                            <th>To</th>
                            <th>Transportation</th>
                            <th>Daily Allowance</th>
                            <th>Accommodation</th>
                            <th>Accomodation Currency</th>
                            <th>Other</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="membersDynamicBody" style="color: #0f0f0f">
                            <tr style="color: black">
                                <td><form:select path="membersEntityList[0].userId" items="${users}" cssClass="sarvar"/></td>
                                <%--<td> <form:input type='text' name = "membersEntityList[0].organizationName" path="membersEntityList[0].organizationName"/> </td>--%>
                                <td> <input type='date' name = "membersEntityList[0].dateFrom" /> </td>
                                <td> <input type='date' name = "membersEntityList[0].dateTo" /> </td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseTransportation" min="0" path="membersEntityList[0].expenseTransportation"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].dailyAllowance" min="0" path="membersEntityList[0].dailyAllowance"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseAccommodation" min="0" path="membersEntityList[0].expenseAccommodation"/></td>
                                <td><form:select path="membersEntityList[0].accomodationCurrency" items="${currency}" cssClass="sarvar"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseOther" min="0" path="membersEntityList[0].expenseOther"/></td>
                                <td id="deleteRowTd"><a class="deleteRow"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                    <input type="button" class="btn btn-normal" value="+ Add Row" id="addrow"/>
                </div>
                <div class="form-group" style="margin-left: 0px; margin-right: 0px">
                    <label class="tableLabel"><span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="width: 2%; font-style: normal"></span>
                        Detail scheadule and To-do list:
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: black"
                           id="toDoDynamicHead">
                        <thead>
                        <tr>
                            <th width="25%">Date</th>
                            <th width="75%">Description</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="toDoDynamicBody" />
                        <tr>
                            <td><input  class="dateInput" type='date' name = "toDoEntityList[0].date" min="0" /></td>
                            <td><input  class="descInput" type='text' name = "toDoEntityList[0].description" min="0" /></td>
                            <td><a class="deleteRow"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="button" class="btn btn-normal" value=" + Add Row" id="addrowToDo"/>
                </div>

            </div>
            <div class="form-footer" style="padding-bottom: 2%;">
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon">Approvals:</span>
                    <div class="tab-content" id="approvals">
                        <input type="text" id="demo-input-local"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon">Executive:</span>
                    <div class="tab-content" id="executives">
                        <input type="text" id="demo-input-local2"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon">Reference:</span>
                    <div class="tab-content" id="references">
                        <input type="text" id="demo-input-local3"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon">Attachment:</span>
                    <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="date-addon">Date(Start/End):</span>
                    <input type="date" class="form-control" style="width:50%" name="start" />
                    <input type="date" class="form-control" style="width:50%" name="end" />
                </div>
            </div>
        </div>

            <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
                <input id="tv2" type="submit" name="Save" value="Save" class="btn btn-warning"/>
                <input id="tv3" type="submit" name="Submit" value="Submit" class="btn btn-success"/>
                <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger" />
            </div>
        </form:form>
    </div>

</div>

<script type="text/javascript">
    $("#tv3").click(function (){
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
            },
            error : function(e) {
                alert('Error: ' + e);
            }
        });
    });
</script>


<script type="text/javascript">

    /* Send json data for approvals list*/
    $(document).ready(function () {

        $("#demo-input-local").tokenInput(${jsonData});
        $("#demo-input-local2").tokenInput(${jsonData});
        $("#demo-input-local3").tokenInput(${jsonData});

        var targetB = $('#membersDynamicBody');

        var counter = 1;

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
