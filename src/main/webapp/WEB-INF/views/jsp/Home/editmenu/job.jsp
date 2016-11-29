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
<%
    ProfileViewModel a = (ProfileViewModel) request.getAttribute("userProfile");
    request.setAttribute("FullName", a.getFirstName()[2] + " " + a.getLastName()[2]);
    request.setAttribute("JobTitle", a.getJobTitle());
%>
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

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHRLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-lg-10">
            <div class="col-lg-8 col-lg-offset-2">
                <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %></h1>
                <h2 class="page-header">Job Experience</h2>
                        <!--Job experience table-->
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Organization</th>
                                <th>Position</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${jobexpVM}" var="jobexp" varStatus="status">
                                <tr>
                                    <td>${jobexp.organization}</td>
                                    <td>${jobexp.position}</td>
                                    <td>${jobexp.startDate}</td>
                                    <td>${jobexp.endDate}</td>
                                    <td><a href="./jobexp/updateJob/${jobexp.id}/" class="btn btn-default">Edit</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;" value="Print this page" onclick="printpage()"/>
                        <a href="" class="btn btn-primary">Add</a>
                    </div>
                </div>
            </div>
        </div>
<script>
    /*menu handler*/
    $(function(){
        function stripTrailingSlash(str) {
            if(str.substr(-1) == '/') {
                return str.substr(0, str.length - 1);
            }
            return str;
        }

        var url = window.location.pathname;
        var activePage = stripTrailingSlash(url);

        $('.nav li a').each(function(){
            var currentPage = stripTrailingSlash($(this).attr('href'));

            if (activePage == currentPage) {
                $(this).parent().addClass('active');
            }
        });
    });
</script>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
