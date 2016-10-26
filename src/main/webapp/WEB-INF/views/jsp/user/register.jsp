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
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">New User Registration</h1>
            <form:form modelAttribute="registrationVM" cssClass="form-horizontal" method="post" action="/User/Register">

            <div class="tab-content">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#English">English</a></li>
            <li><a data-toggle="tab" href="#Russian">Russian</a></li>
            <li><a data-toggle="tab" href="#Uzbek">Uzbek</a></li>
        </ul>
        <div id="English" class="tab-pane fade in active">
            <div class="form-horizontal">
                <hr/>
                    <div class="form-group">
                        <label class="control-label col-md-3">First name <font color='red'>*</font></label>
                        <div class="col-md-5">
                            <form:input path="firstName" placeholder="First Name" required="required" cssClass="form-control text-box single-line"/>
                            <form:errors path="firstName" cssClass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">Last name <font color='red'>*</font></label>
                        <div class="col-md-5">
                            <form:input path="lastName" placeholder="Last Name" required="required" cssClass="form-control text-box single-line"/>
                            <form:errors path="lastName" cssClass="error" />
                        </div>
                    </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Father's name </label>
                    <div class="col-md-5">
                        <form:input path="fathersName" placeholder="Father's name" cssClass="form-control text-box single-line"/>
                        <form:errors path="fathersName" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3" for="Address">Address <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:textarea path="address" placeholder="Home address" required="required" cssClass="form-control text-box" rows="3" cols="5"/>
                        <form:errors path="address" cssClass="error" />
                    </div>
                </div>

            </div>
        </div>
        <div id="Russian" class="tab-pane fade">
            <div class="form-horizontal">
                <hr/>
                <div class="form-group">
                    <label class="control-label col-md-3">Имя <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:input path="firstNameRu" placeholder="Имя" required="required" cssClass="form-control text-box single-line"/>
                        <form:errors path="firstNameRu" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Фамилия <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:input path="lastNameRu" placeholder="Фамилия" required="required" cssClass="form-control text-box single-line"/>
                        <form:errors path="lastNameRu" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Отчество </label>
                    <div class="col-md-5">
                        <form:input path="fathersNameRu" placeholder="Отчество" cssClass="form-control text-box single-line"/>
                        <form:errors path="fathersNameRu" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3" for="Address">Адрес <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:textarea path="addressRu" placeholder="Адрес" required="required" cssClass="form-control text-box" rows="3" cols="5"/>
                        <form:errors path="addressRu" cssClass="error" />
                    </div>
                </div>

            </div>
        </div>
        <div id="Uzbek" class="tab-pane fade">
            <div class="form-horizontal">
                <hr/>
                <div class="form-group">
                    <label class="control-label col-md-3">Ism <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:input path="firstNameUz" placeholder="Ism" required="required" cssClass="form-control text-box single-line"/>
                        <form:errors path="firstNameUz" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Familiya <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:input path="lastNameUz" placeholder="Familiya" required="required" cssClass="form-control text-box single-line"/>
                        <form:errors path="lastNameUz" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Sharf <font color='red'>*</font></label>
                    <div class="col-md-5">
                        <form:input path="fathersNameUz" placeholder="Sharf" pattern="[a-z]" cssClass="form-control text-box single-line"/>
                        <form:errors path="fathersNameUz" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3" for="Address">Manzil </label>
                    <div class="col-md-5">
                        <form:textarea path="addressUz" placeholder="Uy manzili" required="required" cssClass="form-control text-box" rows="3" cols="5"/>
                        <form:errors path="addressUz" cssClass="error" />
                    </div>
                </div>

            </div>
        </div>
    </div>


    <div class="form-horizontal">
        <hr/>

        <div class="form-group">
            <label class="control-label col-md-3">Date of Birth <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:input path="dateOfBirth" type="date" cssClass="form-control text-box single-line"/>
                <form:errors path="dateOfBirth" cssClass="error" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3">Is in political party <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:checkbox path="isInPoliticalParty"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Passport Number<font color='red'>*</font></label>
            <div class="col-md-5">
                <form:input path="passportNumber" placeholder="AA01234567" cssClass="form-control single-line"/>
            </div>
        </div>
    <div class="form-group">
        <label class="control-label col-md-3">Personal Email <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:input path="email" placeholder="Personal email address" type="email" cssClass="form-control single-line"/>
            <form:errors path="email" cssClass="error" />
        </div>
    </div>
        <div class="form-group">
            <label class="control-label col-md-3">Company Email <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:input path="companyEmail" placeholder="Company email address" type="email" cssClass="form-control single-line"/>
                <form:errors path="companyEmail" cssClass="error" />
            </div>
        </div>

    <div class="form-group">
        <label class="control-label col-md-3">Mobile Phone <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:input path="mobilePhone" placeholder="Mobile phone number" type="tel"
                        cssClass="form-control text-box single-line"/>
            <form:errors path="mobilePhone" cssClass="error" />
        </div>
    </div>

        <div class="form-group">
            <label class="control-label col-md-3">Phone <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:input path="homePhone" placeholder="Home phone number" type="tel"
                            cssClass="form-control text-box single-line"/>
                <form:errors path="homePhone" cssClass="error" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3">Hiring Date <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:input path="hiringDate" type="date" cssClass="form-control text-box single-line"/>
                <form:errors path="hiringDate" cssClass="error" />
            </div>
        </div>

    <div class="form-group">
        <label class="control-label col-md-3">Status <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:select path="statusId" items="${statuses}" cssClass="form-control text-box single-line" />
            <form:errors path="statusId" cssClass="error" />
        </div>
    </div>
        <div class="form-group">
            <label class="control-label col-md-3">Department <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:select path="departmentId" items="${departments}" cssClass="form-control text-box single-line" />
                <form:errors path="departmentId" cssClass="error" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Has head</label>
            <div class="col-md-5">
                <form:checkbox path="hasHead" id="hasHead" onclick="DisableList()"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Chief <font color='red'>*</font></label>
            <div class="col-md-5">
                <form:select path="chiefId" items="${heads}" cssClass="form-control text-box single-line" />
                <form:errors path="chiefId" cssClass="error" />
            </div>
        </div>
    <div class="form-group">
        <label class="control-label col-md-3">Username <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:input path="userName" placeholder="Username" cssClass="form-control text-box single-line"/>
            <form:errors path="userName" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-3">Password <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:password path="password" placeholder="Password" cssClass="form-control text-box single-line"/>
            <form:errors path="password" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-3">Repeat password <font color='red'>*</font></label>
        <div class="col-md-5">
            <form:password path="repeatPassword" placeholder="Repeat Password" cssClass="form-control text-box single-line"/>
            <form:errors path="repeatPassword" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-offset-3 col-md-9">
            <input type="submit" value="Register" class="btn btn-default"/>
            <input type="button" onclick="location.href='/User'" value="Cancel" class="btn btn-default"/>
        </div>
    </div>
</div>

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

</form:form>


<%--<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/core/css/Registration.css" var="registrationCss"/>
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss"/>
    <spring:url value="/resources/core/css/Site.css" var="siteCss"/>
    <spring:url value="/resources/core/js/jquery-1.12.4.min.js" var="jquery"/>
    <spring:url value="/resources/core/js/notify.min.js" var="notifyMinJs"/>
    <spring:url value="/resources/core/js/bootstrap.js" var="bootstrapJs"/>
    <spring:url value="/resources/core/js/respond.js" var="respondJs"/>
    <spring:url value="/resources/core/js/registrationMultiStep.js" var="registrationMultiStep"/>
    &lt;%&ndash;<script src="${jquery}"></script>&ndash;%&gt;
    &lt;%&ndash;<script src="${notifyMinJs}"></script>&ndash;%&gt;
    &lt;%&ndash;<script src="${bootstrapJs}"></script>&ndash;%&gt;
    &lt;%&ndash;<script src="${respondJs}"></script>&ndash;%&gt;

    <!-- jQuery -->
    <script src="http://thecodeplayer.com/uploads/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <!-- jQuery easing plugin -->
    <script src="http://thecodeplayer.com/uploads/js/jquery.easing.min.js" type="text/javascript"></script>

    <link href="${registrationCss}" rel="stylesheet"/>
    <script src="${registrationMultiStep}"></script>

    &lt;%&ndash;<link href="${bootstrapCss}" rel="stylesheet" />&ndash;%&gt;
    <link href="${siteCss}" rel="stylesheet"/>
</head>

<body>

<form:form modelAttribute="registrationVM" cssClass="form-horizontal" method="post" action="/User/Register" id="msform">
<ul id="progressbar">
    <li class="active">Personal</li>
    <li>Company</li>
    <li>System</li>
</ul>
<hr/>
<fieldset>
    <h2 class="fs-title">Create new user account</h2>
    <h3 class="fs-subtitle">Personal Information</h3>
    <form:input path="firstName" placeholder="First Name" cssClass="form-control text-box single-line"/>
    <form:input path="lastName" placeholder="Last Name" cssClass="form-control text-box single-line"/>
    <form:input path="fathersName" placeholder="Father's name" cssClass="form-control text-box single-line"/>
    <form:input path="dateOfBirth" placeholder="Date of birth" type="date"
                cssClass="form-control text-box single-line"/>
    <form:input path="mobilePhone" placeholder="Mobile phone number" type="tel"
                cssClass="form-control text-box single-line"/>
    <form:input path="homePhone" placeholder="Home phone number" type="tel"
                cssClass="form-control text-box single-line"/>
    <form:input path="email" placeholder="Personal email address" type="email" cssClass="form-control single-line"/>
    <form:textarea path="address" placeholder="Home address" cssClass="form-control text-box" rows="3" cols="5"/>
    <input type="button" name="next" class="next action-button" value="Next"/>
</fieldset>

<fieldset>
    <h2 class="fs-title">Create new user account</h2>
    <h3 class="fs-subtitle">Company Information</h3>
    <form:select path="statusId">
        <form:option value="0" label="--Current Status--" disabled="true"/>
        <form:options items="${heads}"/>
    </form:select>
    <form:input path="hiringDate" placeholder="Date of hire" type="date" cssClass="form-control single-line"/>
    <form:select path="chiefId">
        <form:option value="0" label="--Select Direct Head--" disabled="true"/>
        <form:options items="${heads}"/>
    </form:select>
    nput path="companyEmail" placeholder="Company email address" type="email" cssClass="form-control single-line"/>
    <input<form:i type="button" name="previous" class="previous action-button" value="Previous"/>
    <input type="button" name="next" class="next action-button" value="Next"/>
</fieldset>

<fieldset>
    <h2 class="fs-title">Create new user account</h2>
    <h3 class="fs-subtitle">System Credentials</h3>
    <form:input path="userName" placeholder="Username" cssClass="form-control text-box single-line"/>
    <form:password path="password" placeholder="Password" cssClass="form-control text-box single-line"/>
    <form:password path="repeatPassword" placeholder="Repeat Password" cssClass="form-control text-box single-line"/>
    <input type="button" name="previous" class="previous action-button" value="Previous"/>
    <input type="submit" name="submit" class="submit action-button" value="Submit"/>
</fieldset>--%>
    <%-- <div class="form-group">
         <form:label path="lastName" cssClass="control-label col-md-2">Last Name</form:label>
         <div class="col-md-2">
             <form:input path="lastName" cssClass="form-control text-box single-line"/>
         </div>
     </div>
     <div class="form-group">
         <form:label path="fathersName" cssClass="control-label col-md-2">Father's Name</form:label>
         <div class="col-md-2">
             <form:input path="fathersName" cssClass="form-control text-box single-line"/>
         </div>
     </div>
     <div class="form-group">
         <form:label path="dateOfBirth" cssClass="control-label col-md-2">Date of Birth</form:label>
         <div class="col-md-2">
             <form:input path="dateOfBirth" type="date" cssClass="form-control text-box single-line"/>
         </div>
     </div>
     <div class="form-group">
         <form:label path="mobilePhone" cssClass="control-label col-md-2">Phone Number</form:label>
         <div class="col-md-2">
             <form:input path="mobilePhone" type="tel" cssClass="form-control text-box single-line"/>
         </div>
     </div>
     <div class="form-group">
         <form:label path="email" cssClass="control-label col-md-2">Email</form:label>
         <div class="col-md-2">
             <form:input path="email" type="email" cssClass="form-control text-box single-line"/>
         </div>
     </div>
     <div class="form-group">
         <form:label path="address" cssClass="control-label col-md-2">Home Address</form:label>
         <div class="col-md-5">
             <form:textarea path="address" cssClass="form-control text-box" rows="3" cols="5"/>
         </div>
     </div>

     <br/>
     <div class="form-group">
         <form:label path="chiefId" cssClass="control-label col-md-2">Head</form:label>
         <div class="col-md-5">
             <form:select path="chiefId" cssClass="form-control" items="${heads}"/>
         </div>
     </div>
     <div class="form-group">
         <div class="col-md-offset-2 col-md-10">
             <input type="submit" value="Register" class="btn btn-default" />
             <input type="button" onclick="location.href='/User'" value="Cancel" class="btn btn-default" />
         </div>
     </div>--%>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>