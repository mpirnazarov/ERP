<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="pageTitle" scope="request" value="Home"/>--%>
<%
    request.setAttribute("Mode", 2);
    request.setAttribute("EditAdd", 1);
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
        <form:form commandName="person" cssClass="form-horizontal" method="post">

            <h2 class="headerText">Personal info edit</h2>
            <div class="form-group"><label class="control-label col-md-4">Home phone: </label>
                <div class="col-lg-5">
                    <form:input path="homePhone" placeholder="998971234546"
                                value="${person.homePhone}"
                                type="tel" pattern="[0-9]{12}"
                                title="Phone Number with 12 digits (998971112233)"
                                cssClass="form-control single-line"/>
                        <%--<form:errors path="personalInfo.homePhone" cssClass="error field-validation-error"/>--%>
                </div>
            </div>
            <div class="form-group"><label class="control-label col-md-4">Mobile phone: </label>
                <div class="col-lg-5">
                    <form:input path="mobilePhone" placeholder="998971234546"
                                value="${person.mobilePhone}"
                                type="tel" pattern="[0-9]{12}"
                                title="Phone Number with 12 digits (998971112233)"
                                cssClass="form-control single-line"/>
                        <%--<form:errors path="personalInfo.PassportNumber" cssClass="error field-validation-error"/>--%>
                </div>
            </div>
            <div class="form-group"><label class="control-label col-md-4">E-mail (company): <font
                    color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:input path="eMail" placeholder="test@lgcns.uz"
                                value="${person.eMail}" type="email"
                                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                                cssClass="form-control single-line"/>
                        <%--<form:errors path="personalInfo.emailCompany" cssClass="error field-validation-error"/>--%>
                </div>
            </div>
            <div class="form-group"><label class="control-label col-md-4">E-mail (personal): <font
                    color='red'>*</font></label>
                <div class="col-lg-5">
                    <form:input path="personalEmail" placeholder="test@lgcns.uz"
                                value="${person.personalEmail}"
                                cssClass="form-control single-line"/>
                        <%--<form:errors path="personalInfo.emailPersonal" cssClass="error field-validation-error"/>--%>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-9">
                    <input type="submit" value="Save" class="btn btn-green"/>
                    <input type="button" onclick="history.back()" value="Cancel"
                           class="btn btn-red"/>
                </div>
            </div>
        </form:form>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
