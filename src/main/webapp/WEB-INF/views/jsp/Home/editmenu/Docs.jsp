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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Documents"/>
<% request.setAttribute("Mode", 2); %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1>${fullName}, ${jobTitle}</h1>
        <h2 class="page-header">Documents</h2>
        <h3>List of documents</h3>
        <table class="table">
            <thead>
            <tr>
                <th class="col-md-4">Document name</th>
                <th class="col-md-3">Document type</th>
                <th class="col-md-3 text-center">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${documents}" var="doc" varStatus="status">
                <tr>
                    <td>${doc.name}</td>
                    <td>${doc.documentType}</td>
                    <td class="text-center"><a href="Docs/Download/<c:out value="${doc.id}"/>" class="btn btn-primary">Download</a> <a
                            href="Docs/Del/<c:out value="${doc.id}"/>" class="btn btn-danger">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h3>Upload document</h3>

        <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="file">Upload a file</label>
                    <div class="col-md-7">
                        <form:input cssClass="form-control text-box" path="name" required="true"/>
                        <form:input type="text" cssClass="form-control text-box" path="type" required="true"/>
                        <form:input type="file" path="file" id="file" class="form-control input-sm" required="true"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-actions floatRight">
                    <input type="submit" value="Upload" class="btn btn-primary btn-sm">
                </div>
            </div>
        </form:form>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>