<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.Format" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.lgcns.erp.hr.viewModel.WorkloadViewModels.CalendarReturningModel" %>
<%@ page import="com.lgcns.erp.hr.enums.WorkloadType" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.ProjectLocalizationsEntity" %><%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 31.10.2016
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="Manage Workload"/>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | ERP System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/core/css/normalize.css" var="normalizeCss"/>
    <spring:url value="/resources/core/css/style.css" var="styleCss"/>
    <spring:url value="/resources/core/css/fullcalendar.css" var="fullCalendar"/>
    <spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss"/>
    <spring:url value="/resources/core/js/jquery-1.12.4.min.js" var="jquery"/>
    <spring:url value="/resources/core/js/jquery.validate.js" var="jqueryValidation"/>
    <link rel="icon" href="/resources/images/lg-2-multi-size.ico" type="image/x-icon">
    <link rel="stylesheet" href="${normalizeCss}"/>
    <link rel='stylesheet prefetch' href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${styleCss}"/>
    <link rel="stylesheet" href="${fullCalendar}"/>
    <script src="${jquery}"></script>
    <script src="${jqueryValidation}"></script>
</head>

<body>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
<div class="container">
    <h2>Calendar</h2>

    <span class="hidden" id="mondayHidden"><fmt:formatDate value="${model.monday}" pattern="dd.MM.yyyy"/></span>


    <div class="table-responsive">
        <table class="table table-bordered">
            <%
                float monTotal = 0, tueTotal = 0, wedTotal = 0, thuTotal = 0, friTotal = 0, satTotal = 0, sunTotal = 0;
                int tabindex = 1;
            %>
            <thead>
            <tr>
                <th colspan="2">
                    <div style="text-align:center"><h3>Week</h3></div>
                </th>

                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    CalendarReturningModel model = (CalendarReturningModel) request.getAttribute("model");
                    Calendar c = Calendar.getInstance();
                    c.setTime(model.getMonday());
                    c.add(Calendar.DATE, -7);
                    Date tempDatePrev = c.getTime();
                    c.setTime(model.getMonday());
                    c.add(Calendar.DATE, 7);
                    Date tempDateNext = c.getTime();

                %>
                <th id="previous">
                    <div style="text-align:center">
                        <form action="/Workload/DiffCalendar" method="get">

                            <input type="hidden" name="today" value="<%= sdf.format(tempDatePrev) %>"/>
                            <button type="submit"><i class="fa fa-caret-left fa-3x"></i></button>

                        </form>
                    </div>
                </th>
                <th colspan="5">
                    <div style="text-align:center"><h3><fmt:formatDate value="${model.monday}"
                                                                       pattern="dd MMM yyy"/></h3></div>
                </th>
                <th id="next">
                    <div style="text-align:center">
                        <form action="/Workload/DiffCalendar" method="get">

                            <input type="hidden" name="today" value="<%= sdf.format(tempDateNext) %>"/>
                            <button type="submit"><i class="fa fa-caret-right fa-3x"></i></button>

                        </form>
                    </div>
                </th>
            </tr>
            <tr id="weekDays" class="headers">
                <td colspan="2" align="center">Days</td>
                <%!
                    String addDaysAndFormat(Date day, int daysToAdd, String format) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(day);
                        c.add(Calendar.DATE, daysToAdd);
                        SimpleDateFormat sdf = new SimpleDateFormat(format);

                        return sdf.format(c.getTime());
                    }

                    String generateProject(CalendarReturningModel model, String projectCode, String projectName, int projectId, int tabindex) {
                        String returning = "" +
                                "<tr>\n" +
                                "   <td class=\"td-head-default\" id=\"" + projectId + "\" rowspan=\"5\" style=\"vertical-align:middle;\">" + projectCode + "<br>" + projectName + "</td>\n" +
                                "</tr>";
                        for (int i = 1; i < 5; i++) {
                            int typeCode;
                            String typeName;
                            switch (i) {
                                case 1: {
                                    typeCode = WorkloadType.Work_Project.getValue();
                                    typeName = "Work";
                                    break;
                                }
                                case 2: {
                                    typeCode = WorkloadType.Training.getValue();
                                    typeName = "Training";
                                    break;
                                }
                                case 3: {
                                    typeCode = WorkloadType.Sick_leave.getValue();
                                    typeName = "Sick leave";
                                    break;
                                }
                                case 4: {
                                    typeCode = WorkloadType.Annual_leave.getValue();
                                    typeName = "Annual";
                                    break;
                                }
                                default: {
                                    typeCode = WorkloadType.Work_Administrative.getValue();
                                    typeName = "Default";
                                }
                            }
                            returning += "<tr>\n" +
                                    " <td class=\"hidden\"id=\"" + projectId + "\"></td>\n" +
                                    " <td class=\"td-head-default\" id=\"" + typeName + "\">" + typeName + "</td>\n";

                            for (int ii = 0; ii < 7; ii++) {
                                returning += "" +
                                        "<td class=\"duration\" data-tabindex=\"" + tabindex + "\"> \n";

                                Calendar c = Calendar.getInstance();
                                c.setTime(model.getMonday());
                                c.add(Calendar.DATE, ii);
                                for (WorkloadEntity wl : model.getWorkloads()) {
                                    if (wl.getDate() == c.getTime() && wl.getWorkloadType().equals(typeCode)) {
                                        //monTotal += wl.getDuration();
                                        //out.write(wl.getDuration());
                                        returning += String.valueOf(wl.getDuration()) + "\n </td>";
                                        break;
                                    }
                                }

                                tabindex++;
                            }
                            returning += "\n" +
                                    "</tr>";
                        }


                        return returning;
                    }

                    String getEmptyRow(){
                        return "<tr>\n" +
                                "    <td class=\"td-space\" colspan=\"9\"></td>\n" +
                                "</tr>";
                    }
                %>
                <td id="Mon" align="center">Mon<br/><%=addDaysAndFormat(model.getMonday(), 0, "dd MMMM")%>
                </td>
                <td id="Tue" align="center">Tue<br/><%=addDaysAndFormat(model.getMonday(), 1, "dd MMMM")%>
                </td>
                <td id="Wed" align="center">Wed<br/><%=addDaysAndFormat(model.getMonday(), 2, "dd MMMM")%>
                </td>
                <td id="Thu" align="center">Thu<br/><%=addDaysAndFormat(model.getMonday(), 3, "dd MMMM")%>
                </td>
                <td id="Fri" align="center">Fri<br/><%=addDaysAndFormat(model.getMonday(), 4, "dd MMMM")%>
                </td>
                <td id="Sat" align="center">Sat<br/><%=addDaysAndFormat(model.getMonday(), 5, "dd MMMM")%>
                </td>
                <td id="Sun" align="center">Sun<br/><%=addDaysAndFormat(model.getMonday(), 6, "dd MMMM")%>
                </td>
            </tr>
            </thead>
            <tbody id="body">
            <%
                out.print(generateProject(model, "Team project","", 0, 1));
                tabindex = 8;
                for(ProjectsEntity project : model.getProjects()){
                    out.print(getEmptyRow());
                    ProjectLocalizationsEntity temp = (ProjectLocalizationsEntity)project.getProjectLocalizationsesById().toArray()[0];
                    out.print(generateProject(model, project.getCode(), temp.getName(), project.getId(), tabindex));
                    tabindex+=28;
                }
            %>
            <%--<c:forEach items="${model.projects}" var="project">
                <tr>
                    <td class="td-space" colspan="9"></td>
                </tr>
                <%
                    ProjectsEntity tempP = (ProjectsEntity) pageContext.findAttribute("project");
                    ProjectLocalizationsEntity tempPLoc = (ProjectLocalizationsEntity)(tempP.getProjectLocalizationsesById().toArray()[0]);
                    out.print(generateProject(model, tempP.getCode(), tempPLoc.getName(), tempP.getId(), tabindex));
                    //generateProject(model, ((ProjectsEntity) pageContext.findAttribute("project")).getCode(), ((ProjectLocalizationsEntity) (((ProjectsEntity) pageContext.findAttribute("project")).getProjectLocalizationsesById().toArray()[0])).getName(), ((ProjectsEntity) pageContext.findAttribute("project")).getId(), 8)
                %>
            </c:forEach>--%>

            </tbody>

        </table>
    </div>
</div>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>