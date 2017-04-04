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

<div class="mainBodyBlock">
    <h1 class="headerText"><span class="fa fa-fw fa-users"></span> Userslist</h1>
    <div class="table-responsive">

        <%
            if ((int) request.getAttribute("SystemRole") == 3) {
        %>
        <%--<div class="col-lg-offset-10 col-xs-1">--%>
        <div class="text-right">
            <a href="/Hr/Register" class="btn btn-green" role="button">Create User</a>
        </div>
        <%--</div>--%>
        <% } %>
        <br/>
        <table id="myTable" class="display table table-bordered sartable" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Employee ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Username</th>
                <th>Role</th>
                <th style="text-align: center">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hrUserslistVM}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.userName}"/></td>
                    <td><c:out value="${roles.get(user.roleId)}"/></td>
                    <%
                        if ((int) request.getAttribute("SystemRole") == 1) {
                    %>
                    <td style="text-align: center">
                        <spring:url value="/Manager/user/${user.id}/Geninfo" var="userUrl"/>
                        <button class="btn btn-blue" onclick="location.href='${userUrl}'">View</button>
                    </td>

                    <% } %>

                    <%
                        if ((int) request.getAttribute("SystemRole") == 3) {
                    %>
                    <td style="text-align: center">
                        <spring:url value="/Hr/user/${user.id}/Geninfo" var="userUrl"/>
                        <spring:url value="/Hr/user/${user.id}/disable/" var="disableUrl"/>
                        <spring:url value="/Hr/user/${user.id}/enable/" var="enableUrl"/>
                        <spring:url value="/Hr/user/${user.id}/update/Geninfo" var="updateUrl"/>

                        <button class="btn btn-blue" onclick="location.href='${userUrl}'">View</button>
                        <button class="btn btn-darkblue" onclick="location.href='${updateUrl}'">Update</button>
                        <c:if test="${not user.enabled}">
                        <button class="btn btn-green" onclick="location.href='${enableUrl}'">Enable</button>
                    </td>

                    </c:if>
                    <c:if test="${user.enabled}">
                        <button class="btn btn-red" onclick="location.href='${disableUrl}'">Disable</button>
                        </td>
                    </c:if>

                    <% } %>

                </tr>
            </c:forEach>
            </tbody>
        </table>
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
                'copy', 'excel', 'pdf', 'print',
            ]
        });
    });
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>