<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
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
<%
    ProfileViewModel a = (ProfileViewModel)request.getAttribute("userProfile");
    request.setAttribute("ProfileModel", (a.getFirstName()[0]).toString());
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">${userProfile.firstname} ${userProfile.lastname}'s profile</h1>

<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
    <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
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
                    <label class="control-label col-md-3">Last name: </label><div class="col-lg-5"><c:out value="${userProfile.lastName[2]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">First name: </label><div class="col-lg-5"><c:out value="${userProfile.firstName[2]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Fathers name: </label><div class="col-lg-5"><c:out value="${userProfile.fathersName[2]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Address: </label><div class="col-lg-5"><c:out value="${userProfile.address[2]}"></c:out></div></div>
            </div>
        </div>
            <div id="ru" class="tab-pane fade">
                <p>На русском</p>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-3">Фамилия: </label><div class="col-lg-5"><c:out value="${userProfile.lastName[0]}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Имя: </label><div class="col-lg-5"><c:out value="${userProfile.firstName[0]}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Отчество: </label><div class="col-lg-5"><c:out value="${userProfile.fathersName[0]}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Адрес: </label><div class="col-lg-5"><c:out value="${userProfile.address[0]}"></c:out></div></div>
                </div>
            </div>
        <div id="uz" class="tab-pane fade">
            <p>O'zbekchada</p>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">Familiya: </label><div class="col-lg-5"><c:out value="${userProfile.lastName[1]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Ism: </label><div class="col-lg-5"><c:out value="${userProfile.firstName[1]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Sharf: </label><div class="col-lg-5"><c:out value="${userProfile.fathersName[1]}"></c:out></div></div>
                <div class="form-group"><label class="control-label col-md-3">Adres: </label><div class="col-lg-5"><c:out value="${userProfile.address[1]}"></c:out></div></div>
            </div>
        </div>
        </div>
        <hr/>
        <!--Other information-->
        <div class="form-horizontal">
            <div class="form-group"><label class="control-label col-md-3">User ID: </label><div class="col-lg-5"><c:out value="${userProfile.id}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Department: </label><div class="col-lg-5"><c:out value="${userProfile.department}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Position: </label><div class="col-lg-5"><c:out value="${userProfile.position}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Joint type: </label><div class="col-lg-5"><c:out value="${userProfile.jointType}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Status: </label><div class="col-lg-5"><c:out value="${userProfile.status}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Job title: </label><div class="col-lg-5"><c:out value="${userProfile.jobTitle}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Passport Number: </label><div class="col-lg-5"><c:out value="${userProfile.passportNumber}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Entry date: </label><div class="col-lg-5"><c:out value="${userProfile.entryDate}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Vacation days: </label><div class="col-lg-5"><c:out value="${userProfile.vacationDaysLeft}/${userProfile.vacationDaysAll}"></c:out></div></div>

        </div>

    </div>
                <!--General info/Personal info Tab-->
    <div id="personalinfo" class="tab-pane fade">
        <h3>Personal information</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#pien">EN</a></li>
            <li><a data-toggle="tab" href="#piru">RU</a></li>
            <li><a data-toggle="tab" href="#piuz">UZ</a></li>
        </ul>
        <!--English, Russian, Uzbek (Localizations)-->
        <div class="tab-content">
            <div id="pien" class="tab-pane fade in active">
                <p>In english</p>
                <div class="form-horizontal">
            <div class="form-group"><label class="control-label col-md-3">Birth place: </label><div class="col-lg-5"><c:out value="${userProfile.birthPlace}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Date of Birth: </label><div class="col-lg-5"><c:out value="${userProfile.dateOfBirth}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Home phone: </label><div class="col-lg-5"><c:out value="${userProfile.homePhone}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">Mobile phone: </label><div class="col-lg-5"><c:out value="${userProfile.mobilePhone}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">E-mail (company): </label><div class="col-lg-5"><c:out value="${userProfile.companyEmail}"></c:out></div></div>
            <div class="form-group"><label class="control-label col-md-3">E-mail (personal): </label><div class="col-lg-5"><c:out value="${userProfile.personalEmail}"></c:out></div></div>
                </div>
            </div>
            <div id="piru" class="tab-pane fade">
                <p>На русском</p>
                <div class="form-horizontal">
                    <div class="form-group"><label class="control-label col-md-3">Место рождения: </label><div class="col-lg-5"><c:out value="${userProfile.birthPlace}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Дата рождения: </label><div class="col-lg-5"><c:out value="${userProfile.dateOfBirth}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Домашний телефон: </label><div class="col-lg-5"><c:out value="${userProfile.homePhone}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Мобильный телефон: </label><div class="col-lg-5"><c:out value="${userProfile.mobilePhone}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">E-mail (рабочий): </label><div class="col-lg-5"><c:out value="${userProfile.companyEmail}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">E-mail (персональный): </label><div class="col-lg-5"><c:out value="${userProfile.personalEmail}"></c:out></div></div>
                </div>
            </div>
            <div id="piuz" class="tab-pane fade">
                <p>O'zbekchada</p>
                <div class="form-horizontal">
                    <div class="form-group"><label class="control-label col-md-3">Tug'ilgan joyi: </label><div class="col-lg-5"><c:out value="${userProfile.birthPlace}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Tug'ilgan sanasi: </label><div class="col-lg-5"><c:out value="${userProfile.dateOfBirth}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Uy telefon raqami: </label><div class="col-lg-5"><c:out value="${userProfile.homePhone}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">Uyali aloqa raqami: </label><div class="col-lg-5"><c:out value="${userProfile.mobilePhone}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">E-mail (ish): </label><div class="col-lg-5"><c:out value="${userProfile.companyEmail}"></c:out></div></div>
                    <div class="form-group"><label class="control-label col-md-3">E-mail (shaxsiy): </label><div class="col-lg-5"><c:out value="${userProfile.personalEmail}"></c:out></div></div>
                </div>
            </div>
            </div>

                    <!--General info/Family info Tab-->
    <div id="familyinfo" class="tab-pane fade">
        <h3>Family information</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#fien">EN</a></li>
            <li><a data-toggle="tab" href="#firu">RU</a></li>
            <li><a data-toggle="tab" href="#fiuz">UZ</a></li>
        </ul>
        <!--English, Russian, Uzbek (Localizations)-->
        <div class="tab-content">
            <div id="fien" class="tab-pane fade in active">
                <p>In english</p>
                <div class="form-horizontal">
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
            <c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                <tr>
                    <td>${family.relation}</td>
                    <td>${family.fullName}</td>
                    <td>${family.dateOfBirth}</td>
                    <td>${family.jobTitle}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
                </div>
            </div>
            <div id="firu" class="tab-pane fade in active">
            <p>На русском</p>
                <div class="form-horizontal">
                <!--Family table-->
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Родство</th>
                        <th>Ф.И.О.</th>
                        <th>Дата рождения</th>
                        <th>Паспортные данные</th>
                        <th>Вид деятельности</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                        <tr>
                            <td>${family.relation}</td>
                            <td>${family.fullName}</td>
                            <td>${family.dateOfBirth}</td>
                            <td>${family.jobTitle}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
            <div id="fiuz" class="tab-pane fade in active">
            <p>O'zbekchada</p>
                <div class="form-horizontal">
                <!--Family table-->
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Qarindoshligi</th>
                        <th>F.I.Sh.</th>
                        <th>Tug'ilgan sanasi</th>
                        <th>Pasport</th>
                        <th>Faoliyati</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                        <tr>
                            <td>${family.relation}</td>
                            <td>${family.fullName}</td>
                            <td>${family.dateOfBirth}</td>
                            <td>${family.jobTitle}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
            </div>
    </div>
</div>
</div>
</div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
