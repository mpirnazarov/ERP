<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jas Shaykhov
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Old version--%>
<%--<div class="col-md-2 userNavMenu">
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
                    <img class="userimg" src="/image/<%= request.getAttribute("userId") %>.jpg" onerror="this.src='/resources/images/ppicture.png'">
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
                <a href="/Hr/Profile/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation history</a>
            </li>
            <li>
                <a href="/Hr/GenerateDocs"><i class="fa fa-fw fa-file"></i> Generate documents</a>
            </li>
            <li>
                <a href="/Hierarchy"><i class="fa fa-line-chart"></i> Hierarchy</a>
            </li>
            <hr/>
            User's profile
                <% if(request.getAttribute("EditAdd")!= null){
                    if((int)request.getAttribute("EditAdd")==1){%>
                    <li>
                        <a href="../Geninfo"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                    </li>
                    <li>
                        <a href="../Appointment"><i class="fa fa-fw fa-hand-pointer-o"></i> Appointment record</a>
                    </li>
                    <li>
                        <a href="../Salary"><i class="fa fa-fw fa-money"></i> Salary details</a>
                    </li>
                    <li>
                        <a href="../Edu"><i class="fa fa-fw fa-graduation-cap"></i> Education certificate</a>
                    </li>
                    <li>
                        <a href="../Jobexp"><i class="fa fa-fw fa-briefcase"></i> Job experience</a>
                    </li>
                    <li>
                        <a href="../Train"><i class="fa fa-fw fa-book"></i> Training record</a>
                    </li>
                    <li>
                        <a href="../Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
                    </li>
                    <li>
                        <a href="../UploadPic"><i class="fa fa-fw fa-file"></i> Change Picture</a>
                    </li>
                    <li>
                        <a href="../ChangePass"><i class="fa fa-fw fa-gears"></i> User Change Password</a>
                    </li>
                    <li>
                        <a href="../Head"><i class="fa fa-fw fa-gears"></i> Change Head</a>
                    </li>
                <%
                    }}
                    else{ %>
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
                    <a href="./UploadPic"><i class="fa fa-fw fa-file"></i> Change Picture</a>
                </li>
                <li>
                    <a href="./ChangePass"><i class="fa fa-fw fa-gears"></i> User Change Password</a>
                </li>
                <li>
                    <a href="./Head"><i class="fa fa-fw fa-gears"></i> Change Head</a>
                </li>
                <% } %>
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
</div>--%>

<%--MY Decision--%>
<div class="col-md-2 userNavMenu">
    <nav class="navbar navbar-fixed-side __scrollBar">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                Welcome <%= request.getAttribute("FirstName") %>
            </a>
        </div>
        <div class="userImgBox">
            <img class="userimg" src="/image/<%= request.getAttribute("userId") %>.jpg"
                 onerror="this.src='/resources/images/ppicture.png'">
        </div>
        <a href="/"><i class="fa fa-fw fa-home"></i> Home</a>
        <div style="background: transparent" class="panel-group" id="accordion" role="tablist"
             aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="false" aria-controls="collapseOne">
                            HR System
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group active">
                            <li>
                                <a href="/Hr/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-folder"></i> Projects</a>
                            </li>
                            <li>
                                <a href="/Hr/Profile/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation history</a>
                            </li>
                            <li>
                                <a href="/Hr/GenerateDocs"><i class="fa fa-fw fa-file"></i> Generate documents</a>
                            </li>
                            <li>
                                <a href="/Hierarchy"><i class="fa fa-line-chart"></i> Hierarchy</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingUser">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseUser"
                           aria-expanded="true" aria-controls="collapseUser">
                            <%--Check for null could be mandatory--%>
                            User: <span style="color: green;">${fullName}</span>
                        </a>
                    </h4>
                </div>
                <div id="collapseUser" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingUser">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">

                            <% if(request.getAttribute("EditAdd")!= null){
                                if((int)request.getAttribute("EditAdd")==1){%>
                            <li>
                                <a href="../Geninfo"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                            </li>
                            <li>
                                <a href="../Appointment"><i class="fa fa-fw fa-hand-pointer-o"></i> Appointment record</a>
                            </li>
                            <li>
                                <a href="../Salary"><i class="fa fa-fw fa-money"></i> Salary details</a>
                            </li>
                            <li>
                                <a href="../Edu"><i class="fa fa-fw fa-graduation-cap"></i> Education certificate</a>
                            </li>
                            <li>
                                <a href="../Jobexp"><i class="fa fa-fw fa-briefcase"></i> Job experience</a>
                            </li>
                            <li>
                                <a href="../Train"><i class="fa fa-fw fa-book"></i> Training record</a>
                            </li>
                            <li>
                                <a href="../Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
                            </li>
                            <li>
                                <a href="../UploadPic"><i class="fa fa-fw fa-file"></i> Change Picture</a>
                            </li>
                            <li>
                                <a href="../ChangePass"><i class="fa fa-fw fa-gears"></i> Change Password</a>
                            </li>
                            <%
                                }}
                            else{ %>
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
                                <a href="./UploadPic"><i class="fa fa-fw fa-file"></i> Change Picture</a>
                            </li>
                            <li>
                                <a href="./ChangePass"><i class="fa fa-fw fa-gears"></i> Change Password</a>
                            </li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseThree"
                           aria-expanded="false" aria-controls="collapseThree">
                            Settings
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">
                            <li>
                                <a href="/Hr/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                            </li>
                            <li>
                                <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>

<!-- /#sidebar-wrapper -->


<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


