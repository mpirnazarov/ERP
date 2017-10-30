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
                        <li class="dropdown-header">Information about HR</li>
                        <li><a href="/Hr/Profile">General information</a></li>
                        <li><a href="/Hr/Profile/Appointment">Appointment record</a></li>
                        <li><a href="/Hr/Profile/Salary">Salary details</a></li>
                        <li><a href="/Hr/Profile/Edu">Education certificate</a></li>
                        <li><a href="/Hr/Profile/Jobexp">Job experience</a></li>
                        <li><a href="/Hr/Profile/Train">Training record</a></li>
                    </ul>
                </li>
                <li>
                    <a href="/Hr/Userslist"><i class="fa fa-fw fa-users"></i> Users</a>
                </li>

                <li>
                    <a href="/Hr/Profile/Evaluation"><i class="fa fa-fw fa-bar-chart"></i> Evaluation history</a>
                </li>
                <li>
                    <a href="/Hr/GenerateDocs"><i class="fa fa-fw fa-file"></i> Generate documents</a>
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
                    <a href="/Hr/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                </li>
                <li>
                    <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</div>--%>
<!-- /#sidebar-wrapper -->
<style>
    .quick-btn .label {
        position: absolute;
        top: -5px;
        right: -5px;
    }

    .btn-metis-4 {
        color: #ffffff;
        background-color: #a264e7;
        border-color: #62309a;
    }


</style>



<%--MY Decision--%>
<div id="UserNavigationMenuDiv" class="userNavMenu">
    <nav class="navbar navbar-fixed-side __scrollBar">
        <div class="panel-group" id="Nav-accordion" role="tablist"
             aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#Nav-accordion" href="#collapseOne"
                           aria-expanded="true" aria-controls="collapseOne">
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
                                   aria-controls="collapseExample"><span class="fa fa-user-circle"></span> My profile
                                </a>
                                <div class="collapse" id="collapseExample">

                                    <ul style="list-style: none" class="list-group">
                                        <li><a href="/Hr/Profile"><span class="fa fa-fw fa-info-circle"></span> General information</a></li>
                                        <li><a href="/Hr/Profile/Appointment"><span class="fa fa-fw fa-hand-pointer-o"></span> Appointment record</a>
                                        </li>
                                        <li><a href="/Hr/Profile/Salary"><span class="fa fa-fw fa-money"></span> Salary details</a>
                                        </li>
                                        <li><a href="/Hr/Profile/Edu"><span class="fa fa-fw fa-graduation-cap"></span> Education
                                            certificate</a></li>
                                        <li><a href="/Hr/Profile/Jobexp"><span class="fa fa-fw fa-briefcase"></span> Job experience</a>
                                        </li>
                                        <li><a href="/Hr/Profile/Train"><span class="fa fa-fw fa-book"></span> Training record</a>
                                        </li>
                                    </ul>

                                </div>
                            </li>

                            <li>
                                <a class="linkcolor" href="/Hr/Userslist"><span class="fa fa-fw fa-users"></span> Users</a>
                            </li>
                            <li>
                                <a class="linkcolor" href="/Hr/Profile/Evaluation"><span class="fa fa-fw fa-bar-chart"></span>
                                    Evaluation</a>
                            </li>
                            <li>
                                <a class="linkcolor" href="/Hr/GenerateDocs"><span class="fa fa-fw fa-file"></span> Documents</a>
                            </li>
                            <li>
                                <a class="linkcolor" href="/Hierarchy"><span class="fa fa-line-chart"></span> Hierarchy</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#Nav-accordion"
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
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#Nav-accordion"
                           href="#collapseWF"
                           aria-expanded="false" aria-controls="collapseWF">
                            Workflow
                        </a>
                    </h4>
                </div>
                <div id="collapseWF" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingWF">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">
                            <li>
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
                            </li>

                        </ul>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingAM">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#Nav-accordion"
                           href="#collapseAM"
                           aria-expanded="false" aria-controls="collapseAM">
                            Asset Management
                        </a>
                    </h4>
                </div>
                <div id="collapseAM" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingAM">
                    <div class="panel-body">
                        <ul style="list-style: none" class="list-group">
                            <li>
                                <ul style="list-style: none" class="list-group">
                                    <li><a href="/AssetManagement/main"><span
                                            class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
                                        Manage Assets</a></li>
                                    <li><a href="/Asset/Category"><span
                                            class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create Asset Category</a>
                                    </li>
                                    <li>
                                        <a href="/Asset/Owner"><span class="glyphicon glyphicon-transfer"
                                                                                      aria-hidden="true"></span>
                                            Manage Asset Owners</a>
                                    </li>
                                    <li>
                                        <a href="/Asset/MyAsset"><span class="glyphicon glyphicon-hdd"
                                                                     aria-hidden="true"></span>
                                            My Assets</a>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#Nav-accordion"
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
            </div>
        </div>
    </nav>

    <div id="navigationButton" style="visibility: hidden">
        <span id="sarcon" class="glyphicon glyphicon-chevron-right" aria-hidden="false"
              style="margin-top: 11px; margin-left: 13px; font-size: 20px"></span>
    </div>

</div>


<script>
    $(document).ready(function () {

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
        });
    });
</script>

<%--<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<%--<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>


