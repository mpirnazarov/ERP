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

    .w3-container {
        background-color: #FFFFFF;

        -webkit-box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.62);
        -moz-box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.62);
        box-shadow: 0px 10px 20px 0px rgba(0, 0, 0, 0.62);
    }

    .w3-container input {
        width: 98%;

        border: 1px solid #999999;

    }

    .w3-container span {
        width: 17%;
        font-style: italic;
        font-size: 17px;
        border: none;
        background-color: #FFFFFF;
    }

    .paper {
        background-color: #fff;
        /* Need position to allow stacking of pseudo-elements */
        position: relative;
        /* Padding for demo purposes */
        padding: 30px;
    }

    .paper,
    .paper::before,
    .paper::after {
        /* Add shadow to distinguish sheets from one another */
        box-shadow: 2px 1px 1px rgba(0,0,0,0.15);
    }

    .paper::before,
    .paper::after {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        background-color: #eee;
    }

    /* Second sheet of paper */
    .paper::before {
        left: 7px;
        top: 5px;
        z-index: -1;
    }

    /* Third sheet of paper */
    .paper::after {
        left: 12px;
        top: 10px;
        z-index: -2;
    }

    div .input-group {
        width: 98%;
    }

    #buttonGroupcha {
        margin-left: 40%;
        margin-top: 2%;
    }

    #buttonGroupcha input {
        width: 71px;
        margin-left: 8px;

    }

    .reqfield:before {
        content: "*";
        color: #ff0000;
    }

    .w3-container span.warningIcon {

        position: absolute;
        color: #ff0000;
        width: 0%;
        font-style: normal;
        margin-left: 6px;

    }

    .warningBorder {

    }






</style>



<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="page-header" style="border: none; padding-top: 6%"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> Leave approve</h2>

        <form:form modelAttribute="leaveApproveVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form paper">
            <div class="form-header" style="padding-top: 1%">

                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"><span class="reqfield"></span>Absence type:</span>
                    <form:select id="absenseSelect" class="form-control" aria-describedby="saerchtype-addon" style="width: 100%; border-color:#999999" path="absenceType" items="${absenceType}">
                    </form:select>
                    <span class="glyphicon warningIcon " aria-hidden="true"></span>
                </div>

                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon">Description</span>
                    <form:textarea class="form-control warningBorder" rows="3" id="comment" aria-describedby="purpose-addon"
                              style="width: 100%; border-color:#999999" path="description"></form:textarea>
                </div>
            </div>
            <div class="form-footer" style="padding-bottom: 2%">
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"><span class="reqfield"></span>Approvals</span>
                    <div class="tab-content" id="approvals">
                        <span style="margin-left: 83.7%" id="approvalSpan" class="glyphicon warningIcon " aria-hidden="true"></span>
                        <input type="text" id="demo-input-local"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon">Executive</span>
                    <div class="tab-content" id="executives">
                        <input type="text" id="demo-input-local2"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon">Reference</span>
                    <div class="tab-content" id="references">
                        <input type="text" id="demo-input-local3"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open>Attachment</span>
                    <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                    <span class="glyphicon warningIcon " aria-hidden="true"></span>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="date-addon"><span class="reqfield"></span>Date(Start/End)</span>
                    <form:input type="date" class="form-control" style="width:50%" name="start" id="dateStart" path="start"/>
                    <form:input type="date" class="form-control" style="width:50%" name="end" id="dateEnd" path="end"/>
                    <span class="glyphicon warningIcon" aria-hidden="true"></span>
                </div>
            </div>
        </div>

            <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
                <input id="tv2" type="submit" name="Save" value="Save" class="btn btn-warning"/>
                <input id="tv" type="submit" name="Submit" value="Submit" class="btn btn-success"/>
                <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger" />
            </div>
        </form:form>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> Warning</h4>
                        </div>
                        <div class="modal-body">
                            <span id="message"></span>
                        </div>
                    </div>
                </div>
            </div>
    </div>

</div>



<script>


    $(document).ready(function () {
    })
</script>

<script type="text/javascript">
    /* Send input from approval list to controller by AJAX */
    $(document).ready(function() {

        var today = new Date();
        var month = today.getMonth().toString().length == 1 ? "0" + today.getMonth().toString() : today.getMonth().toString();
        var dayOfMonth = today.getDate().toString().length == 1 ? "0" + today.getDate().toString() : today.getDate().toString();
        var todayString = today.getFullYear() + "-" + month + "-" + dayOfMonth;
       /* $('#dateStart').val(todayString);
        $('#dateEnd').val(todayString);*/


        var msg = "";

        $("#tv").click(function (){
            var flag = true;
            msg = "";
            /* Subject cannot be empty*/
            if($("#absenseSelect option:selected").text() == ""){
                $('#absenseSelect').css("border","2px solid red");
                $('#absenseSelect').next('span').addClass('glyphicon-info-sign')
                flag = false;
                msg += "⛔ Absence type cannot be empty" + "<br/>";
            }else {
                $('#absenseSelect').css("border", "1px solid #999999");
                $('#absenseSelect').next('span').removeClass('glyphicon-info-sign')
            }

            /* Comments cannot be empty*/
            /*if($("#comment").val().trim() == ""){
                flag = false;
                msg += "Comment cannot be empty \n";
            }*/

            /* file size limitation */
            if($("#file").val().trim() != "") {
                var size = 0;
                input = document.getElementById('file');
                for (var i = 0; i < input.files.length; i++) {
                    size += input.files[0].size;
                }
                if (size > 104857600) {
                    flag = false;
                    msg += "⛔ Attached files cannot be more than 100MB" + "<br/>";
                    $('#file').css("border","2px solid red");
                    $('#file').next('span').addClass('glyphicon-info-sign');
                }else {
                    $('#file').css("border", "1px solid #999999");
                    $('#file').next('span').removeClass('glyphicon-info-sign')
                }
            }

            /* Start date cannot be more than end date*/

            var dStart = new Date($("#dateStart").val());
            var dEnd = new Date($("#dateEnd").val());

            if(dStart > dEnd){
                flag = false;
                msg += "⛔ Start date cannot be later than end date" + "<br/>";
                $('#dateStart').css("border","2px solid red");
                $('#dateEnd').css("border","2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#dateStart').css("border", "1px solid #999999");
                $('#dateEnd').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
            }


            /* Date start cannot be empty */
            if($("#dateStart").val().trim() == "") {
                flag = false;
                msg += "⛔ Start date cannot be empty" + "<br/>";
                $("#dateStart").css("border","2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            }else {
                $('#dateStart').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
            }

            /* Date end cannot be empty */
            if($("#dateEnd").val().trim() == "") {
                flag = false;
                msg += "⛔ End date cannot be empty" + "<br/>";
                $('#dateEnd').css("border","2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            }else {
                $('#dateEnd').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
            }

            var a=[];
            var b=[];
            var c=[];

            a = $("#approvals").children().siblings("input[type=text]").val();
            if(a.length == 0)
            {
                msg += "⛔ At least one approval should be selected" + "<br/>";
                flag = false;
                $('#approvals').css("border","2px solid red");
                $('#approvalSpan').addClass('glyphicon-info-sign');
            } else {
                $('#approvals').css("border", "1px solid #999999");
                $('#approvalSpan').removeClass('glyphicon-info-sign');
            }

                alert(flag);
            if (flag != false){
                $('#message').html(msg);
                $('#myModal').modal('show');
            }


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
<script>
    var isTrue = true;

    function validateFile() {
        /* file size limitation */
        if($("#file").val().trim() != "") {
            var size = 0;
            input = document.getElementById('file');
            for (var i = 0; i < input.files.length; i++) {
                size += input.files[0].size;
            }
            if (size > 104857600) {
/*
                msg += "⛔ Attached files cannot be more than 100MB" + "<br/>";
*/
                $('#file').css("border","2px solid red");
                $('#file').next('span').addClass('glyphicon-info-sign');
                flag = false;
                isTrue = false;
            }else {
                $('#file').css("border", "1px solid #999999");
                $('#file').next('span').removeClass('glyphicon-info-sign');
                flag = true;
                isTrue = true;
            }
        }
    }

    $("#tv2").click(function () {
        var a=[];
        var b=[];
        var c=[];
        a = $("#approvals").children().siblings("input[type=text]").val();
        b = $("#references").children().siblings("input[type=text]").val();
        c = $("#executives").children().siblings("input[type=text]").val();


        var dEnd = $("#dateEnd").datepicker({format: "mm-dd-yyyy"}).val();
        var dStart = $("#dateStart").datepicker({format: "mm-dd-yyyy"}).val();

        var year = "1111";
        var month = "11";
        var dayOfMonth = "11";
        var todayString = year + "-" + month + "-" + dayOfMonth;

        validateFile();

        if(dStart==""&&isTrue){
            $("#dateStart").val(todayString);
        }

        if(dEnd==""&&isTrue){
            $("#dateEnd").val(todayString);
        }

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

     /*   $(':input').each(function() {
            if ($(this).val() !== '') {
                alert($(this).val());
            }
        });*/

        return isTrue;
    });
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
