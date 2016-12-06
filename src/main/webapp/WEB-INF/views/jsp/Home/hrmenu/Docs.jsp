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
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
        <div class="col-lg-offset-2">
            <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
            <h2 class="page-header">Documents</h2>

            <form:form method="post" modelAttribute="docs" action="/Hr/Generate/">
                <%-- <form:errors path="*" cssClass="error" /> --%>
                <table>
                    <!-- DROPDOWN code -->

                    <tr>
                        <td><spring:message text="Document" /></td>
                        <td><form:select path="docId" items="${documents}" cssClass="form-control text-box single-line"/></td>

                    </tr>

                    <tr>
                        <td><spring:message text="Users" /></td>
                        <td><form:select path="userId" items="${users}" cssClass="form-control text-box single-line"/></td>

                    </tr>

                    <tr>
                        <td colspan="3"><input type="submit" name="Save" value="Save"/></td>
                        <td colspan="3"><input type="submit" name="Print" value="Print"/></td>
                    </tr>
                </table>
            </form:form>






<%--
<form:form  commandName="docs" action="/Hr/Generate/"  cssClass="form-horizontal" method="post" >



            <div class="form-horizontal">

                <div class="form-group">
                    <label class="control-label col-md-3">Documents: <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:select path="docId">
                            <form:option value="NONE" label="--- Select ---" />
                            <form:options items="${documents}" />
                        </form:select>
                    </div>
                </div>
            </div>











           &lt;%&ndash; <form name="Generate" action="/Hr/Generate/" method="POST" commandName="docs">
                <table class="table table-default">
                    <thead>
                        <tr>
                            <th>Document name</th>
                            <th>Document type</th>
                            <th>Name</th>
                            <th>Action</th>
                            &lt;%&ndash;<th><i class="fa fa-fw fa-download"></i></th>&ndash;%&gt;
                        </tr>
                    </thead>
                    <tbody>
                        &lt;%&ndash;<c:forEach items="${documents}" var="doc" varStatus="status">&ndash;%&gt;
                            <tr>
                                <td>

                                    <select items="${documents}" value="${docs.docId}"
                                                 cssClass="form-control text-box single-line"/>
                                </td>
                                <td>Docx</td>
                                <td>
                                    &lt;%&ndash;<form:select name="user2" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"  path="userId"><span class="caret"></span>
                                        <option value="NONE">--- Select ---</option>
                                        <c:forEach items="${users}" var="user" varStatus="status">
                                            <option value="${user.key}">${user.value} ${user.key}</option>
                                        </c:forEach>
                                    </form:select>&ndash;%&gt;
                                </td>

                                <td>
                                    <input type="submit" name="Print" value="Print">
                                    <input type="submit" name="Save" value="Save">
                                </td>
                            </tr>
                            &lt;%&ndash;<form:hidden path="id" />&ndash;%&gt;
                        &lt;%&ndash;</c:forEach>&ndash;%&gt;
                    </tbody>
                </table>&ndash;%&gt;
</form:form>
--%>
        </div>
    </div>
</div>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
