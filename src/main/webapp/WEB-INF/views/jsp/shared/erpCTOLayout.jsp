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
                    Welcome <%= request.getAttribute("FullName") %>
                </a>
            </div>
            <ul class="nav navbar-nav">
                <div class="userImgBox">
                    <img class="userimg" src="/image/<%= request.getAttribute("userId") %>.jpg" onerror="this.src='/resources/images/ppicture.png'">
                </div>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-home"></i> Home
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">Information about Manager</li>
                        <li><a href="/Manager/Profile">General information</a></li>
                        <li><a href="/Manager/Profile/Appointment">Appointment record</a></li>
                        <li><a href="/Manager/Profile/Salary">Salary details</a></li>
                        <li><a href="/Manager/Profile/Edu">Education certificate</a></li>
                        <li><a href="/Manager/Profile/Jobexp">Job experience</a></li>
                        <li><a href="/Manager/Profile/Train">Training record</a></li>
                        <li><a href="/Manager/Profile/Evaluation">Evaluation history </a></li>
                    </ul>
                </li>
                <li>
                    <a href="/Manager/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
                </li>
                <li>
                    <a href="/Manager/Evaluation"><i class="fa fa-fw fa-bar-chart"></i> Evaluation</a>
                </li>
                <li>
                    <a href="/Manager/Docs"><i class="fa fa-fw fa-file"></i> Documents</a>
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
                    <a href="/Appoint"><i class="fa fa-hand-o-up fa-fw"></i> Appoint</a>
                </li>
                <li>
                    <a href="/Organizations"><i class="fa fa-briefcase fa-fw"></i> Customer organizations</a>
                </li>
                <li>
                    <a href="/Contacts"><i class="fa fa-male fa-fw"></i> Contacts</a>
                </li>
                <hr/>
                Settings
                <li>
                    <a href="/Manager/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                </li>
                <li>
                    <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>--%>

<%--MY Decision--%>
<div id="UserNavigationMenuDiv" class="col-md-2 userNavMenu">
    <nav class="navbar navbar-fixed-side __scrollBar">
        <div style="background: transparent" class="panel-group" id="accordion" role="tablist"
             aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="true" aria-controls="collapseOne">
                            HR System
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
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
                                        <li><a href="/Manager/Profile"><span class="fa fa-fw fa-info-circle"></span> General information</a></li>
                                        <li><a href="/Manager/Profile/Appointment"><span class="fa fa-fw fa-hand-pointer-o"></span> Appointment record</a></li>
                                        <li><a href="/Manager/Profile/Salary"><span class="fa fa-fw fa-money"></span> Salary details</a></li>
                                        <li><a href="/Manager/Profile/Edu"><span class="fa fa-fw fa-graduation-cap"></span> Education certificate</a></li>
                                        <li><a href="/Manager/Profile/Jobexp"><span class="fa fa-fw fa-briefcase"></span> Job experience</a></li>
                                        <li><a href="/Manager/Profile/Train"><span class="fa fa-fw fa-book"></span> Training record</a></li>
                                        <li><a href="/Manager/Profile/Evaluation"><span class="fa fa-fw fa-bar-chart"></span> Evaluation history </a></li>
                                    </ul>

                                </div>
                            </li>

                            <li>
                                <a href="/Manager/Userslist"><span class="fa fa-fw fa-users"></span> Users</a>
                            </li>
                            <li>
                                <a href="/Manager/Evaluation"><span class="fa fa-fw fa-bar-chart"></span> Evaluation</a>
                            </li>
                            <li>
                                <a href="/Manager/Docs"><span class="fa fa-fw fa-file"></span> Documents</a>
                            </li>
                            <li>
                                <a href="/Hierarchy"><span class="fa fa-line-chart"></span> Hierarchy</a>
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
                                <a href="/Workload"><span class="fa fa-edit fa-fw"></span> Workload</a>
                            </li>
                            <li>
                                <a href="/Projects"><span class="fa fa-file-powerpoint-o fa-fw"></span> Projects</a>
                            </li>
                            <li>
                                <a href="/Monitor"><span class="fa fa-pie-chart fa-fw"></span> Monitor</a>
                            </li>
                            <li>
                                <a href="/Contacts"><span class="fa fa-address-book-o"></span> Contacts</a>
                            </li>
                            <li>
                                <a href="/Appoint"><span class="fa fa-hand-o-up fa-fw"></span> Appoint</a>
                            </li>
                            <li>
                                <a href="/Organizations"><span class="fa fa-briefcase fa-fw"></span> Organizations</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingWF">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseWF"
                           aria-expanded="false" aria-controls="collapseWF">
                            Workflow
                        </a>
                    </h4>
                </div>
                <div id="collapseWF" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingWF">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">

                            <li class="well">
                                <a id="workflowPanelButton" class="linkcolor" role="button" data-toggle="collapse"
                                   href="#collapseNewForm" aria-expanded="true"
                                   aria-controls="collapseNewForm"><span class="glyphicon glyphicon-minus-sign"
                                                                         aria-hidden="true"></span>
                                    New Form
                                </a>
                                <div class="collapse in" id="collapseNewForm">

                                    <ul style="list-style: none" class="list-group">
                                        <li><a href="/Workflow/NewForm/BusinessTripForm"><span
                                                class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
                                            Business trip</a></li>
                                        <li><a href="/Workflow/NewForm/LeaveApproveForm"><span
                                                class="glyphicon glyphicon-calendar" aria-hidden="true"></span> Leave
                                            approve</a>
                                        </li>
                                        <li>
                                            <a href="/Workflow/NewForm/Unformatted"><span class="glyphicon glyphicon-th"
                                                                                          aria-hidden="true"></span>
                                                Unformatted</a>
                                        </li>
                                    </ul>

                                </div>
                            </li>
                            <li class="well">
                                <a id="workflowPanelButton2" class="linkcolor" role="button" data-toggle="collapse"
                                   href="#collapseMyForm" aria-expanded="false"
                                   aria-controls="collapseMyForm"><span class="glyphicon glyphicon-minus-sign"
                                                                        aria-hidden="true"></span>
                                    My Forms
                                </a>
                                <div class="collapse in" id="collapseMyForm">

                                    <ul style="list-style: none" class="list-group">
                                        <li><a href="/Workflow/MyForms/Request"><span class="glyphicon glyphicon-edit"
                                                                                      aria-hidden="true"></span> Request
                                            <span
                                                    class="badge" style="background-color: red"
                                                    id="userRequestNotifHr"></span></a></li>
                                        <li><a href="/Workflow/MyForms/todo/load"><span
                                                class="glyphicon glyphicon-check" aria-hidden="true"></span> To-do <span
                                                class="badge" style="background-color: red"
                                                id="todoNotification"></span></a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingSchedule">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#Nav-accordion"
                           href="#collapseSchedule"
                           aria-expanded="false" aria-controls="collapseSchedule">
                            Schedule
                        </a>
                    </h4>
                </div>
                <div id="collapseSchedule" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSchedule">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">
                            <li>
                                <a href="/ScheduleManagement/main"><span class="fa fa-braille"></span> Calendar</a>
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
                                <a href="/Manager/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <div id="navigationButton" style="visibility: hidden">
        <span id="sarcon" class="glyphicon glyphicon-chevron-right" aria-hidden="false" style="margin-top: 11px; margin-left: 13px; font-size: 20px"></span>
    </div>
</div>

<script>
    $(document).ready(function(){

        $('#navigationButton').click(function () {
            $('#navigationButton').toggleClass('pressed');
            $('#UserNavigationMenuDiv').toggleClass('shownav');
            $('#sarcon').toggleClass('twistIcon');
        });



        $('#detailBody').click(function () {
            if ($('#UserNavigationMenuDiv').hasClass('shownav')) {
                $('#UserNavigationMenuDiv').toggleClass('shownav');
                $('#sarcon').toggleClass('twistIcon');
            }
        })

    });
</script>
<!-- /#sidebar-wrapper -->

<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<%--<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>

<script>
    setInterval(function () {
        $.ajax({
            type:"GET",
            processData: false,
            url : '${pageContext.request.contextPath}/Workflow/MyForms/todo/notification',
            success: function(data){
                $('#ctoNotif').text(data);
            },
            error: function () {
            }
        });
    }, 5000);

    setInterval(function () {
        $.ajax({
            type:"GET",
            processData: false,
            url : '${pageContext.request.contextPath}/Workflow/MyForms/Request/Notification',
            success: function(data){
                $('#userRequestNotifCto').text(data);
            },
            error: function () {
            }
        });
    }, 5000);
</script>
