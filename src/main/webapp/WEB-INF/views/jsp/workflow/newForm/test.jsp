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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Appointment Record"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type=button]").click(function () {
            var a=[];
            a=$(this).siblings("input[type=text]").val();

            $.ajax({
                type : "POST",
                url : "/Workflow",
                data : {
                    myArray: a //notice that "myArray" matches the value for @RequestParam
                               //on the Java side
                },
                success : function(response) {
                    alert("Would submit: " + response.toString());
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });

        });
    });
</script>


<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <div class="tab-content">
            <input type="text" id="demo-input-local" name="blah" />
            <input type="button" value="Submit" />
            <script type="text/javascript">
                $(document).ready(function() {
                    $("#demo-input-local").tokenInput(${jsonData});
                });
            </script>
        </div>
    </div>
</div>

<div class="col-sm-9 col-md-offset-1">
    <div class="col-lg-8 col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Request</h2>

        <div class="tab-content">
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
                <%--<input id="printpagebutton" type="button" style="color: #0c0c0c; visibility:hidden;"--%>
                <%--value="Print this page" onclick="printpage()"/>--%>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product" varStatus="status">
                <c:url value="/user/detail/${product.ID}" var="detailUrl" />
                <tr>
                    <%--<td><a href="${detailUrl}">${product.ID}</a> <form:hidden path="products[${status.index}].ID" value="${product.ID}" /></td>--%>
                    <td><form:input type="text" style="color: #0f0f0f" value="${products[status.index].firstName}" path="products[${status.index}].firstName"/></td>
                    <%--<td><form:input path="products[${status.index}].lastName" class="input-mini" type="text" /></td>--%>

                    <td><button id="save" name="save" value="${product.ID}" class="btn btn-success"><i class="fa fa-save"></i> Save </button>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editProduct${product.ID}"><i class="fa fa-edit"></i> Edit</button>
                        <button id="delete" name="delete" value="${product.ID}" class="btn btn-danger"><i class="fa fa-trash-o"></i></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
