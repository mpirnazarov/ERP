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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageTitle" scope="request" value="Details"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<spring:url value="/resources/core/css/detailViewCss.css" var="detailViewCss"/>
<link rel="stylesheet" href="${detailViewCss}"/>


    <div id="detailBody" class="detail-body">
        <div id="detailHeaderDiv">
            <div onclick="history.back()" class="btn btn-darkred backButton"><span class="fa fa-arrow-circle-o-left" aria-hidden="true"></span> Return</div>
            <div class="col-md-4 col-md-offset-1" style="display: inline-flex" id="typeHeader"><label
                    style="margin-right: 1%">Workflow type: </label>
                <p style="border-bottom: 1px solid #fff ">${model.form_type}</p></div>
            <div class="col-md-4 col-md-offset-2" style="display: inline-flex" id="statusHeader"><label
                    style="margin-right: 1%">Current status: </label>
                <p class="statusTextColor">${model.status}</p></div>
        </div>

        <div style="margin-bottom: 1%" id="authorsTools" class="col-md-5 col-md-offset-1">
            <div class="btn-group btn-group-justified" role="group" aria-label="...">
                <div class="btn-group" role="group">
                    <button style="color: #008000" id="editButton" type="button" class="btn btn-default"
                            onclick="location.href='/Workflow/EditForm/${model.request_id}';"><span
                            style="opacity: 1" class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit
                    </button>
                </div>
                <div class="btn-group" role="group">
                    <button style="color: #ff0000" id="deleteButton" type="button" class="btn btn-default"
                            onclick="deleteShow()"><span
                            style="opacity: 1" class="glyphicon glyphicon-remove" aria-hidden="true"></span> Delete
                    </button>
                    <div id="deleteVerificationOverflow">
                        <label>Are you sure?</label>
                        <div class="btn-group" role="group" aria-label="...">
                            <button id="deleteVerificationTrue" style="background-color: green" type="button"
                                    class="btn btn-default"
                                    onclick="location.href='/Workflow/MyForms/Delete/${model.request_id}'"><span
                                    class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span></button>
                            <button id="deleteVerificationFalse" style="background-color: red" type="button"
                                    class="btn btn-default" onclick="deleteHide()"><span
                                    class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span></button>
                        </div>
                    </div>
                </div>
                <div class="btn-group" role="group">
                    <button style="color: #1a85e0" id="terminationButton" type="button" class="btn btn-default"
                            onclick="location.href='/Workflow/MyForms/cancellation/${model.request_id}'">
                        <span style="opacity: 1" class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
                        Terminate
                    </button>
                </div>
            </div>
        </div>

        <div id="processStepsBar">
            <div class="stepwizard">
                <div class="stepwizard-row">
                    <c:forEach items="${approvers}" var="approve" varStatus="i">
                        <c:choose>
                            <c:when test="${approve.active==true}">
                                <div class="stepwizard-step">
                                    <button type="button" class="btn btn-success btn-circle">${i.index+1}</button>
                                    <p>${approve.name} ${approve.surname}</p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="stepwizard-step">
                                    <button type="button" class="btn btn-default btn-circle">${i.index+1}</button>
                                    <p>${approve.name} ${approve.surname} </p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>

            </div>
        </div>
        <div id="w3Cont" class="w3-container col-md-12 b3form" style="margin-left: 1%; margin-bottom: 8%">

            <div class="row">
                <div id="left-info" class="col-md-7">
                    <%--generated Field--%>
                </div>
                <div id="right-info" class="col-md-5">
                    <div id="input-group-div">
                        <%--<div class="input-group" style="width: 100%">
                            <span class="input-group-addon" id="type-addon" style="width: 35%">Form type:</span>
                            <input type="text" disabled class="form-control" placeholder="" aria-describedby="type-addon"
                                   style="width: 80%" value="${model.form_type}">
                        </div>--%>
                        <div class="input-group" style="width: 100%; margin-top: 1%">
                            <span class="input-group-addon" id="author-addon" style="width: 35%">Author:</span>
                            <input type="text" disabled class="form-control" placeholder=""
                                   aria-describedby="author-addon"
                                   style="width: 80%" value="${model.user_name}">
                        </div>
                        <div class="input-group" style="width: 100%; margin-top: 1%">
                            <span class="input-group-addon" id="creation-addon" style="width: 35%">Creation date:</span>
                            <input type="text" disabled class="form-control" placeholder=""
                                   aria-describedby="creation-addon"
                                   style="width: 80%" value="${model.date_created}">
                        </div>
                        <div class="input-group" style="width: 100%; margin-top: 1%">
                            <span class="input-group-addon" id="approvals-addon" style="width: 35%">Approvals:</span>
                            <textarea style="width: 80%;" disabled placeholder="Comment..." class="form-control"
                                      rows="3"
                                      id="approvalsTextArea">${model.approvals} </textarea>
                        </div>
                        <div class="input-group" style="width: 100%; margin-top: 1%">
                            <span class="input-group-addon" id="ex-addon" style="width: 35%">Executive:</span>
                            <textarea style="width: 80%;" disabled placeholder="Comment..." class="form-control"
                                      rows="3"
                                      id="executivesTextArea">${model.executives} </textarea>
                        </div>
                        <div class="input-group" style="width: 100%; margin-top: 1%">
                            <span class="input-group-addon" id="referenced-addon" style="width: 35%">Referenced:</span>
                            <textarea style="width: 80%;" disabled placeholder="Comment..." class="form-control"
                                      rows="3"
                                      id="referencesTextArea">${model.references} </textarea>
                        </div>

                        <div class="input-group" style="width: 100%; margin-top: 1%">
                            <span class="input-group-addon" id="attach-addon" style="width: 35%">Attachment:</span>
                            <c:forEach items="${model.attachments}" var="attachment">
                            <div class="attachmentDiv"><span class="glyphicon glyphicon-list-alt" aria-hidden="false"></span>
                                <c:choose>
                                <c:when test="${attachment.id == '0'}">
                                <a href="#">${attachment.fileName}</a>
                            </div>
                            </c:when>
                            <c:otherwise>
                            <a href="/Workflow/MyForms/files/${attachment.id}">${attachment.fileName}</a></div>
                            </c:otherwise>
                            </c:choose>

                            </c:forEach>

                    </div>
                </div>

                <%--Decision section--%>
                <div id="decisionSection" class="input-group" style="width: 100%; margin-top: 4%; margin-left: 8%">
                    <div class="btn-group" data-toggle="buttons" style="width: 100%">
                        <label id="approveButton" class="btn btn-success active" style="width: 27%">
                            Approve
                            <input type="radio" name="options" id="approve" autocomplete="off"
                                   onchange="checkedRadioBtn(this.id)" chacked>
                            <span class="glyphicon glyphicon-ok"></span>
                        </label>

                        <label id="rejectButton" class="btn btn-danger" style="width: 27%">
                            Reject
                            <input type="radio" name="options" id="reject" autocomplete="off"
                                   onchange="checkedRadioBtn(this.id)">
                            <span class="glyphicon glyphicon-ok"></span>
                        </label>

                        <label id="reviewButton" class="btn btn-warning" style="width: 27%">
                            Review
                            <input type="radio" name="options" id="review" autocomplete="off"
                                   onchange="checkedRadioBtn(this.id)">
                            <span class="glyphicon glyphicon-ok"></span>
                        </label>
                    </div>
                </div>
                <%--Comment section--%>
                <div class="commentTable">
                    <div class="comment-section">
                        <!-- Contenedor Principal -->
                        <div class="comments-container">

                            <c:if test="${empty model.comments}">
                                <h1 id="detail_comment"></h1>
                            </c:if>
                            <c:if test="${not empty model.comments}">
                                <h1 id="detail_comment">Comments</h1>
                            </c:if>

                            <ul id="comments-list" class="comments-list">
                                <c:forEach items="${model.comments}" var="comment" varStatus="status">
                                    <li>
                                        <div class="comment-main-level">
                                            <!-- Avatar -->
                                            <!-- Contenedor del Comentario -->
                                            <div class="comment-box">
                                                <div class="comment-head">
                                                    <h6 class="comment-name by-author"><a>${comment.author}</a></h6>
                                                    <div><label>Action:</label>
                                                        <p class="comment-action-text">${comment.action}</p></div>
                                                </div>
                                                <div class="comment-content">
                                                        ${comment.comment}
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div id="commentTextArea">
                    <textarea placeholder="Comment..." class="form-control" rows="5" id="detailComment"></textarea>
                </div>

                <%--Submit Button--%>
                <div id="submitButton">
                    <button class="btn btn-default" onclick="submitTheForm()">Submit</button>
                </div>
            </div>
        </div>

    </div>
<%--</div>--%>

<script>


    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});

</script>

<script>
    /*Sarvar*/

    var buttonOverlay = $("#deleteVerificationOverflow");

    function deleteShow() {
        buttonOverlay.toggleClass("showValidation");
    }

    function deleteHide() {
        buttonOverlay.toggleClass("showValidation");
    }


    var currentStatus = "${model.status}";

    /*processBarScript*/
    var elem = $('#processStepsBar');
    $(window).scroll(function () {
        setTimeout(function () {
            elem.css({"opacity": "0.1", "transition": "1s"});
        }, 1500);
        elem.css({"opacity": "1", "transition": "1s"});
    });
    elem.hover(function () {
        elem.css({"opacity": "1", "transition": "0.1s"});
    })
    elem.mouseleave(function () {
        elem.css({"opacity": "0.1", "transition": "1s"});
    })
    /*processBarScript*/


    $(document).ready(function () {

        /*action & status Color changer*/

        $('.comment-action-text:contains("Rejected")').css('color', '#ff0000');
        $('.comment-action-text:contains("Approved")').css('color', '#00e200');
        $('.comment-action-text:contains("Revision")').css('color', '#edb81f');

        $('.statusTextColor:contains("In progress")').css('color', '#35bce1');
        $('.statusTextColor:contains("Revision")').css('color', '#fec91f');
        $('.statusTextColor:contains("Rejected")').css('color', '#ff0000');
        $('.statusTextColor:contains("Draft")').css('color', '#edb81f');
        $('.statusTextColor:contains("Approved")').css('color', '#1efe1a');
        $('.statusTextColor:contains("Terminated")').css('color', '#ff0000');
        $('.statusTextColor:contains("Finished")').css('color', '#59feef');
        $('.statusTextColor:contains("Deleted")').css('color', '#000000');


        /*action Color changer*/

        $('#navigationButton').css('visibility', 'visible');


        /*Generation IF*/
        var formtype = ${model.form_type_id};
        var isOpened = ${isViewed};

        if (formtype == 1) {
            generateBusinessTrip();
        }
        else if (formtype == 2) {
            generateLeaveApprove();
        }
        else if (formtype == 3) {
            generateUnformatted();
        }
        else if (formtype == 4) {
            generateTermination()
        }
        else {
            alert("No Form");
        }

        /*
         alert("Form Type is: " + formtype + " | " + "Form is Viewed: " + isOpened);
         */


        /*Source Identification*/
        var sourceId = ${controllerId};
        if (sourceId == 1) {
            isFromToDo()
        }
        else if (sourceId == 2) {
            isFromRequest();
        }
        else {
            /*defaultSource*/
        }


        function isFromToDo() {
            $('#authorsTools').hide()

            if (formtype == 4) {
                $('#reviewButton').remove();
                $('#approveButton').css("width", "43%");
                $('#rejectButton').css("width", "43%");
            }

        }

        function isFromRequest() {

            var viewed = ${isViewed};
            var cstatus = "${model.status}";
            var statusid = ${model.status_id};

            if (statusid == 5 && formtype != 4) {
                $('#terminationButton').prop('disabled', false);
            } else {
                $('#terminationButton').prop('disabled', true);
            }


            if (viewed == false || statusid == 2) {
                $('#editButton').prop('disabled', false);
            } else {
                $('#editButton').prop('disabled', true);
            }

            if (viewed == false || statusid == 7 || statusid == 8 || statusid == 3) {
                $("#deleteButton").prop('disabled', false);
            } else {
                $("#deleteButton").prop('disabled', true);
            }

            $('#decisionSection').hide();
            $('#submitButton').hide();
            $('#commentTextArea').hide();

        }


    });

    function generateBusinessTrip() {

        var subject = "${bmodel.subject}";
        var domestic = "${bmodel.domestic}";
        var tripType = "${tripTypeName.get(bmodel.tripType)}";
        var destination = "${bmodel.destination}";
        var purpose = "${bmodel.purpose}";
        var startDate = "${bmodel.start.toString()}";
        var endDate = "${bmodel.end.toString()}";


        if (domestic) {
            tripType = tripType + "," + " Domestic";
        } else {
            tripType = tripType + "," + " Overseas";
        }


        $("#left-info").append('<div id="businessTripFormGen"> ' +
            '<div id="BusinessTripHeader"> ' +
            '<div class="labelDiv">' +
            '<label>Subject:</label> ' +
            '<p>' + subject + '</p> ' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Type of business trip:</label> ' +
            '<p>' + tripType + '</p> ' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Destination:</label> ' +
            '<p>' + destination + '</p> ' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Purpose of business trip:</label> ' +
            '<p>' + purpose + '</p> ' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Duration:</label> ' +
            '<p>Start: ' + startDate + ' | ' + 'End: ' + endDate + '</p> ' +
            '</div>' +
            '</div> ' +
            '<div id="BusinessTripBody" style="margin-top: 15px"> ' +
            '<label>List of Business Trip Members:</label> ' +
            '<table class="table table-bordered table-hover" style="color: inherit; font-size: 13px"id="tripMembersTable"> ' +
            '<thead style="background-color: #b1beca; font-size: 15px"> ' +
            '<tr> ' +
            '<th>Emoployee</th> ' +
            '<th>Department</th> ' +
            '<th>From</th> ' +
            '<th>To</th> ' +
            '</tr> ' +
            '</thead> ' +
            '<tbody id="tripMembersTableBody"> ' +
            '</tbody> ' +
            '</table> ' +
            '<label>Detail Schedule and To-do list:</label> ' +
            '<ul id = "ToDoListBody"> ' +
            '</ul> ' +
            '<label>Business trip Expanses:</label> ' +
            '<table class="table table-bordered table-hover" style="color: inherit; font-size: 13px"id="tripExpensesTable"> ' +
            '<thead style="background-color: #b1beca; font-size: 15px"> ' +
            '<tr> ' +
            '<th>Name</th> ' +
            '<th>Airfair</th> ' +
            '<th>Daily Allowance</th> ' +
            '<th>Accommodation</th> ' +
            '<th>Other</th> ' +
            '</tr> ' +
            '</thead> ' +
            '<tbody id="tripExpensesTableBody"> ' +
            '</tbody> ' +
            '</table> ' +
            '</div> ' +
            '</div>');


        /*Members ForEach*/
        var TotalUZS = 0;
        var TotalUSD = 0;
        <c:forEach var="m" items="${bmodel.membersEntityList}">

        var memberId = ${m.userId};
        var accomodationCurrency = ${m.accomodationCurrency};
        var memberName = "";
        var memberSurename = "";
        var memberJobTitle = "";
        var memberDepartment = "";

        <c:forEach var="member" items="${bmodel.members}">
        if (memberId == ${member.id}) {
            memberName = '${member.firstName}';
            memberSurename = '${member.lastName}';
            memberJobTitle = '${member.jobTitle}';
            memberDepartment = '${member.department}';
        }
        </c:forEach>

        var memberFullName = memberName + ' ' + memberSurename;
        var currencyString = "";
        var memberFrom = "${m.dateFrom.toString()}";
        var memberTo = "${m.dateTo.toString()}";
        var memberAirfair = ${m.expenseTransportation};
        var memberDailyAllowance = ${m.dailyAllowance};
        var memberAccommodation = ${m.expenseAccommodation};
        var memberOtherExpenses = ${m.expenseOther};
        /*var memberSubTotalExpenses = memberAirfair + memberDailyAllowance + memberAccommodation + memberOtherExpenses;
         TotalUZS += memberSubTotalExpenses;*/

        if (domestic) {

            if (accomodationCurrency == 1) {

                TotalUZS += memberAccommodation;

                memberAccommodation += " UZS";


            } else if (accomodationCurrency == 2) {

                TotalUSD += memberAccommodation;

                memberAccommodation += " USD"
            }

            TotalUZS += memberAirfair + memberDailyAllowance + memberOtherExpenses;

            memberAirfair += " UZS";
            memberDailyAllowance += " UZS";
            memberOtherExpenses += " UZS";

        } else {

            TotalUSD += memberAirfair + memberDailyAllowance + memberOtherExpenses + memberAccommodation;

            memberAirfair += " USD";
            memberDailyAllowance += " USD";
            memberOtherExpenses += " USD";
            memberAccommodation += " USD";

        }


        $("#tripMembersTableBody").append('<tr>' +
            '<td> ' +
            '<p class="tablep">' + memberFullName + '</p> ' +
            '<p>' + '#' + memberId + ', ' + memberJobTitle + '</p> ' +
            '</td> ' +
            '<td>' + ' <p class="tablep"> ' + memberDepartment + ' </p> ' + '</td> ' +
            '<td>' + ' <p class="tablep"> ' + memberFrom + ' </p> ' + '</td> ' +
            '<td>' + ' <p class="tablep"> ' + memberTo + ' </p> ' + '</td> ' +
            '</tr>');


        $("#tripExpensesTableBody").append('<tr> ' +
            '<td>' + memberFullName + ' </td> ' +
            '<td>' + memberAirfair + '</td> ' +
            '<td>' + memberDailyAllowance + '</td> ' +
            '<td>' + memberAccommodation + '</td> ' +
            '<td>' + memberOtherExpenses + '</td> ' +
            '</tr>');
        </c:forEach>
        $('#tripExpensesTable').append('<div class="labelDiv">' +
            '<label for="currencyDiv" style="font-size: 15px; margin-top: 5%; margin-right: 7%" > Total:</label>' +
            '<div id="currencyDiv" style="font-size: 18px">' +
            '<div>' + 'USD ' + ':' + ' ' + TotalUSD + '</div>' +
            '<div>' + 'UZS ' + ':' + ' ' + TotalUZS + '</div>' +
            '</div>' +
            '</div>')


        /*foreach of todoinglist*/
        <c:forEach var="m" items="${bmodel.toDoEntityList}">

        var toDoDate = "${m.date.toString()}";
        var toDoDescription = "${m.description}";

        $("#ToDoListBody").append('<li>Date: ' + toDoDate + ' | ' + toDoDescription + ' </li> ')

        </c:forEach>


    }
    function generateLeaveApprove() {


        var absenceType = '${leaveTypeName.get(leavemodel.absenceType)}';
        var description = "${leavemodel.description}";
        var start = '${leavemodel.start.toString()}';
        var end = '${leavemodel.end.toString()}';
        var leavingHours = '${leavemodel.leavingHours}';

        if(leavingHours == '1'){
            leavingHours = "8 HR";
        }else if(leavingHours == '2'){
            leavingHours = "4 AM";
        }else if(leavingHours == '3'){
            leavingHours = "4 PM";
        }


        $("#left-info").append('<div id="unformattedFormGen"> ' +
            '<div class="labelDiv">' +
            '<label>Absence Type: </label>' +
            '<p>' + absenceType + '</p>' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Leaving Hours: </label>' +
            '<p>' + leavingHours + '</p>' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Description: </label>' +
            '<p>' + description + '</p>' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Duration: </label>' +
            '<p>Start: ' + start + ' | ' + 'End: ' + end + '</p> ' +
            '</div>' +
            '</div>')

    }
    function generateUnformatted() {

        var title = '${umodel.subject}';
        var description = '${umodel.description}';
        var start = '${umodel.start.toString()}';
        var end = '${umodel.end.toString()}';

        $("#left-info").append('<div id="unformattedFormGen"> ' +
            '<div class="labelDiv">' +
            '<label>Title: </label>' +
            '<p>' + title + '</p>' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Description: </label>' +
            '<p>' + description + '</p>' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Duration: </label>' +
            '<p>Start: ' + start + ' | ' + 'End: ' + end + '</p> ' +
            '</div>' +
            '</div>');

    }
    function generateTermination() {

        var terminationTarget = ${termination.old_req_id};
        var title = "${termination.subject}";
        var description = "${termination.description}";
        var approvers = "${termination.approves}";
        var executives = "${termination.executives}";
        var references = "${termination.references}"


        $("#left-info").append('<div id="unformattedFormGen"> ' +
            '<div class="labelDiv">' +
            '<label>Title: </label>' +
            '<p>' + title + '</p>' +
            '</div>' +
            '<div class="labelDiv">' +
            '<label>Description: </label>' +
            '<p>' + description + '</p>' +
            '</div>' +
            '</div>');

    }
    /*Generation IF*/


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

        $.ajax({
            type: "POST",
            processData: false,
            data: 'comment=' + comment + '&status=' + status + '&reqId=' + reqId,
            url: '${pageContext.request.contextPath}/Workflow/MyForms/details',
            success: function () {
                window.location.href = "/Workflow/MyForms/todo/load";
            },
            error: function () {
                window.location.href = "/Workflow/MyForms/todo/load";
            }
        });
    }
</script>