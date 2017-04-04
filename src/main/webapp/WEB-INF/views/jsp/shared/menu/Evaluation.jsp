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

    <div class="mainBodyBlock">
        <h1 class="headerText"><span class="fa fa-fw fa-bar-chart"></span> Evaluation page </h1>
        <div class="table-responsive">

            <br/>
            <form:form method="POST" modelAttribute="formModel">
                <table id="myTable" class="display table sartable" cellspacing="0" width="100%">
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
                <div class="form-group">
                    <div class="col-lf-offset-1 col-md-9">
                        <input type="submit" name="submit" value="Submit" class="btn btn-green"/>
                        <input type="button" onclick="history.back()" value="Cancel"
                               class="btn btn-red"/>
                    </div>
                </div>
            </form:form>
        </div>

    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>