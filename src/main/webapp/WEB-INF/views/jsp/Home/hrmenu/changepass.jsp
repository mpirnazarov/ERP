<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>

<%--
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
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Change password</h1>
            <br/>
            <div class="form-horizontal">
                <%--<form action="<c:url value='/user_changepass' />" method='POST'>
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
                </form>--%>
                    <form:form modelAttribute="changepassVM" cssClass="form-horizontal" method="post" action="/user_changepass">

                        <div class="form-group">
                            <label class="control-label col-md-3">Old password <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:password path="oldPassword" placeholder="Old Password"
                                               cssClass="form-control text-box single-line"/>
                                <form:errors path="OldPassword" cssClass="error field-validation-error"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">Password <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:password path="password" placeholder="Password"
                                               cssClass="form-control text-box single-line"/>
                                <form:errors path="Password" cssClass="error field-validation-error"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">Repeat password <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:password path="repeatPassword" placeholder="Repeat Password"
                                               cssClass="form-control text-box single-line"/>
                                <form:errors path="RepeatPassword" cssClass="error field-validation-error"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input type="submit" value="Change password" class="btn btn-default"/>
                                <input type="button" onclick="location.href='/User/changepass'" value="Cancel"
                                       class="btn btn-default"/>
                            </div>
                        </div>
                    </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#changepassVM").validate({
            rules: {
                'password': {
                    required: true,
                    minlength: 5
                },
                'repeatPassword': {
                    required: true,
                    equalTo: "#password"
                },
            },
            messages: {
                'repeatPassword': {
                    equalTo: "Your passwords do not match"
                }
            }
        });
    })
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>