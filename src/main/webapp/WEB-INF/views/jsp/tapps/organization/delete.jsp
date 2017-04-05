<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 03.10.2016
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Delete customer organizations"/>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">
        <h1 class="headerText">Delete customer organizations</h1>
        <form:form modelAttribute="viewModel" cssClass="form-horizontal" method="post" action="/Organizations/Delete"
                   id="myForm">

            <br/>

            <form:hidden path="id"/>

            <div class="row">
                <div class="form-group">
                    <label class="control-label col-md-3">Name <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:input path="name" cssClass="form-control text-box single-line" disabled="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">TIN <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:input path="tin" cssClass="form-control text-box" disabled="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3">Address <font color='red'>*</font></label>
                    <div class="col-lg-5">
                        <form:textarea path="address" cssClass="form-control" disabled="true"/>
                    </div>
                </div>
            </div>

            <hr/>

            <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                    <input type="submit" value="Delete" class="btn btn-red"/>
                    <input type="button" onclick="location.href='/Organizations'" value="Cancel"
                           class="btn btn-grey"/>
                </div>
                <div class="clearfix"></div>
            </div>
        </form:form>
    </div>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">TAPPS</h4>
            </div>
            <div class="modal-body">
                <span id="message">
                    <c:forEach items="${errors}" var="error" varStatus="i">
                        <c:out value="${error.defaultMessage.toString()}"/> <br/>
                    </c:forEach>
                    <c:out value="${error}"/> <br/>
                </span>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        if ($.trim($('#message').text()) != '') {
            $('#myModal').modal('show');
        }
    });
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>