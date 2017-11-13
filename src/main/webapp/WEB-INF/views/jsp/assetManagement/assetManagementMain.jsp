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
<div class="mainBodyBlock">

    <h2 class="headerText"><span class="fa fa-cubes" aria-hidden="true"></span> Asset Management</h2>

    <table id="asset_table" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Inventory Number</th>
            <th>Name</th>
            <th>Owner</th>
            <th>Location</th>
            <th>Registration date</th>
            <th>Registration info</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody id="asset_table_body"></tbody>
    </table>

    <button class="btn btn-danger" onclick="assetModal()">Sarvar</button>
    <button class="btn btn-danger" onclick="fill3()">Test</button>
    <button class="btn btn-info" onclick="saveAssets()">Save</button>

    <jsp:include page="/WEB-INF/views/jsp/shared/assetManagement/assetManagementModals.jsp"></jsp:include>
</div>
<script>

    var changedItems = [];

    $(document).ready(function () {

        var table_body = $("#asset_table_body");

        $.get("/AssetManagement/AllAssetJSON", function (data, status) {

            $.each(data, function (item, value) {
                table_body.append("<tr data-asset-id='" + value.id + "'>" +
                    "<td>" + value.inventNum + "</td>" +
                    "<td><span class='asset-link'>" + value.nameRu + "</span></td>" +
                    "<td>" +
                    '<select class="form-control asset-input-owner" onchange="fill(this)"></select>' +
                    "</td>" +
                    "<td>" + '<input type="text" class="form-control asset-input-location" onchange="fill(this)" placeholder="Location" value="' + value.location + '">' + "</td>" +
                    "<td>" + value.regDate + "</td>" +
                    "<td>" + value.regInfo + "</td>" +
                    "<td>" + value.cost + "</td>" +
                    "</tr>");
            });

            $.each(${UserslistJson}, function (item, value) {
                $('select.asset-input-owner').append('<option value="' + value.id + '">' + value.name + '</option>');
            });

            $("#asset_table").DataTable();
        });
    });

    function fill(item) {

        var targetRow = $(item).parent().parent();
        var assetId = targetRow.data('asset-id');
        var assetOwner = targetRow.find('select.asset-input-owner').val();
        var assetLocation = targetRow.find('input.asset-input-location').val();


        if (changedItems != "") {
            $.each(changedItems, function (item, value) {

                if (value.a_id == assetId) {
                    changedItems.splice(item, 1);
                }

            });
        }

        var asset = {
            a_id: assetId,
            a_owner: assetOwner,
            a_location: assetLocation
        };

        changedItems.push(asset);
        /*console.log(changedItems);*/
    }

    /*function saveAssets() {

        $.ajax({
            type : "POST",
            url : "/AssetManagement/testSubmit",
            data : {
                myArray: changedItems //notice that "myArray" matches the value for @RequestParam
                           //on the Java side
            },
            success : function(response) {
                console.log(response)
            },
            error : function(e) {
                alert('Error: ' + e);
            }
        });
    }*/

</script>
</body>


