<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <c:set var="pageTitle" scope="request" value="Asset Management"/>
    <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

    <spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
    <script type="text/javascript" src="${tokenInputJs}"></script>

</head>
<body>
<div class="mainBodyBlock asset_index">
    <h2 class="headerText"><span class="fa fa-cubes" aria-hidden="true"></span> Asset Management</h2>

    <div class="container">
        <button class='btn btn-darkblue' style="margin-bottom: 1%" onclick="asset_create()"><span class="fa fa-plus-circle" aria-hidden="true"></span> Add New Asset</button>
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
                    <h4 class="modal-title" id="gridSystemModalLabel"></h4>
                </div>
                <form:form modelAttribute="assetVM" method="post" id="assetForm">
                    <div class="modal-body">
                        <form:input class="hidden" id="asset_id" path="id"></form:input>


                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Inventory Number</span>
                            <form:input id="asset_inventoryNumber" path="inventNumber" class="form-control"/>
                        </div>

                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Category</span>
                            <form:select id="asset_category_select" path="categoryId"></form:select>
                        </div>

                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Public</span>
                            <form:checkbox id="asset_public" path="checkPublic" />
                        </div>

                        <div class="input-group assetInputGroup">
                            <span class="input-group-addon calSpan">Owner</span>
                            <div id="assetOwnerDiv">
                                <form:input id="asset_owner" path="userId" class="form-control"/>
                            </div>
                        </div>


                        <div class="btn btn-red" onclick="test()">Test</div>



                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" style="float: left;">Close</button>
                        <button id="assetSaveBtn" class="btn btn-green" type="submit" name="submit">Save</button>
                        <button id="assetUpdateBtn" class="btn btn-blue" type="submit" name="update">Update</button>
                    </div>
                </form:form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <button onclick="test()">Test</button>


    <script>

        var table_data = [];
        <c:forEach items="${AssetList}" var="asset">

        var $a = {};
        $a.assetId = ${asset.id};
        $a.invNumber = ${asset.inventNumber};
        $a.userId = ${asset.userId};
        $a.userName = "${asset.userNameSurname}";
        $a.categoryName = "${asset.categoryName}";
        $a.categoryId = ${asset.categoryId};
        $a.regDate = "${asset.regDate}";
        $a.publicLabel = "";
        $a.public = ${asset.checkPublic};
        ${asset.checkPublic}
            ? $a.publicLabel = "true" : $a.publicLabel = "false";
        <%--${asset.checkPublic()} == 1 ? $a.public = "True" : $a.public = "False";--%>

        table_data.push([$a.invNumber, $a.categoryName, $a.public, $a.userName, $a.regDate, "<button class='btn btn-blue' onclick='asset_edit(this)' data-asset='" + JSON.stringify($a) + "'>Edit</button>"]);
        </c:forEach>

        $(document).ready(function () {

            $('#asset_owner').tokenInput(${UserslistJson}, {tokenLimit: 1},{theme: "facebook"})

            $('#asset_table').DataTable({
                data: table_data,
                columns: [
                    {title: "Invent Number"},
                    {title: "Category"},
                    {title: "Public"},
                    {title: "Owner"},
                    {title: "Reg Date"},
                    {title: "Action"}
                ],
                dom: 'Bfrtip',
                buttons: [
                    {
                        extend: 'excel',
                        text: '<i class="fa fa-file-excel-o" aria-hidden="true" style="color:inherit"></i> ' + 'Excel',
                        className: 'btn btn-green',
                        exportOptions: {
                            columns: 'th:not(:last-child)'
                        }
                    },
                    {
                        extend: 'pdf',
                        text: '<i class="fa fa-file-pdf-o" aria-hidden="true" style="color:inherit"></i> ' + 'PDF',
                        className: 'btn btn-darkyellow',
                        exportOptions: {
                            columns: 'th:not(:last-child)'
                        }
                    },
                    {
                        extend: 'print',
                        text: '<i class="fa fa-print" aria-hidden="true" style="color:inherit"></i> ' + 'Print',
                        className: 'btn btn-blue',
                        exportOptions: {
                            columns: 'th:not(:last-child)'
                        }
                    }
                ]
            });

        });

        function test() {

        }

        function asset_edit(obj) {
            asset_clear();

            var asset = $(obj).data("asset");
            var owner = [];

            $('#asset_id').val(asset.categoryId);
            $('#asset_inventoryNumber').val(asset.invNumber);

            if(asset.public){
                $('#asset_public').prop('checked', true);
            }else if(!asset.public){
                $('#asset_public').prop('checked', false);
            }

            $.each(${UserslistJson}, function( key, value ) {

                    if(value.id == asset.userId){
                        var user = {
                            id: value.id,
                            name: value.name,
                            jobTitle: value.jobTitle,
                            department: value.department
                        }

                        owner.push(user)
                    }

            });

            $('#asset_owner').tokenInput(${UserslistJson},{prePopulate: owner, tokenLimit: 1});

            generateCategory(asset.categoryId);
            $('#gridSystemModalLabel').text("Edit Asset")
            $('#assetSaveBtn').hide();
            $('#assetUpdateBtn').show();
            $('#AssetModal').modal('show');
        }
        
        function asset_create() {
            asset_clear();

            $('#asset_id').val('1');

            $('#asset_owner').tokenInput(${UserslistJson},{tokenLimit: 1});

            generateCategory();
            $('#gridSystemModalLabel').text("Create New Asset")
            $('#assetSaveBtn').show();
            $('#assetUpdateBtn').hide();
            $('#AssetModal').modal('show');
        }

        function generateCategory(sl) {

            var category = $('#asset_category_select');

            $.get("/Asset/Category/JsonList", function (data, status) {

                data.forEach(function (cat) {
                    category.append($("<option></option>").attr("value", cat.id).text(cat.assetCategoryName));
                })

                if(typeof(sl) != "undefined"){
                    $('#asset_category_select').val('' + sl);
                }

            });
        }
        
        function asset_clear() {

            $('#assetForm :input').val("");
            $('#asset_category_select').empty();
            $('#asset_public').prop('checked', 0);
            $('#asset_owner').tokenInput("clear");
            $('#assetForm ul.token-input-list').remove();
        }

        

    </script>

</div>
</body>


