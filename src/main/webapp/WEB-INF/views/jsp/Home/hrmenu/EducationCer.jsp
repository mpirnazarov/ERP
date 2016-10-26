<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Education certificates"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header">Education Certificate</h1>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#edu">Educations</a></li>
                <li><a data-toggle="tab" href="#langsum">Language summary</a></li>
                <li><a data-toggle="tab" href="#cersum">Certificates</a></li>
            </ul>

            <div class="tab-content">
                <div id="edu" class="tab-pane fade in active">
                    <h3>Educations</h3>
                </div>
                <div id="langsum" class="tab-pane fade">
                    <h3>Language summary</h3>
                    <p>Some content in menu 1.</p>
                </div>
                <div id="cersum" class="tab-pane fade">
                    <h3>Certificates</h3>
                    <p>Some content in menu 1.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
