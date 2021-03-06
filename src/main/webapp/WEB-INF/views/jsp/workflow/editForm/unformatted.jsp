<%@ page import="java.util.Date" %>
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
        background: #fff;
        box-shadow:
            /* The top layer shadow */
                0 -1px 1px rgba(0,0,0,0.15),
                    /* The second layer */
                0 -10px 0 -5px #eee,
                    /* The second layer shadow */
                0 -10px 1px -4px rgba(0,0,0,0.15),
                    /* The third layer */
                0 -20px 0 -10px #eee,
                    /* The third layer shadow */
                0 -20px 1px -9px rgba(0,0,0,0.15);
        /* Padding for demo purposes */
        padding: 30px;
    }

    div .input-group {
        width: 98%;
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


</style>



    <div class="mainBodyBlock">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="headerText"><span class="glyphicon glyphicon-th" aria-hidden="true"></span> Unformatted {EDIT}</h2>

        <form:form modelAttribute="unformattedVM" cssClass="form-horizontal" method="post" id="myform" enctype="multipart/form-data">
            <div class="w3-container b3form">
                <div class="form-header" style="padding-top: 1%">

                    <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon">Subject:</span>
                            <form:input type="text" class="form-control" path="subject" id="subject"/>
                        <span class="glyphicon warningIcon" aria-hidden="true"></span>
                    </div>
                    <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon">Description:</span>
                        <form:textarea class="form-control" rows="3" id="comment" aria-describedby="purpose-addon"
                                       style="width: 100%; border-color:#999999" path="description"></form:textarea>
                    </div>
                </div>
                <div class="form-footer" style="padding-bottom: 2%">

                    <%-- Approvals, references, executors shouldn't be edited --%>
                    <div class="input-group" style="margin-top: 1%">
                        <span class="input-group-addon" id="attach-addon">Attached files:</span>
                        <div id="attachmentDiv" >

                            <c:if test="${empty unformattedVM.attachments}">
                                <p style="color:#000000;">
                                    No attachment
                                </p>
                            </c:if>
                            <c:if test="${not empty unformattedVM.attachments}">
                                <c:forEach items="${unformattedVM.attachments}" var="attachment">
                                    <p >
                                        <a style="color: #000" href="/Workflow/MyForms/files/${attachment.id}">${attachment.fileName}</a>
                                        <a style="color: #000" href="/Workflow/EditForm/files/delete/${attachment.id}"><span style="color: #ff0000; font-style: normal" class="glyphicon glyphicon-remove-sign" aria-hidden="false"></span></a>
                                    </p>
                                </c:forEach>
                            </c:if>

                        </div>
                    </div>
                    <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open>New attachment:</span>
                        <form:input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                        <span class="glyphicon warningIcon" aria-hidden="true"></span>
                    </div>
                    <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="date-addon">Date(Start/End):</span>
                        <form:input type="text" class="form-control" style="width:50%" name="start" id="dateStart" path="start"/>
                        <form:input type="text" class="form-control" style="width:50%" name="end" id="dateEnd" path="end"/>
                        <span class="glyphicon warningIcon" aria-hidden="true"></span>
                    </div>
                </div>
            </div>

            <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
                <input id="tv" type="submit" name="submitUnformatted" value="Submit" class="btn btn-green"/>
                <input type="button" onclick="history.back()" value="Cancel" class="btn btn-red" />
            </div>

        </form:form>

        <%--alertModal--%>
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






<script type="text/javascript">

    /* Send input from approval list to controller by AJAX */
    $(document).ready(function() {

        $('#dateStart').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
        $('#dateEnd').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});

        globalFlag = false;

        $(document).ready(function() {
            $(window).keydown(function(event){
                if( (event.keyCode == 13) && (globalFlag == false) ) {
                    event.preventDefault();
                    return false;
                }
            });
        });


        $("#tv").click(function (){
            var flag = true;
            var msg = "";

            /* Subject cannot be empty*/


            if ($("#subject").val().trim() == "") {
                flag = false;
                msg += "⛔ Subject cannot be empty" + "<br/>";
                $('#subject').css("border","2px solid red");
                $('#subject').next('span').addClass('glyphicon-info-sign');
            }else {
                $('#subject').css("border", "1px solid #999999");
                $('#subject').next('span').removeClass('glyphicon-info-sign')

                if ($("#subject").val().length > 50){
                    msg += "⛔ Subject cannot be more than 50 characters" + "<br/>";
                    flag = false
                    $('#subject').next('span').addClass('glyphicon-info-sign')
                    $(this).css("border", "2px solid red");
                }else {
                    $(this).css("border", "1px solid #999999");
                    $('#subject').next('span').removeClass('glyphicon-info-sign')
                }
            }

            if ($("#comment").val().length > 600) {
                flag = false;
                msg += "⛔ Description can not be more than 600 characters" + "<br/>";
                $('#comment').css("border", "2px solid red");
                $('#comment').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#comment').css("border", "1px solid #999999");
                $('#comment').next('span').removeClass('glyphicon-info-sign')
            }




            /* file size limitation */
            if ($("#file").val().trim() != "") {
                var size = 0;
                input = document.getElementById('file');
                for (var i = 0; i < input.files.length; i++) {
                    size += input.files[0].size;
                }
                if (size > 104857600) {
                    flag = false
                    msg += "⛔ Attached files cannot be more than 100MB" + "<br/>";
                    $('#file').css("border","2px solid red");
                    $('#file').next('span').addClass('glyphicon-info-sign');
                }else {
                    $('#file').css("border", "1px solid #999999");
                    $('#file').next('span').removeClass('glyphicon-info-sign')
                }
            }

            var dStart = new Date($("#dateStart").val());
            var dEnd = new Date($("#dateEnd").val());


            /* Start date cannot be more than end date*/
            if (dStart > dEnd) {
                flag = false;
                msg += "⛔ Start date cannot be later than end date" + "<br/>";
                $('#dateStart').css("border","2px solid red");
                $('#dateEnd').css("border","2px solid red");
                $('#dateEnd').next('span').addClass('glyphicon-info-sign');
            } else {
                $('#dateStart').css("border", "1px solid #999999");
                $('#dateEnd').css("border", "1px solid #999999");
                $('#dateEnd').next('span').removeClass('glyphicon-info-sign');

                /* Date start cannot be empty */
                if ($("#dateStart").val().trim() == "") {
                    flag = false;
                    msg += "⛔ Start date cannot be empty" + "<br/>";
                    $("#dateStart").css("border","2px solid red");
                    $('#dateEnd').next('span').addClass('glyphicon-info-sign');
                }else {
                    $('#dateStart').css("border", "1px solid #999999");
                    $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
                }

                /* Date end cannot be empty */
                if ($("#dateEnd").val().trim() == "") {
                    flag = false;
                    msg += "⛔ End date cannot be empty" + "<br/>";
                    $('#dateEnd').css("border","2px solid red");
                    $('#dateEnd').next('span').addClass('glyphicon-info-sign');
                }else {
                    $('#dateEnd').css("border", "1px solid #999999");
                    $('#dateEnd').next('span').removeClass('glyphicon-info-sign');
                }
            }




            if (!flag){
                $('#message').html(msg);
                $('#myModal').modal('show');
            }

            globalFlag = flag;

            return flag;


            var a=[];
            var b=[];
            var c=[];

            a = $("#approvals").children().siblings("input[type=text]").val();
            b = $("#references").children().siblings("input[type=text]").val();
            c = $("#executives").children().siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/NewForm/UnformattedFormAjax",
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
        $("#demo-input-local2").tokenInput(${jsonData});
        $("#demo-input-local3").tokenInput(${jsonData});

    });





</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
