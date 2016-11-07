<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Job experience"/>
<%
    String a = request.getAttribute("name").toString();
    request.setAttribute("ProfileModel", a);
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Job experience</h1>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#jobexp">Job experience</a></li>
            </ul>

            <div class="tab-content">
                <div id="jobexp" class="tab-pane fade in active">
                    <h3>Job experience</h3>
                    <!--Job experience table-->
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Organization</th>
                            <th>Position</th>
                            <th>Start date</th>
                            <th>End date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                        <tr>
                            <td>${jobexp.organization}</td>
                            <td>${jobexp.position}</td>
                            <td>${jobexp.startDate}</td>
                            <td>${jobexp.endDate}</td>
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
