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

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid" id="page">
    <div class="row">
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
            <form:form  commandName="family" cssClass="form-horizontal" method="post" action="/Hr/user/${person.id}/update/faminfo">
                    <!--General info/Family info Tab-->
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
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Relation: <font color='red'>*</font></label>
                                                                <div class="col-lg-5"><form:input placeholder="Relation" required="true"
                                                                                                  cssClass="form-control text-box single-line"
                                                                                                  path="relation[2]" value="${family.relation[2]}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Full name: <font color='red'>*</font></label>
                                                                <div class="col-lg-5"><form:input placeholder="Full name" required="true"
                                                                                                  cssClass="form-control text-box single-line"
                                                                                                  path="fullName[2]" value="${family.fullName[2]}"/></div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Job title: <font color='red'>*</font></label>
                                                                <div class="col-lg-5"><form:input placeholder="Job title" required="true"
                                                                                                  cssClass="form-control text-box single-line"
                                                                                                  path="jobTitle[2]" value="${family.jobTitle[2]}"/></div>
                                                            </div>
                            </div>
                        </div>
                        <div id="firu" class="tab-pane fade in active">
                            <p>На русском</p>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Relation: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Relation" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="relation[0]" value="${family.relation[0]}"/></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Full name: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Full name" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="fullName[0]" value="${family.fullName[0]}"/></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Job title: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Job title" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="jobTitle[0]" value="${family.jobTitle[0]}"/></div>
                                </div>
                            </div>
                        </div>
                        <div id="fiuz" class="tab-pane fade in active">
                            <p>O'zbekchada</p>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Relation: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Relation" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="relation[1]" value="${family.relation[1]}"/></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Full name: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Full name" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="fullName[1]" value="${family.fullName[1]}"/></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Job title: <font color='red'>*</font></label>
                                    <div class="col-lg-5"><form:input placeholder="Job title" required="true"
                                                                      cssClass="form-control text-box single-line"
                                                                      path="jobTitle[1]" value="${family.jobTitle[1]}"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-3">Date of Birth: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:input path="dateOfBirth" type="date" value="${family.dateOfBirth}"
                                                      cssClass="form-control text-box single-line requiredDate"/>

                                </div>
                        </div>
                    <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input type="submit" value="Save" class="btn btn-default"/>
                                <input type="button" onclick="location.href='/Hr/user/${id}/update/${path}'" value="Cancel"
                                       class="btn btn-default"/>
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

    <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
