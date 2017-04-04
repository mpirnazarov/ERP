<%--
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
<c:set var="pageTitle" scope="request" value="Project history"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">

        <h2 class="headerText"><span class="fa fa-file-powerpoint-o fa-fw"></span> Projects History</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="text-center">Project name</th>
                <th class="text-center">PM</th>
                <th class="text-center">Description (Role)</th>
                <th class="text-center">Period</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${viewModel.size() == 0}">
                <tr>
                    <td colspan="4">
                        You have not participated in any project yet.<br>
                        Please contact your project manager to add you into the project.
                    </td>
                </tr>
            </c:if>
            <c:forEach items="${viewModel}" var="model">
                <tr>
                    <td>${model.projectName}</td>
                    <td>${model.projectManager}</td>
                    <td>${model.description}</td>
                    <td>${model.period}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
