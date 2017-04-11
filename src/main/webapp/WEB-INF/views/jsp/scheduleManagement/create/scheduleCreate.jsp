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
<c:set var="pageTitle" scope="request" value="Schedule | Create"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<html>

<body>

<div class="mainBodyBlock">
    <h2 class="headerText"><span
            class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Create Schedule</h2>
    <form:form modelAttribute="schedule" cssClass="form-horizontal" method="post" id="scheduleMain" enctype="multipart/form-data">
        <div class="w3-container ">
            <div class="container">
                <%--Radio buttons--%>
                <span>Type:</span>
                <label class="radio-inline">
                    <form:radiobutton  placeholder="" aria-describedby="subject-addon" path="sType" value="1"/>Meeting
                </label>
                <label class="radio-inline">
                    <form:radiobutton  placeholder="" aria-describedby="subject-addon" path="sType" value="2"/>Out of office
                </label>
                <label class="radio-inline">
                    <form:radiobutton  placeholder="" aria-describedby="subject-addon" path="sType" value="3"/>Personal
                </label>
                <label class="radio-inline">
                    <form:radiobutton  placeholder="" aria-describedby="subject-addon" path="sType" value="4"/>Other
                </label>
            </div>
            <%--Date from to--%>
            <div class="container">
                <span>From:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="dateFrom"/>
                <span>To:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="dateTo"/>
            </div>

            <<%--div class="container">
                <span>Compulsory:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="isCompulsory"/>
                <span>Notify by Email:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="toNotify"/>
            </div>
--%>
            <div class="container">
                <span>Participants:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="participants"/>
                <span>References:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="references"/>
            </div>

            <div class="form-header" style="padding-top: 1%">
                <span>Title:</span><form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon" path="title"/>
                <span>Place:</span><form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon" path="place"/>
                <span>Description:</span><form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon" path="description"/>
            </div>

            <div class="container">
                <span>Attachment:</span><form:input class="" placeholder="" aria-describedby="subject-addon" path="attachments"/>
            </div>

        </div>
        <div class="btn-group" role="group" aria-label="...">
            <input type="submit" name="Save" value="Save" class="btn btn-blue"/>
            <input type="submit" name="Submit" value="Submit" class="btn btn-green"/>
            <input type="button" onclick="history.back()" value="Cancel" class="btn btn-red"/>
        </div>
    </form:form>
</div>
</body>
</html>
