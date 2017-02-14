<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
<%--
  Created by IntelliJ IDEA.
  User: Muslimbek Pirnazarov
  Date: 7-Feb-17
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Unformatted"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>
<!-- JS file of autocomplete -->
<script src="/resources/core/js/jquery.easy-autocomplete.min.js"></script>

<!-- CSS file of autocomplete -->
<link rel="stylesheet" href="/resources/core/css/easy-autocomplete.min.css" />

<%--<script src="/resources/core/js/jquery-1.12.4.min.js"></script>--%>

<style>
    body :not(a) {
        color: inherit;
    }


</style>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Unformatted</h2>

        <form:form modelAttribute="unformattedVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form">
                <div class="form-header">

                    <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"
                          style="width: 25%">Subject:</span>
                        <div class="input-group" id="">
                            <form:input type="text" class="form-control" path="subject"/>
                        </div>
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon"
                          style="width: 25%">Description:</span>
                        <form:textarea class="form-control" rows="15" id="comment" aria-describedby="purpose-addon"
                                       style="width: 60%" path="description"></form:textarea>
                    </div>
                </div>
                <div class="form-footer" style="margin-bottom: 5%">
                    <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"
                          style="width: 25%">Approvals:</span>
                        <div class="tab-content" id="approvals">
                            <input type="text" id="demo-input-local"/>
                        </div>
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon"
                          style="width: 25%">Executive:</span>
                        <div class="tab-content" id="executives">
                            <input type="text" id="demo-input-local2"/>
                        </div>
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon"
                          style="width: 25%">Reference:</span>
                        <div class="tab-content" id="references">
                            <input type="text" id="demo-input-local3"/>
                        </div>
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open
                          style="width: 25%">Attachment:</span>
                        <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="date-addon"
                          style="width: 25%">Date(Start/End):</span>
                        <input type="date" class="form-control" style="width:36%" placeholder="Start" name="start" />
                        <input type="date" class="form-control" style="width:36%" placeholder="End" name="end" />
                    </div>
                    <div class="btn-group" role="group" aria-label="..." style="margin-left: 40%; margin-top: 3%">
                        <input id="tv2" type="submit" name="Save" value="Save" class="btn btn-success"/>
                        <input id="tv" type="submit" name="Submit" value="Submit" class="btn btn-success"/>
                        <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger" />
                    </div>
                </div>
            </div>
        </form:form>
    </div>

</div>




<script type="text/javascript">

    /* Send input from approval list to controller by AJAX */
    $(document).ready(function() {
        /*$("input[type=submit]").click(function ()*/
        $("#tv").click(function (){
            var a=[];
            var b=[];
            var c=[];

            a = $("#approvals").children().siblings("input[type=text]").val();
            b = $("#references").children().siblings("input[type=text]").val();
            c = $("#executives").children().siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/NewForm/BusinessTripFormAjax",
                data :'approvals='+a+'&references='+b+'&executives='+c,
                success : function(response) {
//                    window.location.href = "/Workflow/NewForm/BusinessTripForm"
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });
        });
    });

    /* Send json data for approvals list*/
    $(document).ready(function () {
        $("#demo-input-local").tokenInput(${jsonData});
    });
    $(document).ready(function () {
        $("#demo-input-local2").tokenInput(${jsonData});
    });
    $(document).ready(function () {
        $("#demo-input-local3").tokenInput(${jsonData});
    });
</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
