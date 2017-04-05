<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%--
  Created by IntelliJ IDEA.
  User: JAS SHAYKHOV
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Error"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<script>
    $(document).ready(function () {
        $('#mainHeaderDiv').hide();
    })
</script>

<style>
    .btn3d {
        transition:all .08s linear;
        position:relative;
        outline:medium none;
        -moz-outline-style:none;
        border:0px;
        margin-right:10px;
        margin-top:15px;
    }
    .btn3d:focus {
        outline:medium none;
        -moz-outline-style:none;
    }
    .btn3d:active {
        top:9px;
    }
    .btn-default {
        box-shadow:0 0 0 1px #ebebeb inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #adadad, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
        background-color:#fff;
    }
</style>
<div class="mainBodyBlock" style="width: 66%; margin-left: auto">
    <img style="width: 70%; height: 50%; margin-left: -70px" src="/resources/images/404.png">
    <h1>Sorry, something went wrong!</h1>
    <h2>Please, contact to our developers</h2>
    <h3>
        <button style="width: 53%" class="btn btn-default btn-lg btn3d" type="button" name="back" onclick="history.back()">Back</button>
    </h3>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>