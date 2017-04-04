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
<link rel="stylesheet" href="/resources/core/css/easy-autocomplete.min.css"/>

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

    div .input-group {
        width: 98%;
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
        margin-left: 0px;
    !important;
        margin-right: 0px;
    !important;
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

    #addrow, #addrowToDo {
        width: 100%;
        color: #000;
    }

    .datepicker thead {
        background-color: #FFFFFF;
    }


</style>



    <div class="mainBodyBlock">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="headerText"><span
                class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Business trip {EDIT}</h2>


        <form:form modelAttribute="businessTripVM" cssClass="form-horizontal" method="post" id="myform"
                   enctype="multipart/form-data">
            <div class="w3-container b3form">
                <div class="form-header" style="padding-top: 1%">
                    <div class="input-group">
                        <span class="input-group-addon" id="subject-addon"><span class="reqfield"></span>Subject:</span>
                        <form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon"
                                    path="subject"/>
                        <span class="glyphicon warningIcon " aria-hidden="true"></span>
                    </div>
                    <div class="input-group" style="margin-top: 1%">
                        <span class="input-group-addon" id="saerchtype-addon"><span class="reqfield"></span>Type of business trip:</span>
                        <form:select class="form-control" aria-describedby="saerchtype-addon" path="tripType"
                                     items="${tripTypeList}">
                        </form:select>

                        <div class="input-group">
                            <label class="radio-inline">
                                <form:radiobutton value="true" path="domestic"/> Domestic
                            </label>
                            <label class="radio-inline">
                                <form:radiobutton value="false" path="domestic"/> Overseas
                            </label>
                        </div>

                    </div>
                    <div class="input-group" style="margin-top: 1%">
                        <span class="input-group-addon" id="destination-addon"><span class="reqfield"></span>Destination:</span>
                        <form:input type="text" class="form-control" placeholder="" aria-describedby="destination-addon"
                                    path="destination"/>
                    </div>
                    <div class="input-group" style="margin-top: 1%">
                        <span class="input-group-addon" id="purpose-addon">Purpose of Business trip:</span>
                        <form:textarea class="form-control" rows="3" id="comment" aria-describedby="purpose-addon"
                                       style="width: 100%; border-color:#999999" path="purpose"></form:textarea>
                    </div>
                    <div class="form-group" style="margin-right: 0px; margin-left: 0px">
                        <label class="tableLabel"><span class="glyphicon glyphicon-user" aria-hidden="true"
                                                        style="width: 2%; font-style: normal"></span>
                            <span class="reqfield"></span>List of Business Trip members:
                        </label>
                        <div id="myTablecha" style="overflow: auto; width: 100%">
                            <table class="table order-list table-bordered"
                                   style="background-color: #2b669a; color: inherit" id="myTable">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                        <%--<th>Department</th>--%>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Transportation</th>
                                    <th>Daily Allowance</th>
                                    <th>Accommodation</th>
                                    <th>Accommodation Currency</th>
                                    <th>Other</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody id="membersDynamicBody" style="color: #0f0f0f">
                                <c:forEach items="${businessTripVM.membersEntityList}" varStatus="i">
                                    <tr style="color: black">
                                        <td><form:select path="membersEntityList[${i.index}].userId" items="${users}"
                                                         cssClass="sarvar" id="userId"/></td>
                                            <%--<td> <form:input type='text' name = "membersEntityList[0].organizationName" path="membersEntityList[0].organizationName"/> </td>--%>
                                        <td><form:input type='text' path="membersEntityList[${i.index}].dateFrom"
                                                        name="membersEntityList[${i.index}].dateFrom"
                                                        cssClass="memfrom"/></td>
                                        <td><form:input type='text' path="membersEntityList[${i.index}].dateTo"
                                                        name="membersEntityList[${i.index}].dateTo"
                                                        cssClass="memto"/></td>
                                        <td><form:input type='number' min="0"
                                                        path="membersEntityList[${i.index}].expenseTransportation"
                                                        name="membersEntityList[${i.index}].expenseTransportation"/></td>
                                        <td><form:input type='number' min="0"
                                                        path="membersEntityList[${i.index}].dailyAllowance"
                                                        name="membersEntityList[${i.index}].dailyAllowance"/></td>
                                        <td><form:input type='number' min="0"
                                                        path="membersEntityList[${i.index}].expenseAccommodation"
                                                        name="membersEntityList[${i.index}].expenseAccommodation"/></td>
                                        <td><form:select path="membersEntityList[${i.index}].accomodationCurrency"
                                                         name="membersEntityList[${i.index}].accomodationCurrency"
                                                         items="${currency}" cssClass="sarvar"/></td>
                                        <td><form:input type='number' min="0"
                                                        path="membersEntityList[${i.index}].expenseOther"
                                                        name="membersEntityList[${i.index}].expenseOther"/></td>
                                        <td><input type="button" class="ibtnDel btn btn-md btn-danger" value="Delete"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <input style="color: #000" type="button" class="btn btn-normal" value="+ Add member"
                               id="addRowEdit"/>
                    </div>
                    <div class="form-group" style="margin-right: 0px; margin-left: 0px">
                        <label class="tableLabel"></span><span class="glyphicon glyphicon-list-alt" aria-hidden="true"
                                                               style="width: 2%; font-style: normal"></span>
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
                            <tbody id="toDoDynamicBody"/>
                            <c:forEach items="${businessTripVM.toDoEntityList}" varStatus="i">
                                <tr>
                                    <td><form:input type='text' min="0" path="toDoEntityList[${i.index}].date" cssClass="dateinput"/></td>
                                    <td><form:input type='text' min="0"
                                                    path="toDoEntityList[${i.index}].description"/></td>
                                    <td><input type="button" class="ibtnDel btn btn-md btn-red" value="Delete"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input type="button" class="btn btn-normal" value="+ Add activity" id="addrowToDo"/>
                    </div>

                </div>
                <div class="form-footer" style="padding-bottom: 2%">

                        <%-- Approvals, references, executors shouldn't be edited --%>
                    <div class="input-group" style="margin-top: 1%">
                        <span class="input-group-addon" id="attach-addon">Attached:</span>
                        <div id="attachmentDiv">
                            <c:forEach items="${businessTripVM.attachments}" var="attachment">
                                <p style="color: #000">
                                    <a style="color: #000"
                                       href="/Workflow/MyForms/files/${attachment.id}">${attachment.fileName}</a>
                                    <a style="color: #000" href="/Workflow/EditForm/files/delete/${attachment.id}"><span
                                            style="color: #ff0000" class="glyphicon glyphicon-remove-sign"
                                            aria-hidden="false"></span></a>
                                </p>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="input-group" style="margin-top: 2%">
                        <span class="input-group-addon" id="attachment-addon">New attachment:</span>
                        <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                    </div>
                    <div class="input-group" style="margin-top: 2%">
                        <span class="input-group-addon" id="date-addon"><span
                                class="reqfield"></span>Date(Start/End):</span>
                        <form:input id="dateStart" type="text" class="form-control" style="width:36%" name="start" path="start"/>
                        <form:input id="dateEnd" type="text" class="form-control" style="width:36%" name="end" path="end"/>
                        <span class="glyphicon warningIcon" aria-hidden="true"></span>
                    </div>
                </div>
            </div>

            <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
                <input id="tv" type="submit" name="submitBusinessTrip" value="Submit" class="btn btn-green"/>
                <input type="button" onclick="history.back()" value="Cancel" class="btn btn-red"/>
            </div>

        </form:form>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> Warning</h4>
                        </div>
                        <div class="modal-body">
                            <span id="message"></span>
                        </div>
                    </div>
                </div>
            </div>


    </div>



<script type="text/javascript">


    /* Send input from approval list to controller by AJAX */
    $(document).ready(function () {

        $('#dateStart').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
        $('#dateEnd').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});

        $('.memfrom').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
        $('.memto').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});




        $("#tv").click(function () {

            var msg = "";
            var flag = true;

            /*VALIDATION*/
            if ($("#subject").val() == "") {
                flag = false;
                msg += "⛔ Subject cannot be empty" + "<br/>";
                $('#subject').css("border", "2px solid red");
                $('#subject').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#subject').css("border", "1px solid #999999");
                $('#subject').next('span').removeClass('glyphicon-info-sign')

                if ($("#subject").val().length > 50){
                    msg += "⛔ Subject cannot be more than 50 characters" + "<br/>";
                    flag = false
                    $('#subject').next('span').addClass('glyphicon-info-sign')
                    $(this).css("border", "2px solid red");
                }else {
                    $(this).css("border", "1px solid #999999");
                    $('#subject').next('span').removeClass('glyphicon-info-sign')
                }
            }

            if ($("#comment").val().length > 600) {
                flag = false;
                msg += "⛔ Purpose of business trip can not be more than 600 characters" + "<br/>";
                $('#comment').css("border", "2px solid red");
                $('#comment').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#comment').css("border", "1px solid #999999");
                $('#comment').next('span').removeClass('glyphicon-info-sign')
            }


            if ($("#tripType option:selected").text() == "") {
                $('#tripType').css("border", "2px solid red");
                $('#tripType').next('span').addClass('glyphicon-info-sign')
                flag = false;
                msg += "⛔ Trip type cannot be empty" + "<br/>";
            } else {
                $('#tripType').css("border", "1px solid #999999");
                $('#tripType').next('span').removeClass('glyphicon-info-sign')
            }

            /*Business trip  members*/
            if ($("select.sarvar option:selected").text() == "") {
                $('#myTablecha').css("border", "2px solid red");
                /*$('#memberLabel').next('span').addClass('glyphicon-info-sign')*/
                flag = false;
                msg += "⛔ At least one member should be selected" + "<br/>";
            } else {
                $('#myTablecha').css("border", "1px solid #999999");
                /*$('#memberLabel').next('span').removeClass('glyphicon-info-sign')*/
            }


            /*Destination*/
            if ($("#destination").val().trim() == "") {
                flag = false;
                msg += "⛔ Destination cannot be empty" + "<br/>";
                $('#destination').css("border", "2px solid red");
                $('#destination').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#destination').css("border", "1px solid #999999");
                $('#destination').next('span').removeClass('glyphicon-info-sign')

                if ($("#destination").val().length > 100){
                    msg += "⛔ Destination cannot be more than 100 characters" + "<br/>";
                    flag = false
                    $('#destination').next('span').addClass('glyphicon-info-sign')
                    $(this).css("border", "2px solid red");
                }else {
                    $(this).css("border", "1px solid #999999");
                    $('#destination').next('span').removeClass('glyphicon-info-sign')
                }
            }

            /* file size limitation */
            if ($("#file").val().trim() != "") {
                var size = 0;
                input = document.getElementById('file');
                for (var i = 0; i < input.files.length; i++) {
                    size += input.files[0].size;
                }
                if (size > 104857600) {
                    flag = false;
                    msg += "⛔ Attached files cannot be more than 100MB" + "<br/>";
                    $('#file').css("border", "2px solid red");
                    $('#file').next('span').addClass('glyphicon-info-sign');
                } else {
                    $('#file').css("border", "1px solid #999999");
                    $('#file').next('span').removeClass('glyphicon-info-sign')
                }
            }

            /* Start date cannot be more than end date*/

            var dStart = new Date($("#dateStart").val());
            var dEnd = new Date($("#dateEnd").val());

            if (dStart > dEnd) {
                flag = false;
                msg += "⛔ Start date cannot be later than end date" + "<br/>";
                $('#dateStart').css("border", "2px solid red");
                $('#dateEnd').css("border", "2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#dateStart').css("border", "1px solid #999999");
                $('#dateEnd').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
            }


            /* Date start cannot be empty */
            if ($("#dateStart").val().trim() == "") {
                flag = false;
                msg += "⛔ Start date cannot be empty" + "<br/>";
                $("#dateStart").css("border", "2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#dateStart').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
            }

            /* Date end cannot be empty */
            if ($("#dateEnd").val().trim() == "") {
                flag = false;
                msg += "⛔ End date cannot be empty" + "<br/>";
                $('#dateEnd').css("border", "2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#dateEnd').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
            }


            /*Business trip  members*/

            /*Name validation*/

            $("select.sarvar").each(function () {

                var curVal = $(this).find('option:selected').text();

                if (curVal == "") {
                    flag = false;
                    msg += "⛔ Names of Business trip members should not be empty" + "<br/>";
                    $(this).css('border-color', 'red');

                } else {
                    $(this).css('border-color', '#999999');
                }

            });


            /*Accommodation currency*/
            $("select.currencyInput").each(function () {

                var curVal = $(this).find("option:selected").text();

                if (curVal == "") {
                    flag = false;
                    msg += "⛔ Accommodation currency field should not be empty" + "<br/>";
                    $(this).css('border-color', 'red');
                } else {
                    $(this).css('border-color', '#999999');
                }
            });

            /*Date from validation*/
            $("input.memfrom").each(function () {
                var memfromvar = new Date($(this).val());
                if (memfromvar != "Invalid Date" && memfromvar != "") {
                    $(this).css('border-color', '#999999');
                    if (memfromvar < dStart) {
                        flag = false;
                        msg += "⛔ Business trip members <strong>From</strong> date can not be earlier than <strong>Start</strong> date of business trip" + "<br/>";
                        $(this).css('border-color', 'red');
                    } else if (memfromvar > dEnd) {
                        flag = false;
                        msg += "⛔ Business trip members <strong>From</strong> date can not be later than <strong>End</strong> date of business trip" + "<br/>";
                        $(this).css('border-color', 'red');
                    }

                } else {
                    flag = false;
                    msg += "⛔ Business trip members <strong>From</strong> field should not be empty" + "<br/>";
                    $(this).css('border-color', 'red');
                }


            });

            /*Date to validation*/
            $("input.memto").each(function () {
                var memtovar = new Date($(this).val());
                if (memtovar != "Invalid Date" && memtovar != "") {
                    $(this).css('border-color', '#999999');
                    if (memtovar < dStart) {
                        flag = false;
                        msg += "⛔ Business trip members <strong>To</strong> date can not be earlier than <strong>Start</strong> date of business trip" + "<br/>";
                        $(this).css('border-color', 'red');
                    } else if (memtovar > dEnd) {
                        flag = false;
                        msg += "⛔ Business trip members <strong>To</strong> date can not be later than <strong>End</strong> date of business trip" + "<br/>";
                        $(this).css('border-color', 'red');
                    }
                } else {
                    flag = false;
                    msg += "⛔ Business trip members <strong>To</strong> field should not be empty" + "<br/>";
                    $(this).css('border-color', 'red');
                }
            });



            if (!flag) {
                $('#message').html(msg);
                $('#myModal').modal('show');
            }

            return flag;



        });
    });

    /* Send json data for approvals list*/



    var doc = document.getElementById("myTablecha");
    var counter = 4;

    $("#addRowEdit").on("click", function () {
        var ab = document.getElementById('membersDynamicBody');
        var tr = document.createElement('tr');

        var td = document.createElement('td');
        var select = document.createElement('select');
        select.name = "membersEntityList[" + counter + "].userId";
        /*elect.className = "form-control text-box single-line";*/
        select.className = 'sarvar';
        var opt;

        $.ajax({
            type: "GET",
            url: "/Workflow/Users",
            success: function (data) {
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
        date.setAttribute('type', 'text');
        date.name = "membersEntityList[" + counter + "].dateFrom";
        date.className = 'memfrom';
        td.appendChild(date);
        tr.appendChild(td);

        /*Input field for to date*/
        var td = document.createElement('td');
        var date = document.createElement('input');
        date.setAttribute('type', 'text');
        date.name = "membersEntityList[" + counter + "].dateTo";
        date.className = 'memto'
        td.appendChild(date);
        tr.appendChild(td);


        /*Input field for expence transportation*/
        var td = document.createElement('td');
        var num = document.createElement('input');
        num.setAttribute('type', 'number');
        num.setAttribute('min', 0);
        num.name = "membersEntityList[" + counter + "].expenseTransportation";
        td.appendChild(num);
        tr.appendChild(td);

        /*Input field for dailyAllowance*/
        var td = document.createElement('td');
        var num = document.createElement('input');
        num.setAttribute('type', 'number');
        num.setAttribute('min', 0);
        num.name = "membersEntityList[" + counter + "].dailyAllowance";
        td.appendChild(num);
        tr.appendChild(td);

        /*Input field for expenseAccommodation*/
        var td = document.createElement('td');
        var num = document.createElement('input');
        num.setAttribute('type', 'number');
        num.setAttribute('min', 0);
        num.name = "membersEntityList[" + counter + "].expenseAccommodation";
        td.appendChild(num);
        tr.appendChild(td);

        /*Input field for select currency*/
        var td = document.createElement('td');
        var select2 = document.createElement('select');
        select2.name = "membersEntityList[" + counter + "].accomodationCurrency";
        /*elect.className = "form-control text-box single-line";*/
        select2.className = 'sarvar';

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
        num.name = "membersEntityList[" + counter + "].expenseOther";
        td.appendChild(num);
        tr.appendChild(td);

        /*Delete button*/
        var td = document.createElement('td');
        var input = document.createElement('input');

        input.setAttribute('type', 'button');
        input.className = "ibtnDel btn btn-md btn-danger";
        input.value = "Delete";
        td.appendChild(input);
        tr.appendChild(td);
        ab.appendChild(tr);
        counter++;

        $('.memfrom').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
        $('.memto').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});

        $("table.order-list.table-bordered").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();

        });


    });

    $(document).ready(function () {
        var counter = 1;
        $('.dateinput').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});

        $("#addrowToDo").on("click", function () {
            var ab = document.getElementById('toDoDynamicBody');
            var tr = document.createElement('tr');

            /*Input field for date*/
            var td = document.createElement('td');
            var date = document.createElement('input');
            date.setAttribute('type', 'text');
            date.setAttribute('class', 'dateInput')
            date.name = "toDoEntityList[" + counter + "].date";
            td.appendChild(date);
            tr.appendChild(td);

            /*Input field for description*/
            var td = document.createElement('td');
            var input = document.createElement('input');
            input.name = "toDoEntityList[" + counter + "].description";
            td.appendChild(input);
            tr.appendChild(td);

            /*Delete button*/
            var td = document.createElement('td');
            var input = document.createElement('input');

            input.setAttribute('type', 'button');
            input.className = "ibtnDel btn btn-md btn-danger";
            input.value = "Delete";
            td.appendChild(input);
            tr.appendChild(td);
            ab.appendChild(tr);
            counter++;

            $('.dateinput').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
        });

        $("table.table-bordered").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();

        });


    });

</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
