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
<c:set var="pageTitle" scope="request" value="Monitor workloads"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <%
            if((int)(request.getAttribute("SystemRole"))==1)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");
            else if((int)(request.getAttribute("SystemRole"))==2)

        %>

        <div class="col-md-offset-1 col-sm-10">
            <div class=" col-lg-offset-2 col-lg-10">
                <h1 class="page-header">Monitor Workloads</h1>
                <div class="row">
                    <div class="col-sm-3 col-md-2">
                        <div class="form-group">
                            <select class="form-control myTargetComponent">
                                <option> Rafatdin Shimbergenov </option>
                                <option> ALL </option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-4 col-md-3">
                        <div class="form-group">
                            <select class="form-control myTargetComponent">
                                <option> PJ 2016-007-R</option>
                                <option> PJ 2016-008-H</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-3 col-md-2">
                        <div class="form-group">
                            <select class="form-control myTargetComponent" id="typeId">
                                <option> Project</option>
                                <option> Administrative</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-sm-3 col-md-2">
                        <div class="form-group">
                            <input type="date" class="form-control myTargetComponent" id="dateFrom">
                        </div>
                    </div>
                    <div class="col-sm-3 col-md-2">
                        <div class="form-group">
                            <input type="date" class="form-control myTargetComponent" id="dateTo">
                        </div>
                    </div>
                </div>
            </div>
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
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${viewModel}" var="project">
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