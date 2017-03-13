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

    .sarvar, .currencyInput {
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
        <h2 class="page-header" style="border: none; margin-left: -3%; padding-top: 6%;"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Business trip</h2>


        <form:form modelAttribute="businessTripVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form">
            <div class="form-header" style="padding-top: 1%">
                <div class="input-group" style="">
                    <span class="input-group-addon" id="subject-addon"><span class="reqfield"></span>Subject:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon" path="subject" />
                    <span class="glyphicon warningIcon " aria-hidden="true"></span>
                </div>
                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"><span class="reqfield"></span>Type of business trip:</span>
                    <form:select class="form-control" aria-describedby="saerchtype-addon" path="tripType" items="${triptypeList}">
                    </form:select>
                    <span class="glyphicon warningIcon " aria-hidden="true"></span>

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
                    <span class="input-group-addon" id="destination-addon"><span class="reqfield"></span>Destination:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="destination-addon" path="destination" />
                    <span class="glyphicon warningIcon " aria-hidden="true"></span>
                </div>
                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon">Purpose of Business trip:</span>
                    <form:textarea class="form-control" rows="3" id="comment" aria-describedby="purpose-addon"
                              style="width: 100%; border-color:#999999" path="purpose"></form:textarea>
                </div>
                <div class="form-group" style="margin-left: 0px; margin-right: 0px">
                    <label id="memberLabel" class="tableLabel"><span class="glyphicon glyphicon-user" aria-hidden="true" style="width: 2%; font-style: normal"></span>
                        <span class="reqfield"></span>Business Trip members:
                    </label>
                    <span class="glyphicon warningIcon" aria-hidden="true"></span>
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
                                <<td><form:select path="membersEntityList[0].userId" items="${users}" cssClass="sarvar"/></td>
                                <%--<td> <form:input type='text' name = "membersEntityList[0].organizationName" path="membersEntityList[0].organizationName"/> </td>--%>
                                <td> <input type='date' name = "membersEntityList[0].dateFrom"  class="memfrom"/> </td>
                                <td> <input type='date' name = "membersEntityList[0].dateTo" class="memto"/> </td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseTransportation" min="0" path="membersEntityList[0].expenseTransportation"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].dailyAllowance" min="0" path="membersEntityList[0].dailyAllowance"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseAccommodation" min="0" path="membersEntityList[0].expenseAccommodation"/></td>
                                <td><form:select path="membersEntityList[0].accomodationCurrency" items="${currency}" cssClass="currencyInput"/></td>
                                <td><form:input type='number' name = "membersEntityList[0].expenseOther" min="0" path="membersEntityList[0].expenseOther"/></td>
                                <td><input type="button" class="ibtnDel btn btn-md btn-danger" name="deletebutton0" value="Delete" style="display: none"/></td>
                                <td id="deleteRowTd"><a class="deleteRow"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                    <input type="button" class="btn btn-normal" value="+ Add member" id="addrow"/>
                </div>
                <div class="form-group" style="margin-left: 0px; margin-right: 0px">
                    <label id="toDoLabel" class="tableLabel"><span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="width: 2%; font-style: normal"></span>
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
                    <input type="button" class="btn btn-normal" value=" + Add activity" id="addrowToDo"/>
                </div>

            </div>
            <div class="form-footer" style="padding-bottom: 2%;">
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"><span class="reqfield"></span>Approvals:</span>
                    <div class="tab-content" id="approvals">
                        <span style="margin-left: 74.2%" id="approvalSpan" class="glyphicon warningIcon " aria-hidden="true"></span>
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
                    <span class="input-group-addon" id="date-addon"><span class="reqfield"></span>Date(Start/End):</span>
                    <input id="dateStart" type="date" class="form-control" style="width:50%" name="start" />
                    <input id="dateEnd" type="date" class="form-control" style="width:50%" name="end" />
                    <span class="glyphicon warningIcon" aria-hidden="true"></span>
                </div>
            </div>
        </div>

            <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
                <input id="tv2" type="submit" name="Save" value="Save" class="btn btn-warning"/>
                <input id="tv3" type="submit" name="Submit" value="Submit" class="btn btn-success"/>
                <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger" />
            </div>
        </form:form>
            <%--MODAL--%>
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

</div>

<script type="text/javascript">

    var msg = "";
    var flag = true;

    $("#tv3").click(function (){
        flag = true;
        msg = "";

        /*VALIDATION*/


        if ($("#subject").val().trim() == "") {
            flag = false;
            msg += "⛔ Subject cannot be empty" + "<br/>";
            $('#subject').css("border","2px solid red");
            $('#subject').next('span').addClass('glyphicon-info-sign');
        }else {
            $('#subject').css("border", "1px solid #999999");
            $('#subject').next('span').removeClass('glyphicon-info-sign')
        }

        /* Subject cannot be empty*/
        if($("#tripType option:selected").text() == ""){
            $('#tripType').css("border","2px solid red");
            $('#tripType').next('span').addClass('glyphicon-info-sign')
            flag = false;
            msg += "⛔ Trip type cannot be empty" + "<br/>";
        }else {
            $('#tripType').css("border", "1px solid #999999");
            $('#tripType').next('span').removeClass('glyphicon-info-sign')
        }

        /*Business trip  members*/
        

        var memflag = true;
        $("select.sarvar option:selected").each(function () {
            if ($(this).text().trim() == ""){
                memflag = false;
            }
        });

        $("select.currencyInput option:selected").each(function () {
            if ($(this).text().trim() == "") {
                memflag = false;
            }
        });



        $("input.memfrom").each(function () {
            var memfromvar = new Date($(this).val());
            if (memfromvar == "Invalid Date") {
                memflag = false;
            }

        });

        $("input.memto").each(function () {
            var memtovar = new Date($(this).val());
            if (memtovar == "Invalid Date") {
                memflag = false;
            }

        });

        if(memflag == false){
            $('#myTablecha').css("border","2px solid red");
            /*$('#memberLabel').next('span').addClass('glyphicon-info-sign')*/
            flag = false;
            msg += "⛔ All fields of members table should be filled" + "<br/>";

        }else {
            $('#myTablecha').css("border", "1px solid #999999");
            /*$('#memberLabel').next('span').removeClass('glyphicon-info-sign')*/
        }


        /*Destination*/
        if ($("#destination").val().trim() == "") {
            flag = false;
            msg += "⛔ Destination cannot be empty" + "<br/>";
            $('#destination').css("border","2px solid red");
            $('#destination').next('span').addClass('glyphicon-info-sign');
        }else {
            $('#destination').css("border", "1px solid #999999");
            $('#destination').next('span').removeClass('glyphicon-info-sign')
        }



        /* Start date cannot be more than end date*/

        var dStart = new Date($("#dateStart").val());
        var dEnd = new Date($("#dateEnd").val());

        if(dStart > dEnd){
            flag = false;
            msg += "⛔ Start date cannot be later than end date" + "<br/>";
            $('#dateStart').css("border","2px solid red");
            $('#dateEnd').css("border","2px solid red");
            $('#dateEnd').next('span').addClass('glyphicon-info-sign');
        } else {
            $('#dateStart').css("border", "1px solid #999999");
            $('#dateEnd').css("border", "1px solid #999999");
            $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
        }


        /* Date start cannot be empty */
        if($("#dateStart").val().trim() == "") {
            flag = false;
            msg += "⛔ Start date cannot be empty" + "<br/>";
            $("#dateStart").css("border","2px solid red");
            $('#dateEnd').next('span').addClass('glyphicon-info-sign');
        }else {
            $('#dateStart').css("border", "1px solid #999999");
            $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
        }

        /* Date end cannot be empty */
        if($("#dateEnd").val().trim() == "") {
            flag = false;
            msg += "⛔ End date cannot be empty" + "<br/>";
            $('#dateEnd').css("border","2px solid red");
            $('#dateEnd').next('span').addClass('glyphicon-info-sign');
        }else {
            $('#dateEnd').css("border", "1px solid #999999");
            $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
        }

        /*VALIDATION*/


        var a=[];
        var b=[];
        var c=[];

        a = $("#approvals").children().siblings("input[type=text]").val();
        if(a.length == 0)
        {
            msg += "⛔ At least one approval should be selected" + "<br/>";
            flag = false;
            $('#approvals').css("border","2px solid red");
            $('#approvalSpan').addClass('glyphicon-info-sign');
        } else {
            $('#approvals').css("border", "1px solid #999999");
            $('#approvalSpan').removeClass('glyphicon-info-sign');
        }

        if (!flag){
            $('#message').html(msg);
            $('#myModal').modal('show');
        }



        a = $("#approvals").children().siblings("input[type=text]").val();
        b = $("#references").children().siblings("input[type=text]").val();
        c = $("#executives").children().siblings("input[type=text]").val();

        validateFile();

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
        return flag;
    });

    var isTrue = true;

    function validateFile() {
        /* file size limitation */
        if($("#file").val().trim() != "") {
            var size = 0;
            input = document.getElementById('file');
            for (var i = 0; i < input.files.length; i++) {
                size += input.files[0].size;
            }
            if (size > 104857600) {
                flag = false;
                msg += "⛔ Attached files cannot be more than 100MB" + "<br/>";
                $('#file').css("border","2px solid red");
                $('#file').next('span').addClass('glyphicon-info-sign');
                $("#file").val("");
                isTrue = false;
            }else {
                $('#file').css("border", "1px solid #999999");
                $('#file').next('span').removeClass('glyphicon-info-sign')
                isTrue = true;
            }
        }

        setDefaultValueToEmptyTodoDate();

    }

    function setDefaultValueToEmptyTodoDate() {
        var year = "1111";
        var month = "11";
        var dayOfMonth = "11";
        var todayString = year + "-" + month + "-" + dayOfMonth;

        $("input.dateInput").each(function () {
            var ins = new Date($(this).val());

            if(ins=="Invalid Date"){
                $(this).val(todayString);
            }
        });
    }


</script>


<script type="text/javascript">

    $("#tv2").click(function (){

        var draftApprovals = true;


        var msg = "";

        a = $("#approvals").children().siblings("input[type=text]").val();
        if(a.length == 0)
        {
            msg += "⛔ At least one approval should be selected" + "<br/>";
            draftApprovals = false;
            $('#approvals').css("border","2px solid red");
            $('#approvalSpan').addClass('glyphicon-info-sign');

            $('#dateEnd').css("border", "1px solid #999999");
            $('#dateEnd').next('span').removeClass('glyphicon-info-sign');

            $('#dateStart').css("border", "1px solid #999999");
            $('#dateEnd').next('span').removeClass('glyphicon-info-sign');

            $('#subject').css("border", "1px solid #999999");
            $('#subject').next('span').removeClass('glyphicon-info-sign')
        } else {
            $('#approvals').css("border", "1px solid #999999");
            $('#approvalSpan').removeClass('glyphicon-info-sign');
        }

        if (!draftApprovals){
            $('#message').html(msg);
            $('#myModal').modal('show');

            return draftApprovals;
        }





        var a=[];
        var b=[];
        var c=[];

        var year = "1111";
        var month = "11";
        var dayOfMonth = "11";
        var todayString = year + "-" + month + "-" + dayOfMonth;

        validateFile();

        if(isTrue){
            $('input[type="date"]').each(function () {
                if($(this).val()==""){
                    $(this).val(todayString);
                }
            });
        }





        isTrue = draftApprovals;

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

        return isTrue;
    });



    /* Send json data for approvals list*/
    $(document).ready(function () {

        $("#demo-input-local").tokenInput(${jsonData});
        $("#demo-input-local2").tokenInput(${jsonData});
        $("#demo-input-local3").tokenInput(${jsonData});

        var targetB = $('#membersDynamicBody');

        var counter = 1;

        var i = 1;

        $("#addrow").on("click", function () {
            $("#membersDynamicBody tr:first").clone().find("input, select").each(function() {
                var inputName = $(this).attr("name").toString();
                 $(this).val('').attr('name', inputName.replace('0', i))
                 $(this).val('').attr('path', inputName.replace('0', i))
                if($(this).attr('type')=='button'){
                     $(this).attr('value', 'Delete').show();
                }
             }).end().appendTo("#membersDynamicBody");
            i++;
        });
       /* $("#addrow").on("click", function () {
            var ab = document.getElementById('membersDynamicBody');
            var tr = document.createElement('tr');

            var td = document.createElement('td');
            var select = document.createElement('select');
            select.name = "membersEntityList["+counter+"].userId";
            /!*elect.className = "form-control text-box single-line";*!/
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

            /!*Input field for from date*!/
            var td = document.createElement('td');
            var date = document.createElement('input');
            date.setAttribute('type', 'date');
            date.name = "membersEntityList["+counter+"].dateFrom";
            date.className = "memFrom";
            td.appendChild(date);
            tr.appendChild(td);

            /!*Input field for to date*!/
            var td = document.createElement('td');
            var date = document.createElement('input');
            date.setAttribute('type', 'date');
            date.name = "membersEntityList["+counter+"].dateTo";
            date.className = "memTo";
            td.appendChild(date);
            tr.appendChild(td);

            /!*Input field for expence transportation*!/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseTransportation";
            td.appendChild(num);
            tr.appendChild(td);

            /!*Input field for dailyAllowance*!/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].dailyAllowance";
            td.appendChild(num);
            tr.appendChild(td);

            /!*Input field for expenseAccommodation*!/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseAccommodation";

            td.appendChild(num);
            tr.appendChild(td);

            /!*Input field for select currency*!/
            var td = document.createElement('td');
            var select2 = document.createElement('select');
            select2.name = "membersEntityList["+counter+"].accomodationCurrency";
            /!*elect.className = "form-control text-box single-line";*!/
            select2.className = 'currencyInput';

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

            /!*Input field for expenseOther*!/
            var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('min', 0);
            num.name = "membersEntityList["+counter+"].expenseOther";
            td.appendChild(num);
            tr.appendChild(td);

            /!*Input field for expenceTotal*!/
            /!*var td = document.createElement('td');
            var num = document.createElement('input');
            num.setAttribute('type', 'number');
            num.setAttribute('value', 100);
            num.setAttribute('disabled', true);
            td.appendChild(num);
            tr.appendChild(td);*!/


            /!*Delete button*!/
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
*/


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
            date.setAttribute('class', 'dateInput')
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
