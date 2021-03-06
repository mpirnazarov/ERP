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

            <div class="mainBodyBlock">

                <h2 class="headerText"><span class="fa fa-upload"></span>Upload documents</h2>
                <h3>List of documents</h3>
                <table class="table sartable table-bordered">
                    <thead>
                    <tr>
                        <th>Document name</th>

                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${documents}" var="doc" varStatus="status">
                        <tr>
                            <td>${doc.name}</td>
                            <td><a href="Manage/Del/<c:out value="${doc.id}"/>" class="btn btn-red">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br/>
                <h3>Upload document</h3>

                <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-3">Document name: </label>
                            <div class="col-md-5">
                                <form:input cssClass="form-control text-box" path="name" required="true"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">Choose file: </label>
                            <div class="col-md-5">
                                <form:input type="file" path="file" id="file" class="form-control input-sm" required="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-3"></div>
                            <div class="col-md-2">
                                <input type="submit" value="Upload" class="btn btn-darkblue btn-sm">
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>

    </div>
</div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
