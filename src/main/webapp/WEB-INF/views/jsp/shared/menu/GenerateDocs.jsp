<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Jasur Shaykhov
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Documents"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <h2 class="page-header">Documents</h2>

        <form:form method="post" modelAttribute="docs" action="/Hr/Generate/">
            <%-- <form:errors path="*" cssClass="error" /> --%>
            <table>
                <!-- DROPDOWN code -->

                <tr>
                    <td><spring:message text="Document"/></td>
                    <td><form:select path="docId" items="${documents}"
                                     cssClass="form-control text-box single-line"/></td>

                </tr>

                <tr>
                    <td><spring:message text="Users"/></td>
                    <td><form:select path="userId" items="${users}" cssClass="form-control text-box single-line"/></td>

                </tr>

                <tr>
                    <td colspan="3"><input type="submit" class="btn btn-default" name="Save" value="Save"/></td>
                    <td colspan="3"><input type="submit" class="btn btn-default" name="Print" value="Print"/></td>
                </tr>
            </table>
        </form:form>
        <a href="Docs/Manage" class="btn btn-success">Manage Documents</a>

    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
