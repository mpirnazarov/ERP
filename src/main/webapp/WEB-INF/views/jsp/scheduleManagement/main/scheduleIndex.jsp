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
<link rel="stylesheet" href="${fullcalendarCSS}"/>
<%--CSS--%>

<%--JS--%>
<spring:url value="/resources/core/js/jquery.min.js" var="jqueryJS"/>
<spring:url value="/resources/core/js/moment.min.js" var="momentJS"/>
<spring:url value="/resources/core/js/fullcalendar.js" var="fullcalendarJS"/>
<%--<script src="${jqueryJS}"></script>--%>
<script src="${momentJS}"></script>
<script src="${fullcalendarJS}"></script>
<%--JS--%>
<script>

    $(document).ready(function() {

        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: ''
            },
            titleFormat: 'MMMM  D, YYYY',
            columnFormat: 'ddd DD MMM',
            minTime: '09:00:00',
            maxTime: '22:00:00',
            slotLabelInterval:'00:30',
            firstDay: 1,
            allDaySlot:false,
            defaultDate: new Date(),
            defaultView: 'agendaWeek',
            navLinks: false, // can click day/week names to navigate views
            selectable: true,
            selectHelper: true,

            select: function(start, end, jsEvent, view) {
                // start contains the date you have selected
                // end contains the end date.
                // Caution: the end date is exclusive (new since v2).
                /*var allDay = !start.hasTime() && !end.hasTime();
                alert(["Event Start date: " + moment(start).format(),
                    "Event End date: " + moment(end).format(),
                    "AllDay: " + allDay].join("\n"));*/

/*
                alert("Start: " + moment(start).format() + "    End :" + moment(end).format())
*/


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
                        window.location.href="${pageContext.request.contextPath}/ScheduleManagement/create/"
                    }
                });

            },

            /*on DAY clik FUNCTION*/
           /* dayClick: function(date, jsEvent, view, resourceObj) {

                alert('Date: ' + date.format());
                alert('Resource ID: ' + resourceObj.id);



                $(this).css('background-color', 'red');

            },*/
            /*on DAY clik FUNCTION END*/
            /*on EVENT click FUNCTION*/
            eventClick: function(calEvent, jsEvent, view) {

                alert('Event: ' + calEvent.title);
                alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
                alert('View: ' + view.name);

                // change the border color just for fun
                $(this).css('border-color', 'red');

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
                    title: 'Meeting',
                    start: '2017-04-12T10:30:00',
                    end: '2017-04-12T12:30:00'
                },
                {
                    title: 'Lunch',
                    start: '2017-04-12T12:00:00'
                },
                {
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

</script>

<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span> Calendar</h2>

    <div  class="w3-container">

        <div id='calendar'></div>

    </div>
</div>