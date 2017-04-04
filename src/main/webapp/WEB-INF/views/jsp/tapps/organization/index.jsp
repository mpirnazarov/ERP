<%@ page import="com.lgcns.erp.hr.enums.ProjectStatus" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity" %>
<%@ page import="com.lgcns.erp.hr.enums.ProjectRole" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 08.11.2016
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Customer Organizations"/>
<c:set var="managerId" scope="request" value="<%= ProjectRole.Manager.getValue()%>"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <%
            if ((int) (request.getAttribute("SystemRole")) == 1)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpCTOLayout.jsp\"></jsp:include>");
            else if ((int) (request.getAttribute("SystemRole")) == 2)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");
            else if ((int) (request.getAttribute("SystemRole")) == 3)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpHrLayout.jsp\"></jsp:include>");
            else
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");
        %>
            <div class="mainBodyBlock">
                <h1 class="headerText"><span class="fa fa-briefcase fa-fw"></span> Customer organization</h1>
                <%
                    if (((int) request.getAttribute("SystemRole")) == 1)
                        out.print("<div style=\"overflow: hidden\">\n" +
                                "                    <input type=\"button\" value=\"Create new\" class=\"btn btn-green\" style=\"float: left;\"  onclick=\"location.href='/Organizations/Create'\">\n" +
                                "                    <div style=\"clear: both;\">&nbsp;</div>\n" +
                                "                </div>");
                %>

                <div class="table-responsive">
                    <table id="myTable" class="sartable display table table-bordered">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Organization name</th>
                            <th>TIN</th>
                            <th>Address</th>
                            <c:if test='<%=(int)request.getAttribute("SystemRole") == 1%>'>
                                <td class="nosort"></td>
                                <td class="nosort"></td>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <%int i = 0;%>
                        <c:forEach items="${viewModel}" var="organization">
                            <%i++;%>
                            <tr>
                                <td><%=i%>
                                </td>
                                <td>${organization.name}</td>
                                <td>${organization.tin}</td>
                                <td>${organization.address}</td>
                                <c:if test='<%=(int)request.getAttribute("SystemRole") == 1%>'>
                                    <td>
                                        <a class="btn btn-md btn-blue .btn-md"
                                           href="/Organizations/Edit/<c:out value="${organization.id}"/>">Edit</a>
                                    </td>
                                </c:if>
                                <c:if test='<%=(int)request.getAttribute("SystemRole") == 1%>'>
                                    <td>
                                        <a class="btn btn-md btn-red .btn-md"
                                           href="/Organizations/Delete/<c:out value="${organization.id}"/>">Delete</a>
                                    </td>
                                </c:if>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
    </div>
</div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            dom: 'Bfrtip',
            buttons: [
                'copy', 'excel', 'pdf', 'print'
            ],
            aoColumnDefs: [{
                'bSortable': false,
                'aTargets': ['nosort']
            }]
        });
    });
</script>