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
        <h2 class="page-header">Termination</h2>

            <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"
                          style="width: 25%">Subject:</span>
                <div class="input-group" id="subject">
                    <input type="text" class="form-control" value="${termination.subject}"/>
                </div>
            </div>

             <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="term_span"
                              style="width: 25%">Description:</span>
                        <textarea class="form-control" rows="15" id="termination_desc" aria-describedby="purpose-addon"
                           style="width: 60%" path="description">${termination.description}</textarea>
                 <span class="error text-danger" id="error_desc" style="display: none">Description cannot be empty</span>
             </div>

            <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon"
                          style="width: 25%">Approves:</span>
                <div class="input-group">
                    <input type="text" class="form-control" value="${termination.approves}"/>
                </div>
            </div>

            <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon"
                          style="width: 25%">References:</span>
                <div class="input-group">
                    <input type="text" class="form-control" value="${termination.references}"/>
                </div>
            </div>

            <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon"
                          style="width: 25%">Executives:</span>
                <div class="input-group">
                    <input type="text" class="form-control" value="${termination.executives}"/>
                </div>
            </div>

            <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"
                          style="width: 25%">Approvals:</span>
            <div class="tab-content" id="approvals1">
                <input type="text" id="demo-input-local"/>
            </div>
        </div>
        <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon"
                          style="width: 25%">Executive:</span>
            <div class="tab-content" id="executives1">
                <input type="text" id="demo-input-local2"/>
            </div>
        </div>
        <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon"
                          style="width: 25%">Reference:</span>
            <div class="tab-content" id="references1">
                <input type="text" id="demo-input-local3"/>
            </div>
        </div>

        <input type="button" value="Submit" class="btn btn-primary" id="submit_btn"/>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        /*$("input[type=submit]").click(function ()*/
        $("#submit_btn").click(function (){
            var a=[];
            var b=[];
            var c=[];

            var old_Id = '${termination.old_req_id}';
            var description = $('#termination_desc').val();
            var subject = '${termination.subject}';

            if($(this).isDisabled){
                alert("disabled");
            }
            alert(description+old_Id);

            a = $("#approvals1").children().siblings("input[type=text]").val();
            b = $("#references1").children().siblings("input[type=text]").val();
            c = $("#executives1").children().siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/MyForms/cancellation",
                data :'approvals='+a+'&references='+b+'&executives='+c+'&description='+description+'&old_id='+old_Id+'&subject='+subject,
                success : function(response) {
                    window.location.href = "/Workflow/MyForms/Request";
                },
                error : function(e) {
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
       var desc = document.getElementById("error_desc");
           $('#termination_desc').blur(function(){
               if($(this).val().trim()==""){

               }
            });
   });

</script>
