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

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
        <div class="col-lg-8 col-lg-offset-2">
            <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
            <h2 class="page-header">Evaluation</h2>
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#listofdocs">List of documents</a></li>
                <%--<li><a data-toggle="tab" href="#gen">Generatable documents</a></li>--%>
            </ul>
            <div class="tab-content">
                <div id="listofdocs" class="tab-pane fade in active">
                    <h3>Evaluation</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Comment</th>
                    <th>Grade</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${evaluationsVM}" var="evaluation" varStatus="status">
                    <tr>
                        <td>${evaluation.date}</td>
                        <td>${evaluation.comments}</td>
                        <td>
                        <c:if test="${evaluation.grade=='1'}">
                            S
                        </c:if>
                            <c:if test="${evaluation.grade=='2'}">
                                A
                            </c:if>
                            <c:if test="${evaluation.grade=='3'}">
                                B
                            </c:if>
                            <c:if test="${evaluation.grade=='4'}">
                                C
                            </c:if>
                            <c:if test="${evaluation.grade=='5'}">
                                D
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

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
    <script>
        function updateSize() {
            var nBytes = 0,
                    oFiles = document.getElementById("fileInput").files,
                    nFiles = oFiles.length;
            for (var nFileId = 0; nFileId < nFiles; nFileId++) {
                nBytes += oFiles[nFileId].size;
            }

            var sOutput = nBytes + " bytes";
            // optional code for multiples approximation
            for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
                sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
            }
            // end of optional code

            document.getElementById("fileNum").innerHTML = nFiles;
            document.getElementById("fileSize").innerHTML = sOutput;
        }
    </script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
