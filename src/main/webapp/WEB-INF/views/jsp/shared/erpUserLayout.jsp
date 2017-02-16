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

<%--Old Version--%>
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
                    <!--Profile picture-->

                    &lt;%&ndash;<img src="/resources/images/ppicture.png" style="width:45%; margin-left: 25%"><br>&ndash;%&gt;
                    <div class="userImgBox">
                        <img class="userimg" src="/image/<%= request.getAttribute("userId") %>.jpg" onerror="this.src='/resources/images/ppicture.png'">
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
                        <a href="/Projects/ProjectHistory"><i class="fa fa-fw fa-folder"></i> Project history</a>
                    </li>
                    <li>
                        <a href="/User/Profile/Evaluation"><i class="fa fa-fw fa-bar-chart"></i> Evaluation history</a>
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
                        <a href="/User/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
                    </li>
                    <li>
                        <a href="/logout"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
    <!-- /#sidebar-wrapper -->
</div>--%>

<%--MY Decision--%>
<div id="UserNavigationMenuDiv" class="col-md-2 userNavMenu">
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
                                <a href="/Projects/ProjectHistory"><i class="fa fa-fw fa-folder"></i> Project history</a>
                            </li>
                            <li>
                                <a href="/User/Profile/Evaluation"><i class="fa fa-fw fa-bar-chart"></i> Evaluation history</a>
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
                                <a href="/Roles"><i class="fa fa-male fa-fw"></i> Roles</a>
                            </li>
                            <li>
                                <a href="/Appoint"><i class="fa fa-hand-o-up fa-fw"></i> Appoint</a>
                            </li>
                            <li>
                                <a href="/Customers"><i class="fa fa-briefcase fa-fw"></i> Customers</a>
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

                            <li>
                                <a class="linkcolor" role="button" data-toggle="collapse"
                                   href="#collapseNewForm" aria-expanded="false"
                                   aria-controls="collapseNewForm"><span class="glyphicon glyphicon-edit"
                                                                         aria-hidden="true"></span>
                                    New Form
                                </a>
                                <div class="collapse" id="collapseNewForm">

                                    <ul style="list-style: none" class="list-group">
                                        <li><a class="linkcolor" href="/Workflow/NewForm/BusinessTripForm">Business trip</a></li>
                                        <li><a class="linkcolor" href="/Workflow/NewForm/LeaveApproveForm">Leave approve</a>
                                        </li>
                                        <li><a class="linkcolor" href="/Workflow/Create/Unformatted">Unformatted</a>
                                        </li>
                                    </ul>

                                </div>
                            </li>
                            <li>
                                <a class="linkcolor" role="button" data-toggle="collapse"
                                   href="#collapseMyForm" aria-expanded="false"
                                   aria-controls="collapseMyForm"><span class="glyphicon glyphicon-check"
                                                                        aria-hidden="true"></span>
                                    My Forms
                                </a>
                                <div class="collapse" id="collapseMyForm">

                                    <ul style="list-style: none" class="list-group">
                                        <li><a href="/Workflow/MyForms/Request">Request <span
                                                class="badge" id="userReqNotifSpan"></span></a></li>
                                        <li><a href="/Workflow/MyForms/todo/load">To-do <span
                                                class="badge" style="background-color: red" id="userTodoSpan"></span></a>
                                        </li>
                                        <li><a href="/Workflow/MyForms/Details">Details</a>
                                        </li>

                                    </ul>

                                </div>
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
                                <a href="/User/changepass"><i class="fa fa-fw fa-gears"></i> Change password</a>
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
<script>
    setInterval(function () {
        $.ajax({
            type:"GET",
            processData: false,
            url : '${pageContext.request.contextPath}/Workflow/MyForms/todo/notification',
            success: function(data){
                $('#userTodoSpan').text(data);
            },
            error: function () {
            }
        });
    }, 3000);
    setInterval(function () {
        $.ajax({
            type:"GET",
            processData: false,
            url : '${pageContext.request.contextPath}/Workflow/MyForms/Request/Notification',
            success: function(data){
                $('#userReqNotifSpan').text(data);
            },
            error: function () {
            }
        });
    }, 3000);
</script>