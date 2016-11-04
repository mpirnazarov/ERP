<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Documents"/>
<%
    /*    ProfileViewModel a = (ProfileViewModel)request.getAttribute("userProfile");
        request.setAttribute("ProfileModel", (a.getFirstName()[0]).toString());*/
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Documents</h1>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Document name</th>
                    <th>Document type</th>
                    <th>Download</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${}" var="doc" varStatus="status">
                    <tr>
                        <td>${}</td>
                        <td>${}</td>
                        <td>${}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
