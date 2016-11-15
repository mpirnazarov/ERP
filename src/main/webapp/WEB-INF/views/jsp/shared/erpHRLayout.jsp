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
            <img class="img-circle" src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"/>
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-home"></i> Home <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
            <li class="dropdown-header">Information about HR</li>
            <li><a href="/Hr/Profile">General information</a></li>
            <li><a href="/Hr/Profile/Appointment">Appointment record</a></li>
            <li><a href="#">Workload</a></li>
            <li><a href="/Hr/Profile/Edu">Education certificate</a></li>
            <li><a href="/Hr/Profile/Jobexp">Job experience</a></li>
            <li><a href="/Hr/Profile/Train">Training record</a></li>
            </ul>
            </li>
            <li>
                <a href="/Hr/Profile/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-folder"></i> Projects</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-file-o"></i> Evaluation</a>
            </li>
            <li>
                <a href="/Hr/Profile/Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
            </li>
            <hr/>
            <li>
                <a href="#"><i class="fa fa-fw fa-gears"></i> Change password</a>
            </li>
            <li>
                <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
            </li>
        </ul>
        </div>
    </nav>
</div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <%--<div id="page-content-wrapper">--%>
        <%--<button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">--%>
            <%--<span class="hamb-top"></span>--%>
            <%--<span class="hamb-middle"></span>--%>
            <%--<span class="hamb-bottom"></span>--%>
        <%--</button>--%>
    <%--</div>--%>
    <!-- /#page-content-wrapper -->
<!-- /#wrapper -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<spring:url value="/resources/core/js/index.js" var="indexJs"></spring:url>
<script src="${indexJs}"></script>

