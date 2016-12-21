<%@ page import="com.lgcns.erp.hr.enums.WorkloadType" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 08.11.2016
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Appoint"/>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/modernizr.min.js" var="modernizr"/>
<spring:url value="/resources/core/js/moment.min.js" var="momentJs"/>
<spring:url value="/resources/core/js/fullcalendar.js" var="fullcalencdarJs"/>
<spring:url value="/resources/core/css/fullcalendar.css" var="fullcalendarCss"/>
<script src="${modernizr}"></script>
<script src='${momentJs}'></script>
<script src="${fullcalencdarJs}"></script>
<link rel="stylesheet" href="${fullcalendarCss}"/>
<style>
    #myModalCalendar, #myModalLabel, #calendarBody, #calendar {
        color: #1b6d85 !important;
    }

    .modal-title, .modal-body, .modal-footer {
        color: #1a1a1a !important;
    }

    .fc th, .fc td, .fc-toolbar h2 {
        color: #1a1a1a !important;
    }
</style>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <form hidden="hidden" method="post" action="/Appoint/Create">
            <input type="number" id="tbxProject" name="ProjectId"/>
            <input type="number" id="tbxEmp" name="EmpId"/>
            <input type="number" id="tbxRole" name="RoleId"/>
            <input type="date" id="tbxFrom" name="DateFrom"/>
            <input type="date" id="tbxTo" name="DateTo"/>
            <input style="display: none" id="btnAddEmp" type="submit"/>
        </form>
        <div class="col-md-offset-1 col-sm-10">
            <div class=" col-lg-offset-2 col-lg-10">
                <h1 class="page-header">Appoint project members</h1>
                <table class="table" id="Projects">
                    <tr>
                        <th>Project name</th>
                        <th>Start date</th>
                        <th>Finish date</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${viewModel}" var="model">
                        <tr data-prop="${model.projectId}" onclick="viewMembers(${model.projectId})">
                            <td>${model.projectName}</td>
                            <td id="Start">${model.startDate}</td>
                            <td id="End">${model.endDate}</td>
                            <td>${model.status}</td>
                        </tr>
                        <div class="table table-hover" hidden="hidden" id="project_<c:out value="${model.projectId}"/>">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12">
                                    <div class="row">
                                        <div class="col-xs-3 col-sm-3">
                                            Name
                                        </div>
                                        <div class="col-xs-2 col-sm-2">
                                            Role
                                        </div>
                                        <div class="col-xs-2 col-sm-2">
                                            From
                                        </div>
                                        <div class="col-xs-2 col-sm-2">
                                            To
                                        </div>
                                        <div class="col-xs-3 col-sm-3">
                                            &nbsp;
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--<table hidden="hidden" class="table" id="project_<c:out value="${model.projectId}"/>">
                            <th>Name</th>
                            <th>Role</th>
                            <th>From</th>
                            <th>To</th>

                            <th></th>
                            <c:forEach items="${model.members}" var="member">
                                <tr>
                                    <td>${member.userName}</td>
                                    <td>${member.roleName}</td>
                                    <td>${member.participatingFrom}</td>
                                    <td>${member.participatingTo}</td>
                                    <td><a class="btn btn-md btn-default .btn-md"href="/Appoint/Edit/<c:out value="${member.appointmentId}"/>">Edit</a>
                                        <a class="btn btn-md btn-default .btn-md"href="/Appoint/Delete/<c:out value="${member.appointmentId}"/>">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>--%>
                    </c:forEach>
                </table>

                <div id="membersTable">
                    <h2>Project members</h2>
                    <p>
                        <button id="addMember" onclick="displayAddForm()" class="btn btn-sm btn-success .btn-sx">Add new
                            member
                        </button>
                    </p>
                    <table class="table" id="myTable">
                        <tr>

                            <th>
                                Name
                            </th>
                            <th>
                                Role
                            </th>
                            <th>
                                From
                            </th>
                            <th>
                                To
                            </th>

                            <th></th>
                        </tr>
                        <tbody id="tbody"></tbody>

                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Oops, wrong input</h4>
            </div>
            <div class="modal-body" id="msg">
                <span id="message">
                <c:forEach items="${errors}" var="error" varStatus="i">
                    <c:out value="${error.defaultMessage.toString()}"/> <br/>
                </c:forEach>
                </span>
            </div>
        </div>
    </div>
</div>


<style>
    .highlight {
        background-color: lightsteelblue;
    }
</style>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
<div class="modal fade" id="myModalCalendar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Time and Productivity Planning System</h4>
            </div>
            <div class="modal-body" id="calendarBody">
                <div id="calendar"></div>
            </div>
            <div class="modal-footer">
                TAPPS
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        if ($.trim($('#message').text()) != '') {
            $('#myModal').modal('show');
        }
    });
    var calendar = $("#calendar");
    $('#Projects').on('click', 'tr', function (e) {
        $('table').find('tr.highlight').removeClass('highlight');
        $(this).addClass('highlight');
    });

    function viewMembers(projectId) {
        $.ajax({
            url: '/Appoint/GetMembers?projectId=' + projectId,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            error: function (xhr) {
                alert('Error: ' + xhr.statusText);
            },
            success: function (members) {
                $("#tbody").empty();
                if (members.members.length == 0) {
                    var row = $("<tr/>")
                    $("#tbody").append(row);
                    row.append($("<td id = \"msgNoMembers\"colspan = \"5\" align=\"center\">Click Add Member button to assign employee to the project</td>"));
                }
                for (var i = 0; i < members.members.length; i++) {
                    drawRow(members.members[i]);
                }
            },
            async: true,
            processData: false
        });
    }

    function drawRow(rowData) {
        var row = $("<tr/>")
        $("#tbody").append(row);
        row.append($("<td>" + rowData.userName + "</td>"));
        row.append($("<td>" + rowData.roleName + "</td>"));

        row.append($("<td>" + new Date(rowData.participatingFrom).toLocaleDateString() + "</td>"));
        row.append($("<td>" + new Date(rowData.participatingTo).toLocaleDateString() + "</td>"));
        row.append($("<td> <a class=\"btn btn-sm btn-info .btn-sx\" href=\"/Appoint/Edit/" + rowData.appointmentId + "\">Edit</a> <a class=\"btn btn-sm btn-info .btn-sx\" href=\"/Appoint/Delete/" + rowData.appointmentId + "\">Delete</a></td>"))

    }
    function displayAddForm(projectId) {
        if ($('#addEmpRow').length > 0) {
            return;
        }
        var projectId = $('table').find('tr.highlight').data('prop');

        var url = "/Appoint/FillDropDowns";
        var request = $.ajax({
            url: url + "?projectId=" + projectId,
            type: "POST",
            contentType: "application/json",
            dataType: "json"
        });
        request.done(function (result) {
            if ($('#msgNoMembers').length > 0) {
                $('#tbody').empty();
            }
            if (result.Message != null) {
                $('#msg').empty();
                $('#msg').append(result.Message);
                $('#myModal').modal('show');
                return;
            }
            var row = $('<tr/>');
            row.attr("id", "addEmpRow");
            $('#tbody').append(row);
            var employees = result.emps;
            var roles = result.roles;
            var selectEmp = '<select class="form-control" onchange="SetEmpId()" id="Employee">'
            $.each(employees, function (id, fullName) {
                selectEmp += '<option value="' + id + '">' + fullName + '</option>';
            });
            /*for (var i = 0; i < employees.length; i++) {
             selectEmp += '<option value="' + employees[i].Id + '">' + employees[i].FullName + '</option>';
             }*/
            selectEmp += '</select>';
            row.append($('<td>' + selectEmp + '</td>'));
            var selectRoles = '<select class="form-control" id="Role">'
            $.each(roles, function (id, roleName) {
                selectRoles += '<option value="' + id + '">' + roleName + '</option>';
            });
            /*
             for (var i = 0; i < roles.length; i++) {
             selectRoles += '<option value="' + roles[i].Id + '">' + roles[i].Role + '</option>';
             }*/
            selectRoles += '</select>';
            row.append($('<td>' + selectRoles + '</td>'));
            row.append($('<td>' + '<input class="form-control text-box single-line" data-val="true" data-val-date="The field Finish Date must be a date." id="From" type="date" value="' + new Date().toJSON().slice(0, 10) + '"/> </td>'));
            row.append($('<td><input class="form-control text-box single-line" data-val="true" data-val-date="The field Finish Date must be a date." id="To" type="date" value="' + new Date().toJSON().slice(0, 10) + '"/> </td>'));
            row.append($('<td><button onclick="Add()" class=\"btn btn-sm btn-info .btn-sx\" >Add</button> <button onclick="Cancel()" class=\"btn btn-sm btn-info .btn-sx\">Cancel</button> <button id="Schedule" onclick="ShowCalendar()" class=\"btn btn-sm btn-danger .btn-sx\"><i class="fa fa-calendar fa-fw"></i></button></td>'));
            SetEmpId();
            setDatePicker();
        });
    }
    function setDatePicker() {
        if (!Modernizr.inputtypes.date) {
            $('[type="date"]').datepicker();
        }
    }
    function SetEmpId() {
        var empId = $('#Employee').val();
        $('#Schedule').data('prop', empId);
    }
    function ShowCalendar() {
        var empId = $('#Schedule').data('prop')
        var url = "/Appoint/GetCurProjects";
        var request = $.ajax({
            url: url + "?userId=" + empId,
            type: "POST",
            contentType: "application/json",
            dataType: "json"
        });
        request.done(function (data) {
            calendar.fullCalendar('destroy')
            calendar.fullCalendar();
            calendar.fullCalendar('removeEvents');
            calendar.fullCalendar("addEventSource", data.response);
            $('#myModalCalendar').modal('show');
            window.setTimeout(clickToday, 150);
        });

    }
    function Add() {

        var projectId = $('table').find('tr.highlight').data('prop');
        var projectStart = $('table').find('tr.highlight').find('#Start').html().trim();
        var projectEnd = $('table').find('tr.highlight').find('#End').html().trim();
        var empId = $('#Employee').val();
        var roleId = $('#Role').val();
        var from = ChangeDateFormat($('#From').val());
        var to = ChangeDateFormat($('#To').val());
        $('#msg').empty();
        if (from >= to) {
            $('#msg').append("Date To cannot be earlier than or equal to Date From");
            $('#myModal').modal('show');
            return;
        }
        else if (to < projectStart) {
            $('#msg').append("Date To cannot be earlier than Project Start Date");
            $('#myModal').modal('show');
            return;
        }
        else if (to > projectEnd) {
            $('#msg').append("Date To cannot be later than Project Finish Date");
            $('#myModal').modal('show');
            return;
        }
        $('#tbxProject').val(projectId);
        $('#tbxEmp').val(empId);
        $('#tbxRole').val(roleId);
        $('#tbxFrom').val(from);
        $('#tbxTo').val(to);
        $('#btnAddEmp').trigger("click");
    }
    function Cancel() {
        var projectId = $('table').find('tr.highlight').data('prop');
        viewMembers(projectId);
    }
    function clickToday() {
        $('.fc-today-button').click();
    }
    /*function DisplayAddFormNotUsed(){
     var projectId = $('table').find('tr.highlight').data('prop');
     var url = "/Appoint/IsActive";
     var request = $.ajax({
     url: url + "?projectId=" + projectId,
     type: "POST",
     contentType: "application/json",
     dataType: "json",
     success: function (data) {
     if (data.msg != "") {
     $('#msg').empty();
     $('#msg').append(data.msg);
     $('#myModal').modal('show');
     return false;
     }
     else { ShowForm(projectId);}
     }
     });

     }*/
    function ChangeDateFormat(curDate) {
        if (curDate.substr(0, 4) > 0)
            return curDate;
        var month = curDate.substr(0, 2);
        var day = curDate.substr(3, 2);
        var year = curDate.substr(6, 4);
        var date = year + "-" + month + "-" + day;
        return date;

    }
</script>