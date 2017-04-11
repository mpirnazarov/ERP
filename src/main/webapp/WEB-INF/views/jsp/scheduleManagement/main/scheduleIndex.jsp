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

        $('#eventParticipants').tokenInput(${UserslistJson});
        $('#eventReferences').tokenInput(${UserslistJson});
        $('ul.token-input-list').addClass('tokenOverwriteClass');

        $('#dateTimeStart').datetimepicker({
            mask: '9999/19/39 29:59'
        });
        $('#dateTimeEnd').datetimepicker({
            mask: '9999/19/39 29:59'
        });


        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: ''
            },
            titleFormat: 'MMMM  D, YYYY',
            columnFormat: 'ddd DD MMM',
            minTime: '09:00:00',
            maxTime: '21:00:00',
            slotLabelInterval: '00:30',
            firstDay: 1,
            allDaySlot: false,
            aspectRatio: 2,
            defaultDate: new Date(),
            defaultView: 'agendaWeek',
            navLinks: false, // can click day/week names to navigate views
            selectable: true,
            selectHelper: true,

            select: function (start, end, jsEvent, view) {

                var myData = {
                    start: moment(start).format(),
                    end: moment(end).format()
                }

                $.ajax({
                    type: "POST",
                    processData: false,
                    data: 'start=' + moment(start).format() +
                    '&end=' + moment(end).format(),
                    url: '${pageContext.request.contextPath}/ScheduleManagement/create/',
                    success: function (data) {
                        // do what ever you want with data
                    }
                });

                createEvent(moment(start).format('YYYY/MM/DD hh:mm'), moment(end).format('YYYY/MM/DD hh:mm'), "calendar");

            },

            eventClick: function (calEvent, jsEvent, view, start, end) {


                /*alert('Event: ' + calEvent.title);
                 alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
                 alert('View: ' + view.name);*/
                alert('start: ' + moment(start).format());

                // change the border color just for fun
                $(this).css('border-color', 'red');


                updateEvent(moment(calEvent.start).format('YYYY/MM/DD hh:mm'), moment(calEvent.end).format('YYYY/MM/DD hh:mm'), calEvent.title, calEvent.type, calEvent.place, calEvent.description, calEvent.isCompulsory, calEvent.notifyByEmail);

            },
            /*on EVENT click FUNCTION END*/
            editable: false,
            eventLimit: true, // allow "more" link when too many events
            events: [
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
                    start: '2017-04-12T10:30:00',
                    end: '2017-04-12T12:30:00',
                    title: 'Meetingggggg',
                    type: 3,
                    place: 'LG CNS Uzbekistan head office',
                    description: 'everyone MUST ET',
                    isCompulsory: true,
                    notifyByEmail: false
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

            ]
        });

    });

    function createEvent(start, end, calendarObject) {


        clearModal();

        var targetCalendar = $('#' + calendarObject)
        var EventStart = start;
        var EventEnd = end;
        var msg = "Start " + EventStart + "\n End: " + EventEnd;
        /*alert("Start " + EventStart + "\n End: " + EventEnd );*/

        $('#message').html(msg);

        $('#dateTimeStart').val(EventStart);
        $('#dateTimeEnd').val(EventEnd);


        $('#CalendarModal').modal('show');

    }

    function updateEvent(start, end, title, type, place, description, isCompulsory, notifyByEmail) {

        clearModal();

        var EventStart = start;
        var EventEnd = end;
        var EventTitle = title;
        var EventType = type;
        var EventPlace = place;
        var EventDescription = description;
        var EventIsCompulsory = isCompulsory;
        var EventNotifyByEmail = notifyByEmail;

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

        if (EventIsCompulsory) {
            $('#isCompulsory').prop('checked', true);
        }

        if (EventNotifyByEmail) {
            $('#notifyByEmail').prop('checked', true);
        }

        $('#CalendarModal').modal('show');

        $('#message').html('this is EDIT FORM')

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
            $('#otherType').css('display', 'none');
            $('#otherType').css('opacity', '0');
        }

        $('#option1').prop('checked', true);
        $('#isCompulsory').attr('checked', false);
        $('#notifyByEmail').attr('checked', false);

        $('#eventParticipants').tokenInput("clear");
        $('#eventReferences').tokenInput("clear");


    }

</script>

<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span> Calendar</h2>

    <div class="w3-container">

        <%--Calendar--%>
        <div id='calendar'></div>

        <%--Modal--%>
        <div class="modal fade" id="CalendarModal" tabindex="-1" role="dialog" aria-labelledby="CalendarModalLabel">
            <div class="modal-dialog calModal" role="document">
                <div class="modal-content">
                    <form:form modelAttribute="ScheduleVM" method="post" >
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 style="font-size:25px" class="modal-title" id="CalendarModalLabel"><span
                                    class="glyphicon glyphicon glyphicon-edit"
                                    aria-hidden="true"></span> New Event</h4>
                        </div>
                        <div class="modal-body">

                            <div id="createBodyDiv">


                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Title</span>
                                    <input id="eventTitle" type="text" class="form-control" name="title"
                                           placeholder="...">
                                </div>
                                <%--<div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Type:</span>
                                    <label class="btn" onclick="checkPressing()">
                                        <form:radiobutton id="option1" path="sType" value="1"/> Meeting
                                    </label>
                                    <label class="btn" onclick="checkPressing()">
                                        <form:radiobutton id="option2" path="sType"/> Out of office
                                    </label>
                                    <label class="btn" onclick="checkPressing()">
                                        <form:radiobutton id="option3" path="sType"/> Personal
                                    </label>
                                    <label id="otherTypeLabel" class="btn" onclick="checkPressing(this.id)">
                                        <form:radiobutton id="option4" path="sType"/> Other
                                    </label>
                                    <input id="otherType" type="text" class="form-control calOtherTypeInput"
                                           name="other" placeholder="Type other type.....">
                                </div>--%>

                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">More</span>
                                    <label style="margin-left: 1%" class="checkbox-inline"><input id="isCompulsory"
                                                                                                  name="isCompulsory"
                                                                                                  type="checkbox"
                                                                                                  value="">Is compulsory</label>
                                    <label class="checkbox-inline"><input id="notifyByEmail" name="toNotify"
                                                                          type="checkbox" value="">Notify by
                                        email</label>
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
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Participants</span>
                                    <input id="eventParticipants" type="text" class="form-control" placeholder="...">
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">References</span>
                                    <input id="eventReferences" type="text" class="form-control" placeholder="...">
                                </div>
                                <div class="input-group calInputGroup">
                                    <span class="input-group-addon calSpan">Attachment</span>
                                    <input id="eventAttachment" type="text" class="form-control"
                                           placeholder="...">
                                </div>

                            </div>

                        </div>
                        <div class="modal-footer">
                            <div class="btn-group" role="group" aria-label="...">
                                <input type="submit" name="Save" value="Save" class="btn btn-blue"/>
                                <input type="submit" name="Submit" value="Submit" class="btn btn-green"/>
                                <input type="button" data-dismiss="modal" value="Cancel" class="btn btn-red"/>
                            </div>
                        </div>
                    </form:form>
                    <span id="message"></span>
                </div>
            </div>
        </div>
    </div>

</div>
</div>

