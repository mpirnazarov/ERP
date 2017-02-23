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
<c:set var="pageTitle" scope="request" value="Leave approve"/>
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

    div.input-group span.input-group-addon {
        width: 19%;
    }

    .w3-container {
        width: 63%;
        height: 90%;
        margin-left: auto;
        margin-right: auto;
    }


</style>



<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Leave approve</h2>

        <form:form modelAttribute="leaveApproveVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form">
            <div class="form-header">

                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon">Absence type:</span>
                    <form:select class="form-control" aria-describedby="saerchtype-addon" style="width: 40%" path="absenceType" items="${absenceType}">
                    </form:select>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon">Description</span>
                    <form:textarea class="form-control" rows="3" id="comment" aria-describedby="purpose-addon"
                              style="width: 100%" path="description"></form:textarea>
                </div>
            </div>
            <div class="form-footer" style="margin-bottom: 5%">
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon">Approvals</span>
                    <div class="tab-content" id="approvals">
                        <input type="text" id="demo-input-local"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon">Executive</span>
                    <div class="tab-content" id="executives">
                        <input type="text" id="demo-input-local2"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon">Reference</span>
                    <div class="tab-content" id="references">
                        <input type="text" id="demo-input-local3"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open>Attachment</span>
                    <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="date-addon">Date(Start/End)</span>
                    <form:input type="date" class="form-control" style="width:36%" name="start" id="dateStart" path="start"/>
                    <form:input type="date" class="form-control" style="width:36%" name="end" id="dateEnd" path="end"/>
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

        var flag = true;
        var msg = "";
        $("#tv").click(function (){

            /* Subject cannot be empty*/
            if($("#absenceType").val().trim() == ""){
                flag = false;
                msg += "Absence type cannot be empty \n";
            }

            /* Comments cannot be empty*/
            if($("#comment").val().trim() == ""){
                flag = false;
                msg += "Comment cannot be empty \n";
            }

            /* file size limitation */
            if($("#file").val().trim() != "") {
                var size = 0;
                input = document.getElementById('file');
                for (var i = 0; i < input.files.length; i++) {
                    size += input.files[0].size;
                }
                if (size > 104857600) {
                    flag = false;
                    msg += "Attached files cannot be more than 100MB \n";
                }
            }


            /* Date start cannot be empty */
            if($("#dateStart").val().trim() == "") {
                flag = false;
                msg += "Start date cannot be empty \n";
            }

            /* Date end cannot be empty */
            if($("#dateEnd").val().trim() == "") {
                flag = false;
                msg += "End date cannot be empty \n";
            }
            var dStart = new Date($("#dateStart").val());
            var dEnd = new Date($("#dateEnd").val());

            /* Start date cannot be more than end date*/
            if(dStart > dEnd){
                flag = false;
                msg += "Start date cannot be later than end date \n";
            }


            var a=[];
            var b=[];
            var c=[];

            a = $("#approvals").children().siblings("input[type=text]").val();
            if(a.length == 0)
            {
                msg += "At least one approval should be selected \n";
                flag = false;
            }
            if(msg != "")
                alert(msg);
            msg = "";



            b = $("#references").children().siblings("input[type=text]").val();
            c = $("#executives").children().siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/NewForm/LeaveApproveFormAjax",
                data :'approvals='+a+'&references='+b+'&executives='+c,
                success : function(response) {
//                    window.location.href = "/Workflow/NewForm/BusinessTripForm"
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });
            return flag;
        });
    });

    /* Send json data for approvals list*/
    $(document).ready(function () {
        $("#demo-input-local").tokenInput(${jsonData});
        $("#demo-input-local2").tokenInput(${jsonData});
        $("#demo-input-local3").tokenInput(${jsonData});
    });
</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
