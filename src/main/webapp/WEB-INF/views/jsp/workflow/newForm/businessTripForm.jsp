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
<c:set var="pageTitle" scope="request" value="Business trip"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>
<!-- JS file of autocomplete -->
<script src="/resources/core/js/jquery.easy-autocomplete.min.js"></script>

<!-- CSS file of autocomplete -->
<link rel="stylesheet" href="/resources/core/css/easy-autocomplete.min.css">

<%--<script src="/resources/core/js/jquery-1.12.4.min.js"></script>--%>

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
        <h2 class="page-header">Business trip</h2>

        <form:form modelAttribute="businessTripVM" cssClass="form-horizontal" method="post" id="myform">
            <div class="w3-container b3form">
            <div class="form-header">
                <div class="input-group" style="width: 100%">
                    <span class="input-group-addon" id="subject-addon" style="width: 25%">Subject:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="subject-addon"
                           style="width: 40%" path="subject" />
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"
                          style="width: 25%">Type of business trip:</span>
                    <form:select class="form-control" aria-describedby="saerchtype-addon" style="width: 40%" path="tripType" items="${triptypeList}">

                    </form:select>
                    <label class="radio-inline" style="margin-left: 1%; margin-top: 1%">
                        <form:radiobutton value="1" path="typeDomOver" /> Domestic
                    </label>
                    <label class="radio-inline" style="margin-top: 1%">
                        <form:radiobutton value="2" path="typeDomOver" /> Overseas
                    </label>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="destination-addon" style="width: 25%">Destination:</span>
                    <form:input type="text" class="form-control" placeholder="" aria-describedby="destination-addon"
                           style="width: 40%" path="destination" />
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon"
                          style="width: 25%">Purpose of Business trip:</span>
                    <form:textarea class="form-control" rows="5" id="comment" aria-describedby="purpose-addon"
                              style="width: 40%" path="purpose"></form:textarea>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        List of Business Trip members:
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: inherit"
                           id="dataTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Employee ID</th>
                            <th>Job title</th>
                            <th>Name of (Organization/Project)</th>
                            <th>From</th>
                            <th>To</th>
                        </tr>
                        </thead>
                        <tbody id="membersDynamicBody">
                            <TR style="color: black">
                                <TD> 1 </TD>
                                <TD> <input id="members0name"  name="" onclick="f2(this.id)"/> </TD>
                                <TD> <input type='text' id="members0userId" name = "membersEntityList[0].userId"  disabled/> </TD>
                                <TD> <input type='text' id='members0jobTitle' name = "" disabled/> </TD>
                                <TD> <input type='text' name = "membersEntityList[0].organizationName"/> </TD>
                                <TD> <input type='date' name = "membersEntityList[0].dateFrom"/> </TD>
                                <TD> <input type='date' name = "membersEntityList[0].dateTo"/> </TD>
                            </TR>

                        </tbody>
                    </table>
                    <input type="button" class="btn btn-normal" value="Add Row" onclick="addRow('dataTable')"/>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        Detail scheadule and To-do list:
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: inherit"
                           id="toDoDynamicHead">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody id="toDoDynamicBody">
                        <tr>
                            <td>01/02/2017</td>
                            <td>Meeting</td>
                        </tr>
                        <tr>
                            <td>02/02/2017</td>
                            <td>Some activity</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        Business trip expenses::
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: inherit"
                           id="expensesDynamicHead">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Airfair(A)</th>
                            <th>Daily Allowance(B)</th>
                            <th>Accommodation (C)</th>
                            <th>Other(D)</th>
                            <th>Total (A+B+C+D)</th>
                        </tr>
                        </thead>
                        <tbody id="expensesDynamicBody">
                        <tr>
                            <td>Kamola HR</td>
                            <td>900</td>
                            <td>450</td>
                            <td>1500</td>
                            <td>200</td>
                            <td>sum</td>
                        </tr>
                        <tr>
                            <td>Sarvar Zokirov</td>
                            <td>900</td>
                            <td>450</td>
                            <td>1500</td>
                            <td>200</td>
                            <td>sum</td>
                        </tr>
                        <tr>
                            <td>Jasur Shaykhov</td>
                            <td>900</td>
                            <td>450</td>
                            <td>1500</td>
                            <td>200</td>
                            <td>sum</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form-footer" style="margin-bottom: 5%">
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"
                          style="width: 25%">Approvals:</span>
                    <div class="tab-content" id="approvals">
                        <input type="text" id="demo-input-local"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon"
                          style="width: 25%">Executive:</span>
                    <div class="tab-content" id="executives">
                        <input type="text" id="demo-input-local2"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon"
                          style="width: 25%">Reference:</span>
                    <div class="tab-content" id="references">
                        <input type="text" id="demo-input-local3"/>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open
                          style="width: 25%">Attachment:</span>
                    <button type="button" class="btn btn-default" aria-describedby="attachment-addon"><span
                            class="glyphicon glyphicon-open" aria-hidden="true"></span> Upload
                    </button>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="date-addon"
                          style="width: 25%">Date(Start/End):</span>
                    <%--<form:input type="date" class="form-control" style="width:36%" placeholder="Start" path="start" />
                    <form:input type="date" class="form-control" style="width:36%" placeholder="End" path="end" />--%>
                </div>
                <div class="btn-group" role="group" aria-label="..." style="margin-left: 40%; margin-top: 3%">
                    <button type="button" class="btn btn-default">Save</button>
                    <input id="tv" type="submit" value="Submit" class="btn btn-success"/>
                    <%--<button id="tv" type="submit" class="btn btn-success">Submit</button>--%>
                    <button type="button" class="btn btn-danger">Cancel</button>
<<<<<<< Updated upstream
                </div>

                <div class="row" style="width: 500px">
                    <div class="userImgBox col-md-6" style="background-color: red">
                        <img class="userimg" src="/image/00096.jpg" height="50px" width="50px"/>
                    </div>
                    <div class="col-md-6" style="background-color: blue">
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                    </div>

                </div>

                <%--<div class="input-group" style="width:100%; height: 100px; background-color: red">
                    <div class="input-group-addon" id="myimg" style="background-color: green; width: 40%; height: 100%">
                        <img class="userimg" src="/image/00096.jpg" height="50px" width="50px"/>
                    </div>
                    <div aria-describedby="myimg" style="background-color: darkslategrey;">
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                    </div>
                </div>--%>
=======
                </div>
                <input id="1" onclick="f1(this.id)">

>>>>>>> Stashed changes
            </div>
        </div>
        </form:form>
    </div>

</div>




<script type="text/javascript">

    function f1(id) {
        alert(id);
    }

    /* Send input from approval list to controller by AJAX */
    $(document).ready(function() {
        /*$("input[type=submit]").click(function ()*/
        $("#tv").click(function (){
            var a=[];
            var b=[];
            var c=[];

            a = $("#approvals").children().siblings("input[type=text]").val();
            b = $("#references").children().siblings("input[type=text]").val();
            c = $("#executives").children().siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/NewForm/BusinessTripFormAjax",
                data :'approvals='+a+'&references='+b+'&executives='+c,
                success : function(response) {
//                    window.location.href = "/Workflow/NewForm/BusinessTripForm"
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });
        });


    });

    $(document).ready(function () {
        var form = $('#myForm').submit(function () {
            alert($('#members0userId').val());
        });
    });
    /* Send json data for approvals list*/
    $(document).ready(function () {
        $("#demo-input-local").tokenInput(${jsonData});
    });
    $(document).ready(function () {
        $("#demo-input-local2").tokenInput(${jsonData});
    });
    $(document).ready(function () {
        $("#demo-input-local3").tokenInput(${jsonData});
    });


    /* Add row and delete row from table*/
    function addRow(tableID) {
        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell2 = row.insertCell(0);
        cell2.innerHTML = rowCount ;


        var cell3 = row.insertCell(1);
        var element2 = document.createElement("input");


        var length=(table.rows.length)-1;
        element2.name = "";
        element2.id = "members"+length+"name";
        element2.onclick="f1(this.id)";
        cell3.appendChild(element2);


        var cell4 = row.insertCell(2);
        var element3 = document.createElement("input");
        element3.type = "text";
        var length=(table.rows.length)-1;
        element3.name = "membersEntityList["+length+"].userId";
        element3.id = "members"+length+"userId"
        cell4.appendChild(element3);


        var cell5 = row.insertCell(3);
        var element4 = document.createElement("input");
        element4.type = "text";
        var length=(table.rows.length)-1;
        element4.name = "";
        cell5.appendChild(element4);

        var cell6 = row.insertCell(4);
        var element5 = document.createElement("input");
        element5.type = "text";
        var length=(table.rows.length)-1;
        element5.name = "membersEntityList["+length+"].organizationName";
        cell6.appendChild(element5);

        var cell7 = row.insertCell(5);
        var element6 = document.createElement("input");
        element6.type = "date";
        var length=(table.rows.length)-1;
        element6.name = "membersEntityList["+length+"].dateFrom";
        cell7.appendChild(element6);

        var cell8 = row.insertCell(6);
        var element7 = document.createElement("input");
        element7.type = "date";
        var length=(table.rows.length)-1;
        element7.name = "membersEntityList["+length+"].dateTo";
        cell8.appendChild(element7);

        $('#members0name').trigger('click');
    }

    function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;

            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        }catch(e) {
            alert(e);
        }
    }


    /* Autocomplete functioning */

    var options = {
        data: ${jsonData},

        getValue: "name",

        list: {

            onSelectItemEvent: function() {
                 /*for(var i=id; i<500; i++)
                 {
                     var str = "";
                     str = "#members"+i+"name";
                     var str2 = "#members"+i+"userId";
                     var str3 = "#members"+i+"jobTitle";
                     var valueName = $(str).getSelectedItemData().id;
                     var valueJobTitle = $(str).getSelectedItemData().jobTitle;
                     $(str2).val(valueName).trigger("change");
                     $(str3).val(valueJobTitle).trigger("change");
                 }*/
                $("#members0userId").val($("#members0name").getSelectedItemData().id).trigger("change");
                $("#members2userId").val($("#members2name").getSelectedItemData().id).trigger("change");

            }
         }
    };

    $("#members0name").easyAutocomplete(options);

    /*function f2(id) {
        for(var i=0; i<500; i++) {
            str = "#members" + i + "name";
            $(str).easyAutocomplete(options);
        }
    }*/
    function f2(id) {
        $("#members2name").easyAutocomplete(options);
    }


</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
