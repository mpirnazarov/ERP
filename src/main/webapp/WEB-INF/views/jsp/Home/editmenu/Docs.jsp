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
<c:set var="pageTitle" scope="request" value="Home"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <h2 class="page-header">Upload documents</h2>
                <h3>List of documents</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Document name</th>
                        <th>Document type</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${docsVM}" var="doc" varStatus="status">
                        <tr>
                            <td>${doc.name}</td>
                            <td>${doc.type}</td>
                            <td><a href="" class="btn btn-danger">Delete</a><a
                                    href="Docs/download?id=<c:out value="${doc.docId}"/>"><i
                                    class="fa fa-fw fa-download"></i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h3>Upload document</h3>

                <form name="uploadingForm" enctype="multipart/form-data" action="/Hr/user/${id}/UploadPic/"
                      method="POST">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-md-5">
                                <input id="fileInput" class="single-line" type="file" name="uploadingFiles"
                                       onchange="updateSize();"
                                       multiple>
                                selected files: <span id="fileNum">0</span>;
                                total size: <span id="fileSize">0</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-5">
                                <input type="submit" class="btn btn-success" value="Upload files">
                            </div>
                        </div>
                    </div>
                </form>

                <div>
                    <div>Uploaded files:</div>
                    <#list files as file>
                        <div>
                            ${file.getName()}
                        </div>
                    </#list>
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