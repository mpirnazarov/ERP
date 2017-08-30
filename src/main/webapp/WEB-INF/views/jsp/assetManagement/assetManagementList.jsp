<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: EvilDecision
  Date: 24.08.2017
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

</head>
<body>
<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-cubes" aria-hidden="true"></span> Asset Management</h2>

    <div class="container">
        <table cellspacing="0" width="100%" id="asset_table" class="display table table-bordered sarTable"></table>
    </div>


    <%--Modal--%>
    <div class="modal fade" id="AssetModal" tabindex="-1" role="dialog" aria-labelledby="AssetModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
                </div>
                <%-- <form:form modelAttribute="assetVM" method="post" id="categoryForm" enctype="multipart/form-data">
                     <div class="modal-body">
                         <form:input class="hidden" id="assetCategoryId" path="id"></form:input>
                         <label for="categoryName">Inventory Name</label>
                         <form:input id="categoryName" path="inventNumber" type="number" class="form-control"/>

                     </div>
                     <div class="modal-footer">
                         <button class="btn btn-default" data-dismiss="modal" style="float: left;">Close</button>
                         <button id="catSaveBtn" class="btn btn-green" type="submit" name="submit">Add</button>
                         <button id="catUpdateBtn" class="btn btn-blue" type="submit" name="update">Update</button>
                     </div>
                 </form:form>--%>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <script>

        var table_data = [];
        <c:forEach items="${AssetList}" var="asset">
        table_data.push([${asset.inventNumber},${asset.userId},"${asset.categoryName}",${asset.categoryId},"${asset.regDate}","<button>Click!</button>"]);
        </c:forEach>

        $(document).ready(function () {
           $('#asset_table').DataTable({
               data: table_data,
               columns: [
                   { title: "Invent Number" },
                   { title: "User id" },
                   { title: "Category" },
                   { title: "Category id" },
                   { title: "Reg Date" },
                   { title: "Action" }
               ]
           });

        });





        alert("working")
    </script>

</div>
</body>


