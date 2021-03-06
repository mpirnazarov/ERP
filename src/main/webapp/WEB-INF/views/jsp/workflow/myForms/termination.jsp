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
<c:set var="pageTitle" scope="request" value="Termination"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>
<!-- JS file of autocomplete --><%--
<script src="/resources/core/js/jquery.easy-autocomplete.min.js"></script>

<!-- CSS file of autocomplete -->
<link rel="stylesheet" href="/resources/core/css/easy-autocomplete.min.css" />--%>

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
        width: 20%;
        font-style: italic;
        font-size: 17px;
        border: none;
        background-color: #FFFFFF;
    }

    .paper {
        background: #fff;
        box-shadow: /* The top layer shadow */ 0 -1px 1px rgba(0, 0, 0, 0.15),
            /* The second layer */ 0 -10px 0 -5px #eee,
            /* The second layer shadow */ 0 -10px 1px -4px rgba(0, 0, 0, 0.15),
            /* The third layer */ 0 -20px 0 -10px #eee,
            /* The third layer shadow */ 0 -20px 1px -9px rgba(0, 0, 0, 0.15);
        /* Padding for demo purposes */
        padding: 30px;
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


</style>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>--%>
        <h2 class="page-header" style="border: none; padding-top: 6%"><span class="glyphicon glyphicon-ban-circle"
                                                                            aria-hidden="true"></span> Termination</h2>

        <div class="w3-container b3form">
            <div class="divBody" style="padding: 1% 0 2% 0;">

                <div class="input-group" id="subject-container" style="margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon">Subject:</span>
                    <div class="div" id="subject">
                        <input type="text" class="form-control" value="${termination.subject}"
                               id="termination_subject"/>
                    </div>
                    <span class="glyphicon glyphicon-record" id="error_subject" style="display: none">Description cannot be empty</span>
                </div>

                <div class="input-group" id="desc_container" style="margin-top: 2%">
                    <span class="input-group-addon" id="term_span">Description:</span>
                    <textarea class="form-control" rows="3" id="termination_desc" aria-describedby="purpose-addon"
                              style="width: 100%; border-color:#999999"
                              path="description">${termination.description}</textarea>
                    <span class="glyphicon glyphicon-record" id="error_desc" style="display: none">Description cannot be empty</span>
                </div>

                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon">Assigned approves:</span>
                    <input disabled type="text" class="form-control" value="${termination.approves}"/>
                </div>

                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon">Assigned references:</span>
                    <input disabled type="text" class="form-control" value="${termination.references}"/>
                </div>

                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon">Assigned executives:</span>
                    <input disabled type="text" class="form-control" value="${termination.executives}"/>
                </div>

                <div id="approvals-container" class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon">New approvals:</span>
                    <div class="tab-content" id="approvals1">
                        <input type="text" id="demo-input-local"/>
                        <span class="error text-danger" id="error_approves" style="display: none">Description cannot be empty</span>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon">New executives:</span>
                    <div class="tab-content" id="executives1">
                        <input type="text" id="demo-input-local2"/>
                    </div>
                </div>
                <div class="input-group" style="margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon">New reference:</span>
                    <div class="tab-content" id="references1">
                        <input type="text" id="demo-input-local3"/>
                    </div>
                </div>
            </div>
        </div>

        <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
            <input type="button" value="Submit" onclick="testt()" class="btn btn-success" id="submit_btn"/>
            <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger"/>
        </div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign"
                                                                        aria-hidden="true"></span> Warning</h4>
                    </div>
                    <div class="modal-body">
                        <span id="message"></span>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<%--
<div class="form-group has-warning has-feedback">
    <label class="col-sm-2 control-label" for="inputWarning">Input with warning and glyphicon</label>
    <div class="col-sm-10">
        <input type="text" class="form-control" id="inputWarning">
        <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
    </div>
</div>--%>

<script type="text/javascript">
    var isTrue = false;


    /*$("input[type=submit]").click(function ()*/
    //$("#submit_btn").click(function () {

    function testt() {
        /*Validation*/

        var flag = true;
        msg = "";
        /* Subject cannot be empty*/
        if ($("#termination_subject").val().trim() == "") {
            flag = false;
            msg += "⛔ Subject cannot be empty" + "<br/>";
            $('#termination_subject').css("border", "2px solid red");
            $('#termination_subject').next('span').addClass('glyphicon-info-sign');
        } else {
            $('#termination_subject').css("border", "1px solid #999999");
            $('#termination_subject').next('span').removeClass('glyphicon-info-sign')
        }


        var a = [];
        var b = [];
        var c = [];


        a = $("#approvals1").children().siblings("input[type=text]").val();
        if (a.length == 0) {
            msg += "⛔ At least one approval should be selected" + "<br/>";
            flag = false;
            $('#approvals1').css("border", "2px solid red");
            $('#approvalSpan').addClass('glyphicon-info-sign');
        } else {
            $('#approvals1').css("border", "1px solid #999999");
            $('#approvalSpan').removeClass('glyphicon-info-sign');
        }



        isTrue = flag;

        var old_Id = '${termination.old_req_id}';
        var description = $('#termination_desc').val();
        var subject = $('#termination_subject').val();

        if ($(this).isDisabled) {
            alert("disabled");
        }

        a = $("#approvals1").children().siblings("input[type=text]").val();
        b = $("#references1").children().siblings("input[type=text]").val();
        c = $("#executives1").children().siblings("input[type=text]").val();


        if (flag) {
            $.ajax({
                type: "POST",
                url: "/Workflow/MyForms/cancellation",
                data: 'approvals=' + a + '&references=' + b + '&executives=' + c + '&description=' + description + '&old_id=' + old_Id + '&subject=' + subject,
                success: function (response) {
                    window.location.href = "/Workflow/MyForms/Request";
                },
                error: function (e) {
                    msg += "⛔ JSON Error" + "<br/>";
                    flag = false;
                }
            });
        }

        if (!flag) {
            $('#message').html(msg);
            $('#myModal').modal('show');
        }


        return flag;
    }
    ;


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

    //validation
    $(document).ready(function () {


        $('#termination_subject').blur(function () {
            if ($(this).val().trim() == "") {
                /*$('#error_desc').css("display", "block");*/
                $("#submit_btn").prop("disabled", true);
                $('#subject-container').addClass('has-error has-feedback');
                isTrue = false;

            } else {
                $('#error_subject').css("display", "none");
                $("#submit_btn").prop("disabled", false);
                $('#subject-container').removeClass('has-error has-feedback');
                isTrue = true;
            }
        });

    });


</script>
