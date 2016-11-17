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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="pageTitle" scope="request" value="Home"/>--%>
<%--<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("ProfileModel", a.getFirstName()[2]);
%>--%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">

            For testing
            <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
                <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
                <li><a data-toggle="tab" href="#familyinfo">Family information</a></li>
            </ul>
            <div class="tab-content">
                <!--General info/Header information Tab-->
                <div id="headerinfo" class="tab-pane fade in active">
                    <h3>Header information</h3>
                    <%--<ul class="nav nav-tabs">
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
                                    <label class="control-label col-md-3">Last name: </label>
                                    <div class="col-lg-5"><form:input placeholder="Last name"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">First name: </label>
                                    <div class="col-lg-5"><form:input placeholder="First name"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Fathers name: </label>
                                    <div class="col-lg-5"><form:input placeholder="Fathers name"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Address: </label>
                                    <div class="col-lg-5"><form:input placeholder="Address"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                            </div>
                        </div>
                        <div id="ru" class="tab-pane fade">
                            <p>На русском</p>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Фамилия: </label>
                                    <div class="col-lg-5"><form:input placeholder="Фамилия"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Имя: </label>
                                    <div class="col-lg-5"><form:input placeholder="Имя"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Отчество: </label>
                                    <div class="col-lg-5"><form:input placeholder="Отчество"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Адрес: </label>
                                    <div class="col-lg-5"><form:input placeholder="Адрес"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                            </div>
                        </div>
                        <div id="uz" class="tab-pane fade">
                            <p>O'zbekchada</p>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Familiya: </label>
                                    <div class="col-lg-5"><form:input placeholder="Familiya"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Ism: </label>
                                    <div class="col-lg-5"><form:input placeholder="Ism"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Sharf: </label>
                                    <div class="col-lg-5"><form:input placeholder="Sharf"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Manzil: </label>
                                    <div class="col-lg-5"><form:input placeholder="Manzil"
                                                                      cssClass="form-control text-box single-line"
                                                                      path=""/></div>
                                </div>
                            </div>
                        </div>
                    </div>--%>
                    <hr/>
                    <!--Other information-->
                    <div class="form-horizontal">
                        <div class="form-group"><label class="control-label col-md-3">User ID: </label>
                            <div class="col-lg-5"><form:input placeholder="User ID"
                                                              cssClass="form-control text-box single-line"
                                                              path=""/></div>
                        </div>
                        <%--<div class="form-group"><label class="control-label col-md-3">Department: </label>
                            &lt;%&ndash;<div class="col-lg-5"><form:select path="departmentId" items="${departments}"
                                                               cssClass="form-control text-box single-line"/></div>&ndash;%&gt;
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Position: </label>
                            <div class="col-lg-5"><form:input placeholder="Position"
                                                              cssClass="form-control text-box single-line"
                                                              path=""/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Joint type: </label>
                            <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.jointType}"></c:out>&ndash;%&gt;</div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Status: </label>
                            <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.status}"></c:out>&ndash;%&gt;</div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Job title: </label>
                            <div class="col-lg-5"><form:input placeholder="Job title"
                                                              cssClass="form-control text-box single-line"
                                                              path=" "/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Passport Number: </label>
                            <div class="col-lg-5">
                                <form:input path="passportNumber" placeholder="AA01234567"
                                            cssClass="form-control single-line"/>
                                <form:errors path="PassportNumber" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Entry date: </label>
                            <div class="col-lg-5">
                                <form:input path="hiringDate" type="date"
                                            cssClass="form-control text-box single-line requiredDate"/>
                                <form:errors path="HiringDate" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Vacation days: </label>
                            <div class="col-lg-5"><c:out value=""></c:out></div>
                        </div>--%>

                    </div>

                </div>
                <!--General info/Personal info Tab-->
                <%--<div id="personalinfo" class="tab-pane fade">
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
                                <div class="form-group"><label class="control-label col-md-4">Birth place: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.birthPlace.get(2).birthPlace}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Date of Birth: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.dateOfBirth}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Home phone: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.homePhone}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Mobile phone: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.mobilePhone}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">E-mail (company): </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.emailCompany}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">E-mail (personal): </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.emailPersonal}"></c:out>&ndash;%&gt;</div>
                                </div>
                            </div>
                        </div>
                        <div id="piru" class="tab-pane fade">
                            <p>На русском</p>
                            <div class="form-horizontal">
                                <div class="form-group"><label class="control-label col-md-4">Место рождения: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.birthPlace.get(0).birthPlace}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Дата рождения: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.dateOfBirth}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Домашний телефон: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.homePhone}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Мобильный телефон: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.mobilePhone}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">E-mail (рабочий): </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.emailCompany}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">E-mail (персональный): </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.emailPersonal}"></c:out>&ndash;%&gt;</div>
                                </div>
                            </div>
                        </div>
                        <div id="piuz" class="tab-pane fade">
                            <p>O'zbekchada</p>
                            <div class="form-horizontal">
                                <div class="form-group"><label class="control-label col-md-4">Tug'ilgan joyi: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.birthPlace.get(1).birthPlace}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Tug'ilgan sanasi: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.dateOfBirth}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Uy telefon raqami: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.homePhone}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">Uyali aloqa raqami: </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.mobilePhone}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">E-mail (ish): </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.emailCompany}"></c:out>&ndash;%&gt;</div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-4">E-mail (shaxsiy): </label>
                                    <div class="col-lg-5">&lt;%&ndash;<c:out value="${userProfile.personalInfo.emailPersonal}"></c:out>&ndash;%&gt;</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>--%>
                <!--General info/Family info Tab-->
                <%--<div id="familyinfo" class="tab-pane fade">
                    <h3>Family information</h3>
                    <ul class="nav nav-tabs">
                        <li><a data-toggle="tab" id="fienref" href="#fien">EN</a></li>
                        <li><a data-toggle="tab" id="firuref"  href="#firu">RU</a></li>
                        <li><a data-toggle="tab" id="fiuzref"  href="#fiuz">UZ</a></li>
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
                                        <th>Duties</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                   &lt;%&ndash; <c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                                        <tr>
                                            <td>${family.relation[2]}</td>
                                            <td>${family.fullName[2]}</td>
                                            <td>${family.dateOfBirth}</td>
                                            <td>${family.jobTitle[2]}</td>
                                        </tr>
                                    </c:forEach>&ndash;%&gt;
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
                                        <th>Вид деятельности</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    &lt;%&ndash;<c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                                        <tr>
                                            <td>${family.relation[0]}</td>
                                            <td>${family.fullName[0]}</td>
                                            <td>${family.dateOfBirth}</td>
                                            <td>${family.jobTitle[0]}</td>
                                        </tr>
                                    </c:forEach>&ndash;%&gt;
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
                                        <th>Faoliyati</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    &lt;%&ndash;<c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                                        <tr>
                                            <td>${family.relation[1]}</td>
                                            <td>${family.fullName[1]}</td>
                                            <td>${family.dateOfBirth}</td>
                                            <td>${family.jobTitle[1]}</td>
                                        </tr>
                                    </c:forEach>&ndash;%&gt;
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>--%>
            </div>
        </div>
        </div>
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
