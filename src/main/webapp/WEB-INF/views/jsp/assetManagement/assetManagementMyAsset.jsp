<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: EvilDecision
  Date: 25.08.2017
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" scope="request" value="My Assets"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-cube" aria-hidden="true"></span> My Assets</h2>
    <div class="container">
        <table id="myTable" class="table sarTable table-bordered">
            <!-- here should go some titles... -->
            <tr style="background-color: #e6e6e6;">
                <th>Inventory Number</th>
                <th>Public</th>
                <th>Category</th>
            </tr>
            <c:forEach items="${myAssets}" var="assets">
                <tr>
                    <td>
                        <span class="fa fa-cube" aria-hidden="true"></span> <c:out value="${assets.inventNumber}"/>
                    </td>
                    <td>
                        <c:out value="${assets.checkPublic}"/>
                    </td>
                    <td>
                        <c:out value="${assets.categoryName}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            dom: 'Bfrtip',
            select: true,
            "order": [[0, "desc"]],
            buttons: [
                'copy', 'excel', 'pdf', 'print',
            ]
        });
    });
</script>

