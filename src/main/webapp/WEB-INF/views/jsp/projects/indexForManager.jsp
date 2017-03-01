<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
<%@ page import="com.lgcns.erp.hr.enums.ProjectRole" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity" %>
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
<c:set var="pageTitle" scope="request" value="Projects"/>
<c:set var="managerId" scope="request" value="<%= ProjectRole.Manager.getValue()%>"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <%
            if ((int) (request.getAttribute("SystemRole")) == 1)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpCTOLayout.jsp\"></jsp:include>");
            else if ((int) (request.getAttribute("SystemRole")) == 2)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");
            else if ((int) (request.getAttribute("SystemRole")) == 3)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpHrLayout.jsp\"></jsp:include>");
            else
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");
        %>
        <div class="col-md-offset-2 col-sm-10">
            <div class=" col-lg-offset-1 col-lg-11">
                <h1 class="page-header">Participating projects</h1>
                <%
                    if (((int) request.getAttribute("SystemRole")) == 1)
                        out.print("<div style=\"overflow: hidden\">\n" +
                                "                    <input type=\"button\" value=\"Create new project\" class=\"btn btn-success\" style=\"float: left;\"  onclick=\"location.href='/Projects/Create'\">\n" +
                                "                    <div style=\"clear: both;\">&nbsp;</div>\n" +
                                "                </div>");
                %>

                <div class="table-responsive">
                    <table id="myTable" class="display table table-bordered">
                        <col style="width: 2%">
                        <col style="width: 12%">
                        <col style="width: 8%">
                        <col style="width: 5%">
                        <col style="width: 2%">
                        <col style="width: 2%">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Project name</th>
                            <th>Project duration</th>
                            <th>Status</th>
                            <th>UZS</th>
                            <th>USD</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${projects}" var="project">
                            <tr>
                                <td> PJ-<c:out
                                        value="${project.code}-${project.type}"/></td>
                                <td><c:out value="${project.name}"/></td>
                                <td><fmt:formatDate value="${project.startDate}"
                                                    pattern="yyyy MMM dd"/> -
                                    <fmt:formatDate value="${project.endDate}"
                                                    pattern="yyyy MMM dd"/></td>
                                <td><%
                                    ProjectsEntity project = (ProjectsEntity)pageContext.getAttribute("project");
                                    if(project.getStatus()==1)
                                        out.print("Active");
                                    else if(project.getStatus()==2)
                                        out.print("Preparing");
                                    else
                                        out.print("Completed");
                                    %>
                                </td>
                                <td>
                                    <fmt:formatNumber type="number" maxIntegerDigits="13" maxFractionDigits="2"
                                                      value="${project.moneyUzs}" /></td>
                                </td>
                                <td>
                                    <fmt:setLocale value="en_US"/>
                                    <fmt:formatNumber type="currency" value="${project.moneyUsd}" /></td>
                                </td>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
<script>
    $(document).ready(function () {
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