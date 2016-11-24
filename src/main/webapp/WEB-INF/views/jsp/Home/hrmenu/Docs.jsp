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
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("FullName", a.getFirstName()[2] + " " + a.getLastName()[2]);
    request.setAttribute("JobTitle", a.getJobTitle());
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
            <h2 class="page-header">Documents</h2>
            <form:form cssClass="form-horizontal" method="post" action="/Hr/Profile/Docs">
            <table class="table table-default">
                <thead>
                <tr>
                    <th>Document name</th>
                    <th>Document type</th>
                    <th>Username</th>
                    <th><i class="fa fa-fw fa-download"></i></th>
                </tr>
                </thead>
                <tbody>
                <%--<c:forEach items="${}" var="doc" varStatus="status">--%>
                    <tr>
                        <td>Certification of Employment</td>
                        <td>Docx</td>
                        <td>
                            <%--<div class="dropdown">--%>
                                <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select employee--%>
                                    <%--<span class="caret"></span></button>--%>
                                <%--<ul class="dropdown-menu">--%>
                                    <%--<li><a href="#">Jasur Shaykhov</a></li>--%>
                                    <%--<li><a href="#">Muslimbek Pirnazarov</a></li>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                                <form name="jump2">
                                <select name="user2" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span>
                                    OnChange="location.href=jump2.user2.options[selectedIndex].value">
                                    <c:forEach items="${users}" var="user" varStatus="status">
                                        <option value="1/${user.key}/">${user.value}</option>
                                    </c:forEach>
                                </select>
                                </form>
                        </td>
                        <td><a href="1/${requestScope.user2}/"><i class="fa fa-fw fa-download"></i></a></td>
                    </tr>
                <%--</c:forEach>--%>
                </tbody>
            </table>
            </form:form>
            <div>
                <form name="uploadingForm" enctype="multipart/form-data" action="/Hr/user/${id}/UploadPic/" method="POST">
                    <p>
                        <input id="fileInput" type="file" name="uploadingFiles" onchange="updateSize();" multiple>
                        selected files: <span id="fileNum">0</span>;
                        total size: <span id="fileSize">0</span>
                    </p>
                    <p>
                        <input type="submit" class="btn btn-success" value="Upload file">
                    </p>
                </form>
                <%--<div>--%>
                <%--<div>Uploaded files:</div>--%>
                <%--<#list files as file>--%>
                <%--<div>--%>
                <%--${file.getName()}--%>
                <%--</div>--%>
                <%--</#list>--%>
                <%--</div>--%>
            </div>
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
