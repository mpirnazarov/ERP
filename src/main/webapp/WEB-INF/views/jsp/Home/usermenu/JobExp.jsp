<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function printpage() {
        //Get the print button and put it into a variable
        var printButton = document.getElementById("printpagebutton");
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("jobex");
        printButton.style.visibility = 'hidden';
        printButton = document.getElementById("je");
        printButton.style.visibility = 'hidden';

        //Print the page content
        window.print()
        //Set the print button to 'visible' again
        //[Delete this line if you want it to stay hidden after printing]
        var printButton = document.getElementById("printpagebutton");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("jobex");
        printButton.style.visibility = 'visible';
        printButton = document.getElementById("je");
        printButton.style.visibility = 'visible';
    }

</script>
<c:set var="pageTitle" scope="request" value="Job experience"/>
<%
    String a = request.getAttribute("name").toString();
    request.setAttribute("ProfileModel", a);
%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
        <div class="col-lg-8 col-lg-offset-2">
            <h1 class="page-header" id="je">Job experience</h1>

            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#jobexp">Job experience</a></li>
            </ul>

            <div class="tab-content">
                <div id="jobexp" class="tab-pane fade in active">
                    <h3 id="jobex">Job experience</h3>
                    <!--Job experience table-->
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Organization</th>
                            <th>Position</th>
                            <th>Start date</th>
                            <th>End date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                        <tr>
                            <td>${jobexp.organization}</td>
                            <td>${jobexp.position}</td>
                            <td>${jobexp.startDate}</td>
                            <td>${jobexp.endDate}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <input id="printpagebutton" type="button" style="color: #0c0c0c" value="Print this page" onclick="printpage()"/>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
