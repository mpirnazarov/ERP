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

<%--
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
<script type="text/javascript" src="https://simonbengtsson.github.io/jsPDF-AutoTable/libs/jspdf.plugin.autotable.src.js"></script>
--%>


<spring:url value="/monitorScripts/tableExport.js" var="tableExport"/>
<spring:url value="/monitorScripts/jquery.base64.js" var="jqueryBase64"/>
<spring:url value="/monitorScripts/html2canvas.js" var="html2canvas"/>
<spring:url value="/monitorScripts/jspdf/libs/sprintf.js" var="sprintf"/>
<spring:url value="/monitorScripts/jspdf/jspdf.js" var="jspdf"/>
<spring:url value="/monitorScripts/jspdf/libs/base64.js" var="base64"/>
<script type="text/javascript" src="${tableExport}"></script>
<script type="text/javascript" src="${jqueryBase64}"></script>
<script type="text/javascript" src="${html2canvas}"></script>
<script type="text/javascript" src="${sprintf}"></script>
<script type="text/javascript" src="${jspdf}"></script>
<script type="text/javascript" src="${base64}"></script>

<style>
    .panel{
        width: 100%;
    }
</style>
<div class="container-fluid">
    <div class="row">


            <div class="mainBodyBlock">
                <h1 class="headerText"><span class="fa fa-pie-chart fa-fw"></span> Monitor Workloads</h1>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bar-chart-o fa-fw"></i> Monitor
                        <div class="pull-right">
                            <div class="btn-group">
                                <a style="margin-top: -2px; margin-right: 4px; font-size: 12px; line-height: 1" class="btn btn-green" href="" onclick="ExportExcel()"><span class="fa fa-file-excel-o"></span> Export to Excel</a>
                                <a style="margin-top: -2px; margin-right: 4px; font-size: 12px; line-height: 1" class="btn btn-darkyellow" href="" onclick="$('#myTable').tableExport({type:'csv',escape:'false'});"><span class="fa fa-file-code-o"></span> Export to CSV</a>
                                <a style="margin-top: -2px; margin-right: 4px; font-size: 12px; line-height: 1" class="btn btn-darkblue" href="" onclick="$('#myTable').tableExport({type:'txt',escape:'false'});"><span class="fa fa-file-word-o"></span> Export to TXT</a>
                                <%--<button type="button" class="btn btn-grey btn-xs dropdown-toggle"
                                        data-toggle="dropdown">
                                    Actions
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu pull-right" role="menu">
                                    <li>
                                        <a href="" onclick="ExportExcel()">Export to Excel</a>
                                    </li>
                                    <li>
                                        <a href="" onclick="$('#myTable').tableExport({type:'excel',escape:'false'});">Export to XLS</a>
                                    </li>
                                    <li>
                                        <a href="" onclick="$('#myTable').tableExport({type:'csv',escape:'false'});">Export to CSV</a>
                                    </li>
                                    <li>
                                        <a href="" onclick="$('#myTable').tableExport({type:'txt',escape:'false'});">Export to TXT</a>
                                    </li>
                                    <li class="divider"></li>
                                </ul>--%>
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
                                <div class="table-responsive" id="targetData">
                                    <table class="table sartable table-bordered" id="myTable"
                                           style="color: #696969;">
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
                                <div id="morris-bar-chart" class="info"><p></p></div>
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
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

<script>
    function ExportExcel() {
        var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
        var textRange; var j = 0;
        tab = document.getElementById('myTable'); // id of table

        for (j = 0 ; j < tab.rows.length ; j++) {
            tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
            //tab_text=tab_text+"</tr>";
        }

        tab_text = tab_text + "</table>";
        tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
        tab_text = tab_text.replace(/<img[^>]*>/gi, ""); // remove if u want images in your table
        tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE");

        if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
        {
            txtArea1.document.open("txt/html", "replace");
            txtArea1.document.write(tab_text);
            txtArea1.document.close();
            txtArea1.focus();
            sa = txtArea1.document.execCommand("SaveAs", true, "Say Thanks to V_endetta.xls");
        }
        else                 //other browser not tested on IE 11
            sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));

        return (sa);
    }
    $(document).ready(function () {
        /*$('document').ready(function () {
         getval("null");
         })*/
        function toDataTable() {
            $('#myTable').DataTable({
                dom: 'Bfrtip',
                bPaginate:false,
                bFilter: false,
                buttons: [
                    'copy', 'excel', 'pdf', 'print'
                ],
                aoColumnDefs: [{
                    'bSortable': false,
                    'aTargets': ['nosort']
                }]
            });
            var table = $('#mytable').dataTable();
            var tableTools = new $.fn.dataTable.TableTools( table, {
                buttons: [
                    'copy', 'excel', 'pdf', 'print'
                ]
            } );
            $(tableTools.fnContainer()).insertAfter('#forButtons');
        }


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
                return;
            }
            var tempTotal = 0;
            for (var i = 0; i < result.projects.length; i++) {
                drawRow(result.projects[i]);
                tempTotal += parseInt(result.projects[i].duration);
            }

            var row = $("<tr/>");
            row.append($("<td colspan = \"4\" align=\"right\"> Total</td>"));
            row.append($("<td>" + tempTotal + "</td>"));
            $("#myTable").append(row);
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

    });
</script>