<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="pageTitle" scope="request" value="Documents"/>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%
    String b = request.getAttribute("name").toString();
    request.setAttribute("ProfileModel", b);
%>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("FullName", a.getFirstName()[2] + " " + a.getLastName()[2]);
    request.setAttribute("JobTitle", a.getJobTitle());
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
            <h2 class="page-header">Documents</h2>
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#listofdocs">List of documents</a></li>
                <%--<li><a data-toggle="tab" href="#gen">Generatable documents</a></li>--%>
            </ul>
            <div class="tab-content">
                <div id="listofdocs" class="tab-pane fade in active">
                    <h3>List of documents</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>Document name</th>
                    <th>Document type</th>
                    <th>Download</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${docsVM}" var="doc" varStatus="status">
                    <tr>
                        <td>${doc.name}</td>
                        <td>${doc.type}</td>
                        <td><a href="Docs/download?id=<c:out value="${doc.docId}"/>">download</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%--<h3>File Upload:</h3>
            <form:form method="POST" commandName="fileUploadForm"
                       enctype="multipart/form-data">

                <form:errors path="*" cssClass="errorblock" element="div" />

                Please select a file to upload : <input type="file" name="file" />
                <input type="submit" value="upload" />
                <span><form:errors path="file" cssClass="error" />
                </span>

            </form:form>--%>
                </div>
                <%--<div id="gen" class="tab-pane fade">
                    <h3>Generatable documents</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Document name</th>
                            <th>Document language</th>
                        </tr>
                        </thead>
                        <tbody>
                        &lt;%&ndash;<c:forEach items="${}" var="doc" varStatus="status">&ndash;%&gt;
                            <tr>
                                <td>sdfsdfsdf</td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">Choose language
                                            <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">EN</a></li>
                                            <li><a href="#">RU</a></li>
                                            <li><a href="#">UZ</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        &lt;%&ndash;</c:forEach>&ndash;%&gt;
                        </tbody>
                    </table>
                </div>--%>
        </div>
    </div>
</div>
            </div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
