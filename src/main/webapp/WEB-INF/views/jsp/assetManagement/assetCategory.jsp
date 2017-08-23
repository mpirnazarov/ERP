<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: DS
  Date: 15.08.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" scope="request" value="ERP | Contacts"/>
<c:set var="pageTitle" scope="request" value="Projects"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/commonHead.jsp"></jsp:include>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>


<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <%
           /*if ((int) (request.getAttribute("SystemRole")) == 1)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpCTOLayout.jsp\"></jsp:include>");
            else if ((int) (request.getAttribute("SystemRole")) == 2)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");
            else if ((int) (request.getAttribute("SystemRole")) == 3)
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpHrLayout.jsp\"></jsp:include>");
            else
                out.print("<jsp:include flush=\"true\" page=\"/WEB-INF/views/jsp/shared/erpUserLayout.jsp\"></jsp:include>");*/
        %>
        <div class="mainBodyBlock">
            <h1 class="headerText"><span class="fa fa-sticky-note-o fa-fw"></span> Asset management</h1>
            <%
              /*  if (((int) request.getAttribute("SystemRole")) == 1)
                    out.print("<div style=\"overflow: hidden\">\n" +
                            "                    <input type=\"button\" value=\"Create new project\" class=\"btn btn-green\" style=\"float: left;\"  onclick=\"location.href='/Projects/Create'\">\n" +
                            "                    <div style=\"clear: both;\">&nbsp;</div>\n" +
                            "                </div>");*/
            %>
            <div class="w3-container">

                    <div id="myTable_filter" class="dataTables_filter">
                        <button type="button" class="btn btn-green" data-dismiss="modal" id="clickModal">Add new asset</button>
                        <label name="search_type">Search:<input id="search_type" type="search" class="form-control input-sm" placeholder="" aria-controls="myTable">
                        </label>
                    </div>
            </div>
            <div class="table-responsive">
                <table id="myTable" class="display table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Asset type</th>
                        <th>Asset description</th>
                        <th>Serial number</th>
                        <th>Date</th>
                        <th class="nosort">Status</th>
                        <td class="nosort">Status</td>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td>2017-02-08</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a class="btn btn-md btn-green .btn-md" id="editBtn">Edit</a></td>
                            <td><a class="btn btn-md btn-red .btn-md">Delete</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content" style="width:800px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Add new asset</h4>
                </div>
                <div class="modal-body" id="msg" style="height: 500px;">
                <!--<span id="message">
                </span>-->

                    <div class="w3-container b3form main_asset">
                         <div class="form-header" style="padding-top: 1%">
                             <div class="input-group" >
                              <span class="input-group-addon no-border" id="saerchtype-addon">Absence type:</span>
                                    <input type="text" class="form-control" placeholder="Absence type"/>
                                </select>
                            </div>
                            <div class="input-group" style="margin-top: 1%">
                                <span class="input-group-addon no-border" id="purpose-addon">Asset description:</span>
                                <textarea class="form-control" rows="4" id="comment" aria-describedby="purpose-addon"
                                               style="border-color:#999999" path="description"></textarea>
                            </div>
                             <div class="input-group" style="margin-top: 1%">
                                 <span class="input-group-addon no-border" id="saerchtype-addons">Serial number:</span>
                                 <input type="text" class="form-control" placeholder="Serial number"/>
                                 </select>
                             </div>

                             <div class="input-group"  style="margin-top: 1%" >
                                 <span class="input-group-addon no-border" id="date-addon">Date:</span>
                                 <input placeholder="yyyy-mm-dd" type="text" class="form-control" id="reqsandbox-container-to">
                                 </select>
                             </div>

                             <div class="input-group"  style="margin-top: 1%">
                                 <span class="input-group-addon no-border" id="condition-addon">Condition:</span>
                                 <select id="absenseSelect" name="absenceType" aria-describedby="saerchtype-addon"  class="form-control">
                                     <option value="0" selected="selected"></option>
                                     <option value="1">Good</option>
                                     <option value="2">Bad</option>
                                     <option value="3">Middle</option>
                                 </select>
                             </div>
                        </div>

                        <!--<div class="form-footer" style="padding-bottom: 2%">

                            <div class="input-group" style="margin-top: 2%">
                                <span class="input-group-addon" id="attachment-addon">Attachment:</span>
                                <input type="file" path="file" id="file" class="form-control input-sm" multiple="true"/>
                                <span class="glyphicon warningIcon" aria-hidden="true"></span>
                            </div>
                            <div class="input-group" style="margin-top: 2%">
                                <span class="input-group-addon" id="date-addon">Date(Start/End):</span>
                                <input type="text" class="form-control" style="width:50%" name="start" path="start"
                                            id="start"/>
                                <input type="text" class="form-control" style="width:50%" name="end" path="end" id="end"/>
                                <span class="glyphicon warningIcon" aria-hidden="true"></span>
                            </div>
                        </div>-->
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-blue" data-dismiss="modal">Add</button>
                    <button type="button" class="btn btn-red" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--<div class="container col-lg-10 col-lg-offset-1 col-sm-12">
    <div class="headerDiv">
        <div class="logoDiv" onclick="location='/login'"></div>
        <hr>
        <h1>Asset Management</h1>
    </div>
    <div class="modal-body">
        <%-------------------------------%>
      <!--  <div id="createBodyDiv">
           <%/* <form:form id="assetCategoryForm" modelAttribute="AssetCategory" method="post" enctype="multipart/form-data">
                <form:input path="assetItemName" type="text" class="form-control calOtherTypeInput"
                            name="other" placeholder="Type category name..."/>
                <div class="btn-group">
                    <input type="submit" value="Submit">
                </div>
           </form:form>*/%>

        </div>-->
    <!--</div>-->
<!--</div>
</div>-->

<script>

    $('#reqsandbox-container-to').datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});

    $(document).ready(function(){
        $("#clickModal").click(function(){
            $("#myModal").modal();
        });

        $("#editBtn").click(function(){
            $("#myModal").modal();
        });
    });
</script>
</body>
</html>