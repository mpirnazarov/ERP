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
                    <!--Profile picture-->

                    <%--<img src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"><br>--%>
                    <div class="userImgBox">
                        <img class="userimg" src="/resources/images/users/choi.jpg">
                    </div>
                    <hr/>
                    HR System
                    <li>
                        <a href="/User/Profile"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                    </li>
                    <li>
                        <a href="/User/Profile/Appointment"><i class="fa fa-fw fa-hand-pointer-o"></i> Appointment
                            record</a>
                    </li>
                    <li>
                        <a href="/User/Profile/Salary"><i class="fa fa-fw fa-money"></i> Salary details</a>
                    </li>
                    <li>
                        <a href="/User/Profile/Edu"><i class="fa fa-fw fa-graduation-cap"></i> Education certificate</a>
                    </li>
                    <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-plus"></i> Dropdown <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu" role="menu">--%>
                    <%--<li class="dropdown-header">Dropdown heading</li>--%>
                    <%--<li><a href="#">Action</a></li>--%>
                    <%--<li><a href="#">Another action</a></li>--%>
                    <%--<li><a href="#">Something else here</a></li>--%>
                    <%--<li><a href="#">Separated link</a></li>--%>
                    <%--<li><a href="#">One more separated link</a></li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
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
                        <a href="/User/Profile/Project"><i class="fa fa-fw fa-folder"></i> Project history</a>
                    </li>
                    <hr/>
                    TAPPS System
                    <li>
                        <a href="/Workload/Index"><i class="fa fa-fw fa-calendar-o"></i> Workload</a>
                    </li>
                    <hr/>
                    Settings
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
<%--<!-- Page Content -->--%>
<%--<div id="page-content-wrapper">--%>
<%--<button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">--%>
<%--<span class="hamb-top"></span>--%>
<%--<span class="hamb-middle"></span>--%>
<%--<span class="hamb-bottom"></span>--%>
<%--</button>--%>
<%--</div>--%>
<%--<!-- /#page-content-wrapper -->--%>
<!-- /#wrapper -->
<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<spring:url value="/resources/core/js/index.js" var="indexJs"></spring:url>
<script src="${indexJs}"></script>