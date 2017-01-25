<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
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
<c:set var="pageTitle" scope="request" value="Details"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<style>
    body :not(a) {
        color: inherit;
    }

    .btn span.glyphicon {
        opacity: 0;
    }

    .btn.active span.glyphicon {
        opacity: 1;
    }

</style>

<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Details</h2>

        <div class="w3-container col-md-12 b3form">

            <div class="row">
                <div class="col-md-6 left-info">.col-md-6</div>
                <div class="col-md-6 right-info">
                    <div class="input-group" style="width: 100%">
                        <span class="input-group-addon" id="type-addon" style="width: 35%">Form type:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="type-addon"
                               style="width: 80%">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="author-addon" style="width: 35%">Author:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="author-addon"
                               style="width: 80%">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="creation-addon" style="width: 35%">Creation date:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="creation-addon"
                               style="width: 80%">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="ex-addon" style="width: 35%">Executive:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="ex-addon"
                               style="width: 80%">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="referenced-addon" style="width: 35%">Referenced:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="referenced-addon"
                               style="width: 80%">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="attach-addon" style="width: 35%">Attachment:</span>
                        <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-save"
                                                                            aria-hidden="true"></span>Download
                        </button>
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 4%; margin-left: 8%">
                        <div class="btn-group" data-toggle="buttons" style="width: 100%">
                            <label class="btn btn-success active" style="width: 27%">
                                Approve
                                <input type="radio" name="options" id="option1" autocomplete="off" chacked>
                                <span class="glyphicon glyphicon-ok"></span>
                            </label>

                            <label class="btn btn-danger" style="width: 27%">
                                Reject
                                <input type="radio" name="options" id="option2" autocomplete="off">
                                <span class="glyphicon glyphicon-ok"></span>
                            </label>

                            <label class="btn btn-warning" style="width: 27%">
                                Revice
                                <input type="radio" name="options" id="option3" autocomplete="off">
                                <span class="glyphicon glyphicon-ok"></span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>



        </div>
    </div>
</div>

<script>
    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

