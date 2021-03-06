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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Change picture"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">
        <h1>${fullName}, ${jobTitle}</h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;">${external}</p>
        <h2 class="headerText">Change profile picture</h2>
        <form method="post" enctype="multipart/form-data">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Choose picture: </label>
                    <div class="col-md-5">
                        <input type="file" class="single-line" id="choose" name="fileUpload" style="display: none;"
                               size="50"/>
                        <button type="button" data-target="choose" class="btn btn-grey filingbutton col-sm-9">Choose file
                        </button>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <input class="btn btn-green" type="submit" value="Upload"/>
                        <input class="btn btn-red" type="button" onclick="history.back()" value="Cancel"/>
                    </div>
                </div>
            </div>
        </form>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>