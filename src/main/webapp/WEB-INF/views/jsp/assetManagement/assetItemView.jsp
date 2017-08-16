<%--
  Created by IntelliJ IDEA.
  User: Sarvar
  Date: 23.06.2017
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/commonHead.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container col-lg-10 col-lg-offset-1 col-sm-12">

    <div class="headerDiv">
        <div class="logoDiv" onclick="location='/login'"></div>
        <hr>
        <h1>Asset Management</h1>
    </div>
    <div class="row">
        <div class="itemSearchDiv col-lg-12 col-sm-12">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Enter asset code.." aria-describedby="basic-addon2">
                <span class="input-group-addon" id="basic-addon2"><i class="fa fa-search" aria-hidden="true"></i></span>
            </div>
        </div>
        <div id="itemDesc" class="col-lg-6 col-sm-12" style="border:1px solid red">
            <div class="input-group">
                <span class="input-group-addon" id="itemIdLabel">ID: </span>
                <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="itemNameLabel">Name: </span>
                <input type="text" class="form-control" placeholder="Username" aria-describedby="itemNameLabel">
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="itemTypeLabel">Type: </span>
                <input type="text" class="form-control" placeholder="Username" aria-describedby="itemTypeLabel">
            </div>
            <span>ID: </span><p id="itemId">028</p>
            <p><span>Name: </span> Asus Rogue</p>
            <p><span>Type: </span> Laptop</p>
        </div>
        <div id="itemPic" class="col-lg-6 col-sm-12 centered">
            <img src="http://lorempixel.com/output/technics-q-c-640-480-3.jpg" alt="LapTop" class="img-thumbnail" width="300" height="300">
        </div>


    </div>
    </div>
</body>
</html>
