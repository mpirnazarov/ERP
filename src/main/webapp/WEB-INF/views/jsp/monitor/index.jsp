<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
<%@ page import="com.lgcns.erp.hr.enums.ProjectRole" %>
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
<c:set var="pageTitle" scope="request" value="Monitor workloads"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>

        <div class="col-md-offset-1 col-sm-10">
            <div class=" col-lg-offset-2 col-lg-10">
                <h1 class="page-header">Monitor Workloads</h1>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bar-chart-o fa-fw"></i> Monitor
                        <div class="pull-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle"
                                        data-toggle="dropdown">
                                    Actions
                                    <span class="caret"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-3 col-md-2">
                                <div class="form-group">
                                    <select class="form-control myTargetComponent" id="userId">
                                        <c:if test='${requestScope.get("SystemRole")!=2}'>
                                            <option value="0">All employees</option>
                                        </c:if>
                                        <c:forEach items="${users}" var="user">
                                            <option value="${user.key}">${user.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3 col-md-2">
                                <div class="form-group">
                                    <select class="form-control myTargetComponent" id="projectId">
                                        <option value="0">All projects</option>
                                        <c:forEach items="${projects}" var="projects">
                                            <c:if test="${projects.key!=0}">
                                                <option value="${projects.key}">${projects.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-2 col-md-2">
                                <div class="form-group">
                                    <select class="form-control myTargetComponent" id="typeId">
                                        <option value="0">All types</option>
                                        <option value="<%=WorkloadType.Work_Project.getValue()%>">Project</option>
                                        <option value="<%=WorkloadType.Work_Administrative.getValue()%>">
                                            Administrative
                                        </option>
                                        <option value="<%=WorkloadType.Sick_leave.getValue()%>">Sick leave</option>
                                        <option value="<%=WorkloadType.Unpaid_leave.getValue()%>">Unpaid leave</option>
                                        <option value="<%=WorkloadType.Annual_leave.getValue()%>">Annual leave</option>
                                        <option value="<%=WorkloadType.Training.getValue()%>">Training</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <input type="date" class="form-control myTargetComponent"
                                           value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
                                           id="dateFrom">
                                </div>
                            </div>
                            <div class="col-sm-3 col-md-3 col-lg-3">
                                <div class="form-group">
                                    <input type="date" class="form-control myTargetComponent"
                                           value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
                                           id="dateTo">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped" id="myTable"
                                           style="color: #000;">
                                        <thead>
                                        <tr>
                                            <th>Employee</th>
                                            <th>Project</th>
                                            <th>Type</th>
                                            <th>Date</th>
                                            <th>Duration</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tbody">
                                            <c:forEach items="${viewModel}" var="monitor">
                                                <tr>
                                                    <td>${monitor.userName}</td>
                                                    <td>${monitor.projectName}</td>
                                                    <td>${monitor.type}</td>
                                                    <td>${monitor.date}</td>
                                                    <td>${monitor.duration}</td>
                                                </tr>
                                            </c:forEach>
                                                <tr>
                                                    <td colspan="4" align="right">Total</td>
                                                    <td><b>${total}</b></td>
                                                </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.col-lg-4 (nested) -->
                            <div class="col-lg-8">
                                <div id="morris-bar-chart"></div>
                            </div>
                            <!-- /.col-lg-8 (nested) -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.panel-body -->
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

<script>
    $(document).ready(function () {
        /*$('document').ready(function () {
         getval("null");
         })*/

        $(".myTargetComponent").change(function () {
            getval("null");
        });
        function getval(sel) {

            var requestData = {

                dateFrom: $("#dateFrom").val(),
                dateTo: $('#dateTo').val(),
                typeId: $('#typeId option:selected').val(),
                userId: $("#userId option:selected").val(),
                projectId: $("#projectId option:selected").val()
            };

            $.ajax({
                url: '/Monitor/ReceiveDataAjax',
                type: 'POST',
                data: JSON.stringify(requestData),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                error: function (xhr) {
                    alert('Error: ' + xhr.statusText);
                },
                success: function (response) {
                    appendIntoTable(response);
                },
                async: true,
                processData: false
            });
        }

        function appendIntoTable(result) {
            $("#tbody").empty();
            if (result.projects.length == 0) {
                var row = $("<tr/>")
                $("#myTable").append(row);
                row.append($("<td colspan = \"5\" align=\"center\"> There is no data to display for employee <b>" + $("#userId option:selected").text() + "</b>" + " with workload of type <i>" + $('#typeId option:selected').text() + "</i> at project <b> " + $("#projectid option:selected").text() + "</b>, from <u>" + $("#FromDate").val() + "</u> to <u>" + $("#ToDate").val() + "</u></td>"));
            }
            var tempTotal = 0;
            for (var i = 0; i < result.projects.length; i++) {
                drawRow(result.projects[i]);
                tempTotal += parseInt(result.projects[i].duration);
            }
            var row = $("<tr/>");
            $("#myTable").append(row);
            row.append($("<td colspan = \"4\" align=\"right\"> Total</td>"));
            row.append($("<td><b>" + tempTotal + "</b></td>"));
        }

        function drawRow(rowData) {
            var row = $("<tr/>")
            $("#myTable").append(row);
            row.append($("<td>" + rowData.employee + "</td>"));
            row.append($("<td>" + rowData.project + "</td>"));
            row.append($("<td>" + rowData.type + "</td>"));
            row.append($("<td>" + rowData.date + "</td>"));
            row.append($("<td>" + rowData.duration + " </td>"));
        }

        $('#myTable').DataTable({
            dom: 'Bfrtip',
            buttons: [
                'copy', 'excel', 'pdf', 'print'
            ],
            aoColumnDefs: [{
                'bSortable': false,
                'aTargets': ['nosort']
            }]
        });
    });
</script>