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
                <hr/>
            HR System
            <li>
                <a href="/Hr/Profile"><i class="fa fa-fw fa-home"></i> Home</a>
            </li>
            <li>
                <a href="/Hr/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-folder"></i> Projects</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-file-o"></i> Evaluation history</a>
            </li>
            <li>
                <a href="/Hr/GenerateDocs"><i class="fa fa-fw fa-file"></i> Generate documents</a>
            </li>
            <hr/>
            User's profile
                <li>
                    <a href="./Geninfo"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                </li>
                <li>
                    <a href="./Appointment"><i class="fa fa-fw fa-hand-pointer-o"></i> Appointment record</a>
                </li>
                <li>
                    <a href="./Salary"><i class="fa fa-fw fa-money"></i> Salary details</a>
                </li>
                <li>
                    <a href="./Edu"><i class="fa fa-fw fa-graduation-cap"></i> Education certificate</a>
                </li>
                <li>
                    <a href="./Jobexp"><i class="fa fa-fw fa-briefcase"></i> Job experience</a>
                </li>
                <li>
                    <a href="./Train"><i class="fa fa-fw fa-book"></i> Training record</a>
                </li>
                <li>
                    <a href="./Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
                </li>
                <li>
                    <a href="../UploadPic"><i class="fa fa-fw fa-file"></i> Change Picture</a>
                </li>
                <hr/>
                Settings
                <li>
                    <a href="/Hr/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
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


