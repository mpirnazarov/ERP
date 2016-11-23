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

<spring:url value="/resources/core/css/normalize.css" var="normalizeCss"/>
<spring:url value="/resources/core/css/style.css" var="styleCss"/>
<spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>
<spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>

<%--<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" href="/resources/images/lg-2-multi-size.ico" type="image/x-icon">

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${allInOneCss}"/>

    <link rel="stylesheet" href="${normalizeCss}"/>
    <link rel="stylesheet" href="${styleCss}"/>
    <script type="text/javascript" src="${allInOneJs}"></script>

</head>
<body>--%>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css"
      href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css"/>
<script type="text/javascript" src="${allInOneJs}"></script>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
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
                        <td><fmt:formatDate value="${project.projectsByProjectId.startDate}" pattern="yyyy MMM dd"/> -
                            <fmt:formatDate value="${project.projectsByProjectId.endDate}" pattern="yyyy MMM dd"/></td>
                        <td>
                            <%
                                UserInProjectsEntity project = (UserInProjectsEntity)pageContext.getAttribute("project");
                                for(ProjectRole role : ProjectRole.values()){
                                    if(role.getValue() == project.getRoleId())
                                        out.print(role.toString());
                                }
                            %>
                        <td><fmt:formatDate value="${project.dateFrom}" pattern="yyyy MMM dd"/> - <fmt:formatDate
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
                        <td><a class="btn btn-md btn-default .btn-md" href="/Project/Edit/<c:out value="${project.id}"/>">Edit</a></td>
                        <td><a class="btn btn-md btn-default .btn-md" href="/Project/Delete/<c:out value="${project.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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