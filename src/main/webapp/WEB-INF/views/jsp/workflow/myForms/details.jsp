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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    .userNavMenu {
        width: 20%;
    }

    .detail-body {
        width: 80%;
        margin-left: 20%;
    }

    .commentTable {
        width: 100%;
        margin-top: 4%;
        padding-left: 15px;
        padding-right: 15px;
    }

    .input-group-addon {
        text-align: right;
    }


</style>


<%--<div class="col-sm-10 col-md-offset-2">--%>
<div class="detail-body">
    <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
    </h1>
    <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
    </p>
    <h2 class="page-header" style="padding-left: 1%">Details</h2>

    <div class="w3-container col-md-12 b3form" style="margin-left: 1%">



            <div class="row">
                <div id="left-info" class="col-md-7" style="border: double;">.col-md-7</div>
                <div id="right-info" class="col-md-5">
                    <div class="input-group" style="width: 100%">
                        <span class="input-group-addon" id="type-addon" style="width: 35%" readonly="true">Form type:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="type-addon"
                               style="width: 80%" value="${model.form_type}">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="author-addon" style="width: 35%">Author:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="author-addon"
                               style="width: 80%" value="${model.user_name}">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="creation-addon" style="width: 35%">Creation date:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="creation-addon"
                               style="width: 80%" value="${model.date_created}">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="approvals-addon" style="width: 35%">Approvals:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="approvals-addon"
                               style="width: 80%" value="${model.approvals}">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="ex-addon" style="width: 35%">Executive:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="ex-addon"
                               style="width: 80%" value="${model.executives}">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                        <span class="input-group-addon" id="referenced-addon" style="width: 35%">Referenced:</span>
                        <input type="text" class="form-control" placeholder="" aria-describedby="referenced-addon"
                               style="width: 80%" value="${model.references}">
                    </div>
                    <div class="input-group" style="width: 100%; margin-top: 1%">
                         <span class="input-group-addon" id="attach-addon" style="width: 35%">Attachment:</span>
                        <c:forEach var="attach" items="${model.attachments}">
                           <a class="fa fa-file list-group-item" href="/Workflow/MyForms/todo/files/${attach.id}"><c:out value="${attach.fileName}"/></a>
                        </c:forEach>
                    </div>

                        <%--Decision section--%>
                        <div class="input-group" style="width: 100%; margin-top: 4%; margin-left: 8%">
                            <div class="btn-group" data-toggle="buttons" style="width: 100%">
                                <label class="btn btn-success active" style="width: 27%">
                                    Approve
                                    <input type="radio" name="options" id="approve" autocomplete="off"
                                           onchange="checkedRadioBtn(this.id)" chacked>
                                    <span class="glyphicon glyphicon-ok"></span>
                                </label>

                                <label class="btn btn-danger" style="width: 27%">
                                    Reject
                                    <input type="radio" name="options" id="reject" autocomplete="off" onchange="checkedRadioBtn(this.id)">
                                    <span class="glyphicon glyphicon-ok"></span>
                                </label>

                                <label class="btn btn-warning" style="width: 27%">
                                    Review
                                    <input type="radio" name="options" id="review" autocomplete="off" onchange="checkedRadioBtn(this.id)">
                                    <span class="glyphicon glyphicon-ok"></span>
                                </label>
                            </div>
                        </div>
                <%--Comment section--%>
                            <div class="commentTable">
                                <table class="table table-bordered table-condensed" id="commentTableParent"
                                       style="margin-bottom: 0px">
                                    <thead>
                                    <tr style="background-color: #2b669a">
                                        <th>Approval</th>
                                        <th>Action</th>
                                        <th>Comment</th>
                                    </tr>
                                    </thead>
                                    <tbody id="commentTableBody">
                                    <c:forEach items="${model.comments}" var="comment" varStatus="status">
                                        <tr>
                                            <td>${comment.comment}</td>
                                            <td>${comment.comment}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div style="width: 100%; padding-left: 15px; padding-right: 15px">
                                <textarea placeholder="Comment..." class="form-control" rows="5" id="detailComment"></textarea>
                            </div>

                                <%--Submit Button--%>
                            <div style="padding-left: 80%; padding-top: 1%">
                                <button class="btn btn-default" onclick="submitTheForm()">Submit</button>
                            </div>
                    <button id="generate1" class="btn btn-default" type="submit">Generate1</button>
                </div>
            </div>

    </div>
</div>
<%--</div>--%>

<script>
    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});

    /*textScripts*/
    $("#generate1").click(function () {
        $("#left-info").append('<div class="w3-container b3form"><div class="form-header"><div class="input-group" style="width: 100%"> <span class="input-group-addon" id="subject-addon" style="width: 25%">Subject:</span> <input type="text" class="form-control" placeholder="" aria-describedby="subject-addon"style="width: 40%"> </div> <div class="input-group" style="width: 100%; margin-top: 1%"> <span class="input-group-addon" id="saerchtype-addon"style="width: 25%">Type of business trip:</span> <select class="form-control" aria-describedby="saerchtype-addon" style="width: 40%"> <option>Conference or Seminar</option> <option>Benching Marking or Vendor Visit</option> <option>Project or Business Development</option> <option>LG CNS HQ or Affiliate</option> <option>Education or Certificate</option> </select> <label class="radio-inline" style="margin-left: 1%; margin-top: 1%"> <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Domestic </label> <label class="radio-inline" style="margin-top: 1%"> <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Overseas </label> </div> <div class="input-group" style="width: 100%; margin-top: 1%"> <span class="input-group-addon" id="destination-addon" style="width: 25%">Destination:</span> <input type="text" class="form-control" placeholder="" aria-describedby="destination-addon"style="width: 40%"> </div> <div class="input-group" style="width: 100%; margin-top: 1%"> <span class="input-group-addon" id="purpose-addon"style="width: 25%">Purpose of Business trip:</span> <textarea class="form-control" rows="5" id="comment" aria-describedby="purpose-addon"style="width: 40%"></textarea> </div> <div class="form-group"> <label style="margin-top: 2%;">List of Business Trip members: </label> <table class="table table-bordered" style="background-color: #2b669a; color: inherit"id="membersDynamicHead"> <thead> <tr> <th>Name</th> <th>Employee ID</th> <th>Job title</th> <th>Name of (Organization/Project)</th> <th>From</th> <th>To</th> </tr> </thead> <tbody id="membersDynamicBody"> <tr> <td>Kamola HR</td> <td>23</td> <td>HR</td> <td>LG CNS UZB</td> <td>01/02/2017</td> <td>19/02/2017</td> </tr> <tr> <td>Sarvar Zokirov</td> <td>21</td> <td>Developer</td> <td>LG CNS UZB</td> <td>01/02/2017</td> <td>19/02/2017</td> </tr> <tr> <td>Jasur Shaykhov</td> <td>13</td> <td>Developer</td> <td>LG CNS UZB</td> <td>01/02/2017</td> <td>19/02/2017</td> </tr> </tbody> </table> </div>');
    });
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

<script>
    /*Murod*/
    var status = 1;
    var comment = "";
    function checkedRadioBtn(id) {
       if(id=="approve"){
           status = 5;
       }
       else if(id=="review"){
           status = 3;
       }
       else if (id=="reject"){
           status = 2;
       }

       alert(status);
/*
        window.location.href = "/Workflow/MyForms/todo/load";
*/
    }

    function submitTheForm(){
        comment = $('#detailComment').val();

        $.ajax({
            type:"POST",
            processData: false,
            data:'comment='+comment+'&status='+status,
            url:'${pageContext.request.contextPath}/Workflow/MyForms/todo/details',
            success:function () {
                alert("Success");
            },
            error:function () {
                window.location.href = "/Workflow/MyForms/todo/load";
            }
        });
    }
</script>