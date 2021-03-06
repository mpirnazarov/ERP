<%--
  Created by IntelliJ IDEA.
  User: JAS SHAYKHOV
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Evaluation History"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <div class="mainBodyBlock">

        <h2 class="headerText"><span class="fa fa-fw fa-bar-chart"></span> Evaluation</h2>
        <table class="table table-bordered sartable">
            <thead>
            <tr>
                <th>Evaluator</th>
                <th>Date(YYYY-MM-DD)</th>
                <th>Comment</th>
                <th>Grade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${evaluationsVM}" var="evaluation" varStatus="status">
                <tr>
                    <td>${users.get(evaluation.evaluatorId)}</td>
                    <td>${evaluation.date}</td>
                    <td>${evaluation.comments}</td>
                    <td>
                        <c:if test="${evaluation.grade=='1'}">
                            S
                        </c:if>
                        <c:if test="${evaluation.grade=='2'}">
                            A
                        </c:if>
                        <c:if test="${evaluation.grade=='3'}">
                            B
                        </c:if>
                        <c:if test="${evaluation.grade=='4'}">
                            C
                        </c:if>
                        <c:if test="${evaluation.grade=='5'}">
                            D
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="border: solid #ffffff; width: 17%; right: 0; bottom: 0; position: fixed; border-radius: 10px;">
            <h3 style="text-align: center; text-decoration: underline">LEGEND:</h3>
            <h4 style="margin-left: 50px">S - Excellent</h4>
            <h4 style="margin-left: 50px">A - </h4>
            <h4 style="margin-left: 50px">B - </h4>
            <h4 style="margin-left: 50px">C - </h4>
            <h4 style="margin-left: 50px">D - </h4>
            <div></div>
        </div>
        <%--<div id="gen" class="tab-pane fade">
            <h3>Generatable documents</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>Document name</th>
                    <th>Document language</th>
                </tr>
                </thead>
                <tbody>
                &lt;%&ndash;<c:forEach items="${}" var="doc" varStatus="status">&ndash;%&gt;
                    <tr>
                        <td>sdfsdfsdf</td>
                        <td>
                            <div class="dropdown">
                                <button class="btn btn-info dropdown-toggle" type="button" data-toggle="dropdown">Choose language
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">EN</a></li>
                                    <li><a href="#">RU</a></li>
                                    <li><a href="#">UZ</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                &lt;%&ndash;</c:forEach>&ndash;%&gt;
                </tbody>
            </table>
        </div>--%>
    </div>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
