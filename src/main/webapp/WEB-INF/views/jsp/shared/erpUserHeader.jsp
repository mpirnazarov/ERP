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


    <link rel="stylesheet" href="${navbar}"/>
    <link rel="stylesheet" href="${scrollCss}"/>
    <link rel="stylesheet" href="${normalizeCss}"/>
    <link rel="stylesheet" href="${bootstrapminCss}"/>
    <link rel="stylesheet" type="text/css" href="${allInOneCss}"/>
    <link rel="stylesheet" href="${tokenInputCss}" type="text/css" />
    <link rel="stylesheet" href="${tokenInputFacebookCss}" type="text/css" />
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

    <script src="${jquery}"></script>
    <script type="text/javascript" src="${allInOneJs}"></script>
    <script src="${main}"></script>
    <script src="${scrollJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${bootstrapDatePickerJS}"></script>

    <link rel="icon" type="image/x-icon" href="<s:url value="/resources/images/favicon.ico"/>"/>


    <%--Cloud JQ UI--%>
   <%-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">--%>




    <style>

        #UserNavigationMenuDiv {

            background-color: rgb(76, 106, 108);
        }


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

        .panel {
            width: 246px;
        }


        #workflowPanelButton {
            font-family: 'Oswald', sans-serif;
            color: #cfd8d8;;
        }

        #workflowPanelButton2 {
            font-family: 'Oswald', sans-serif;
            color: #cfd8d8;;
        }

        #collapseNewForm a {
            color: #cfd8d8;;
            padding-left: 22px;
        }

        #collapseMyForm a{
            color: #cfd8d8;;
            padding-left: 22px;
        }

        #workflowPanelButton:hover, #workflowPanelButton2:hover, #collapseNewForm a:hover, #collapseMyForm a:hover{
            color: #2aabd2;
        }

        .list-group {
            margin-bottom: 2%;
            margin-top: 2%;
        }



        .well {
            padding: 0;
            margin-bottom: 10px;
            background-color: transparent;

            border: none;


        }


    </style>
</head>


<div id="sarOv">
    <div>
        <%--<img class="sarspinner" src="/images/lg-logo.jpg">--%>
        <div class="sarspinnerBorder"></div>
    </div>
</div>

<div id="restrictOv"><div style="width: 100px; height: 100px; position: absolute; left: 40%; top: 31%; font-size: 10px; text-align: center"><img style="width: 100%; height: 100%" src="/resources/images/wh.png">Smart office does not support mobile version of web site!</div></div>



<script>



    /*load Ovrelay*/
    var sarOv = document.getElementById("sarOv");
    var resOv = document.getElementById("restrictOv");
    var unav = document.getElementById("usernavigationmenudiv");

    window.addEventListener('load', function () {
        sarOv.style.animation = 'fadeout 0.3s ease';
        setTimeout(function (){sarOv.style.display = 'none';}, 300)
    })
    /*load Ovrelay*/


    /*Resize Prevent*/
        $(window).resize(function() {
            if ($(this).width() < 1000){

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

    var currentOpenedPanel = 0;

    $(document).ready(function () {



        if(window.location.href.indexOf("Workflow") > -1) {
            wfbutton.addClass("in")
            hrbutton.removeClass("in")
        }else if(window.location.href.indexOf("Profile") > -1){
            hrbutton.addClass("in")
            wfbutton.removeClass("in")
            taapsbutton.removeClass("in")
            profilebutton.addClass("in")

        }else if(window.location.href.indexOf("Hr") > -1) {
            hrbutton.addClass("in")
            wfbutton.removeClass("in")
        }else if(window.location.href.indexOf("Workload") > -1
              || window.location.href.indexOf("Projects") > -1
              || window.location.href.indexOf("Monitor") > -1
              || window.location.href.indexOf("Roles") > -1
              || window.location.href.indexOf("Appoint") > -1
              || window.location.href.indexOf("Customer") > -1) {
            hrbutton.removeClass("in")
            wfbutton.removeClass("in")
            taapsbutton.addClass("in")
        }else {
           /* def*/
        }
    })

    var newFormButton = $("#workflowPanelButton");
    var myFormButton = $("#workflowPanelButton2");

    newFormButton.click(function () {

        if ($("a#workflowPanelButton span").hasClass("glyphicon-plus-sign")){
            $("a#workflowPanelButton span").removeClass("glyphicon-plus-sign");
            $("a#workflowPanelButton span").addClass("glyphicon-minus-sign");
        }else {
            $("a#workflowPanelButton span").addClass("glyphicon-plus-sign");
            $("a#workflowPanelButton span").removeClass("glyphicon-minus-sign");
        }
    })

    myFormButton.click(function () {

        if ($("a#workflowPanelButton2 span").hasClass("glyphicon-plus-sign")){
            $("a#workflowPanelButton2 span").removeClass("glyphicon-plus-sign");
            $("a#workflowPanelButton2 span").addClass("glyphicon-minus-sign");
        }else {
            $("a#workflowPanelButton2 span").addClass("glyphicon-plus-sign");
            $("a#workflowPanelButton2 span").removeClass("glyphicon-minus-sign");
        }
    })





</script>