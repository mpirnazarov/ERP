<%--
  Created by IntelliJ IDEA.
  User: Sarvar
  Date: 05.04.2017
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Schedule | Calendar"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<%--CSS--%>
<spring:url value="/resources/core/css/fullcalendar.css" var="fullcalendarCSS"/>
<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="dateTimePickerCSS"/>
<link rel="stylesheet" href="${fullcalendarCSS}"/>
<link rel="stylesheet" href="${dateTimePickerCSS}"/>

<%--CSS--%>

<%--JS--%>
<spring:url value="/resources/core/js/jquery.min.js" var="jqueryJS"/>
<spring:url value="/resources/core/js/moment.min.js" var="momentJS"/>
<spring:url value="/resources/core/js/fullcalendar.js" var="fullcalendarJS"/>
<spring:url value="/resources/core/js/jquery.datetimepicker.full.js" var="dateTimePickerJS"/>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>

<%--<script src="${jqueryJS}"></script>--%>
<script src="${momentJS}"></script>
<script src="${fullcalendarJS}"></script>
<script src="${dateTimePickerJS}"></script>
<script type="text/javascript" src="${tokenInputJs}"></script>

<%--JS--%>

<script>

    $(document).ready(function () {

        $('#dateTimeStart').datetimepicker({
            mask: '9999/19/39 29:59'
        });
        $('#dateTimeEnd').datetimepicker({
            mask: '9999/19/39 29:59'
        });


        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: '',
                right: 'title'
            },
            titleFormat: 'MMMM  D, YYYY',
            columnFormat: 'ddd DD MMM',
            timeFormat: 'HH:mm',
            slotLabelFormat: 'HH:mm',
            firstDay: 1,
            allDaySlot: false,
            nowIndicator: true,
            aspectRatio: 1,
            defaultView: 'agendaWeek',
            slotEventOverlap: false,
            timezone: 'local',
            navLinks: false, // can click day/week names to navigate views
            selectable: true,
            selectHelper: true,
            viewRender: function () {
                $('#calendar').fullCalendar('removeEvents');
                getCurrentWeekDays();
            },

            select: function (start, end) {

                $('#dialogDiv').removeClass('showDialog');

                createEvent(moment(start).format('YYYY/MM/DD HH:mm'), moment(end).format('YYYY/MM/DD HH:mm'), "calendar");

            },

            eventClick: function (calEvent, jsEvent, view, start, end) {

                /*alert('Event: ' + calEvent.title);
                 alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
                 alert('View: ' + view.name);*/
                /*alert('start: ' + moment(start).format());*/

                $(this).css('border-color', 'red');

                var dialogDiv = $('#dialogDiv');
                var currentUser = ${userId};
                var curWidth = $(this).width();
                var curHeight = $(this).height();
                var divCenterWidth = curWidth / 2;
                var divCenterHeight = curHeight / 2;

                var curPosition = $(this).offset();

                /*$(this).mouseout(function () {
                 dialogDiv.removeClass('showDialog');
                 })*/
                /*UpdateEvent*/
                if (currentUser == 1) {
                    dialogDiv.addClass('showDialog');
                } else {
                    view(calEvent);
                }

                dialogDiv.css({
                    top: curPosition.top + divCenterHeight - 5,
                    left: curPosition.left + divCenterWidth,
                    width: curWidth
                });

                $('#dialogEditButton').click(function () {
                    editEvent(calEvent);
                })

                $('#dialogViewButton').click(function () {
                    viewEvent(calEvent);
                })

                /*updateEvent(moment(calEvent.start).format('YYYY/MM/DD hh:mm'), moment(calEvent.end).format('YYYY/MM/DD hh:mm'), calEvent.title, calEvent.type, calEvent.place, calEvent.description, calEvent.isCompulsory, calEvent.notifyByEmail);*/

            },
            /*on EVENT click FUNCTION END*/
            editable: false,
            /*events: [
                {
                    title: 'All Day Event',
                    start: '2017-04-01'
                },
                {
                    title: 'Long Event',
                    start: '2017-04-07',
                    end: '2017-04-10'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2017-04-09T16:00:00'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2017-04-16T16:00:00'
                },
                {
                    title: 'Conference',
                    start: '2017-04-11',
                    end: '2017-04-13'
                },
                {
                    start: '2017-04-17T10:30:00',
                    end: '2017-04-17T13:44:00',
                    title: 'Meetingggggg',
                    s_type: 3,
                    place: 'LG CNS Uzbekistan head office',
                    description: 'everyone MUST ET',
                    is_compulsory: true,
                    to_notify: false,
                    participantsList: [{
                        userId: 4,
                        status: null,
                        reason: null,
                        name: "Murodjon",
                        surname: "Muslimov",
                        departmentName: "Technical department",
                        jobTitle: "Developer"
                    }, {
                        userId: 7,
                        status: null,
                        reason: null,
                        name: "Shaykhov",
                        surname: "Jasur",
                        departmentName: "Technical department",
                        jobTitle: "Developer"
                    }]
                },
                {
                    title: 'Lunch',
                    start: '2017-04-12T12:00:00'
                },
                {
                    id: 1111,
                    usha: "asdasd",
                    title: 'Meeting',
                    start: '2017-04-12T14:30:00'
                },
                {
                    title: 'Happy Hour',
                    start: '2017-04-12T17:30:00'
                },
                {
                    title: 'Dinner',
                    start: '2017-04-12T20:00:00'
                },
                {
                    title: 'Birthday Party',
                    start: '2017-04-13T07:00:00'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: '2017-04-28'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: '2017-04-28T14:00:00'
                }

            ]*/
        });
    });

    function createEvent(start, end, calendarObject) {

        switchContentDiv("create");
        clearModal();

        var targetCalendar = $('#' + calendarObject)
        var EventStart = start;
        var EventEnd = end;
        var msg = "Start " + EventStart + "\n End: " + EventEnd;
        /*alert("Start " + EventStart + "\n End: " + EventEnd );*/

        $('#message').html(msg);
        $('#dateTimeStart').val(EventStart);
        $('#dateTimeEnd').val(EventEnd);
        $('#actionTypeId').val(1);
        $('#CalendarModal').modal('show');
        $('#CalendarModalLabel').text('Create New Event');

        $('#eventParticipants').tokenInput(${UserslistJson});
        $('#eventReferences').tokenInput(${UserslistJson});

        $('ul.token-input-list').addClass('tokenOverwriteClass');

    }

    function editEvent(calEvent) {

        switchContentDiv("create")
        clearModal();




        /*moment(start).format('YYYY/MM/DD H:mm'), moment(end).format('YYYY/MM/DD H:mm')*/

        var EventId = calEvent.id;
        var EventAuthorId = calEvent.author_id;
        var EventActionTypeId = calEvent.actionType_id;
        var EventTitle = calEvent.title;
        var EventDescription = calEvent.description;
        var EventPlace = calEvent.place;
        var EventType = calEvent.s_type;
        var EventOtherType = calEvent.other;
        var EventIsCompulsory = calEvent.is_compulsory;
        var EventNotifyByEmail = calEvent.to_notify;
        var EventIsDraft = calEvent.is_draft;
        var EventStart = moment(calEvent.start).format('YYYY/MM/DD HH:mm');
        var EventEnd = moment(calEvent.end).format('YYYY/MM/DD HH:mm');
        var EventParticipants = [];
        var EventReferences = [];
        var EventAttachments = [];


        $(calEvent.participantsList).each(function (i, par) {
            var participant = {id: par.userId, name: par.name + ' ' + par.surname, jobTitle: par.jobTitle, department: par.departmentName};
            EventParticipants.push(participant);
        });

        $(calEvent.referencesList).each(function (i, ref) {
            var references = {id: ref.userId, name: ref.name + ' ' + ref.surname, jobTitle: ref.jobTitle, department: ref.departmentName};
            EventReferences.push(references);
        });

        $(calEvent.attachmentList).each(function (i, at) {
            /*EventAttachments.push(at);*/
        });



        if (calEvent.participantsList == ""){
            $('#eventParticipants').tokenInput(${UserslistJson});
        }else{
            $('#eventParticipants').tokenInput(${UserslistJson}, {
                prePopulate: EventParticipants
            });
        }

        if (calEvent.referencesList == ""){
            $('#eventReferences').tokenInput(${UserslistJson});
        }else {
            $('#eventReferences').tokenInput(${UserslistJson}, {
                prePopulate: EventReferences
            });
        }

        $('#dateTimeStart').val(EventStart);
        $('#dateTimeEnd').val(EventEnd);
        $('#eventTitle').val(EventTitle);
        $('#eventPlace').val(EventPlace);
        $('#eventDescription').val(EventDescription);


        if (EventType == 1) {
            $('#option1').prop('checked', true);
        } else if (EventType == 2) {
            $('#option2').prop('checked', true);
        } else if (EventType == 3) {
            $('#option3').prop('checked', true);
        } else if (EventType == 4) {
            $('#option4').prop('checked', true);
        }

        if (EventIsCompulsory == "true") {
            $('#isCompulsory').prop('checked', true);
        }

        if (EventNotifyByEmail == "true") {
            $('#notifyByEmail').prop('checked', true);
        }

        $('#CalendarModal').modal('show');

        $('#message').html('this is EDIT FORM')

        $('#CalendarModalLabel').text('Edit Event')

        $('ul.token-input-list').addClass('tokenOverwriteClass');

    }

    function viewEvent(calEvent) {
        switchContentDiv("view");
        clearModal();


        var EventId = calEvent.id;
        var EventAuthorId = calEvent.author_id;
        var EventActionTypeId = calEvent.actionType_id;
        var EventTitle = calEvent.title;
        var EventDescription = calEvent.description;
        var EventPlace = calEvent.place;
        var EventType = calEvent.sType;
        var EventOtherType = calEvent.other;
        var EventIsCompulsory = calEvent.is_compulsory;
        var EventNotifyByEmail = calEvent.to_notify;
        var EventIsDraft = calEvent.is_draft;
        var EventStart = moment(calEvent.start).format('YYYY/MM/DD HH:mm');
        var EventEnd = moment(calEvent.end).format('YYYY/MM/DD HH:mm');

        $('#eventViewReferencesList').text("Other: " + EventOtherType + "EventAuthor: " + EventAuthorId + "EventId: " + EventId + " EventDescription: " + EventDescription + '  ' + EventPlace + " IS NOTIFY  |" + EventNotifyByEmail + " COMPULSORY  |" + EventIsCompulsory);


        $('#CalendarModalLabel').text('View Event');
        $('#CalendarModal').modal('show');
    }

    function checkPressing(id) {

        var targetButton = $('#otherType');
        var targetButtonId = id;

        if (targetButton.css('opacity') == 0 && targetButtonId == 'otherTypeLabel') {
            targetButton.css('display', 'inline-block');
            targetButton.animate({opacity: 1}, 300);
        } else if (targetButton.css('opacity') == 1 && targetButtonId != 'otherTypeLabel') {
            targetButton.css('display', 'none');
            targetButton.animate({opacity: 0}, 300);
            targetButton.val('');
        }

        /* targetButton.css('display','none');
         targetButton.animate({opacity: 0}, 400);*/


    }

    function clearModal() {

        var targetButton = $('#otherType');

        $('input:text').each(function () {
            $(this).val('');
        })

        if (targetButton.css('opacity') == 1) {
            targetButton.css('display', 'none');
            targetButton.css('opacity', '0');
        }

        $('ul.token-input-list').remove();
        $('#option1').prop('checked', true);
        $('#isCompulsory').prop('checked', false);
        $('#notifyByEmail').prop('checked', false);
        $('#eventAttachment').val('');
    }

    function submitEvent(id) {

       var clickedButton =  $('#' + id);

        if (clickedButton.attr('value') == 'Submit'){
            $('#isDraft').prop('checked', false)
        }else if (clickedButton.attr('value') == 'Save'){
            $('#isDraft').prop('checked', true)
        }


        /* Getting array of strings of participants and references */
        var participants = [];
        var references = [];


        participants = $("#eventParticipantsGroup").children().siblings("input[type=text]").val();
        references = $("#eventReferencesGroup").children().siblings("input[type=text]").val();


        $.ajax({
            type: "POST",
            url: "/ScheduleManagement/ScheduleMembersAjax",
            data: 'participants=' + participants + '&references=' + references,
            success: function (response) {
//                    window.location.href = "/Workflow/NewForm/BusinessTripForm"
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

        $('#mainCalForm').submit();
    }

    function getSourceJson(startOfWeek, endOfWeek) {

        var listOfEvents = [];

        $.ajax({
            type: "Post",
            data: 'start=' + startOfWeek + '&end=' + endOfWeek,
            url: "${pageContext.request.contextPath}/ScheduleManagement/api/scheduleList",
            success: function (response) {

                $(response).each(function (i, res) {

                    var listOfParticipants = [];
                    var listOfReferences = [];
                    var listOfAttachments = [];
                    var event = {};

                    /*$(res.participants).each(function (i, par) {

                     })*/

                    event.author_id = res.author_id
                    event.id = res.id;
                    event.actionType_id = res.actionType_id;
                    event.title = res.title;
                    event.description = res.description;
                    event.place = res.place;
                    event.s_type = res.s_type;
                    event.other = res.other;
                    event.is_compulsory = res.is_compulsory;
                    event.to_notify = res.to_notify;
                    event.is_draft = res.is_draft;
                    event.start = res.start;
                    event.end = res.end;
                    $(res.participants).each(function (i, par) {
                        listOfParticipants.push(par);
                    });
                    $(res.references).each(function (i, ref) {
                        listOfReferences.push(ref);
                    });
                    $(res.Attachments).each(function (i, at) {
                        listOfAttachments.push(at);
                    });
                    event.participantsList = listOfParticipants;
                    event.referencesList = listOfReferences;
                    event.attachmentList = listOfAttachments;


                    /*alert("dasdfa" + event.start);*/

                    $('#calendar').fullCalendar('renderEvent', event, 'stick');
                    listOfEvents.push(event);
                });



            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });


    }

    function switchContentDiv(divToDisplay) {

        var createDiv = $('#createBodyDiv');
        var createDivAuthorButtons = $('#CalendarModalCreateAuthorButtonGroup');
        var viewDiv = $('#viewBodyDiv');
        var mainModalBody = $('#CalendarModal div.modal-body');

        if (divToDisplay == "create") {
            createDiv.css('display', 'block');
            createDivAuthorButtons.css('display', 'block');
            viewDiv.css('display', 'none');
        } else if (divToDisplay == "view") {
            createDiv.css('display', 'none');
            createDivAuthorButtons.css('display', 'none');
            viewDiv.css('display', 'block');
        } else {
            createDiv.css('display', 'none');
            viewDiv.css('display', 'none');
            mainModalBody.append('<p>All views are disabled</p>')

        }


    }

    function getCurrentWeekDays() {
        var calendar = $('#calendar').fullCalendar('getCalendar');
        var view = calendar.view;
        var startOfWeek = view.start.format('YYYY-MM-DD');
        var endOfWeek = view.end.format('YYYY-MM-DD');
        /*  var dates = ("Start" + startOfWeek + "End" + endOfWeek);
         alert(dates);*/
        getSourceJson(startOfWeek, endOfWeek);

    }

    /*function whatVal(target) {
     var targetTag = target;
     var currentId = $(targetTag).data("user-id");
     alert(targetTag.text() + "DATA ID :" + currentId);

     return currentId;

     }*/


</script>

<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span> Calendar</h2>

    <div class="w3-container">

        <%--EditDiv--%>
        <div id="dialogDiv">
            <div class="btn-group">
                <button id="dialogEditButton" type="button" class="btn btn-darkblue">Edit</button>
                <button id="dialogViewButton" type="button" class="btn btn-green">View</button>
            </div>
        </div>

        <%--Calendar--%>
        <div id='calendar'></div>

        <%--Modal--%>
        <div class="modal fade" id="CalendarModal" tabindex="-1" role="dialog" aria-labelledby="CalendarModalLabel">
            <div class="modal-dialog calModal" role="document">
                <div class="modal-content">
                    <form:form id="mainCalForm" modelAttribute="scheduleVM" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    class="fa fa-window-close"
                                    aria-hidden="true"><%--&times;--%></span></button>
                            <h4 style="font-size:25px" class="modal-title" id="CalendarModalLabel"></h4>
                        </div>
                        <div class="modal-body">
                                <%-------------------------------%>
                            <div id="createBodyDiv">
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Title</span>
                                    <input id="eventTitle" type="text" class="form-control" name="title"
                                           placeholder="...">
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Type:</span>
                                    <label class="btn" onclick="checkPressing()">
                                        <form:radiobutton id="option1" path="sType" value="1"/> Meeting
                                    </label>
                                    <label class="btn" onclick="checkPressing()">
                                        <form:radiobutton id="option2" path="sType" value="2"/> Out of office
                                    </label>
                                    <label class="btn" onclick="checkPressing()">
                                        <form:radiobutton id="option3" path="sType" value="3"/> Personal
                                    </label>
                                    <label id="otherTypeLabel" class="  btn" onclick="checkPressing(this.id)">
                                        <form:radiobutton id="option4" path="sType" value="4"/> Other
                                    </label>
                                    <form:input id="otherType" type="text" class="form-control calOtherTypeInput"
                                                name="other" placeholder="Type other type....." path="other"/>
                                </div>

                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">More</span>
                                    <label style="margin-left: 1%" class="checkbox-inline"><form:checkbox id="isCompulsory" path="compulsory"/>Is compulsory</label>
                                    <label class="checkbox-inline"><form:checkbox id="notifyByEmail" path="toNotify"/>Notify by email</label>
                                    <label class="hidden checkbox-inline"><form:checkbox id="isDraft" path="draft"/>is Draft</label>
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Date and Time</span>
                                    <input style="width: 50%" id="dateTimeStart" type="text" class="form-control"
                                           name="dateFrom" placeholder="...">
                                    <input style="width: 50%" id="dateTimeEnd" type="text" class="form-control"
                                           name="dateTo" placeholder="...">
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Place</span>
                                    <input id="eventPlace" type="text" class="form-control" name="place"
                                           placeholder="...">
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Description</span>
                                    <input id="eventDescription" type="text" class="form-control" name="description"
                                           placeholder="...">
                                </div>
                                <div id="eventParticipantsGroup" class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Participants</span>
                                    <input id="eventParticipants" type="text" class="form-control" placeholder="...">
                                </div>
                                <div id="eventReferencesGroup" class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">References</span>
                                    <input id="eventReferences" type="text" class="form-control" placeholder="...">
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Attachment</span>
                                    <form:input id="eventAttachment" type="file" class="form-control"
                                                placeholder="..." path="file" multiple="true"/>
                                </div>

                            </div>
                            <div id="viewBodyDiv">
                                <div class="CalendarViewHeader">
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Participants</span>
                                        <div id="eventViewParticipantsList"><span class="calendarMemberPill">Sarvar Zokirov</span>Sarvar
                                            Sarvar Sarvar ; Sarvar Sarvsar sarasrw
                                        </div>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">References</span>
                                        <div id="eventViewReferencesList">Sarvar Sarvar Sarvar ; Sarvar Sarvsar
                                            sarasrw
                                        </div>
                                    </div>
                                </div>
                                <div class="CalendarViewBody">

                                </div>
                                <div class="CalendarViewFooter">

                                </div>
                            </div>
                                <%-------------------------------%>
                        </div>
                        <div class="modal-footer">
                            <div id="CalendarModalCreateAuthorButtonGroup" class="btn-group" role="group"
                                 aria-label="...">
                                <input id="calSaveButton" type="button" value="Save"
                                       onclick="submitEvent(this.id)" class="btn btn-blue"/>
                                <input id="calSubmitButton" type="button" value="Submit"
                                       onclick="submitEvent(this.id)" class="btn btn-green"/>
                                <input type="button" data-dismiss="modal"  value="Cancel" class="btn btn-red"/>
                            </div>
                        </div>

                        <form:input type="hidden" path="actionTypeId"/>
                    </form:form>
                    <span id="message"></span>
                </div>
            </div>
        </div>
    </div>

</div>

