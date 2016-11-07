<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 07-Nov-16
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Change password"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Change password</h1>
            <br/>
            <div class="form-horizontal">
                <form action="<c:url value='/user_changepass' />" method='POST'>
                    <div class="form-group">
                        <label class="control-label col-md-3">Old password <font color='red'>*</font></label>
                        <div class="col-md-5">
                            <input type="password" name="Oldpassword" placeholder="Old Password" required="required" class="form-control text-box single-line" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Password <font color='red'>*</font></label>
                        <div class="col-md-5">
                            <input type="password" name="password" placeholder="Password" required="required" class="form-control text-box single-line" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Repeat password <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <input type="password" name="Repeatpassword" placeholder="Repeat Password" required="required" class="form-control text-box single-line" />
                    </div>
                    </div>
                    <div class="form-group">
                        <div class="control-label col-md-3"></div>
                    <div class="col-md-5">
                    <button type="submit" class="btn btn-primary btn-block">Change password</button>
                    </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>