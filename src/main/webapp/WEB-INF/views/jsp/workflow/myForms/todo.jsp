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
                        <option value=""></option>
                        <c:forEach var="type" items="${typeList}">
                            <option value="${type}">${type.toString()}</option>
                        </c:forEach>
                    </select>
                    <span class="input-group-addon" id="datefrom-addon" style="width: 25%;">Status</span>
                    <select class="form-control" aria-describedby="datefrom-addon">
                        <c:forEach var="status" items="${statusList}">
                            <option value="${status}">${status.toString()}</option>
                        </c:forEach>
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
                    <button type="button" class="btn btn-success" style="margin-left: 20%; border-radius: 0; width: 25%" onclick="filter()"><span
                            class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                    </button>
                </div>

            </div>
        </div>

        <table class="table table-bordered" style="background-color: #2b669a; color: inherit" id="tablecha">
            <thead>
            <tr>
                <th>#</th>
                <th>Form type</th>
                <th>Title</th>
                <th>Author</th>
                <th>Request date</th>
                <th>Current status</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="tbodycha">
            </tbody>
        </table>
    </div>

</div>
</div>

<script>


    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});


</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
<script type="text/javascript">
    function initialize() {
        generateTable();
    }


    function filter() {
        $.ajax({
            type: "POST",
            processData: false,
            url : '${pageContext.request.contextPath}/workflow/filter/'+1+'/'+"asdahdkjahsdk",
            success : function(data) {
               alert(data);
            },
            error: function () {
                alert("sdasdasd");
            }
        });
    }

    function generateTable(){
        var table = $('#tablecha');
        var tbody = $('#tbodycha');

        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            processData: false,
            url : '${pageContext.request.contextPath}/workflow/list',
            success : function(data) {
                tbody.appendTo(table);
                $(data).each(function(i, req) {
                    $('<tr/>').appendTo(tbody)
                        .append($('<td/>').text(i+1))
                        .append($('<td/>').text(req.form_type))
                        .append($('<td/>').text(req.request_subject))
                        .append($('<td/>').text(req.user_name))
                        .append($('<td/>').text(req.date_created))
                        .append($('<td/>').text(req.status))
                        .append($('<td/>').append($('<input type="button" value="View" style="color: red"/>')));
                });
            },
            error: function () {
                alert("error");
            }
        });
    }
</script>

<script type="text/javascript">
    $(document).ready(function () {
        initialize();
    });
</script>