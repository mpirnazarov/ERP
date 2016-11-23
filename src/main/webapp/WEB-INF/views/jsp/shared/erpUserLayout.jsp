<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-3 col-lg-2">
    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-side">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    Welcome <%= request.getAttribute("ProfileModel") %>
                </a>
            </div>
            <ul class="nav navbar-nav">
                <!--Profile picture-->

                <%--<img src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"><br>--%>
                <img class="img-circle" src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"/>
                <li>
                    <a href="/User/Profile"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                </li>
                <li>
                    <a href="/User/Profile/Appointment"><i class="fa fa-fw fa-hand-pointer-o"></i> Appointment
                        record</a>
                </li>
                <li>
                    <a href="/User/Profile/Salary"><i class="fa fa-fw fa-hand-pointer-o"></i> Salary details</a>
                </li>
                <li>
                    <a href="/Workload/Index"><i class="fa fa-fw fa-calendar-o"></i> Workload</a>
                </li>
                <li>
                    <a href="/User/Profile/Edu"><i class="fa fa-fw fa-graduation-cap"></i> Education certificate</a>
                </li>
                <li>
                    <a href="/User/Profile/Jobexp"><i class="fa fa-fw fa-briefcase"></i> Job experience</a>
                </li>
                <li>
                    <a href="/User/Profile/Train"><i class="fa fa-fw fa-book"></i> Training record</a>
                </li>
                <li>
                    <a href="/User/Profile/Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
                </li>
                <li>
                    <a href="/User/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                </li>
                <li>
                    <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- /#sidebar-wrapper -->
</div>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<spring:url value="/resources/core/js/index.js" var="indexJs"></spring:url>
<script src="${indexJs}"></script>

