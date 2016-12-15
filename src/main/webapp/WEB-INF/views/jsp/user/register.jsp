<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 03.10.2016
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="User Register"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <h1 class="page-header">New User Registration</h1>
                <form:form modelAttribute="registrationVM" cssClass="form-horizontal" method="post"
                           action="/Hr/Register">

                    <div class="tab-content">
                        <ul class="nav nav-tabs">
                            <c:forEach items="${registrationVM.registrationLocInfos}" var="locInfo" varStatus="i">
                                <li ${i.first ? 'class="active"' : ''}>
                                    <a data-toggle="tab"
                                       href="#<c:out value="${locInfo.languageCode}"/>"
                                       id="<c:out value="${locInfo.languageId}"/>">${locInfo.languageCode}</a>
                                </li>

                                <c:if test="${i.first}">
                                    <c:set var="firstLang" scope="request" value="${locInfo.languageId}"/>
                                </c:if>
                                <c:if test="${i.last}">
                                    <c:set var="secondLang" scope="request" value="${locInfo.languageId}"/>
                                </c:if>
                            </c:forEach>
                        </ul>
                        <c:forEach items="${registrationVM.registrationLocInfos}" var="locInfo" varStatus="i">
                            <form:hidden path="registrationLocInfos[${i.index}].languageId"
                                         name="registrationVM.registrationLocInfos[${i.index}].languageId"
                                         value="${locInfo.languageId}"/>

                            <form:hidden path="registrationLocInfos[${i.index}].languageCode"
                                         name="registrationVM.registrationLocInfos[${i.index}].languageCode"
                                         value="${locInfo.languageCode}"/>
                            <div id="<c:out value="${locInfo.languageCode}"/>" class="tab-pane fade in active"
                                 style="border: 1px solid #fff;">
                                <br/>
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-md-5"><p>${locInfo.languageCode}</p></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Last name <font
                                                color='red'>*</font></label>
                                        <div class="col-md-5">
                                            <form:input path="registrationLocInfos[${i.index}].lastName"
                                                        name="registrationVM.registrationLocInfos[${i.index}].lastName"
                                                        required="required" placeholder="Last Name"
                                                        cssClass="form-control text-box single-line"/>
                                            <form:errors path="RegistrationLocInfos[${i.index}].LastName"
                                                         cssClass="error field-validation-error"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">First name<font
                                                color='red'>*</font></label>
                                        <div class="col-md-5">
                                            <form:input path="registrationLocInfos[${i.index}].firstName"
                                                        name="registrationVM.registrationLocInfos[${i.index}].firstName"
                                                        required="required" placeholder="First Name"
                                                        cssClass="form-control text-box single-line"/>
                                            <form:errors path="RegistrationLocInfos[${i.index}].FirstName"
                                                         cssClass="error field-validation-error"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3">Father's name </label>
                                        <div class="col-md-5">
                                            <form:input path="registrationLocInfos[${i.index}].FathersName"
                                                        name="registrationVM.registrationLocInfos[${i.index}].fathersName"
                                                        placeholder="Father's name"
                                                        cssClass="form-control text-box single-line"/>
                                            <form:errors path="RegistrationLocInfos[${i.index}].FathersName"
                                                         cssClass="error field-validation-error"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">Address <font
                                                color='red'>*</font></label>
                                        <div class="col-md-5">
                                            <form:textarea path="registrationLocInfos[${i.index}].Address"
                                                           name="registrationVM.registrationLocInfos[${i.index}].address"
                                                           required="required" placeholder="Home address"
                                                           cssClass="form-control text-box" rows="3" cols="5"/>
                                            <form:errors path="RegistrationLocInfos[${i.index}].Address"
                                                         cssClass="error field-validation-error"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3">Birth place </label>
                                        <div class="col-md-5">
                                            <form:input path="registrationLocInfos[${i.index}].birthPlace"
                                                        name="registrationVM.registrationLocInfos[${i.index}].birthPlace"
                                                        placeholder="Birth place"
                                                        cssClass="form-control text-box single-line"/>
                                            <form:errors path="RegistrationLocInfos[${i.index}].BirthPlace"
                                                         cssClass="error field-validation-error"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>

                    <div class="form-horizontal">
                        <br/>
                        <div class="form-group  ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('dateOfBirth') ? 'error' : ''}">
                            <label class="control-label col-md-3">Date of Birth <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:input path="dateOfBirth" type="date"
                                            cssClass="form-control text-box single-line requiredDate"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">Political party member</label>
                            <div class="col-md-5">
                                <form:checkbox path="isInPoliticalParty"/>
                            </div>
                        </div>
                        <div class="form-group  ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('passportNumber') ? 'error' : ''}">
                            <label class="control-label col-md-3">Passport Number</label>
                            <div class="col-md-5">
                                <form:input path="passportNumber" placeholder="AA01234567"
                                            cssClass="form-control single-line"/>
                                <form:errors path="PassportNumber" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group  ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('email') ? 'error' : ''}">
                            <label class="control-label col-md-3">E-mail (personal) <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:input path="email" placeholder="Personal email address" type="email"
                                            cssClass="form-control single-line"/>
                                <form:errors path="Email" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">E-mail (corporative) <font
                                    color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:input path="companyEmail" placeholder="Company email address" type="email"
                                            cssClass="form-control single-line"/>
                                <form:errors path="CompanyEmail" cssClass="error field-validation-error"/>

                            </div>
                        </div>
                        <div class="form-group ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('MobilePhone') ? 'error' : ''}">
                            <label class="control-label col-md-3">Mobile Phone </label>
                            <div class="col-md-5">
                                <form:input path="mobilePhone" placeholder="Mobile phone number" type="tel"
                                            cssClass="telPhone form-control text-box single-line"/>
                                <form:errors path="MobilePhone" cssClass="error field-validation-error"/>
                            </div>
                        </div>

                        <div class="form-group ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('homePhone') ? 'error' : ''}">
                            <label class="control-label col-md-3">Home Phone</label>
                            <div class="col-md-5">
                                <form:input path="homePhone" placeholder="Home phone number"
                                            cssClass="telPhone form-control text-box single-line"/>
                                <form:errors path="HomePhone" cssClass="error field-validation-error"/>
                            </div>
                        </div>

                        <div class="form-group ${requestScope['org.springframework.validation.BindingResult.user'].hasFieldErrors('hiringDate') ? 'error' : ''}">
                            <label class="control-label col-md-3">Hiring Date <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:input path="hiringDate" type="date"
                                            cssClass="form-control text-box single-line requiredDate"/>
                                <form:errors path="HiringDate" cssClass="error field-validation-error"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">Status <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:select path="statusId" items="${statuses}"
                                             cssClass="form-control text-box single-line"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Department <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:select path="departmentId" items="${departments}"
                                             cssClass="form-control text-box single-line"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Has head</label>
                            <div class="col-md-1">
                                <form:checkbox path="hasHead" id="hasHead" onclick="DisableList()"/>
                            </div>
                            <div class="col-md-4">
                                <form:select path="chiefId" items="${heads}"
                                             cssClass="form-control text-box single-line"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Username <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:input path="userName" placeholder="Username"
                                            cssClass="form-control text-box single-line required"/>
                                <form:errors path="UserName" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Role <font color='red'>*</font></label>
                            <div class="col-md-5">
                                <form:select path="roleId" items="${roles}"
                                             cssClass="form-control text-box single-line"/>
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
                                <input type="submit" value="Register" class="btn btn-default"/>
                                <input type="button" onclick="location.href='/User'" value="Cancel"
                                       class="btn btn-default"/>
                            </div>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
    <spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation"></spring:url>
    <spring:url value="/resources/core/js/additionalmethods.js" var="additionalMethods"></spring:url>
    <script src="${jqueryValidation}"></script>
    <script src="${additionalMethods}"></script>
    <script>

        $(document).ready(function () {
            $("#<c:out value='${secondLang}'/>").trigger('click');
            document.getElementById("<c:out value='${secondLang}'/>").click();
            $("#<c:out value='${firstLang}'/>").trigger('click');
            document.getElementById("<c:out value='${firstLang}'/>").click();
            $("#registrationVM").validate({
                rules: {
                    'email': {
                        required: true,
                        email: true
                    },
                    'companyEmail': {
                        required: true,
                        email: true
                    },
                    'password': {
                        required: true,
                        minlength: 5
                    },
                    'repeatPassword': {
                        required: true,
                        equalTo: "#password"
                    },
                    'passportNumber': {
                        required: true,
                        exactlength: 9
                    }
                },
                messages: {
                    'repeatPassword': {
                        equalTo: "Your passwords do not match"
                    }
                }

                // Make sure the form is submitted to the destination defined
                // in the "action" attribute of the form when valid
                /*                        submitHandler: function(form) {
                 form.submit();
                 }*/

            });
            $('.requiredDate').each(function () {
                $(this).rules('add', {
                    required: true,
                    date: true,
                    messages: {
                        date: "Please enter date in DD-MM-YYYY format"
                    }
                });
            });
            $('.telPhone').each(function () {
                $(this).rules('add', {
                    matches: "[0-9\-\(\)\s]+.",
                    minlength: 7,
                    maxlength: 15
                });
            });
            $('.required').each(function () {
                $(this).rules('add', {
                    required: true
                });
            });
        });
        jQuery.validator.addMethod("exactlength", function (value, element, param) {
            return this.optional(element) || value.length == param;
        }, jQuery.validator.format("Please enter exactly {0} characters."));

    </script>
    <script>
        var enableList = function () {
            if ($("#hasHead").is(":checked")) {
                $('#chiefId').prop('disabled', false);
            }
            else {
                $('#chiefId').prop('disabled', 'disabled');
            }
        };
        $(enableList);
        $("#hasHead").change(enableList);
    </script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>