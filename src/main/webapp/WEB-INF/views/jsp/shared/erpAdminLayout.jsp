<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    Welcome ${username}!
                </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-folder"></i> Manage users</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-file-o"></i> Workload</a>
            </li>
            <li>
                <a href="/User/Profile/Edu"><i class="fa fa-fw fa-cog"></i> Education certificate</a>
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
                <a href="/User/Profile/Jobexp"><i class="fa fa-fw fa-bank"></i> Job experience</a>
            </li>
            <li>
                <a href="/User/Profile/Train"><i class="fa fa-fw fa-blank"></i> Training record</a>
            </li>
        </ul>
    </nav>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


