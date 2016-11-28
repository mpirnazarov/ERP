<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %><%--
  Created by IntelliJ IDEA.
  User: Muslimbek
  Date: 08.11.2016
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="kendo" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Users List"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpCTOLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
            <div class="col-lg-8 col-lg-offset-2">
                <h1 class="page-header">Userslist</h1>
            <div class="table-responsive">

        <br/>
        <table id="myTable" class="display table" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>ID</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Username</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hrUserslistVM}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.firstName[2]}"/></td>
                    <td><c:out value="${user.lastName[2]}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td>
                        <spring:url value="/Hr/user/${user.id}" var="userUrl" />
                        <button class="btn btn-info" onclick="location.href='${userUrl}'">View</button>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('#myTable').DataTable({
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            dom: 'Bfrtip',
            select: true,
            "order": [[ 0, "desc" ]],
            buttons: [
                'copy', 'excel', 'pdf', 'print',
            ]
        });
    });
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>