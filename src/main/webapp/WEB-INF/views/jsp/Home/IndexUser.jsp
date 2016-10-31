<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">${userprofile.firstname} ${userprofile.lastname}'s profile</h1>

<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
    <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
    <li><a data-toggle="tab" href="#workingplace">Working place</a></li>
    <li><a data-toggle="tab" href="#familyinfo">Family information</a></li>
</ul>
            <div class="tab-content">
                <!--General info/Header information Tab-->
    <div id="headerinfo" class="tab-pane fade in active">
        <h3>Header information</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#en">EN</a></li>
            <li><a data-toggle="tab" href="#ru">RU</a></li>
            <li><a data-toggle="tab" href="#uz">UZ</a></li>
        </ul>
        <!--English, Russian, Uzbek (Localizations)-->
        <div class="tab-content">
        <div id="en" class="tab-pane fade in active">
            <p>In english</p>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Last name: </label><div class="col-md-3"><c:out value="${userProfile.lastName[2]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">First name: </label><div class="col-md-3"><c:out value="${userProfile.firstName[2]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Fathers name: </label><div class="col-md-3"><c:out value="${userProfile.fathersName[2]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Address: </label><div class="col-md-3"><c:out value="${userProfile.address[2]}"></c:out></div></div>
            </div>
        </div>
            <div id="ru" class="tab-pane fade">
                <p>На русском</p>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-3">Фамилия: </label><div class="col-md-3"><c:out value="${userProfile.lastName[0]}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Имя: </label><div class="col-md-3"><c:out value="${userProfile.firstName[0]}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Отчество: </label><div class="col-md-3"><c:out value="${userProfile.fathersName[0]}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Адрес: </label><div class="col-md-3"><c:out value="${userProfile.address[0]}"></c:out></div></div>
                </div>
            </div>
        <div id="uz" class="tab-pane fade">
            <p>O'zbekchada</p>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Familiya: </label><div class="col-md-3"><c:out value="${userProfile.lastName[1]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Ism: </label><div class="col-md-3"><c:out value="${userProfile.firstName[1]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Sharf: </label><div class="col-md-3"><c:out value="${userProfile.fathersName[1]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Adres: </label><div class="col-md-3"><c:out value="${userProfile.address[1]}"></c:out></div></div>
            </div>
        </div>
        </div>
        <hr/>
        <!--Other information-->
        <div class="form-horizontal">
            <div class="form-group"><label class="control-label col-md-3">Department: </label><div class="col-md-3"><c:out value="${userProfile.department}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Position: </label><div class="col-md-3"><c:out value="${userProfile.position}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Joint type: </label><div class="col-md-3"><c:out value="${userProfile.jointType}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Status: </label><div class="col-md-3"><c:out value="${userProfile.status}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Job title: </label><div class="col-md-3"><c:out value="${userProfile.jobTitle}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Passport Number: </label><div class="col-md-3"><c:out value="${userProfile.passportNumber}"></c:out></div></div>
        </div>

    </div>
                <!--General info/Personal info Tab-->
    <div id="personalinfo" class="tab-pane fade">
        <h3>Personal information</h3>
        <div class="form-horizontal">
            <div class="form-group"><label class="control-label col-md-3">Birth place: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Date of Birth: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Home phone: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Mobile phone: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">E-mail (company): </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">E-mail (personal): </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
        </div>
    </div>
                <!--General info/Working place Tab-->
    <div id="workingplace" class="tab-pane fade">
        <h3>Working place</h3>
        <div class="form-horizontal">
            <div class="form-group"><label class="control-label col-md-3">Address: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Phone: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Hiring date: </label><div class="col-md-3"><c:out value="${}"></c:out></div></div>
            </div>
    </div>
                <!--General info/Family info Tab-->
    <div id="familyinfo" class="tab-pane fade">
        <h3>Family information</h3>
        <!--Family table-->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Relation</th>
                <th>Full name</th>
                <th>Date of birth</th>
                <th>Passport</th>
                <th>Duties</th>
            </tr>
            </thead>
            <tbody>
            <%--<c:forEach items="${familyList}" var="family" varStatus="status">--%>
                <tr>
                    <td>Row 1 Data 1</td>
                    <td>Row 1 Data 2</td>
                    <td>Row 1 Data 2</td>
                    <td>Row 1 Data 2</td>
                    <td>Row 1 Data 2</td>
                </tr>
                <tr>
                    <td>Row 2 Data 1</td>
                    <td>Row 2 Data 2</td>
                    <td>Row 1 Data 2</td>
                    <td>Row 1 Data 2</td>
                    <td>Row 1 Data 2</td>
                </tr>
            <%--</c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>
</div>
</div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
