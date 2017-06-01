<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sarvar
  Date: 30.05.2017
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Attendance"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-clock-o" aria-hidden="true"></span> Daily Attendance</h2>
    <table id="attendanceTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>User</th>
            <th>Position</th>
            <th>Office</th>
            <th>Extn.</th>
            <th>Start date</th>
            <th>Salary</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th>User</th>
            <th>Position</th>
            <th>Office</th>
            <th>Extn.</th>
            <th>Start date</th>
            <th>Salary</th>
        </tr>
        </tfoot>
    </table>
</div>
<script>
    $(document).ready(function () {
        $('#attendanceTable').DataTable({
            "ajax": '../ajax/data/arrays.txt',
            "info": false
        });
    });
</script>


