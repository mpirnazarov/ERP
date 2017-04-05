<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jas Shaykhov
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                &lt;%&ndash;<object class="img-circle" data="/resources/images/${id}.jpg" style="width:45%; margin-left: 25%">&ndash;%&gt;
                &lt;%&ndash;<img class="img-circle" src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"/>&ndash;%&gt;
                &lt;%&ndash;</object>&ndash;%&gt;
                <div class="userImgBox">
                    <img class="userimg" src="/image/<%= request.getAttribute("userId") %>.jpg" onerror="this.src='/resources/images/ppicture.png'">
                </div>
                HR System
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-home"></i> Home
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/<%= request.getAttribute("path") %>/Profile">General information</a></li>
                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Appointment">Appointment record</a>
                        </li>
                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Salary">Salary details</a></li>
                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Edu">Education certificate</a></li>
                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Jobexp">Job experience</a></li>
                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Train">Training record</a></li>
                    </ul>
                </li>
                <li>
                    <a href="/<%= request.getAttribute("path") %>/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
                </li>
                <% if ((int) request.getAttribute("SystemRole") == 1) { %>
                <li>
                    <a href="/Manager/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation</a>
                </li>
                <% } %>
                <% if ((int) request.getAttribute("SystemRole") == 3) { %>
                <li>
                    <a href="/Hr/Profile/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation history</a>
                </li>
                <% } %>
                <li>
                    <a href="/<%= request.getAttribute("path") %>/Docs"><i class="fa fa-fw fa-file"></i> Generate
                        documents</a>
                </li>
                <hr/>
                User's profile
                <li>
                    <a href="./Geninfo"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                </li>
                <li>
                    <a href="./Profile/Project"><i class="fa fa-fw fa-folder"></i> Project history</a>
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
                    <a href="/Hierarchy"><i class="fa fa-line-chart"></i> Hierarchy</a>
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
                    <a href="/<%= request.getAttribute("path") %>/changepass"><i class="fa fa-fw fa-gears"></i> Change
                        password</a>
                </li>
                <li>
                    <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>--%>
<!-- /#sidebar-wrapper -->

<%--MY Decision--%>
<div class="col-md-2 userNavMenu">
    <nav class="navbar navbar-fixed-side __scrollBar">

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
                                <a class="linkcolor" role="button" data-toggle="collapse"
                                   href="#collapseExample" aria-expanded="false"
                                   aria-controls="collapseExample"><span class="glyphicon glyphicon-user"
                                                                         aria-hidden="true"></span>
                                    My profile
                                </a>
                                <div class="collapse" id="collapseExample">

                                    <ul style="list-style: none" class="list-group well">
                                        <li><a href="/<%= request.getAttribute("path") %>/Profile">General
                                            information</a>
                                        </li>
                                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Appointment">Appointment
                                            record</a>
                                        </li>
                                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Salary">Salary
                                            details</a>
                                        </li>
                                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Edu">Education
                                            certificate</a></li>
                                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Jobexp">Job
                                            experience</a>
                                        </li>
                                        <li><a href="/<%= request.getAttribute("path") %>/Profile/Train">Training
                                            record</a>
                                        </li>
                                    </ul>

                                </div>
                            </li>

                            <li>
                                <a href="/<%= request.getAttribute("path") %>/Userslist"><i
                                        class="fa fa-fw fa-users"></i>
                                    Users</a>
                            </li>
                            <% if ((int) request.getAttribute("SystemRole") == 1) { %>
                            <li>
                                <a href="/Manager/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation</a>
                            </li>
                            <% } %>
                            <% if ((int) request.getAttribute("SystemRole") == 3) { %>
                            <li>
                                <a href="/Hr/Profile/Evaluation"><i class="fa fa-fw fa-file-o"></i> Evaluation
                                    history</a>
                            </li>
                            <% } %>
                            <li>
                                <a href="/<%= request.getAttribute("path") %>/Docs"><i class="fa fa-fw fa-file"></i>
                                    Generate
                                    documents</a>
                            </li>
                            <li>
                                <a class="linkcolor" href="/Hierarchy"><i class="fa fa-line-chart"></i> Hierarchy</a>
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
                           aria-expanded="false" aria-controls="collapseUser" class="collapsed">
                            <%--Check for null could be mandatory--%>
                            User: <span style="color: green;">${fullName}</span>
                        </a>
                    </h4>
                </div>
                <div id="collapseUser" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingUser">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">

                            <li>
                                <a href="./Geninfo"><i class="fa fa-fw fa-info-circle"></i> General information</a>
                            </li>
                            <li>
                                <a href="./Profile/Project"><i class="fa fa-fw fa-folder"></i> Project history</a>
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
                                <a href="/Hierarchy"><i class="fa fa-line-chart"></i> Hierarchy</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo"
                           aria-expanded="false" aria-controls="collapseTwo">
                            TAAPS System
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">

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
                                <a href="/Contacts"><i class="fa fa-male fa-fw"></i> Contacts</a>
                            </li>
                            <li>
                                <a href="/Appoint"><i class="fa fa-hand-o-up fa-fw"></i> Appoint</a>
                            </li>
                            <li>
                                <a href="/Organizations"><i class="fa fa-briefcase fa-fw"></i> Organizations</a>
                            </li>
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
                        </ul>
                    </div>
                </div>
                <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
            </div>
        </div>
    </nav>
</div>

<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


