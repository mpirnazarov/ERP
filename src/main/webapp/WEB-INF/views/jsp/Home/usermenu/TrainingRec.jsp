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
<c:set var="pageTitle" scope="request" value="Trainings"/>
<%
/*    ProfileViewModel a = (ProfileViewModel)request.getAttribute("userProfile");
    request.setAttribute("ProfileModel", (a.getFirstName()).toString());*/
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Training record</h1>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#train">Trainings</a></li>
            </ul>

            <div class="tab-content">
                <div id="train" class="tab-pane fade in active">
                    <h3>Trainings</h3>
                    <!--Trainings table-->
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Certificate</th>
                            <th>Organization</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Number of hours</th>
                            <th>Mark</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--<c:forEach items="${trainList}" var="train" varStatus="status">--%>
                        <tr>
                            <td>Row 1 Data 1</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                        </tr>
                        <tr>
                            <td>Row 2 Data 1</td>
                            <td>Row 2 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                            <td>Row 1 Data 2</td>
                        </tr>
                        <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
