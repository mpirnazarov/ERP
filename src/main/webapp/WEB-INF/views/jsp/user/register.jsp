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

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHeader.jsp"></jsp:include>

<form:form modelAttribute="registrationVM" cssClass="form-horizontal" method="post" action="/User/Register">
    <div id="tabs">
        <ul>
            <li><a href="#English">English</a></li>
            <li><a href="#Russian">Russian</a></li>
            <li><a href="#Uzbek">Uzbek</a></li>
        </ul>
        <div id="English">
            <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-2">First name <font color='red'>*</font></label>
                        <div class="col-md-2">
                            <form:input path="firstName" placeholder="First Name" cssClass="form-control text-box single-line"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">Last name <font color='red'>*</font></label>
                        <div class="col-md-2">
                            <form:input path="lastName" placeholder="Last Name" cssClass="form-control text-box single-line"/>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="control-label col-md-2">Father's name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="fathersName" placeholder="Father's name" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="Address">Address <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:textarea path="address" placeholder="Home address" cssClass="form-control text-box" rows="3" cols="5"/>
                    </div>
                </div>

            </div>
        </div>
        <div id="Russian">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-2">First name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="firstNameRu" placeholder="Имя" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">Last name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="lastNameRu" placeholder="Фамилия" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">Father's name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="fathersNameRu" placeholder="Отчество" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="Address">Address <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:textarea path="addressRu" placeholder="Адрес" cssClass="form-control text-box" rows="3" cols="5"/>
                    </div>
                </div>

            </div>
        </div>
        <div id="Uzbek">
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-2">First name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="firstNameUz" placeholder="First Name" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">Last name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="lastNameUz" placeholder="Last Name" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">Father's name <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:input path="fathersNameUz" placeholder="Father's name" cssClass="form-control text-box single-line"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2" for="Address">Address <font color='red'>*</font></label>
                    <div class="col-md-2">
                        <form:textarea path="addressUz" placeholder="Home address" cssClass="form-control text-box" rows="3" cols="5"/>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <div class="form-horizontal">
        <hr/>
    <div class="form-group">
        <label class="control-label col-md-2">Email <font color='red'>*</font></label>
        <div class="col-md-2">
            <form:input path="email" placeholder="Personal email address" type="email" cssClass="form-control single-line"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2">Phone <font color='red'>*</font></label>
        <div class="col-md-2">
            <input class="form-control text-box single-line" data-val="true"
                   data-val-regex="Not a valid Phone number. Format: +XXXXXXXXXX"
                   data-val-regex-pattern="^[+]([0-9]{12})$" data-val-required="Phone is compulsory field."
                   id="Phone" name="Phone" type="tel" value=""/>
            <span class="field-validation-valid text-danger" data-valmsg-for="Phone"
                  data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2" for="Major">Major</label>
        <div class="col-md-2">
            <input class="form-control text-box single-line" data-val="true"
                   data-val-regex="Use letters only please" data-val-regex-pattern="^[a-zA-Z\s]+$" id="Major"
                   name="Major" type="text" value=""/>
            <span class="field-validation-valid" data-valmsg-for="Major" data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2">System role <font color='red'>*</font></label>
        <div class="col-md-2">
            <select class="form-control" data-val="true"
                    data-val-number="The field System role must be a number."
                    data-val-required="Требуется поле System role." id="PositionId" name="PositionId">
                <option value="1">Admin</option>
                <option value="2">CTO</option>
                <option value="3">Master</option>
                <option value="4">User</option>
                <option value="5">Monitor</option>
            </select>
            <span class="field-validation-valid" data-valmsg-for="PositionId" data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2" for="HasHead">Has head</label>
        <div class="col-md-2">
            <input data-val="true" data-val-required="Требуется поле Has head." id="HasHead" name="HasHead"
                   onclick="DisableList()" type="checkbox" value="true"/><input name="HasHead" type="hidden"
                                                                                value="false"/>
            <span class="field-validation-valid" data-valmsg-for="HasHead" data-valmsg-replace="true"></span>
        </div>
    </div>
    <%--<div class="form-group">--%>
        <%--<label class="control-label col-md-2">Head</label>--%>
        <%--<div class="col-md-2">--%>
            <%--<select class="form-control" data-val="true" data-val-number="The field Head must selected."--%>
                    <%--id="HeadId" name="HeadId">--%>
                <%--<c:forEach items="${heads}" var="thisHead">--%>
                    <%--<option value="${thisHead[0].id}">${thisHead[1].firstName} ${thisHead[1].lastName}</option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
            <%--<span class="field-validation-valid" data-valmsg-for="HeadId" data-valmsg-replace="true"></span>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="form-group">
        <label class="control-label col-md-2">Username <font color='red'>*</font></label>
        <div class="col-md-2">
            <input class="form-control text-box single-line" data-val="true"
                   data-val-length="Поле Username должно быть строкой с минимальной длиной 1 и максимальной 50."
                   data-val-length-max="50" data-val-length-min="1" data-val-required="Требуется поле Username."
                   id="UserName" name="UserName" type="text" value=""/>
            <span class="field-validation-valid text-danger" data-valmsg-for="UserName"
                  data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2">Password <font color='red'>*</font></label>
        <div class="col-md-2">
            <input class="form-control text-box single-line password" data-val="true"
                   data-val-length="Поле Password должно быть строкой с минимальной длиной 1 и максимальной 100."
                   data-val-length-max="100" data-val-length-min="1"
                   data-val-required="Требуется поле Password." id="Password" name="Password" type="password"
                   value=""/>
            <span class="field-validation-valid text-danger" data-valmsg-for="Password"
                  data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2">Repeat password <font color='red'>*</font></label>
        <div class="col-md-2">
            <input class="form-control text-box single-line password" data-val="true"
                   data-val-equalto="Repeat password mismatch" data-val-equalto-other="*.Password"
                   data-val-length="Поле Repeat password должно быть строкой с минимальной длиной 1 и максимальной 100."
                   data-val-length-max="100" data-val-length-min="1"
                   data-val-required="Требуется поле Repeat password." id="RepeatPassword" name="RepeatPassword"
                   type="password" value=""/>
            <span class="field-validation-valid text-danger" data-valmsg-for="RepeatPassword"
                  data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-offset-2 col-md-10">
            <input type="submit" value="Register" class="btn btn-default"/>
            <input type="button" onclick="location.href='/User'" value="Cancel" class="btn btn-default"/>
        </div>
    </div>
</div>

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
    <form:input path="companyEmail" placeholder="Company email address" type="email" cssClass="form-control single-line"/>
    <input type="button" name="previous" class="previous action-button" value="Previous"/>
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