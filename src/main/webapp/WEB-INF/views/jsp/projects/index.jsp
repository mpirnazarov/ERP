<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
<%@ page import="com.lgcns.erp.hr.enums.ProjectRole" %>
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
            <div class="mainBodyBlock">
                <h1 class="headerText"><span class="fa fa-file-powerpoint-o fa-fw"></span> Participating projects</h1>
                <%
                    if (((int) request.getAttribute("SystemRole")) == 1)
                        out.print("<div style=\"overflow: hidden\">\n" +
                                "                    <input type=\"button\" value=\"Create new project\" class=\"btn btn-green\" style=\"float: left;\"  onclick=\"location.href='/Projects/Create'\">\n" +
                                "                    <div style=\"clear: both;\">&nbsp;</div>\n" +
                                "                </div>");
                %>

                <div class="table-responsive">
                    <table id="myTable" class="display table table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Project name</th>
                            <th>Project duration</th>
                            <th>Participating role</th>
                            <th>Participating duration</th>
                            <th>Status</th>
                            <td class="nosort"></td>
                            <td class="nosort"></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${projects}" var="project">
                            <tr>
                                <td><c:out
                                        value="${project.projectsByProjectId.code} - ${project.projectsByProjectId.type}"/></td>
                                <td><c:out value="${project.projectsByProjectId.name}"/></td>
                                <td><fmt:formatDate value="${project.projectsByProjectId.startDate}"
                                                    pattern="yyyy MMM dd"/> -
                                    <fmt:formatDate value="${project.projectsByProjectId.endDate}"
                                                    pattern="yyyy MMM dd"/></td>
                                <td>
                                            <%
                                UserInProjectsEntity project = (UserInProjectsEntity)pageContext.getAttribute("project");
                                for(ProjectRole role : ProjectRole.values()){
                                    if(role.getValue() == project.getRoleId())
                                        out.print(role.toString());
                                }
                            %>
                                <td><fmt:formatDate value="${project.dateFrom}" pattern="yyyy MMM dd"/> -
                                    <fmt:formatDate
                                            value="${project.dateTo}" pattern="yyyy MMM dd"/></td>
                                <td>
                                    <%
                                        UserInProjectsEntity uip = (UserInProjectsEntity) pageContext.getAttribute("project");
                                        for (ProjectStatus e : ProjectStatus.values()) {
                                            if (e.getValue() == uip.getProjectsByProjectId().getStatus())
                                                out.write(e.name());
                                        }
                                    %>
                                </td>
                                <td><c:if test="${project.roleId == managerId}">
                                    <a class="btn btn-md btn-default .btn-md"
                                       href="/Projects/Edit/<c:out value="${project.projectId}"/>">Edit</a>
                                </c:if></td>

                                <td><c:if test="${project.roleId == managerId}">
                                    <a class="btn btn-md btn-default .btn-md"
                                       href="/Projects/Delete/<c:out value="${project.projectId}"/>">Delete</a>
                                </c:if></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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