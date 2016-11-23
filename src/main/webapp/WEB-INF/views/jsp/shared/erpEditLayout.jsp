<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jas Shaykhov
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-3 col-lg-2">
    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-side" id="inner-content">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    Welcome <%= request.getAttribute("ProfileModel") %>
                </a>
            </div>
            <ul class="nav navbar-nav">
            <img class="img-circle" src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"/>
                <hr/>
            HR
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
                <a href="#"><i class="fa fa-fw fa-file-o"></i> Evaluation</a>
            </li>
            <li>
                <a href="/Hr/Profile/Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
            </li>
            <hr/>
            User's profile
                <li>
                    <a href="/User/Profile"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                </li>
                <li>
                    <a href="/User/Profile/Appointment"><i class="fa fa-fw fa-hand-pointer-o"></i> Appointment record</a>
                </li>
                <li>
                    <a href="/User/Profile/Salary"><i class="fa fa-fw fa-money"></i> Salary details</a>
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
<script>
    $(function(){
        $('#inner-content').slimScroll({
            height: '500px',
            railVisible: true,
            allowPageScroll: false,
            alwaysVisible: true
        });
    });
</script>
<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


