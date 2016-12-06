<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jas Shaykhov
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-2 userNavMenu">
    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-side __scrollBar">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    Welcome <%= request.getAttribute("FirstName") %>
                </a>
            </div>
            <ul class="nav navbar-nav">
                <div class="userImgBox">
                    <img class="userimg" src="/image/<%= request.getAttribute("userId") %>.jpg">
                </div>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-home"></i> Home
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">Information about CTO</li>
                        <li><a href="/CTO/Profile">General information</a></li>
                        <li><a href="/CTO/Profile/Appointment">Appointment record</a></li>
                        <li><a href="/CTO/Profile/Salary">Salary details</a></li>
                        <li><a href="/CTO/Profile/Edu">Education certificate</a></li>
                        <li><a href="/CTO/Profile/Jobexp">Job experience</a></li>
                        <li><a href="/CTO/Profile/Train">Training record</a></li>
                        <li><a href="/CTO/Profile/Evaluation">Evaluation </a></li>
                    </ul>
                </li>
                <li>
                    <a href="/CTO/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
                </li>
                <li>
                    <a href="/CTO/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation</a>
                </li>
                <li>
                    <a href="/CTO/Profile/Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
                </li>
                <hr/>
                TAPPS System
                <li>
                    <a href="/Workload"><i class="fa fa-edit fa-fw"></i> Workload</a>
                </li>
                <li>
                    <a href="/Projects"><i class="fa fa-file-powerpoint-o fa-fw"></i> Projects</a>
                </li>
                <li>
                    <a href="/Monitor"><i class="fa fa-pie-chart fa-fw"></i> Monitor</a>
                </li>
                <li>
                    <a href="/Roles"><i class="fa fa-male fa-fw"></i> Roles</a>
                </li>
                <li>
                    <a href="/Appoint"><i class="fa fa-hand-o-up fa-fw"></i> Appoint</a>
                </li>
                <li>
                    <a href="/Customers"><i class="fa fa-briefcase fa-fw"></i> Customers</a>
                </li>
                <hr/>
                Settings
                <li>
                    <a href="/CTO/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                </li>
                <li>
                    <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<!-- /#sidebar-wrapper -->

<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


