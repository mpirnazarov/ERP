<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="pageTitle" scope="request" value="Home"/>--%>
<%--<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("ProfileModel", a.getFirstName()[2]);
%>--%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>

<body>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>All Users</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Username</th>
            <th>Action</th>
        </tr>
        </thead>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                        ${user.id}
                </td>
                <td>${user.firstName[2]}</td>
                <td>${user.lastName[2]}</td>
                <td>${user.personalInfo.emailCompany}</td>
                <td>${user.username}</td>
                <td>
                    <spring:url value="/users/${user.id}" var="userUrl" />
                    <spring:url value="/users/${user.id}/delete" var="deleteUrl" />
                    <spring:url value="/users/${user.id}/update" var="updateUrl" />

                    <button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
            </tr>
        </c:forEach>
    </table>

</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

</body>
