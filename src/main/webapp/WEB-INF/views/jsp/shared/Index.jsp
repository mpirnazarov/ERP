<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: JAS SHAYKHOV
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<div class="mainBodyBlock">

    <div class="nameTagDiv">


        <img src="/image/<%= request.getAttribute("userId") %>.jpg"
             onerror="this.src='/resources/images/ppicture.png'" class="userImgBox img-thumbnail">
        <div>
            <label>
                <%= request.getAttribute("FullName") %> ${UserProfileUser.fathersName[2]}
            </label>
            <p><span class="fa fa-id-card" aria-hidden="true"></span> ${UserProfileUser.id}</p>
            <p><span class="fa fa-male"
                     aria-hidden="true"></span> ${UserProfileUser.jobTitle}, ${UserProfileUser.external}</p>
        </div>

        <%--<iframe scrolling="no" src="https://dollaruz.net/" style="border: 0px none; margin-left: -185px; height: 574px; margin-top: -887px; width: 377px; position: absolute; top: 387px; z-index: 400;" frameborder="0"></iframe>--%>

    </div>

    <h2 class="page-header"><span class="fa fa-address-card-o" aria-hidden="true"></span> General Information</h2>

    <div class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-md-3">Hiring date:</label>
            <div class="col-sm-3"><c:out value="${UserProfileUser.entryDate}"></c:out></div>
            <label class="control-label col-md-2">Identification number:</label>
            <div class="col-sm-2"><c:out value="${UserProfileUser.id}"></c:out></div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Department: </label>
            <div class="col-sm-3"><c:out value="${UserProfileUser.department}"></c:out></div>
            <label class="control-label col-md-2">Role: </label>
            <div class="col-sm-2"><c:out value="${UserProfileUser.position}"></c:out></div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Work type: </label>
            <div class="col-sm-3"><c:out value="${UserProfileUser.jointType}"></c:out></div>
            <label class="control-label col-md-2">Status: </label>
            <div class="col-sm-2"><c:out value="${UserProfileUser.status}"></c:out></div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Passport Number: </label>
            <div class="col-sm-2"><c:out value="${UserProfileUser.passportNumber}"></c:out></div>
            <label class="control-label col-md-3">Is political: </label>
            <div class="col-sm-2"><c:out value="${UserProfileUser.isPolitical}"></c:out></div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Address: </label>
            <div class="col-sm-2"><c:out value="${UserProfileUser.address[2]}"></c:out></div>
            <label class="control-label col-md-3">Vacation days: </label>
            <div class="col-sm-2"><c:out
                    value="${UserProfileUser.vacationDaysLeft}/${UserProfileUser.vacationDaysAll}"></c:out></div>
        </div>
    </div>

    <h2 class="page-header"><span class="fa fa-user" aria-hidden="true"></span> Personal Information</h2>

    <div class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-md-3">Birth place: </label>
            <div class="col-sm-2">${UserProfileUser.personalInfo.birthPlace[2]}</div>
            <label class="control-label col-md-3">Date of Birth: </label>
            <div class="col-sm-2">${UserProfileUser.personalInfo.dateOfBirth}</div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Mobile phone: </label>
            <div class="col-sm-2">${UserProfileUser.personalInfo.mobilePhone}</div>
            <label class="control-label col-md-3">Home phone: </label>
            <div class="col-sm-2">${UserProfileUser.personalInfo.homePhone}</div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">E-mail (corporative): </label>
            <div class="col-sm-2">${UserProfileUser.personalInfo.emailCompany}</div>
            <label class="control-label col-md-3">E-mail (personal): </label>
            <div class="col-sm-2">${UserProfileUser.personalInfo.emailPersonal}</div>
        </div>
        <%
            if ((int) request.getAttribute("SystemRole") == 2) {
        %>
        <div><a href="/User/Profile/editPersonal" class="btn btn-blue">Edit</a></div>
        <% } %>
    </div>

    <h2 class="page-header"><span class="fa fa-users" aria-hidden="true"></span> Family Information</h2>

    <%--sadfasjdfahsdf--%>

    <div class="form-horizontal">
        <!--Family table-->
        <table class="table sartable table-bordered">
            <thead>
            <tr>
                <th>Relation</th>
                <th>Full name</th>
                <th>Date of birth</th>
                <th>Duties</th>
                <%
                    if ((int) request.getAttribute("SystemRole") == 2) {
                %>
                <th>Action</th>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${UserProfileUser.familyLoc}" var="family" varStatus="status">
                <tr>
                    <td>
                        <div>${family.relation[2]}</div>
                    </td>
                    <td>
                        <div>${family.firstName[2]} ${family.lastName[2]}</div>
                    </td>
                    <td>
                        <div>${family.dateOfBirth}</div>
                    </td>
                    <td>
                        <div>${family.jobTitle[2]}</div>
                    </td>
                    <%
                        if ((int) request.getAttribute("SystemRole") == 2) {
                    %>
                    <td><a href="User/Profile/updateFam/${family.id}/"
                           class="btn btn-default">Edit</a>
                        <a href="User/Profile/deleteFam/${family.id}/" class="btn btn-danger">Delete</a>
                    </td>
                    <% } %>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
        <%
        if ((int) request.getAttribute("SystemRole") == 2) {
    %>
    <div><a href="Profile/addFam" class="btn btn-blue">Add</a>
        <% } %>

    </div>

    <script>
        $(document).ready(function () {
            $("#firuref").trigger('click');
            document.getElementById("firuref").click();
            $("#fienref").trigger('click');
            document.getElementById("fienref").click();
        });
    </script>
    <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
