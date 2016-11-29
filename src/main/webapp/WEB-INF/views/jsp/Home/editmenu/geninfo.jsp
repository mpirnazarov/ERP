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
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("person");
    request.setAttribute("FullName", a.getFirstName()[2] + " " + a.getLastName()[2]);
    request.setAttribute("JobTitle", a.getJobTitle());
    request.setAttribute("id", a.getId());
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid" id="page">
    <div class="row">
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
                <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
                <li><a data-toggle="tab" href="#familyinfo">Family information</a></li>
            </ul>
            <form:form commandName="user" cssClass="form-horizontal" method="post" action="/Hr/user/${person.id}/update/geninfo">
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
                                    <label class="control-label col-md-3">Last name: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Last name" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="lastName[2]" value="${person.lastName[2]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">First name: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="First name" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="firstName[2]" value="${person.firstName[2]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Fathers name: </label>
                                    <div class="col-lg-5"><form:input placeholder="Fathers name"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="fathersName[2]" value="${person.fathersName[2]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Address: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Address" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="address[2]" value="${person.address[2]}"/></div>
                                </div>
                            </div>
                        </div>
                        <div id="ru" class="tab-pane fade">
                            <p>На русском</p>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Фамилия: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Фамилия" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="lastName[0]" value="${person.lastName[0]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Имя: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Имя" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="firstName[0]" value="${person.firstName[0]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Отчество: </label>
                                    <div class="col-lg-5"><form:input placeholder="Отчество"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="fathersName[0]" value="${person.fathersName[0]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Адрес: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Адрес" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="address[0]" value="${person.address[0]}"/></div>
                                </div>
                            </div>
                        </div>
                        <div id="uz" class="tab-pane fade">
                            <p>O'zbekchada</p>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Familiya: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Familiya" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="lastName[1]" value="${person.lastName[1]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Ism: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Ism" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="firstName[1]" value="${person.firstName[0]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Sharf: </label>
                                    <div class="col-lg-5"><form:input placeholder="Sharf"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="fathersName[1]" value="${person.fathersName[1]}"/></div>
                                </div>
                                <div class="form-group"><label class="control-label col-md-3">Manzil: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Manzil" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="address[1]" value="${person.address[1]}"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <!--Other information-->
                    <div class="form-horizontal">
                        <div class="form-group"><label class="control-label col-md-3">User ID: </label>
                            <div class="col-lg-5">${person.id}</div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Department: <font color='red'>*</font></label>
                            <div class="col-lg-5"><form:select path="department" items="${departments}" required="true"
                                                               cssClass="form-control text-box single-line"/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Position: <font color='red'>*</font></label>
                            <div class="col-lg-5"><form:select path="position" items="${positions}" required="true"
                                                               cssClass="form-control text-box single-line"/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Joint type: <font color='red'>*</font></label>
                            <div class="col-lg-5"><form:select path="jointType" items="${jointType}" required="true"
                                                               cssClass="form-control text-box single-line"/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Status: <font color='red'>*</font></label>
                            <div class="col-lg-5"><form:select path="status" items="${statuses}" required="true"
                                                               cssClass="form-control text-box single-line"/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Job title: <font color='red'>*</font></label>
                            <div class="col-lg-5"><form:select path="jobTitle" items="${jobTitles}" required="true"
                                                               cssClass="form-control text-box single-line"/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Passport Number: <font color='red'>*</font></label>
                            <div class="col-lg-5">
                                <form:input path="passportNumber" placeholder="AA01234567" value="${person.passportNumber}" required="true"
                                            cssClass="form-control single-line" maxlength="9"/>
                                <form:errors path="PassportNumber" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Is Political: <font color='red'>*</font></label>
                            <div class="col-lg-5">
                                <c:if test="${person.isPolitical}">
                                    <form:checkbox path="isPolitical" CHECKED="checked" />
                                </c:if>
                                <c:if test="${!person.isPolitical}">
                                    <form:checkbox path="isPolitical"  />
                                </c:if>
                            </div>
                                <%--<form:errors path="PassportNumber" cssClass="error field-validation-error"/>--%>

                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Entry date: <font color='red'>*</font></label>
                            <div class="col-lg-5">
                                <form:input path="entryDate" type="date" value="${person.entryDate}" required="true"
                                            cssClass="form-control text-box single-line requiredDate"/>
                                <form:errors path="entryDate" cssClass="error field-validation-error"/>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">Vacation days: </label>
                            <div class="col-lg-5"><c:out value="${person.vacationDaysLeft} / ${person.vacationDaysAll}"></c:out></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <input type="submit" value="Save" class="btn btn-default"/>
                        </div>
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
                                <div class="form-group"><label class="control-label col-md-4">Birth place: </label>
                                    <div class="col-lg-5"><form:input cssClass="form-control text-box single-line"
                                                                      path="personalInfo.birthPlace[2]" value="${person.personalInfo.birthPlace[2]}" /></div>
                                </div>
                            </div>
                        </div>
                        <div id="piru" class="tab-pane fade">
                            <p>На русском</p>
                            <div class="form-horizontal">
                                <div class="form-group"><label class="control-label col-md-4">Место рождения: </label>
                                    <div class="col-lg-5"><form:input cssClass="form-control text-box single-line"
                                          path="personalInfo.birthPlace[0]" value="${person.personalInfo.birthPlace[0]}" /></div>
                            </div>
                        </div>
                            </div>
                        <div id="piuz" class="tab-pane fade">
                            <p>O'zbekchada</p>
                            <div class="form-horizontal">
                                <div class="form-group"><label class="control-label col-md-4">Tug'ilgan joyi: </label>
                                    <div class="col-lg-5"><form:input placeholder="Birth place:"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="personalInfo.birthPlace[1]" value="${person.personalInfo.birthPlace[1]}" /></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-horizontal">
                        <div class="form-group"><label class="control-label col-md-4">Date of Birth: <font color='red'>*</font></label>
                            <div class="col-lg-5">
                                <form:input path="personalInfo.dateOfBirth" type="date" value="${person.personalInfo.dateOfBirth}"
                                            cssClass="form-control text-box single-line requiredDate"/>
                                    <%--<form:errors path="dateOfBirth" cssClass="error field-validation-error"/>--%>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-4">Home phone: </label>
                            <div class="col-lg-5">
                                <form:input path="personalInfo.homePhone" placeholder="998971234546" value="${person.personalInfo.homePhone}"
                                            type="tel" pattern="[0-9]{12}" title="Phone Number with 12 digits (998971112233)" cssClass="form-control single-line"/>
                                    <%--<form:errors path="personalInfo.homePhone" cssClass="error field-validation-error"/>--%>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-4">Mobile phone: </label>
                            <div class="col-lg-5">
                                <form:input path="personalInfo.mobilePhone" placeholder="998971234546" value="${person.personalInfo.mobilePhone}"
                                            type="tel" pattern="[0-9]{12}" title="Phone Number with 12 digits (998971112233)" cssClass="form-control single-line"/>
                                    <%--<form:errors path="personalInfo.PassportNumber" cssClass="error field-validation-error"/>--%>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-4">E-mail (company): <font color='red'>*</font></label>
                            <div class="col-lg-5">
                                <form:input path="personalInfo.emailCompany" placeholder="test@lgcns.uz" value="${person.personalInfo.emailCompany}" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                                            cssClass="form-control single-line"/>
                                    <%--<form:errors path="personalInfo.emailCompany" cssClass="error field-validation-error"/>--%>
                            </div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-4">E-mail (personal): <font color='red'>*</font></label>
                            <div class="col-lg-5">
                                <form:input path="personalInfo.emailPersonal" placeholder="test@lgcns.uz" value="${person.personalInfo.emailPersonal}"
                                            cssClass="form-control single-line"/>
                                    <%--<form:errors path="personalInfo.emailPersonal" cssClass="error field-validation-error"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-9">
                                <input type="submit" value="Save" class="btn btn-default"/>
                            </div>
                        </div>
                    </div>

                </div>
                <!--General info/Family info Tab-->
                <div id="familyinfo" class="tab-pane fade">
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
                                    <c:set var="count" value="0" scope="page" />
                                    <c:forEach items="${person.familyLoc}" var="family" varStatus="status">
                                        <tr>
                                            <td>${family.relation[2]}</td>
                                            <td>${family.firstName[2]} ${family.lastName[2]}</td>
                                            <td>${family.dateOfBirth}</td>
                                            <td>${family.jobTitle[2]}</td>
                                            <td><a href="./Geninfo/updateFam/${family.id}/" class="btn btn-default">Edit</a>
                                            </td>
                                            <c:set var="count" value="${count + 1}" scope="page"/>
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
                                        <th>Вид деятельности</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                                        <tr>
                                            <td>${family.relation[0]}</td>
                                            <td>${family.firstName[0]} ${family.lastName[0]}</td>
                                            <td>${family.dateOfBirth}</td>
                                            <td>${family.jobTitle[0]}</td>
                                            <td><a href="./Geninfo/updateFam/${family.id}/" class="btn btn-primary">Add</a>
                                                <a href="./Geninfo/updateFam/${family.id}/" class="btn btn-default">Edit</a>
                                            </td>
                                            <c:set var="count" value="${count + 1}" scope="page"/>
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
                                        <th>Faoliyati</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userProfile.familyLoc}" var="family" varStatus="status">
                                        <tr>
                                            <td>${family.relation[1]}</td>
                                            <td>${family.firstName[1]} ${family.lastName[1]}</td>
                                            <td>${family.dateOfBirth}</td>
                                            <td>${family.jobTitle[1]}</td>
                                            <td><a href="./Geninfo/updateFam/${family.id}/" class="btn btn-primary">Add</a>
                                                <a href="./Geninfo/updateFam/${family.id}/" class="btn btn-default">Edit</a>
                                            </td>
                                            <c:set var="count" value="${count + 1}" scope="page"/>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <a href="#" class="btn btn-primary">Add</a>
                    </div>
                </div>
            </div>
            </form:form>
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
<script>
    /*menu handler*/
    $(function(){
        function stripTrailingSlash(str) {
            if(str.substr(-1) == '/') {
                return str.substr(0, str.length - 1);
            }
            return str;
        }

        var url = window.location.pathname;
        var activePage = stripTrailingSlash(url);

        $('.nav li a').each(function(){
            var currentPage = stripTrailingSlash($(this).attr('href'));

            if (activePage == currentPage) {
                $(this).parent().addClass('active');
            }
        });
    });
</script>
    <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
