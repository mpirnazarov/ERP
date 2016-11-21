<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Jasur Shaykhov
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Documents"/>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("FullName", a.getFirstName()[2] + " " + a.getLastName()[2]);
    request.setAttribute("JobTitle", a.getJobTitle());
%>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
            <h2 class="page-header">Documents</h2>
            <table class="table table-default">
                <thead>
                <tr>
                    <th>Document name</th>
                    <th>Document type</th>
                    <th>Username</th>
                    <th><i class="fa fa-fw fa-download"></i></th>
                </tr>
                </thead>
                <tbody>
                <%--<c:forEach items="${}" var="doc" varStatus="status">--%>
                    <tr>
                        <td>Information about Salary</td>
                        <td>Document</td>
                        <td>
                                <select class="selectpicker show-tick" data-live-search="true">
                                    <option data-tokens="jas">Jasur Shaykhov</option>
                                    <option data-tokens="muslimbek">Muslimbek Pirnazarov</option>
                                    <option data-tokens="bakir">Bakir Maksumov</option>
                                </select>
                        </td>
                        <td><i class="fa fa-fw fa-download"></i></td>
                    </tr>
                <%--</c:forEach>--%>
                </tbody>
            </table>
        </div>
    </div>
</div>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
