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
            <table id="tableid" class="testgrid">
                <thead>
                <tr>
                    <th class="editablegrid-name" data-title="Document name"><a style="cursor: pointer;">Document name</a></th>
                    <th class="editablegrid-type" data-title="Document type"><a style="cursor: pointer;">Document type</a></th>
                    <th class="editablegrid-username" data-title="Username"><a style="cursor: pointer;">Username</a></th>
                    <th><i class="fa fa-fw fa-download"></i></th>
                </tr>
                </thead>
                <tbody>
                <%--<c:forEach items="${}" var="doc" varStatus="status">--%>
                    <tr>
                        <td class="editablegrid-name" data-title="Document name">Information about Salary</td>
                        <td>Document</td>
                        <td>
                            <%--<div class="dropdown">--%>
                                <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select employee--%>
                                    <%--<span class="caret"></span></button>--%>
                                <%--<ul class="dropdown-menu">--%>
                                    <%--<li><a href="#">Jasur Shaykhov</a></li>--%>
                                    <%--<li><a href="#">Muslimbek Pirnazarov</a></li>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                                <select class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span>
                                    <option value="volvo">Muslimbek</option>
                                    <option value="saab">Jessi</option>
                                    <option value="mercedes">Pakirrreeee</option>
                                    <option value="audi">Akkkkkbaaaal</option>
                                </select>
                        </td>
                        <td><i class="fa fa-fw fa-download"></i></td>
                    </tr>

                    <tr>
                    <td class="editablegrid-name" data-title="Document name">Zet</td>
                    <td>Document</td>
                    <td>
                        <%--<div class="dropdown">--%>
                        <%--<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select employee--%>
                        <%--<span class="caret"></span></button>--%>
                        <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="#">Jasur Shaykhov</a></li>--%>
                        <%--<li><a href="#">Muslimbek Pirnazarov</a></li>--%>
                        <%--</ul>--%>
                        <%--</div>--%>
                        <select class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span>
                            <option value="volvo">Muslimbek</option>
                            <option value="saab">Jessi</option>
                            <option value="mercedes">Pakirrreeee</option>
                            <option value="audi">Akkkkkbaaaal</option>
                        </select>
                    </td>
                    <td><i class="fa fa-fw fa-download"></i></td>
                </tr>
                <%--</c:forEach>--%>
                </tbody>
            </table>
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
