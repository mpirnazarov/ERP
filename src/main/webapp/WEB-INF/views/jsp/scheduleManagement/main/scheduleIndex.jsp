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
<spring:url value="/resources/core/css/jquery-ui.min.css" var="jqueryUiCSS"/>
<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="dateTimePickerCSS"/>

<link rel="stylesheet" href="${fullcalendarCSS}"/>
<link rel="stylesheet" href="${dateTimePickerCSS}"/>
<link rel="stylesheet" href="${jqueryUiCSS}"/>
<link rel="stylesheet" href="${paceCSS}"/>


<%--CSS--%>

<%--JS--%>
<spring:url value="/resources/core/js/jquery.min.js" var="jqueryJS"/>
<spring:url value="/resources/core/js/jquery-ui.min.js" var="jqueryUiJS"/>
<spring:url value="/resources/core/js/moment.min.js" var="momentJS"/>
<spring:url value="/resources/core/js/fullcalendar.js" var="fullcalendarJS"/>
<spring:url value="/resources/core/js/jquery.datetimepicker.full.js" var="dateTimePickerJS"/>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<spring:url value="/resources/core/js/bootstrapValidator.min.js" var="bootstrapValidationJS"/>


<%--<script src="${jqueryJS}"></script>--%>
<script src="${momentJS}"></script>
<script src="${fullcalendarJS}"></script>
<script src="${dateTimePickerJS}"></script>
<script type="text/javascript" src="${tokenInputJs}"></script>
<script src="${bootstrapValidationJS}"></script>
<script src="${jqueryUiJS}"></script>
<script src="${paceJs}"></script>


<%--JS--%>

<script>

    $(document).ready(function () {


        var currentUser = Number('${userProfile.id}');
        var arrayOfUsers = [];

        $(${UserslistJson}).each(function (i, e) {
            arrayOfUsers.push(e.name);
        });


        /*$('input[type=text]').on('keyup', function () {
         /!*validationCheckInput();*!/
         validationCheckInput(this.id)
         });*/

        $('#dateTimeStart').datetimepicker({
            mask: '9999/19/39 29:59'
        });
        $('#dateTimeEnd').datetimepicker({
            mask: '9999/19/39 29:59'
        });

        /*Calendar*/
        $('#calendar').fullCalendar({
            customButtons: {
                createEventButton: {
                    text: '✚ New Event',
                    click: function () {
                        createEvent();
                    }
                }
            },
            header: {
                left: 'prev,next today',
                center: 'createEventButton',
                right: 'title'
            },
            titleFormat: 'MMMM  D, YYYY',
            columnFormat: 'ddd DD MMM',
            timeFormat: 'HH:mm',
            slotLabelFormat: 'HH:mm',
            minTime: '09:00:00',
            maxTime: '22:00:00',
            slotLabelInterval: '00:30',
            firstDay: 1,
            allDaySlot: false,
            nowIndicator: true,
            aspectRatio: 2,
            defaultView: 'agendaWeek',
            slotEventOverlap: false,
            timezone: 'local',
            navLinks: false, // can click day/week names to navigate views
            selectable: true,
            selectHelper: true,
            eventMouseover:function () {
                var curPosition = $(this).offset();
                var curWidth = $(this).width();
                var notifyspan = $('#calNotifySpan');

                notifyspan.css({
                    top: curPosition.top,
                    left: curPosition.left,
                    width: curWidth
                });
            },
            viewRender: function () {
                filterTrigger("Off");
                $('#calLoaderDiv').css('display', 'block');
                $('#calendar').css('filter', 'blur(2px)');
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

                /*$(this).css('border-color', 'red');*/

                var dialogDiv = $('#dialogDiv');
                var curWidth = $(this).width();
                var curHeight = $(this).height();
                var divCenterWidth = curWidth / 2;
                var divCenterHeight = curHeight / 2;
                var authorId = calEvent.author_id;
                var curPosition = $(this).offset();

                /*UpdateEvent*/
                if (currentUser == authorId) {
                    dialogDiv.addClass('showDialog');
                    dialogDiv.css({
                        top: curPosition.top + divCenterHeight - 5,
                        left: curPosition.left + divCenterWidth,
                        width: curWidth
                    });
                    window.setTimeout(function () {
                        dialogDiv.removeClass('showDialog');
                    }, 5000);
                } else {
                    viewEvent(calEvent);
                }




                $('#dialogEditButton').click(function () {
                    editEvent(calEvent);
                    dialogDiv.removeClass('showDialog');
                });

                $('#dialogViewButton').click(function () {
                    viewEvent(calEvent);
                    dialogDiv.removeClass('showDialog');
                });

                /*updateEvent(moment(calEvent.start).format('YYYY/MM/DD hh:mm'), moment(calEvent.end).format('YYYY/MM/DD hh:mm'), calEvent.title, calEvent.type, calEvent.place, calEvent.description, calEvent.isCompulsory, calEvent.notifyByEmail);*/

            },
            /*on EVENT click FUNCTION END*/
            editable: false
        });

        $("#calFilterSelectInputUsers").tokenInput(${UserslistJson}, {tokenLimit: 1});

    });

    function createEvent(start, end, calendarObject) {
        clearModal();
        $('#calSubmitButton').attr('value', 'Submit');

        switchContentDiv("create");

        var targetCalendar = $('#' + calendarObject)
        var EventStart = start;
        var EventEnd = end;

        $('#dateTimeStart').val(EventStart);
        $('#dateTimeEnd').val(EventEnd);
        $('#actionTypeId').val(1);
        $('#CalendarModal').modal('show');
        $('#CalendarModalLabel').text('Create New Event');

        $('#eventParticipants').tokenInput(${UserslistJson});
        $('#eventReferences').tokenInput(${UserslistJson});

        $('#CalendarModal ul.token-input-list').addClass('tokenOverwriteClass');

    }

    function editEvent(calEvent) {
        clearModal();
        $('#calSubmitButton').attr('value', 'Update');
        switchContentDiv("create")


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
        var EventIsPrivate = calEvent.is_private;
        var EventIsDraft = calEvent.is_draft;
        var EventStart = moment(calEvent.start).format('YYYY/MM/DD HH:mm');
        var EventEnd = moment(calEvent.end).format('YYYY/MM/DD HH:mm');
        var EventParticipants = [];
        var EventReferences = [];
        var EventAttachments = [];

        var response = calendarCommonAjax("POST", "/ScheduleDetails/GetSchedule", "scheduleId=" + calEvent.id, "Update");

        $(response.participants).each(function (i, par) {
            var participant = {
                id: par.userId,
                name: par.name + ' ' + par.surname,
                jobTitle: par.jobTitle,
                department: par.departmentName
            };
            EventParticipants.push(participant);
        });

        $(response.references).each(function (i, ref) {
            var references = {
                id: ref.userId,
                name: ref.name + ' ' + ref.surname,
                jobTitle: ref.jobTitle,
                department: ref.departmentName
            };
            EventReferences.push(references);
        });

        $(response.Attachments).each(function (i, at) {
            $('#eventAttachedFiles').parent().show();
            $('#eventAttachedFiles').append('<a class="attachmentItem" href="/ScheduleDetails/files/' + at.attachmentId + '"> ' + at.attachmentName + ' <a style="color: red" href="/ScheduleDetails/DeleteAttachment/' + at.attachmentId + '"><span class="fa fa-trash" aria-hidden="true"></span></a>' + '</a>');
        });


        if (EventParticipants == "") {
            $('#eventParticipants').tokenInput(${UserslistJson});
        } else {
            $('#eventParticipants').tokenInput(${UserslistJson}, {
                prePopulate: EventParticipants
            });
        }

        if (EventReferences == "") {
            $('#eventReferences').tokenInput(${UserslistJson});
        } else {
            $('#eventReferences').tokenInput(${UserslistJson}, {
                prePopulate: EventReferences
            });
        }

        $('#scheduleIdInputHidden').val(EventId);
        $('#otherType').val(EventOtherType);
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
            checkPressing('otherTypeLabel', 1);
        }

        if (EventIsCompulsory == "true") {
            $('#isCompulsory').prop('checked', true);
        }

        if (EventNotifyByEmail == "true") {
            $('#notifyByEmail').prop('checked', true);
        }

        if(EventIsPrivate == "true"){
            $('#isPrivate').prop('checked',true);
        }


        $('#CalendarModal').modal('show');
        /*$('#message').html('this is EDIT FORM')*/
        $('#CalendarModalLabel').text('Edit Event')
        $('ul.token-input-list').addClass('tokenOverwriteClass');


    }

    function viewEvent(calEvent) {
        switchContentDiv("view");
        clearModal();
        $('.calendarMemberPill').remove();
        var response = calendarCommonAjax("POST", "/ScheduleDetails/GetSchedule", "scheduleId=" + calEvent.id, "View");
        /*var currentUserId = response.UserslistJson.id;*/
        var currentUserId = parseInt("${userId}", 10);
        var participantsId = [];
        var isParticipate = false;
        var isReference = false;
        var totalParticipants = 0;
        var totalWillParticipate = 0;
        var totalNotParticipate = 0;
        var totalNotDecidied = 0;

        $(response).each(function (i, res) {
            $(res.participants).each(function (i, par) {

                if (par.userId == currentUserId) {
                    isParticipate = true;
                }

                /*$('#eventViewParticipantsList').append('<span title="' + par.jobTitle + ", " + par.departmentName + '" class="calendarMemberPill">' + '<span class="fa fa-user-circle" aria-hidden="true"></span>' + ' ' + par.name + ' ' + par.surname + '</span>');*/
                participantsId.push(par.userId);

                if (par.status == 1) {
                    totalWillParticipate += 1;
                    $('#eventViewParticipantsList').append('<span title="' + par.jobTitle + ", " + par.departmentName + '" class="calendarMemberPill greenPill">' + '<span class="fa fa-user-circle" aria-hidden="true"></span>' + ' ' + par.name + ' ' + par.surname + '</span>');
                } else if (par.status == 2) {
                    totalNotParticipate += 1;
                    $('#eventViewParticipantsList').append('<span title="' + par.jobTitle + ", " + par.departmentName + " | Reason of not participating: " + par.reason + '" class="calendarMemberPill redPill">' + '<span class="fa fa-user-circle" aria-hidden="true"></span>' + ' ' + par.name + ' ' + par.surname + '</span>');
                } else if (par.status == 3) {
                    totalNotDecidied += 1;
                    $('#eventViewParticipantsList').append('<span title="' + par.jobTitle + ", " + par.departmentName + '" class="calendarMemberPill greyPill">' + '<span class="fa fa-user-circle" aria-hidden="true"></span>' + ' ' + par.name + ' ' + par.surname + '</span>');
                } else {
                    totalNotDecidied += 1;
                    $('#eventViewParticipantsList').append('<span title="' + par.jobTitle + ", " + par.departmentName + '" class="calendarMemberPill greyPill">' + '<span class="fa fa-user-circle" aria-hidden="true"></span>' + ' ' + par.name + ' ' + par.surname + '</span>');
                }
            });

            $(res.references).each(function (i, ref) {
                if (ref.userId == currentUserId) {
                    isReference = true;
                }
                $('#eventViewReferencesList').append('<span title="' + ref.jobTitle + ", " + ref.departmentName + '" class="calendarMemberPill">' + '<span class="fa fa-user-circle" aria-hidden="true"></span>' + ' ' + ref.name + ' ' + ref.surname + '</span>');
            });

            $(res.Attachments).each(function (i, at) {
                $('#eventAttachedFiles').parent().show();
                $('#eventViewAttachment').append('<a class="attachmentItem" href="/ScheduleDetails/files/' + at.attachmentId + '"> ' + '<span class="fa fa-download" aria-hidden="true"></span> ' + at.attachmentName + '</a>');

            });
        });


        totalParticipants = participantsId.length;

        var EventId = calEvent.id;
        var EventAuthorId = calEvent.author_id;
        var EventActionTypeId = calEvent.actionType_id;
        var EventTitle = calEvent.title;
        var EventDescription = calEvent.description;
        var EventPlace = calEvent.place;
        var EventType = calEvent.s_type;
        var EventOtherType = calEvent.other;
        var EventIsCompulsory = calEvent.is_compulsory;
        var EventIsPrivate = calEvent.is_private;
        var EventNotifyByEmail = calEvent.to_notify;
        var EventIsDraft = calEvent.is_draft;
        var EventStart = moment(calEvent.start).format('YYYY/MM/DD HH:mm');
        var EventEnd = moment(calEvent.end).format('YYYY/MM/DD HH:mm');
        var a_name = response.UserslistJson.name;
        var a_jobtitle = response.UserslistJson.jobTitle;
        var a_department = response.UserslistJson.department;


        /*-------Private Check-------*/
        if (EventIsPrivate == "true") {
            if (currentUserId != EventAuthorId || isParticipate || isReference) {

                $("#calNotifySpan").notify(
                    "This event is private!",
                    { position:"top left" }
                );


                /*$.notify(
                    "I'm to the right of this box",
                    { position:"top center" }
                );*/
                /*$('#dialogDiv').show().notify("Hello!");*/

                return false;
            }
        }

        if (EventAuthorId == currentUserId) {
            $('#CalendarModalViewDecisionButtonGroup').hide();
            $('#CalendarModalViewAuthorButtonGroup').show();
            $('#CalendarModalViewParticipantsButtonGroup').hide();
        } else if (isParticipate) {
            if (EventIsCompulsory == "true") {
                $('#CalendarModalViewDecisionButtonGroup').hide();
            } else {
                $('#CalendarModalViewDecisionButtonGroup').show();
            }
            $('#CalendarModalViewAuthorButtonGroup').hide();
            if (EventIsCompulsory == "true") {
                $('#CalendarModalViewParticipantsButtonGroup').hide();
            } else {
                $('#CalendarModalViewParticipantsButtonGroup').show();
            }
        } else {
            $('#CalendarModalViewDecisionButtonGroup').hide();
            $('#CalendarModalViewAuthorButtonGroup').hide();
            $('#CalendarModalViewParticipantsButtonGroup').hide();
        }


        $('#eventViewAuthor').parent().show();
        $('#eventViewAuthor').append('<span title="' + a_jobtitle + ", " + a_department + '" class="calendarMemberPill authorPill">' + '<span class="fa fa-user" aria-hidden="true"></span>' + ' ' + a_name + '</span>');

        $('#eventTotalParticipants span').text(totalParticipants);
        $('#eventTotalWillParticipate span').text(totalWillParticipate);
        $('#eventTotalNotParticipate span').text(totalNotParticipate);
        $('#eventTotalNotDecided span').text(totalNotDecidied);

        if (EventType == 1) {
            EventType = "Meeting";
        } else if (EventType == 2) {
            EventType = "Out of office";
        } else if (EventType == 3) {
            EventType = "Personal";
        } else if (EventType == 4) {
            EventType = EventOtherType;
        }

        $('#eventViewScheduleId').val(EventId);
        $('#eventViewType').val(EventType);
        $('#eventViewStart').val(EventStart);
        $('#eventViewEnd').val(EventEnd);
        $('#eventViewPlace').val(EventPlace);
        $('#eventViewTitle').val(EventTitle);
        $('#eventViewDescription').val(EventDescription);

        $('#CalendarModalLabel').text('View Event');
        $('#CalendarModal').modal('show');
    }

    function checkPressing(id, groupNumber) {

        var otherTypeTextBox = $('#otherType');
        var targetButtonId = id;
        var group = groupNumber;
        var yesButton = $('#CalendarDecisionYes');
        var noButton = $('#CalendarDecisionNo');
        yesButton.data("pressed", false);
        noButton.data("pressed", false);
        var decisionTextBox = $('#eventDecisionText');
        var decisionResult = "";

        if (group == 1) {
            if (otherTypeTextBox.css('opacity') == 0 && targetButtonId == 'otherTypeLabel') {
                otherTypeTextBox.css('display', 'inline-block');
                otherTypeTextBox.animate({opacity: 1}, 300);
            } else if (otherTypeTextBox.css('opacity') == 1 && targetButtonId != 'otherTypeLabel') {
                otherTypeTextBox.css('display', 'none');
                otherTypeTextBox.animate({opacity: 0}, 300);
                otherTypeTextBox.val('');
            }
        } else if (group = 2) {
            if (targetButtonId == "CalendarDecisionYes") {
                yesButton.data("pressed", true);
                noButton.data("pressed", false);
                yesButton.css("background-color", "#449d44");
                yesButton.css("color", "#ffffff");
                noButton.css("background-color", "#ffffff");
                noButton.css("color", "#337ab7");
                decisionTextBox.css('display', 'none');
                decisionTextBox.animate({opacity: 0}, 200);
                decisionTextBox.val('');
            } else if (targetButtonId == "CalendarDecisionNo") {
                yesButton.data("pressed", false);
                noButton.data("pressed", true);
                yesButton.css("background-color", "#ffffff");
                yesButton.css("color", "#449d44");
                noButton.css("background-color", "#337ab7");
                noButton.css("color", "#ffffff");
                decisionTextBox.css('display', 'inline-block');
                decisionTextBox.animate({opacity: 1}, 200);
            }
        } else {
            alert("Wronge Button!")
            return false;
        }


    }

    function clearModal() {

        var targetButton = $('#otherType');
        var yesButton = $('#CalendarDecisionYes');
        var noButton = $('#CalendarDecisionNo');
        var decisionTextBox = $('#eventDecisionText');
        var mainForm = $('#mainCalForm');

        $('#createBodyDiv :input').each(function () {
            $(this).css('border-color', '#ccc');
        });

        yesButton.data("pressed", false);
        noButton.data("pressed", false);

        $('.attachmentItem').empty();
        $('#eventAttachedFiles').empty();
        $('#eventViewAttachment').empty();
        $('p.calValidationText').remove();

        $('input:text').each(function () {
            $(this).val('');
        });

        if (targetButton.css('opacity') == 1) {
            targetButton.css('display', 'none');
            targetButton.css('opacity', '0');
        }

        $('#calSubmitButton').attr('value', '');
        $('#CalendarModal ul.token-input-list').remove();
        $('#option1').prop('checked', true);
        $('#isCompulsory').prop('checked', false);
        $('#isPrivate').prop('checked', false);
        $('#').prop('checked', false);

        $('#eventAttachment').val('');

        decisionTextBox.val('');
        yesButton.css("background-color", "#ffffff");
        yesButton.css("color", "#449d44");
        noButton.css("background-color", "#ffffff");
        noButton.css("color", "#337ab7");
        decisionTextBox.css('display', 'none');
        decisionTextBox.animate({opacity: 0});

    }

    function submitEvent(id) {

        /*      return validationCheckInput();*/
        var isValid = validationCheckInput();
        var clickedButton = $('#' + id).attr('value');
        var currentUrl = "";
        var currentData = "";
        var participants = [];
        var references = [];
        var scheduleId = "";
        var type = "";

        var dateStart = $('#dateTimeStart');
        var dateEnd = $('#dateTimeEnd');


        if (dateStart.val() == '') {
            dateStart.val(moment().format('YYYY/MM/DD HH:mm'))
            dateEnd.val(moment().add(30, 'm').format('YYYY/MM/DD HH:mm'))
        }

        if (dateEnd.val() == '') {
            dateStart.val(moment().format('YYYY/MM/DD HH:mm'))
            dateEnd.val(moment().add(30, 'm').format('YYYY/MM/DD HH:mm'))
        }

        scheduleId = $("#eventViewScheduleId").val();
        participants = $("#eventParticipantsGroup").children().siblings("input[type=text]").val();
        references = $("#eventReferencesGroup").children().siblings("input[type=text]").val();


        if (clickedButton == 'Submit') {
            if (!isValid) {
                return false;
            }
            currentData = "participants=" + participants + "&references=" + references;
            currentUrl = "/ScheduleManagement/ScheduleMembersAjax";
            type = "POST";
            $('#scheduleIdInputHidden').remove();
            $('#isDraft').prop('checked', false);
            $('#mainCalForm').attr('action', "/ScheduleManagement/main").submit();
        } else if (clickedButton == 'Save') {
            currentData = "participants=" + participants + "&references=" + references;
            currentUrl = "/ScheduleManagement/ScheduleMembersAjax";
            type = "POST";
            $('#scheduleIdInputHidden').remove();
            $('#isDraft').prop('checked', true);
            $('#mainCalForm').attr('action', "/ScheduleManagement/main").submit();
        } else if (clickedButton == 'Update') {
            if (!isValid) {
                return false;
            }
            currentData = "participants=" + participants + "&references=" + references;
            currentUrl = "/ScheduleDetails/ScheduleMembersAjax";
            type = "POST";
            $('#isDraft').prop('checked', false);
            $('#mainCalForm').attr('action', "/ScheduleDetails/UpdateSchedule").submit();
        } else if (clickedButton == 'Delete') {
            currentData = "scheduleId=" + scheduleId + "&participants=" + participants + "&references=" + references;
            currentUrl = "/ScheduleDetails/DeleteSchedule";
            type = "POST";

        } else {
            alert('Value is empty')
            return false;
        }

        calendarCommonAjax(type, currentUrl, currentData, clickedButton);

    }

    function switchContentDiv(divToDisplay) {

        var createDiv = $('#createBodyDiv');
        var createDivAuthorButtons = $('#CalendarModalCreateAuthorButtonGroup');
        var viewDivAuthorButtons = $('#CalendarModalViewAuthorButtonGroup');
        var viewDiv = $('#viewBodyDiv');
        var mainModalBody = $('#CalendarModal div.modal-body');
        $('#eventAttachedFiles').parent().hide();


        if (divToDisplay == "create") {
            $('#eventAttachedFiles').parent().hide();
            createDiv.css('display', 'block');
            createDivAuthorButtons.css('display', 'block');
            viewDivAuthorButtons.css('display', 'none');
            viewDiv.css('display', 'none');
            $('#CalendarModalViewParticipantsButtonGroup').hide();
        } else if (divToDisplay == "view") {
            createDiv.css('display', 'none');
            viewDivAuthorButtons.css('display', 'block');
            createDivAuthorButtons.css('display', 'none');
            viewDiv.css('display', 'block');
        } else {
            createDiv.css('display', 'none');
            viewDiv.css('display', 'none');
            mainModalBody.append('<p>All views are disabled</p>')

        }


    }

    function getCurrentWeekDays(option) {

        var getOption = option;
        var calendar = $('#calendar').fullCalendar('getCalendar');
        var view = calendar.view;
        var startOfWeek = view.start.format('YYYY-MM-DD');
        var endOfWeek = view.end.format('YYYY-MM-DD');
        var currentUserId = '';


        if (getOption == "filter") {
            var inputdata = $('#calFilterSelectGroup li').first().data('user-id');

            if (typeof inputdata == 'undefined') {
                $('#calendar').fullCalendar({selectable: true})
                $('button.fc-createEventButton-button').attr('disabled', false);
                $('button.fc-createEventButton-button').css('opacity', 1);
                currentUserId = 0;
            } else {
                $('#calendar').fullCalendar({selectable: false})
                $('button.fc-createEventButton-button').attr('disabled', true);
                $('button.fc-createEventButton-button').css('opacity', 0.4);
                currentUserId = inputdata;


            }

            calendarCommonAjax("POST", "/ScheduleManagement/Filter", "userId=" + currentUserId + "&start=" + startOfWeek + "&end=" + endOfWeek, "filter");
        } else {
            calendarCommonAjax("POST", "/ScheduleManagement/api/scheduleList", "start=" + startOfWeek + "&end=" + endOfWeek, "renderCurrentWeek");
            /*getSourceJson(startOfWeek, endOfWeek);*/
        }


    }

    function returnToCalendar() {
        $('#calendar').fullCalendar('removeEvents');
        getCurrentWeekDays();
        $('#CalendarModal').modal('hide');
    }

    function validationCheckInput(id) {

        var targetArray = [];
        var mainValidationFlag = true;
        var dateStart = $('#dateTimeStart');
        var dateEnd = $('#dateTimeEnd');
        var otherTypeTextBox = $('#otherType')


        if (typeof id == 'undefined') {
            $('p.calValidationText').remove();
            $('#mainCalForm input[type=text]').each(function () {
                targetArray.push(this.id);
            });
        } else {
            targetArray.push(id);
            $('#' + id).parent().next('p.calValidationText').remove();
        }

        $.each(targetArray, function (index, value) {
            var validationFlag = true;
            var currentValidationObject, $this;
            $this = $('#' + value);
            currentValidationObject = $this.data("cal-validation")
            if (typeof currentValidationObject != 'undefined') {


                var currentValue = $this.val();
                var msg = "";
                var empty = "";
                var minVal = 0;
                var maxVal = 0;

                $(currentValidationObject).each(function (i, obj) {
                    empty = obj.empty;
                    minVal = obj.minval;
                    maxVal = obj.maxval;
                });


                ///EMPTY CHECK
                if (empty == "true") {
                    if (currentValue == '') {
                        msg += " Field can not be empty";
                        validationFlag = false;
                    }
                }

                ///Lenght CHECK
                ///min value
                if (minVal != 0 && currentValue != 0 && currentValue.length < minVal) {
                    msg += "Minimum number of characters should not be less than " + minVal;
                    validationFlag = false;
                }

                ///max value
                if (maxVal != 0 && currentValue != 0 && currentValue.length > maxVal) {
                    msg += "Maximum number of characters should not exceed " + maxVal;
                    validationFlag = false;
                }


                ///VALIDATION TEXT APPEND
                if (validationFlag == false) {
                    $this.css('border', '1px solid #ca4e4e');
                    ($this).parent().after('<p class="calValidationText">' + msg + '</p>');
                    /*($this).parent().append('<p class="calValidationText">' + msg + '</p>');*/
                } else {
                    $this.css('border', '1px solid #ccc');
                    /*  $('p.calValidationText').remove();*/
                }

                mainValidationFlag = validationFlag;

            }


        });

        /*
         other Validation
         */

        if (!(moment(dateStart.val()).isBefore(dateEnd.val()))) {
            dateEnd.css('border', '1px solid #ca4e4e');
            dateEnd.parent().append('<p class="calValidationText">' + 'Event end time can not be earlier than event start time' + '</p>');
            mainValidationFlag = false;
        } else {
            dateEnd.css('border', '1px solid #ccc');
            dateEnd.parent().next('p.calValidation').remove();
        }

        if (otherTypeTextBox.css('opacity') != 0) {
            if (otherTypeTextBox.val().trim() == '') {
                otherTypeTextBox.css('border-color', '#ca4e4e');
                otherTypeTextBox.parent().append('<p class="calValidationText">' + 'Event type can not be empty' + '</p>');
                mainValidationFlag = false;
            } else {
                otherTypeTextBox.css('border-color', '#ccc')
                otherTypeTextBox.parent().next('p.calValidation').remove();
            }
        }


        /*var pNumber = 0;
         $('#eventParticipantsGroup li.token-input-token').each(function () {
         pNumber += 1;
         });

         if (pNumber == 0) {
         mainValidationFlag = false;
         $('#eventParticipantsGroup').after('<p class="calValidationText">' + 'At lease one participant should be selected' + '</p>')
         $('div#token-input-eventParticipants').css('border-color', '#ca4e4e');
         } else {
         $('#eventParticipantsGroup').next('p.calValidationText').remove();
         $('ul.tokenOverwriteClass').css('border-color', '#ccc');
         }*/
        return mainValidationFlag;

        /*ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss*/
        /*$('<div style="display: none;" class="new-link" name="link[]"><input type="text" /></div>').appendTo($('.insert-links')).slideDown("fast");*/

    }

    function eventDecisionMaking(id) {

        /*  targetButton.css('display', 'inline-block');
         targetButton.animate({opacity: 1}, 300);*/
        var currentButtonId = id;
        var decisionTextBox = $('#eventDecisionText');
        var yesButton = $('#CalendarDecisionYes');
        var noButton = $('#CalendarDecisionNo');
        var currentType = "";
        var currentUrl = "";
        var currentData = "";
        var currentAction = "";
        var currentParticipant = ${userId};
        var currentScheduleId = $("#eventViewScheduleId").val();
        var currentStatus = "";
        var currentReason = "";


        if (currentButtonId = "calSubmitDecision") {
            if (yesButton.data("pressed")) {
                currentStatus = 1;
                currentType = "POST";
                currentUrl = "/ScheduleDetails/ParticipantDecision";
                currentData = "participantId=" + currentParticipant + "&scheduleId=" + currentScheduleId + "&status=" + currentStatus + "&reason=" + currentReason;
                /*currentData = "participantId=1&scheduleId=1&status=1&reason=A";*/

            } else if (noButton.data("pressed")) {
                currentStatus = 2;
                currentReason = decisionTextBox.val();
                currentType = "POST";
                currentUrl = "/ScheduleDetails/ParticipantDecision";
                currentData = "participantId=" + currentParticipant + "&scheduleId=" + currentScheduleId + "&status=" + currentStatus + "&reason=" + currentReason;
            } else {
                return false;
            }
        }


        calendarCommonAjax(currentType, currentUrl, currentData, "DecisionSubmit");
    }

    function calendarCommonAjax(type, url, data, actionType) {
        var currentResponse;
        var currentType = type;
        var currentUrl = url;
        var currentData = data;
        var currentAction = actionType;


        $.ajax({
            type: currentType,
            async: false,
            url: currentUrl,
            data: currentData,
            success: function (response) {

                if (currentAction == "Create") {
                    returnToCalendar();
                } else if (currentAction == "Save") {
                    returnToCalendar();
                } else if (currentAction == "Update") {
                    currentResponse = response;
                } else if (currentAction == "Delete") {
                    returnToCalendar();
                } else if (currentAction == "View") {
                    currentResponse = response;
                } else if (currentAction == "DecisionSubmit") {
                    returnToCalendar();
                } else if (currentAction == "renderCurrentWeek") {
                    $('#calLoaderDiv').css('display', 'none');
                    $('#calendar').css('filter', 'blur(0px)');
                    renderFromJson(response);
                } else if (currentAction == "filter") {
                    $('#calendar').fullCalendar('removeEvents');
                    currentResponse = response;
                    renderFromJson(response);
                } else {
                    currentResponse = response;
                }


            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

        return currentResponse;
    }

    function filterTrigger(action) {
        return false;

        var filterDiv = $('#calFilterDiv');

        if (action == "On") {
            filterDiv.slideDown();
        } else if (action == "Off") {
            filterDiv.slideUp();
        } else {
            filterDiv.slideToggle();
        }
    }

    function renderFromJson(response) {

        $(response).each(function (i, res) {


            var listOfParticipants = [];
            var listOfReferences = [];
            var listOfAttachments = [];
            var event = {};
            var currentUser = Number('${userProfile.id}');

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
            event.is_private = res.is_private;
            event.start = res.start;
            event.end = res.end;
            event.borderColor = "#fff";


            if (res.author_id == currentUser) {
                event.backgroundColor = "#14b441";
            }

            if (res.is_draft == "true") {
                event.backgroundColor = "#ffb100";
            }


            $(res.participants).each(function (i, par) {
                listOfParticipants.push(par);
            });
            $(res.references).each(function (i, ref) {
                listOfReferences.push(ref);
            });
            $(res.Attachments).each(function (i, at) {
                listOfAttachments.push({attachmentId: at.attachmentId, attachmentName: at.attachmentName});
            });
            event.participantsList = listOfParticipants;
            event.referencesList = listOfReferences;
            event.attachmentList = listOfAttachments;

            $('#calendar').fullCalendar('renderEvent', event, 'stick');
        });
    }


</script>

<div class="mainBodyBlock">
    <%--<h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span> Calendar</h2>--%>

    <div class="w3-container" style="padding-top: 2%">

        <%--Notify Span--%>
        <span id="calNotifySpan"></span>

        <%--EditDiv--%>
        <div id="dialogDiv">
            <div class="btn-group">
                <button id="dialogEditButton" type="button" class="btn btn-darkblue">Edit</button>
                <button id="dialogViewButton" type="button" class="btn btn-green">View</button>
            </div>
        </div>
        <div id="calFilterDiv">
            <div id="calFilterSelectGroup">
                <label for="calFilterSelectInputUsers">Search by user</label>
                <input id="calFilterSelectInputUsers">
            </div>
            <div class="btn btn-black calFilterSearchButton" onclick="getCurrentWeekDays('filter')">Filter</div>
        </div>
        <%--Calendar--%>
        <div id='calendar'></div>
        <%--Loader--%>
        <div id="calLoaderDiv">
            <div class="calLoader">Loading...</div>
        </div>
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
                                <form:input class="hidden" id="scheduleIdInputHidden" path="scheduleId"></form:input>

                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan control-label ">Title</span>
                                    <input id="eventTitle" type="text" class="form-control" name="title"
                                           placeholder="..."
                                           data-cal-validation='[{"empty": "true", "minval": 3, "maxval": "60"}]'>
                                </div>


                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Type:</span>
                                    <label class="btn" onclick="checkPressing(this.id,1)">
                                        <form:radiobutton id="option1" path="sType" value="1"/> Meeting
                                    </label>
                                    <label class="btn" onclick="checkPressing(this.id,1)">
                                        <form:radiobutton id="option2" path="sType" value="2"/> Out of office
                                    </label>
                                    <label class="btn" onclick="checkPressing(this.id,1)">
                                        <form:radiobutton id="option3" path="sType" value="3"/> Personal
                                    </label>
                                    <label id="otherTypeLabel" class="  btn" onclick="checkPressing(this.id,1)">
                                        <form:radiobutton id="option4" path="sType" value="4"/> Other
                                    </label>
                                    <form:input id="otherType" type="text" class="form-control calOtherTypeInput"
                                                name="other" placeholder="Type other type....." path="other"/>
                                </div>

                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">More</span>
                                    <label style="margin-left: 1%" class="checkbox-inline"><form:checkbox
                                            id="isCompulsory" path="compulsory"/>Is compulsory</label>
                                    <label class="checkbox-inline"><form:checkbox id="notifyByEmail" path="toNotify"/>Notify
                                        by email</label>
                                    <label class="checkbox-inline"><form:checkbox id="isPrivate" path="private"/>Private event</label>
                                    <label class="hidden checkbox-inline"><form:checkbox id="isDraft" path="draft"/>is
                                        Draft</label>
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
                                           placeholder="..."
                                           data-cal-validation='[{"empty": "true", "minval": 3, "maxval": "60"}]'>
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
                                    <span class="input-group-addon calSpan">Attached Files</span>
                                    <span id="eventAttachedFiles"></span>
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">New Attachment</span>
                                    <form:input id="eventAttachment" type="file" class="form-control"
                                                placeholder="..." path="file" multiple="true"/>
                                </div>

                            </div>
                            <div id="viewBodyDiv">

                                <div class="CalendarViewHeader">
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Author</span>
                                        <div id="eventViewAuthor"></div>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Participants</span>
                                        <div id="eventViewParticipantsList"></div>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">References</span>
                                        <div id="eventViewReferencesList"></div>
                                    </div>
                                </div>
                                <hr>
                                <div class="CalendarViewBody">
                                    <input class="hidden" type="text" id="eventViewScheduleId"/>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Type</span>
                                        <input id="eventViewType" disabled/>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Title</span>
                                        <input id="eventViewTitle" disabled/>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Start</span>
                                        <input id="eventViewStart" disabled/>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">End</span>
                                        <input id="eventViewEnd" disabled/>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Place</span>
                                        <input id="eventViewPlace" disabled/>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Description</span>
                                        <input id="eventViewDescription" disabled/>
                                    </div>
                                    <div class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Attachment</span>
                                        <span id="eventViewAttachment"></span>
                                    </div>
                                </div>
                                <div class="CalendarViewFooter">
                                    <div id="CalendarModalViewDecisionButtonGroup" class="input-group calInputGroup">
                                        <span class="input-group-addon calSpan">Will you participate?</span>
                                        <div class="btn-group" role="group"
                                             aria-label="...">
                                            <input id="CalendarDecisionYes" type="button" value="Yes"
                                                   class="btn btn-green" onclick="checkPressing(this.id,2)" data=""/>
                                            <input id="CalendarDecisionNo" type="button" value="No"
                                                   class="btn btn-darkblue" onclick="checkPressing(this.id,2)"/>
                                        </div>
                                        <input id="eventDecisionText" type="text" class="form-control"
                                               name="decisionText"
                                               placeholder="Reason of not participating...">
                                    </div>
                                    <div class="row" id="eventMembersCalculation">
                                        <div class="col-md-2 col-md-offset-2" id="eventTotalParticipants">Total | <span>0</span>
                                        </div>
                                        <div class="col-md-2" id="eventTotalWillParticipate">Will Participate |
                                            <span>0</span></div>
                                        <div class="col-md-2" id="eventTotalNotParticipate">Not Participate |
                                            <span>0</span></div>
                                        <div class="col-md-2" id="eventTotalNotDecided">Not Decided | <span>0</span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                                <%-------------------------------%>
                        </div>
                        <div class="modal-footer">
                            <div id="CalendarModalCreateAuthorButtonGroup" class="btn-group" role="group"
                                 aria-label="...">
                                <input id="calSaveButton" type="button" value="Save"
                                       onclick="submitEvent(this.id)" class="btn btn-blue"/>
                                <input id="calSubmitButton" type="button" value=""
                                       onclick="submitEvent(this.id)" class="btn btn-green"/>
                                <input type="button" data-dismiss="modal" value="Cancel" class="btn btn-darkyellow"/>
                            </div>
                            <div id="CalendarModalViewAuthorButtonGroup" class="btn-group" role="group"
                                 aria-label="...">
                                <div id="calSendEmailToAllButton" type="text" value="SendEmail"
                                     onclick="submitEvent(this.id)" class="btn btn-blue">Send Email <span
                                        class="fa fa-envelope" aria-hidden="true"></span></div>
                                <input id="calDeleteButton" type="button" value="Delete"
                                       onclick="submitEvent(this.id)" class="btn btn-red"/>
                                <input type="button" data-dismiss="modal" value="Cancel" class="btn btn-darkyellow"/>
                            </div>
                            <div id="CalendarModalViewParticipantsButtonGroup" class="btn-group" role="group"
                                 aria-label="...">
                                <input id="calSubmitDecision" type="button" value="Submit"
                                       onclick="eventDecisionMaking(this.id)" class="btn btn-green"/>
                                <input type="button" data-dismiss="modal" value="Cancel" class="btn btn-darkyellow"/>
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

