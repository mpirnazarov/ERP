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

    body {

    }

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
        width: 17%;
    }

    .detail-body {
        width: 85%;
        margin-left: 7%;
        margin-top: 4%;
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

    .tablep {
        font-size: 15px;
        font-weight: bolder;
    }

    #tripMembersTable td {
        line-height: 10px;

    }


    #UserNavigationMenuDiv {
        margin-left: -17%;
        -webkit-transition: all 1s ease;
        transition: all 1s ease;
        background-color: rgba(87,120,118,0.92);
        border-style: inset;
    }

    #UserNavigationMenuDiv.shownav {
        margin-left: 0%;
        -webkit-transition: all 1s ease;
        transition: all 1s ease;
    }

    #sarcon {
        -moz-transition: all 1s linear;
        -webkit-transition: all 1s linear;
        transition: all 1s linear;
    }

    #sarcon.twistIcon {
        -ms-transform:rotateY(179deg);
        -moz-transform:rotateY(179deg);
        -webkit-transform:rotateY(179deg);
        transform: rotateY(179deg);
    }

    #navigationButton.pressed {
        /*somelogic on press*/
    }

    #navigationButton {
        width: 50px;
        height: 50px;
        position: absolute;
        background-color: rgba(86,119,117,0.81);
        border-style: inset;
        border-left: 4px solid rgba(85,117,116,0.89);
        left: 99%;
        top: 40%;
        z-index: 5;
        border-top-right-radius: 18px;
        border-bottom-right-radius: 18px;
    }

    #navigationButton:hover {
        width: 60px;
        -moz-transition: all 0.5s linear;
        -webkit-transition: all 0.5s linear;
        transition: all 0.5s linear;
    }





</style>


<%--<div class="col-sm-10 col-md-offset-2">--%>
<div id="detailBody" class="detail-body">
    <%--<h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
    </h1>
    <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
    </p>--%>
    <h2 class="page-header" style="padding-left: 1%">Details</h2>

    <div class="w3-container col-md-12 b3form" style="margin-left: 1%">

        <div class="row">
            <div id="left-info" class="col-md-7" style="border: dashed;">
                <%--<div id="businessTripFormGen">
                    <div id="BusinessTripHeader">
                        <label>Subject:</label>
                        <p> First integration development</p>
                        <label>Type of business trip:</label>
                        <p>Education or Certificate, Domestic</p>
                        <label>Destination:</label>
                        <p>Korea</p>
                        <label>Purpose of business trip:</label>
                        <p>Education and smth smth and Education and EducationEducation and smth smth and Education and
                            EducationEducation and smth smth and Education and Education</p>
                    </div>

                    <div id="BusinessTripBody">
                        <label>List of Business Trip Members:</label>
                        <table class="table" style="color: inherit; font-size: 11px"
                               id="tripMembersTable">
                            <thead style="background-color: #2b669a; font-size: 15px">
                            <tr>
                                <th>Emoployee</th>
                                <th>Name of (Organization/Project)</th>
                                <th>From</th>
                                <th>To</th>
                            </tr>
                            </thead>
                            <tbody id="tripMembersTableBody">
                            <tr>
                                <td>
                                    <p class="tablep">Kamola Mullamukhamedova</p>
                                    <p>#23, HR Manager</p>
                                </td>
                                <td>LG CNS UZB</td>
                                <td>01/02/2017</td>
                                <td>19/02/2017</td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="tablep">Sarvar Zokirov</p>
                                    <p>#11, Developer</p>
                                </td>
                                <td>LG CNS UZB</td>
                                <td>01/02/2017</td>
                                <td>19/02/2017</td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="tablep">Jasur Shaykhov</p>
                                    <p>#14, Developer</p>
                                </td>
                                <td>LG CNS UZB</td>
                                <td>01/02/2017</td>
                                <td>19/02/2017</td>
                            </tr>
                            </tbody>
                        </table>

                        <label>Detail Shceadule and To-do list:</label>
                        <ul>
                            <li>Date: 01/01/16 | Meeting</li>
                            <li>Date: 02/01/16 | Some Activity</li>
                            <li>Date: 03/01/16 | Another Activity</li>
                        </ul>

                        <label>Business trip Expanses:</label>
                        <table class="table" style="color: inherit; font-size: 11px"
                               id="tripExpensesTable">
                            <thead style="background-color: #2b669a; font-size: 15px">
                            <tr>
                                <th>Name</th>
                                <th>Airfair</th>
                                <th>Daily Allowance</th>
                                <th>Accommodation</th>
                                <th>Other</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                            <tbody id="tripExpensesTableBody">
                            <tr>
                                <td>Kamola HR</td>
                                <td>900</td>
                                <td>450</td>
                                <td>1500</td>
                                <td>200</td>
                                <td>sum</td>
                            </tr>
                            <tr>
                                <td>Sarvar Zokirov</td>
                                <td>900</td>
                                <td>450</td>
                                <td>1500</td>
                                <td>200</td>
                                <td>sum</td>
                            </tr>
                            <tr>
                                <td>Jasur Shaykhov</td>
                                <td>900</td>
                                <td>450</td>
                                <td>1500</td>
                                <td>200</td>
                                <td>sum</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%--<div id="unformattedFormGen">
                    <p>
                        Title: Going to Doctor
                    </p>
                    <p>
                        Description: I need to go to Doctor, so please let me go
                    </p>
                </div>--%>


            </div>
            <div id="right-info" class="col-md-5">
                <div class="input-group" style="width: 100%">
                    <span class="input-group-addon" id="type-addon" style="width: 35%">Form type:</span>
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
                    <c:forEach items="${model.attachments}" var="attachment">
                        <a href="/Workflow/MyForms/files/${attachment.id}">${attachment.fileName}</a>
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
                            <input type="radio" name="options" id="reject" autocomplete="off"
                                   onchange="checkedRadioBtn(this.id)">
                            <span class="glyphicon glyphicon-ok"></span>
                        </label>

                        <label class="btn btn-warning" style="width: 27%">
                            Review
                            <input type="radio" name="options" id="review" autocomplete="off"
                                   onchange="checkedRadioBtn(this.id)">
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
                                <td>${comment.author}</td>
                                <td>${comment.action}</td>
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



                <%--Test Comment Section Start--%>
                <%--<div class="comment-section">
                    <!-- Contenedor Principal -->
                    <div class="comments-container">
                        <h1>Comentarios <a href="http://creaticode.com">creaticode.com</a></h1>

                        <ul id="comments-list" class="comments-list">
                            <li>
                                <div class="comment-main-level">
                                    <!-- Avatar -->
                                    <div class="comment-avatar"><img
                                            src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg"
                                            alt=""></div>
                                    <!-- Contenedor del Comentario -->
                                    <div class="comment-box">
                                        <div class="comment-head">
                                            <h6 class="comment-name by-author"><a href="http://creaticode.com/blog">Agustin
                                                Ortiz</a></h6>
                                            <span>hace 20 minutos</span>
                                            <i class="fa fa-reply"></i>
                                            <i class="fa fa-heart"></i>
                                        </div>
                                        <div class="comment-content">
                                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi
                                            et iure laudantium vitae, praesentium optio, sapiente distinctio illo?
                                        </div>
                                    </div>
                                </div>
                                <!-- Respuestas de los comentarios -->
                                <ul class="comments-list reply-list">
                                    <li>
                                        <!-- Avatar -->
                                        <div class="comment-avatar"><img
                                                src="http://i9.photobucket.com/albums/a88/creaticode/avatar_2_zps7de12f8b.jpg"
                                                alt=""></div>
                                        <!-- Contenedor del Comentario -->
                                        <div class="comment-box">
                                            <div class="comment-head">
                                                <h6 class="comment-name"><a href="http://creaticode.com/blog">Lorena
                                                    Rojero</a></h6>
                                                <span>hace 10 minutos</span>
                                                <i class="fa fa-reply"></i>
                                                <i class="fa fa-heart"></i>
                                            </div>
                                            <div class="comment-content">
                                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis
                                                animi et iure laudantium vitae, praesentium optio, sapiente distinctio
                                                illo?
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <!-- Avatar -->
                                        <div class="comment-avatar"><img
                                                src="http://i9.photobucket.com/albums/a88/creaticode/avatar_1_zps8e1c80cd.jpg"
                                                alt=""></div>
                                        <!-- Contenedor del Comentario -->
                                        <div class="comment-box">
                                            <div class="comment-head">
                                                <h6 class="comment-name by-author"><a href="http://creaticode.com/blog">Agustin
                                                    Ortiz</a></h6>
                                                <span>hace 10 minutos</span>
                                                <i class="fa fa-reply"></i>
                                                <i class="fa fa-heart"></i>
                                            </div>
                                            <div class="comment-content">
                                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis
                                                animi et iure laudantium vitae, praesentium optio, sapiente distinctio
                                                illo?
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </li>

                            <li>
                                <div class="comment-main-level">
                                    <!-- Avatar -->
                                    <div class="comment-avatar"><img
                                            src="http://i9.photobucket.com/albums/a88/creaticode/avatar_2_zps7de12f8b.jpg"
                                            alt=""></div>
                                    <!-- Contenedor del Comentario -->
                                    <div class="comment-box">
                                        <div class="comment-head">
                                            <h6 class="comment-name"><a href="http://creaticode.com/blog">Lorena
                                                Rojero</a></h6>
                                            <span>hace 10 minutos</span>
                                            <i class="fa fa-reply"></i>
                                            <i class="fa fa-heart"></i>
                                        </div>
                                        <div class="comment-content">
                                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit omnis animi
                                            et iure laudantium vitae, praesentium optio, sapiente distinctio illo?
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>--%>
                <%--Test Comment Section END--%>


            </div>
        </div>

    </div>
</div>
<%--</div>--%>

<script>


    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});

    /*textScripts*/
    /*$("#generateBusinessTrip").click(function () {


        $("#left-info").append('<div id="businessTripFormGen"> ' +
            '<div id="BusinessTripHeader"> ' +
            '<label>Subject:</label> ' +
            '<p>' + subject + '</p> ' +
            '<label>Type of business trip:</label> ' +
            '<p>Education or Certificate, Domestic</p> ' +
            '<label>Destination:</label> ' +
            '<p>Korea</p> ' +
            '<label>Purpose of business trip:</label> ' +
            '<p>Education and smth smth and Education and EducationEducation and smth smth and Education and EducationEducation and smth smth and Education and Education</p> ' +
            '</div> ' +
            '<div id="BusinessTripBody"> ' +
            '<label>List of Business Trip Members:</label> ' +
            '<table class="table" style="color: inherit; font-size: 11px"id="tripMembersTable"> ' +
            '<thead style="background-color: #2b669a; font-size: 15px"> ' +
            '<tr> ' +
            '<th>Emoployee</th> ' +
            '<th>Name of (Organization/Project)</th> ' +
            '<th>From</th> ' +
            '<th>To</th> ' +
            '</tr> ' +
            '</thead> ' +
            '<tbody id="tripMembersTableBody"> ' +
            '<tr> ' +
            '<td> ' +
            '<p class="tablep">Kamola Mullamukhamedova</p> ' +
            '<p>#23, HR Manager</p> ' +
            '</td> ' +
            '<td>LG CNS UZB</td> ' +
            '<td>01/02/2017</td> ' +
            '<td>19/02/2017</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td> ' +
            '<p class="tablep">Sarvar Zokirov</p> ' +
            '<p>#11, Developer</p> ' +
            '</td> ' +
            '<td>LG CNS UZB</td> ' +
            '<td>01/02/2017</td> ' +
            '<td>19/02/2017</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td> ' +
            '<p class="tablep">Jasur Shaykhov</p>' +
            '<p>#14, Developer</p> ' +
            '</td> ' +
            '<td>LG CNS UZB</td> ' +
            '<td>01/02/2017</td> ' +
            '<td>19/02/2017</td> ' +
            '</tr> ' +
            '</tbody> ' +
            '</table> ' +
            '<label>Detail Shceadule and To-do list:</label> ' +
            '<ul> ' +
            '<li>Date: 01/01/16 | Meeting</li> ' +
            '<li>Date: 02/01/16 | Some Activity</li> ' +
            '<li>Date: 03/01/16 | Another Activity</li> ' +
            '</ul> ' +
            '<label>Business trip Expanses:</label> ' +
            '<table class="table" style="color: inherit; font-size: 11px"id="tripExpensesTable"> ' +
            '<thead style="background-color: #2b669a; font-size: 15px"> ' +
            '<tr> ' +
            '<th>Name</th> ' +
            '<th>Airfair</th> ' +
            '<th>Daily Allowance</th> ' +
            '<th>Accommodation</th> ' +
            '<th>Other</th> ' +
            '<th>Total</th> ' +
            '</tr> ' +
            '</thead> ' +
            '<tbody id="tripExpensesTableBody"> ' +
            '<tr> ' +
            '<td>Kamola HR</td> ' +
            '<td>900</td> ' +
            '<td>450</td> ' +
            '<td>1500</td> ' +
            '<td>200</td> ' +
            '<td>sum</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td>Sarvar Zokirov</td> ' +
            '<td>900</td> ' +
            '<td>450</td> ' +
            '<td>1500</td> ' +
            '<td>200</td> ' +
            '<td>sum</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td>Jasur Shaykhov</td> ' +
            '<td>900</td> ' +
            '<td>450</td> ' +
            '<td>1500</td> <td>200</td> ' +
            '<td>sum</td> ' +
            '</tr> ' +
            '</tbody> ' +
            '</table> ' +
            '</div> ' +
            '</div>');
    });*/
    $("#generateUnformatted").click(function () {
        $("#left-info").append('<div id="unformattedFormGen"> ' +
            '<p> Title: Going to Doctor </p> ' +
            '<p> Description: I need to go to Doctor, so please let me go </p> ' +
            '</div>')
    })
</script>

<script>
    /*Sarvar*/





    function generateBusinessTrip() {


        var subject = "${bmodel.subject}";
        var domestic = ${bmodel.domestic};
        var tripType = "${tripTypeName.get(bmodel.tripType)}";
        var destination = "${bmodel.destination}";
        var purpose = "${bmodel.purpose}";
        var startDate = "${bmodel.start.toString()}";
        var endDate = "${bmodel.end.toString()}";


        /*alert(members);*/


        if (domestic) {
            tripType = tripType + "," + " Domestic";
        }else {
            tripType = tripType + "," + " Overseas";
        }




        $("#left-info").append('<div id="businessTripFormGen"> ' +
            '<div id="BusinessTripHeader"> ' +
            '<label>Subject:</label> ' +
            '<p>' + subject + '</p> ' +
            '<label>Type of business trip:</label> ' +
            '<p>' + tripType + '</p> ' +
            '<label>Destination:</label> ' +
            '<p>' + destination + '</p> ' +
            '<label>Purpose of business trip:</label> ' +
            '<p>' + purpose + '</p> ' +
            '<label>Business Duration:</label> ' +
            '<p>Start: ' + startDate + ' | ' + 'End: ' + endDate +'</p> ' +
            '</div> ' +
            '<div id="BusinessTripBody"> ' +
            '<label>List of Business Trip Members:</label> ' +
            '<table class="table" style="color: inherit; font-size: 11px"id="tripMembersTable"> ' +
            '<thead style="background-color: #2b669a; font-size: 15px"> ' +
            '<tr> ' +
            '<th>Emoployee</th> ' +
            '<th>Name of (Organization/Project)</th> ' +
            '<th>From</th> ' +
            '<th>To</th> ' +
            '</tr> ' +
            '</thead> ' +
            '<tbody id="tripMembersTableBody"> ' +
            '<tr> ' +
            '<td> ' +
            '<p class="tablep">Kamola Mullamukhamedova</p> ' +
            '<p>#23, HR Manager</p> ' +
            '</td> ' +
            '<td>LG CNS UZB</td> ' +
            '<td>01/02/2017</td> ' +
            '<td>19/02/2017</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td> ' +
            '<p class="tablep">Sarvar Zokirov</p> ' +
            '<p>#11, Developer</p> ' +
            '</td> ' +
            '<td>LG CNS UZB</td> ' +
            '<td>01/02/2017</td> ' +
            '<td>19/02/2017</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td> ' +
            '<p class="tablep">Jasur Shaykhov</p>' +
            '<p>#14, Developer</p> ' +
            '</td> ' +
            '<td>LG CNS UZB</td> ' +
            '<td>01/02/2017</td> ' +
            '<td>19/02/2017</td> ' +
            '</tr> ' +
            '</tbody> ' +
            '</table> ' +
            '<label>Detail Shceadule and To-do list:</label> ' +
            '<ul id = "ToDoListBody"> ' +
            '<li>Date: 01/01/16 | Meeting</li> ' +
            '<li>Date: 02/01/16 | Some Activity</li> ' +
            '<li>Date: 03/01/16 | Another Activity</li> ' +
            '</ul> ' +
            '<label>Business trip Expanses:</label> ' +
            '<table class="table" style="color: inherit; font-size: 11px"id="tripExpensesTable"> ' +
            '<thead style="background-color: #2b669a; font-size: 15px"> ' +
            '<tr> ' +
            '<th>Name</th> ' +
            '<th>Airfair</th> ' +
            '<th>Daily Allowance</th> ' +
            '<th>Accommodation</th> ' +
            '<th>Other</th> ' +
            '<th>Total</th> ' +
            '</tr> ' +
            '</thead> ' +
            '<tbody id="tripExpensesTableBody"> ' +
            '<tr> ' +
            '<td>Kamola HR</td> ' +
            '<td>900</td> ' +
            '<td>450</td> ' +
            '<td>1500</td> ' +
            '<td>200</td> ' +
            '<td>sum</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td>Sarvar Zokirov</td> ' +
            '<td>900</td> ' +
            '<td>450</td> ' +
            '<td>1500</td> ' +
            '<td>200</td> ' +
            '<td>sum</td> ' +
            '</tr> ' +
            '<tr> ' +
            '<td>Jasur Shaykhov</td> ' +
            '<td>900</td> ' +
            '<td>450</td> ' +
            '<td>1500</td> <td>200</td> ' +
            '<td>sum</td> ' +
            '</tr> ' +
            '</tbody> ' +
            '</table> ' +
            '</div> ' +
            '</div>');


        /*Members ForEach*/
        var members = [];
        <c:forEach var="m" items="${bmodel.membersEntityList}">

        var memberId = ${m.userId};
        var memberName = ${m.userId};
        var memberOrg = "${m.organizationName}";
        var memberFrom = "${m.dateFrom.toString()}";
        var memberTo = "${m.dateTo.toString()}";
        var memberAirfair = ${m.expenseTransportation};
        var memberDailyAllowance = ${m.dailyAllowance};
        var memberAccommodation = ${m.expenseAccommodation};
        var memberOtherExpenses = ${m.expenseOther};
        var memberOverAllExpenses = memberAirfair + memberDailyAllowance + memberAccommodation + memberOtherExpenses;

        $("#tripMembersTableBody").append('<tr>' +
        '<td> ' +
        '<p class="tablep">' + memberName + '</p> ' +
        '<p>' + memberId + ', HR Manager</p> ' +
        '</td> ' +
        '<td>' + memberOrg + '</td> ' +
        '<td>' + memberFrom + '</td> ' +
        '<td>' + memberTo + '</td> ' +
        '</tr>');


        $("#tripExpensesTableBody").append('<tr> ' +
        '<td>' + memberId + ' </td> ' +
        '<td>' + memberAirfair + '</td> ' +
        '<td>' + memberDailyAllowance + '</td> ' +
        '<td>' + memberAccommodation + '</td> ' +
        '<td>' + memberOtherExpenses + '</td> ' +
        '<td>' + memberOverAllExpenses + '</td> ' +
        '</tr>');

        </c:forEach>

        /*foreach of todoinglist*/
        <c:forEach var="m" items="${bmodel.toDoEntityList}">

        var toDoDate = "${m.date.toString()}";
        var toDoDescription = "${m.description}";

        $("#ToDoListBody").append('<li>Date: ' + toDoDate + ' | ' + toDoDescription + ' </li> ')

        </c:forEach>

    }
    function generateUnformatted() {

    }

    var ftype = "${model.form_type_id}";

    $(document).ready(function () {

        $('#navigationButton').css("visibility",'visible');

        if (ftype == 1){
            generateBusinessTrip();
        }else if (ftype == 2) {
            /*generateLeave*/
        }else if (ftype == 3) {
            /*genereateUnformatted*/
        } else {
            /*generatedefault*/
        }

        alert(ftype);

    })



</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>

<script>
    /*Murod*/
    var status = 5;
    var comment = "";
    var reqId = "${model.request_id}";
    function checkedRadioBtn(id) {

        if (id == "approve") {
            status = 5;
        }
        else if (id == "review") {
            status = 2;
        }
        else if (id == "reject") {
            status = 3;
        }
    }

    function submitTheForm() {
        comment = $('#detailComment').val();
        alert(reqId);

        $.ajax({
            type: "POST",
            processData: false,
            data: 'comment=' + comment + '&status=' + status+'&reqId='+reqId,
            url: '${pageContext.request.contextPath}/Workflow/MyForms/details',
            success: function () {
                alert("Success");
            },
            error: function () {
                window.location.href = "/Workflow/MyForms/todo/load";
            }
        });
    }
</script>