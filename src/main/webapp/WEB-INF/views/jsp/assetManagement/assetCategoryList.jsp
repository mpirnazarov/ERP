<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: DS
  Date: 15.08.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<%--CSS--%>
<spring:url value="/resources/core/css/fullcalendar.css" var="fullcalendarCSS"/>
<spring:url value="/resources/core/css/jquery-ui.min.css" var="jqueryUiCSS"/>
<spring:url value="/resources/core/css/jquery.datetimepicker.css" var="dateTimePickerCSS"/>

<div class="mainBodyBlock">
    <%--<h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span> Calendar</h2>--%>
    <div class="w3-container" style="padding-top: 2%">
                <table class="table sarTable table-bordered">
                    <!-- here should go some titles... -->
                    <tr>
                        <th>id</th>
                        <th>Category Name</th>
                    </tr>
                    <c:forEach items="${categoryList}" var="category">
                        <tr>
                            <td>
                                <c:out value="${category.id}" />
                            </td>
                            <td>
                                <c:out value="${category.assetItemName}" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    </div>
</div>
</body>
</html>