<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Project history"/>
<% request.setAttribute("Mode", 1); %>
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfileUser");
    request.setAttribute("FullName2", a.getFirstName()[0] + " " + a.getLastName()[0]);

%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>
        </h1>
        <h2 class="page-header">Project History</h2>
        <table class="table">
            <thead>
            <tr>
                <th>Project name</th>
                <th>PM</th>
                <th>Description (Role)</th>
                <th>Period</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Single Portal of e-Government Services</td>
                <td>DAE-OC KIM</td>
                <td>Developer</td>
                <td>2016-11-01</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<%--<script>--%>
    <%--function updateSize() {--%>
        <%--var nBytes = 0,--%>
                <%--oFiles = document.getElementById("fileInput").files,--%>
                <%--nFiles = oFiles.length;--%>
        <%--for (var nFileId = 0; nFileId < nFiles; nFileId++) {--%>
            <%--nBytes += oFiles[nFileId].size;--%>
        <%--}--%>

        <%--var sOutput = nBytes + " bytes";--%>
        <%--// optional code for multiples approximation--%>
        <%--for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {--%>
            <%--sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";--%>
        <%--}--%>
        <%--// end of optional code--%>

        <%--document.getElementById("fileNum").innerHTML = nFiles;--%>
        <%--document.getElementById("fileSize").innerHTML = sOutput;--%>
    <%--}--%>
<%--</script>--%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
