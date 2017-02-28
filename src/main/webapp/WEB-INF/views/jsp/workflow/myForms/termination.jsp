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

                <div class="input-group" style="margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon">Subject:</span>
                    <div class="div" id="subject">
                        <input type="text" class="form-control" value="${termination.subject}"
                               id="termination_subject"/>
                    </div>
                </div>

                <div class="input-group" id="desc_container" style="margin-top: 2%">
                    <span class="input-group-addon" id="term_span">Description:</span>
                    <textarea class="form-control" rows="3" id="termination_desc" aria-describedby="purpose-addon"
                              style="width: 100%; border-color:#999999" path="description">${termination.description}</textarea>
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

                <div class="input-group" style="margin-top: 2%">
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
                <input type="button" value="Submit" class="btn btn-success" id="submit_btn"/>
                <input type="button" onclick="history.back()" value="Cancel" class="btn btn-danger"/>
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
    $(document).ready(function () {
        /*$("input[type=submit]").click(function ()*/
        $("#submit_btn").click(function () {
            var a = [];
            var b = [];
            var c = [];

            var old_Id = '${termination.old_req_id}';
            var description = $('#termination_desc').val();
            var subject = $('#termination_subject').val();

            if ($(this).isDisabled) {
                alert("disabled");
            }
            alert(description + old_Id);

            a = $("#approvals1").children().siblings("input[type=text]").val();
            b = $("#references1").children().siblings("input[type=text]").val();
            c = $("#executives1").children().siblings("input[type=text]").val();
            $.ajax({
                type: "POST",
                url: "/Workflow/MyForms/cancellation",
                data: 'approvals=' + a + '&references=' + b + '&executives=' + c + '&description=' + description + '&old_id=' + old_Id + '&subject=' + subject,
                success: function (response) {
                    window.location.href = "/Workflow/MyForms/Request";
                },
                error: function (e) {
                    alert('Error: ');
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

    //validation
    $(document).ready(function () {
        $('#termination_desc').blur(function () {
            if ($(this).val().trim() == "") {
                /*$('#error_desc').css("display", "block");*/
                $("#submit_btn").prop("disabled", true);
                $('#desc_container').addClass('has-error has-feedback');

            } else {

                $('#error_desc').css("display", "none");
                $("#submit_btn").prop("disabled", false);
                $('#desc_container').removeClass('has-error has-feedback');
            }
        });
    });

</script>
