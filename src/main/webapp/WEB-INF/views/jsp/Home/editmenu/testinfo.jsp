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
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#headerinfo">Header information</a></li>
            <li><a data-toggle="tab" href="#personalinfo">Personal information</a></li>
            <li><a data-toggle="tab" href="#familyinfo">Family information</a></li>
        </ul>
        <form:form commandName="user" cssClass="form-horizontal" method="post" action="/Hr/edit/test">
            <div class="tab-content">
                <!--General info/Header information Tab-->
                <div id="headerinfo" class="tab-pane fade in active">
                    <h3>Header information</h3>

                    <hr/>
                    <!--Other information-->
                    <div class="form-horizontal">
                        <div class="form-group"><label class="control-label col-md-3">User ID: </label>
                            <div class="col-lg-5"><form:input placeholder="User ID"
                                                              cssClass="form-control text-box single-line"
                                                              path="id"/></div>
                        </div>
                        <div class="form-group"><label class="control-label col-md-3">First name: </label>
                            <div class="col-lg-5"><form:input placeholder="First name"
                                                              cssClass="form-control text-box single-line"
                                                              path="firstName" value="${person.firstName}"/></div>
                        </div>
                        <input type="submit" value="Submit"/>
                    </div>

                </div>

            </div>
        </form:form>
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
