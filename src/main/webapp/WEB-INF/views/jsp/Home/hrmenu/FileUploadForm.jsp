<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%--
  Created by IntelliJ IDEA.
  User: JAS SHAYKHOV
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Upload picture"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <h1>Upload your file</h1>
                <form method="post" action="/uploadFile" enctype="multipart/form-data">
                    <table border="0">
                        <tr>
                            <td>Description:</td>
                            <td><input type="text" name="description" size="50"/></td>
                        </tr>
                        <tr>
                            <td>Pick file #1:</td>
                            <td><input type="file" name="fileUpload" size="50"/></td>
                        </tr>
                        <tr>
                            <td>Pick file #2:</td>
                            <td><input type="file" name="fileUpload" size="50"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Upload"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>