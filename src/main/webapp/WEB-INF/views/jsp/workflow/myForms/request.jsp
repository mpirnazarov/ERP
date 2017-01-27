<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.usermenu.AppointmentrecViewModel" %>
<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %>
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

            <div class="col-md-7 searchtoolstyle">
                <div class="input-group" style="margin-top: 10px; width: 100%;">
                    <span class="input-group-addon" id="formtype-addon" style="width: 25%;">Form type</span>
                    <select class="form-control" aria-describedby="formtype-addon" style="">
                        <option value=""></option>
                        <c:forEach var="type" items="${typeList}">
                            <option value="${type}">${type.toString()}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon" id="datefrom-addon" style="width: 25%;">Status:</span>
                    <select class="form-control" aria-describedby="datefrom-addon">
                        <%--<option>In progress</option>
                        <option>Revision</option>
                        <option>Draft</option>
                        <option>Completed</option>--%>
                    </select>
                </div>
                <div class="input-group" style="margin-top: 10px; width: 100%">
                    <span class="input-group-addon" id="saerchtype-addon" style="width: 25%">Attribute:</span>
                    <select class="form-control" aria-describedby="saerchtype-addon" style="width: 36%">
                        <option>Author</option>
                        <option>Title</option>
                    </select>
                    <input type="text" class="form-control" style="width: 64%">
                </div>
                <div class="input-group" style="margin-top: 10px; width: 100%">
                    <span class="input-group-addon" id="date-addon" style="width: 25%">Request date:</span>
                    <input type="text" class="form-control" id="sandbox-container" style="width:36%">
                    <button type="button" class="btn btn-success"
                            style="margin-left: 20%; border-radius: 0; width: 25%"><span
                            class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                    </button>
                </div>

            </div>
        </div>

        <table class="table table-bordered" style="background-color: #2b669a; color: inherit" id="dynamicHead">
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
            </tbody>


        </table>

        <div id="pagedListContainer">
        </div>
    </div>

</div>
</div>

<script>


    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});


</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
<script type="text/javascript">
    function initialize() {
        pagedList(0);
    }

    var currentPage = 0;
    var page = 0;


    function pagedList(id){
        var container = $('#pagedListContainer');
        var numberOfPages = 0;

        var table = $('#dynamicHead');
        var tbody = $('#dynamicBody');

        if(id==-1){
            page = currentPage+1;
        }
        else if (id==-2){
            page=currentPage-1;
        }
        else {
            page=id;
        }

        $.ajax({
            type:"POST",
            processData: false,
            url : '${pageContext.request.contextPath}/Workflow/list/'+page,
            success : function(data) {

                //table
                tbody.appendTo(table);
                tbody.empty();

                //set current page
                currentPage=data.page;

                $(data.models).each(function(i, req) {
                    //generate table
                    $('<tr/>').appendTo(tbody)
                        .append($('<td/>').text(i+1))
                        .append($('<td/>').text(req.form_type))
                        .append($('<td/>').text(req.request_subject))
                        .append($('<td/>').text(req.date_created))
                        .append($('<td/>').text(req.status))
                        .append($('<td/>').append($('<input type="button" value="View" style="color: red"/>')));
                });

                numberOfPages = data.maxPages;
                container.empty();
                container.append($('<input type="button" value="Prev" id="-2" style="color: red" onclick="pagedList(this.id)">'));

                //generate pegination buttons
                for(count=1; count<numberOfPages+1; count++){
                    container.append($('<input type="button" value="'+count+'" id="'+count+'" style="color: red" onclick="pagedList(this.id)">'));
                }
                container.append($('<input type="button" value="Next" id="-1" style="color: red" onclick="pagedList(this.id)">'));
            },

            error: function () {
                alert("Table not loaded");
            }
        });
    }
</script>

<script type="text/javascript">
    $(document).ready(function () {
        initialize();
    });
</script>
