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
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">
        <h1 class="headerText">Userslist</h1>
        <div class="table-responsive">

            <br/>
            <form:form method="post" commandName="person">
                <table id="myTable" class="display table sartable table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th style="text-align: center">Checkbox</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hrUserslistVM}" var="user">
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.firstName}"/></td>
                            <td><c:out value="${user.lastName}"/></td>
                            <td><form:checkbox path="isChecked" value="${user.id}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="submit" class="btn btn-success">
            </form:form>
        </div>

    </div>

<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            dom: 'Bfrtip',
            select: true,
            "order": [[0, "desc"]],
            buttons: [

            ]
        });
    });
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>