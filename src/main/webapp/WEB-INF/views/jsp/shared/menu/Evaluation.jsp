<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %><%--
  Created by IntelliJ IDEA.
  User: Muslimbek
  Date: 08.11.2016
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="kendo" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Users List"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1 class="page-header">Userslist</h1>
        <div class="table-responsive">

            <br/>
            <form:form method="POST" modelAttribute="formModel">
                <table id="myTable" class="display table" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Comments</th>
                        <th>Grade</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${formModel.forms}" var="forms" varStatus="status">
                        <tr>
                            <form:hidden path="forms[${status.index}].id"/>
                            <td><c:out value="${forms.id}"/></td>
                            <td><c:out value="${forms.firstName}"/></td>
                            <td><c:out value="${forms.lastName}"/></td>
                                <%--<td>
                                    <spring:url value="/Manager/user/${user.id}/geninfo" var="userUrl" />
                                    <button class="btn btn-info" onclick="location.href='${userUrl}'">View</button>
                                </td>--%>
                            <td><form:textarea path="forms[${status.index}].comments" placeholder="Comments"
                                               cssClass="form-control text-box" rows="3" cols="15"/></td>
                            <td>
                                <form:select class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                             path="forms[${status.index}].grade">
                                    <form:option value="0" label="...."/>
                                    <form:options items="${forms.grades}"></form:options>

                                </form:select>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <td><input type="submit" name="submit" value="Submit"></td>
            </form:form>
        </div>

    </div>
</div>
<%--<script>
    $(document).ready(function(){
        $('#myTable').DataTable({
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            dom: 'Bfrtip',
            select: true,
            "order": [[ 0, "desc" ]],
            buttons: [
                'copy', 'excel', 'pdf', 'print',
            ]
        });
    });
</script>--%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>