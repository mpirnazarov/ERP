<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>

    <%--Css--%>
    <spring:url value="/resources/core/css/normalize.css" var="normalizeCss"/>
    <spring:url value="/resources/core/css/style.css" var="styleCss"/>
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss"/>
    <spring:url value="/resources/core/css/navbar-fixed-side.css" var="navbar"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapminCss"/>
    <spring:url value="/resources/core/css/datatablesCombined.min.css" var="allInOneCss"/>
    <spring:url value="/resources/core/css/jquery.scrollbar.css" var="scrollCss"/>
    <spring:url value="/resources/core/css/bootstrap-datepicker3.standalone.css" var="bootstrapDatePickerCSS"/>
    <spring:url value="/resources/core/css/token-input.css" var="tokenInputCss"/>
    <spring:url value="/resources/core/css/token-input-facebook.css" var="tokenInputFacebookCss"/>
    <spring:url value="/resources/core/css/pace_barber.css" var="paceCSS"/>


    <link rel="stylesheet" href="${navbar}"/>
    <link rel="stylesheet" href="${scrollCss}"/>
    <link rel="stylesheet" href="${normalizeCss}"/>
    <link rel="stylesheet" href="${bootstrapminCss}"/>
    <link rel="stylesheet" type="text/css" href="${allInOneCss}"/>
    <link rel="stylesheet" href="${tokenInputCss}" type="text/css"/>
    <link rel="stylesheet" href="${tokenInputFacebookCss}" type="text/css"/>
    <link rel="stylesheet" href="${styleCss}"/>
    <link rel="stylesheet" href="${bootstrapDatePickerCSS}"/>


    <%--JS--%>
    <spring:url value="/resources/core/js/jquery.min.js" var="jquery"/>
    <%--<spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation"/>--%>
    <spring:url value="/resources/core/js/datatablesCombined.min.js" var="allInOneJs"/>
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/resources/core/js/jquery.slimscroll.min.js" var="slimScroll"/>
    <spring:url value="/resources/core/js/jquery.scrollbar.min.js" var="scrollJs"/>
    <spring:url value="/resources/core/js/main.js" var="main"/>
    <spring:url value="/resources/core/js/bootstrap-datepicker.min.js" var="bootstrapDatePickerJS"/>
    <spring:url value="/resources/core/js/common-hr.js" var="commonHrJs"/>
    <spring:url value="/resources/core/js/pace.js" var="paceJs"/>
    <spring:url value="/resources/core/js/notify.min.js" var="notifyJs"/>


    <script src="${jquery}"></script>
    <script type="text/javascript" src="${allInOneJs}"></script>
    <script src="${main}"></script>
    <script src="${scrollJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${bootstrapDatePickerJS}"></script>
    <script src="${commonHrJs}"></script>
    <script src="${notifyJs}"></script>

    <link rel="icon" type="image/x-icon" href="<s:url value="/resources/images/SmartOffice-Logo.png"/>"/>

<%--
    <link rel="stylesheet" href="${paceCSS}"/>
    <script src="${paceJs}"></script>--%>


    <%--Cloud JQ UI--%>
    <%-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">--%>


    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<div id="sarOv">
    <div>
        <%--<img class="sarspinner" src="/images/lg-logo.jpg">--%>
        <%--<div class="sarspinnerBorder"></div>--%>

            <div style="width: 100px; height: 100px; position: absolute; left: 48%; top: 32%; font-size: 10px; text-align: center">
                <div class="loader"></div>
            </div>
    </div>
</div>

<%--<div id="restrictOv">
    <div style="width: 100px; height: 100px; position: absolute; left: 40%; top: 31%; font-size: 10px; text-align: center">
        <img style="width: 100%; height: 100%" src="/resources/images/wh.png">Smart office does not support mobile
        version of web site!
    </div>
</div>--%>


<script>

   /* mainCommonAjax("GET","/Workflow/MyForms/Request/Notification","",true,"getNotificationsRequest");*/


    /*load Ovrelay*/
    var sarOv = document.getElementById("sarOv");
    var resOv = document.getElementById("restrictOv");
    var unav = document.getElementById("usernavigationmenudiv");
    var userNavBar = $('.userNavMenu');

    window.addEventListener('load', function () {

        userNavBar.hide()
        sarOv.style.animation = 'fadeout 0.4s ease';
        setTimeout(function () {
            userNavBar.show()
            sarOv.style.display = 'none';
        }, 300)
    });
    /*load Ovrelay*/


    /*Resize Prevent*/
    $(window).resize(function () {
        if ($(this).width() < 1000) {

            resOv.style.display = 'block';

        } else {
            resOv.style.display = 'none';

        }
    });
    /*Resize Prevent*/


</script>

<%--Checking if Hierarchy page, if yes call init()--%>
    <% if(request.getAttribute("Hierarchy")!=null) { %>
<body class="__scrollBar" onload="init()">
    <% } else { %>
<body class="__scrollBar">
    <% } %>
    <%
    if((ProfileViewModel) request.getAttribute("userProfile")!=null){
        ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
        request.setAttribute("FullName", a.getFirstName()[0] + " " + a.getLastName()[0]);
        request.setAttribute("SystemRole", a.getRoleId());
        request.setAttribute("FirstName", a.getFirstName()[0]);
        if(a.getJobTitle()==null)
            request.setAttribute("JobTitle", "");
        else
            request.setAttribute("JobTitle", a.getJobTitle());

        if (a.getExternal()==null)
            request.setAttribute("External", "");
        else
            request.setAttribute("External", a.getExternal());
        request.setAttribute("userId", a.getId());
    }
%>
<div class="container-fluid">
    <div id="headerRow" class="row">
            <%
            if(request.getAttribute("SystemRole")!=null || request.getAttribute("mode")!=null){
                String pageType = "/WEB-INF/views/jsp/shared/erpUserLayout.jsp";
                if ((int) (request.getAttribute("SystemRole")) == 1)
                    pageType =  "/WEB-INF/views/jsp/shared/erpCTOLayout.jsp";
                else if ((int) (request.getAttribute("SystemRole")) == 3)
                    pageType =  "/WEB-INF/views/jsp/shared/erpHRLayout.jsp";
                if(request.getAttribute("Mode") != null){
                    if(((int)request.getAttribute("Mode"))==1)
                        pageType =  "/WEB-INF/views/jsp/shared/erpViewLayout.jsp";
                    else if(((int)request.getAttribute("Mode"))==2)
                        pageType =  "/WEB-INF/views/jsp/shared/erpEditLayout.jsp";
                }
                pageContext.setAttribute("pageType", pageType);
            }
        %>
        <jsp:include page='${pageType}' flush="true"/>
        <script>

            var hrbutton = $("#collapseOne");
            var taapsbutton = $("#collapseTwo");
            var wfbutton = $("#collapseWF");
            var profilebutton = $("#collapseExample");
            var schedulebutton = $("#collapseSchedule");

            var currentOpenedPanel = 0;

            $(document).ready(function () {


                if (window.location.href.indexOf("Workflow") > -1) {
                    wfbutton.addClass("in")
                    hrbutton.removeClass("in")
                } else if (window.location.href.indexOf("Profile") > -1) {
                    hrbutton.addClass("in")
                    wfbutton.removeClass("in")
                    taapsbutton.removeClass("in")
                    profilebutton.addClass("in")

                } else if (window.location.href.indexOf("Hr") > -1) {
                    hrbutton.addClass("in")
                    wfbutton.removeClass("in")
                } else if (window.location.href.indexOf("Workload") > -1
                    || window.location.href.indexOf("Projects") > -1
                    || window.location.href.indexOf("Monitor") > -1
                    || window.location.href.indexOf("Roles") > -1
                    || window.location.href.indexOf("Contacts") > -1
                    || window.location.href.indexOf("Organizations") > -1
                    || window.location.href.indexOf("Appoint") > -1
                    || window.location.href.indexOf("Customer") > -1) {
                    hrbutton.removeClass("in")
                    wfbutton.removeClass("in")
                    taapsbutton.addClass("in")
                }else if(window.location.href.indexOf("ScheduleManagement") > -1){
                    hrbutton.removeClass("in")
                    wfbutton.removeClass("in")
                    taapsbutton.removeClass("in")
                    schedulebutton.addClass("in")
                }else {
                    /* def*/
                }
            })

            var newFormButton = $("#workflowPanelButton");
            var myFormButton = $("#workflowPanelButton2");

            newFormButton.click(function () {

                if ($("a#workflowPanelButton span").hasClass("glyphicon-plus-sign")) {
                    $("a#workflowPanelButton span").removeClass("glyphicon-plus-sign");
                    $("a#workflowPanelButton span").addClass("glyphicon-minus-sign");
                } else if ($("a#workflowPanelButton span").hasClass("glyphicon-minus-sign")) {
                    $("a#workflowPanelButton span").removeClass("glyphicon-minus-sign");
                    $("a#workflowPanelButton span").addClass("glyphicon-plus-sign");
                }
            })

            myFormButton.click(function () {

                if ($("a#workflowPanelButton2 span").hasClass("glyphicon-plus-sign")) {
                    $("a#workflowPanelButton2 span").removeClass("glyphicon-plus-sign");
                    $("a#workflowPanelButton2 span").addClass("glyphicon-minus-sign");
                } else if ($("a#workflowPanelButton span").hasClass("glyphicon-minus-sign")) {
                    $("a#workflowPanelButton2 span").removeClass("glyphicon-minus-sign");
                    $("a#workflowPanelButton2 span").addClass("glyphicon-plus-sign");

                }
            })



        </script>

        <%--Header div--%>

        <div id="mainHeaderDiv">
            <a href="/">
                <img border="0" alt="" src="/resources/images/SmartOffice-Logo-Full.png" class="smartOfficeLogo">
            </a>

            <%--<iframe scrolling="no" src="https://dollaruz.net/" style="border: 0px none; margin-left: -185px; height: 591px; margin-top: -887px; width: 377px; position: absolute; top: 415px; z-index: 400; left: 54%" frameborder="0"></iframe>--%>

            <div onclick="location.href='/logout';" class="btn btn-darkred logoutButton"><span
                    class="fa fa-fw fa-power-off"></span>Logout
            </div>
            <div style="float: right; margin-top: 10px; padding-right: 10px">
                <img src="/image/<%= request.getAttribute("userId") %>.jpg"
                     onerror="this.src='/resources/images/ppicture.png'" class="userImgBox-small">
                <%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
            </div>
            <%--Tabs--%>
            <div class="head-nav-group">
                <div class="head-nav-tab" onclick="location.href = '/Workflow/MyForms/Request'">
                    <span class="fa fa-pencil-square-o head-icon"></span>
                    <p class="head-text">Requests</p>
                    <div id="head-nav-counter-request" class="head-nav-counter">

                    </div>
                </div>
                <div class="head-nav-tab" onclick="location.href = '/Workflow/MyForms/todo/load'">
                    <span class="fa fa-check-square-o head-icon"></span>
                    <p class="head-text">To-Do</p>
                    <div id="head-nav-counter-todo" class="head-nav-counter">

                    </div>
                </div>
                <div class="head-nav-tab" onclick="location.href = '/ScheduleManagement/main'">
                    <span class="fa fa-calendar head-icon"></span>
                    <p class="head-text">Scheduler</p>
                    <div id="head-nav-counter-scheduler" class="head-nav-counter">

                    </div>
                </div>
            </div>
        </div>

                <script>
                    getNotificationsFromAjax();
                </script>


        <%--Header div END--%>



