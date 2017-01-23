<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Requset"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<style>
    body :not(a) {
        color: inherit;
    }

</style>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Request</h2>

        <div class="w3-container">


            <div class="col-md-8 col-md-offset-4"
                 style="height: 180px; transform: scale(0.75, 0.75)">
                <div class="input-group" style="margin-top: 10px; width: 100%">
                    <span class="input-group-addon" id="formtype-addon" style="width: 25%">Form type</span>
                    <select class="form-control" aria-describedby="formtype-addon" style="">
                        <option>Business trip</option>
                        <option>Leave approve</option>
                        <option>Unformatted</option>
                    </select>
                    <span class="input-group-addon" id="datefrom-addon" style="width: 25%;">Status</span>
                    <select class="form-control" aria-describedby="datefrom-addon">
                        <option>In progress</option>
                        <option>Revision</option>
                        <option>Draft</option>
                        <option>Completed</option>
                    </select>
                </div>
                <div class="input-group" style="margin-top: 10px; width: 100%">
                    <span class="input-group-addon" id="saerchtype-addon" style="width: 25%">Attribute</span>
                    <select class="form-control" aria-describedby="saerchtype-addon" style="width: 36%">
                        <option>Author</option>
                        <option>Title</option>
                    </select>
                    <input type="text" class="form-control" style="width: 64%">
                </div>
                <div class="input-group" style="margin-top: 10px; width: 100%">
                    <span class="input-group-addon" id="date-addon" style="width: 25%">Request date</span>
                    <input type="text" class="form-control" id="sandbox-container" style="width:36%">
                    <button type="button" class="btn btn-success" style="margin-left: 20%; border-radius: 0; width: 25%"><span
                            class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                    </button>
                </div>

            </div>
        </div>

        <table class="table table-bordered" style="background-color: #2b669a; color: inherit">
            <thead>
            <tr>
                <th>#</th>
                <th>Form type</th>
                <th>Title</th>
                <th>Request date</th>
                <th>Current status</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="dynamicBody">
            <%--<tr>
                <td>1</td>
                <td>Business trip</td>
                <td>Some Tripde</td>
                <td>01/01/2017</td>
                <td>In Progress</td>
                <td><a class='btn btn-info btn-xs' href="#"><span class="glyphicon glyphicon-eye-open"></span> View</a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Business trip</td>
                <td>Some Tripde</td>
                <td>01/01/2017</td>
                <td>Compleated</td>
                <td><a class='btn btn-info btn-xs' href="#"><span class="glyphicon glyphicon-eye-open"></span> View</a>
                </td>
            </tr>--%>
            </tbody>

            <%--Db connect example--%>
            <%--<div class="tab-content">
                <div id="jobexp" class="tab-pane fade in active">
                    <!--Job experience table-->
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reqList}" var="req">
                            <tr>
                                <td>${req.id}</td>
                                <td>${req.subject}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    &lt;%&ndash;<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"&ndash;%&gt;
                    &lt;%&ndash;value="Print this page" onclick="printpage()"/>&ndash;%&gt;
                </div>
            </div>--%>

        </table>
    </div>

</div>
</div>

<script>


    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});


</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
