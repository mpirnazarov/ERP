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
    <link rel="stylesheet" href="/resources/core/css/datatables-responsiv.min.css">
    <script src="/resources/core/js/datatables-responsive.js"></script>
</head>
<body>
<div class="mainBodyBlock assetManagement_mainBlock">

    <h2 class="headerText"><span class="fa fa-cubes" aria-hidden="true"></span> Asset Management</h2>


    <button id="asset_management_save_button" class="btn btn-green asset-table-button" onclick="saveAssets()"><span class="fa fa-floppy-o"
                                                                                  aria-hidden="true"></span> Save
    </button>
    <div id="asset_management_loader">
        <div class="loader">Loading...</div>
    </div>
    <table id="asset_table" class="table table-bordered display responsive nowrap" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Name</th>
            <th>Inventory Number</th>
            <th>Owner</th>
            <th>Location</th>
            <th>Registration date</th>
            <th>Registration info</th>
            <th>Price</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </tfoot>
        <tbody id="asset_table_body"></tbody>
    </table>


    <%-- <div class="input-group">
         <span class="input-group-addon" id="basic-addon1">@</span>
         <select data-default-owner="' + value.ownerId + '" class="form-control asset-input-owner" aria-describedby="basic-addon1" onchange="fill(this)"></select>
     </div>--%>

    <jsp:include page="/WEB-INF/views/jsp/shared/assetManagement/assetManagementModals.jsp"></jsp:include>

</div>
<script>

    var changedItems = [];

    $(document).ready(function () {

      /*  if (typeof(Worker) !== "undefined") {
            // Yes! Web worker support!
            // Some code.....
            console.log("WORKING")
        } else {
            // Sorry! No Web Worker support..
        }*/

        var table_body = $("#asset_table_body");

        $.get("/AssetManagement/AllAssetJSON", function (data, status) {

            $('#asset_table').addClass('letter');
            $('#asset_management_save_button').addClass('showButton');

            $('#asset_management_loader').fadeOut( 2000, "linear");
          /*  $('#asset_management_save_button').fadeIn(6000);*/




            $.each(data, function (item, value) {
                table_body.append("<tr data-asset-id='" + value.id + "'>" +
                    "<td>" + value.nameRu + "</td>" +
                    "<td><span class='asset-link' onclick='assetModal(" + value.inventNum + ")'>" + value.inventNum + "</span></td>" +
                    '<td data-search="'+ value.ownerFullName +'" data-order="'+ value.ownerFullName +'" data-sort="'+ value.ownerFullName +'" data-filter="'+ value.ownerFullName +'">' +
                    '<div class="input-group">' +
                    '<select data-default-owner="' + value.ownerId + '" class="form-control asset-input-owner" onchange="fill(this)"></select>' +
                    '<span class="input-group-addon"><span class="fa fa-info-circle asset-userinfo-icon" aria-hidden="true"></span></span>' +
                    '</div>' +
                    "</td>" +
                    "<td data-search='"+ value.location +"'>" + '<input type="text" class="form-control asset-input-location" onchange="fill(this)" placeholder="Location" value="' + value.location + '">' + "</td>" +
                    "<td>" + value.regDate + "</td>" +
                    "<td>" + value.regInfo + "</td>" +
                    "<td>" + value.cost + "</td>" +
                    "</tr>");

                /*     $('tr[data-asset-id="' + value.id + '"]').find('select');*/
                /*val(value.owenrId);    */
            });



            $.each(${UserslistJson}, function (item, value) {

                var $s = $('select.asset-input-owner');

                $s.append('<option value="' + value.id + '">' + value.name + '</option>');
                /*$s.val($s.data('default-owner'));*/
            });

            $('tr').find('select').each(function () {
                var $this = $(this);
                var $data = $this.data('default-owner');
                $this.val($data);
            });

            $("#asset_table").DataTable({
                initComplete: function () {
                    this.api().columns([0,4]).every( function () {
                        var column = this;
                        var select = $('<select><option value=""><code>~none~</code></option></select>')
                            .appendTo($(column.footer()).empty())
                            .on( 'change', function () {

                                var val = $.fn.dataTable.util.escapeRegex($(this).val());

                                column
                                    .search( val ? '^'+val+'$' : '', true, false )
                                    .draw();
                            } );

                        column.data().unique().sort().each( function ( d, j ) {
                            /*var html_val = $.parseHTML(d);
                            console.log(d);

                            if(html_val.innerText == ''){
                              /!*  console.log(html_val)
                                var new_d = $(html_val)[0].innerText
                                select.append( '<option value="'+new_d+'">'+new_d+'</option>' )*!/
                            }*/

                            /*var val = $('<div/>').html(d).text();
                            select.append( '<option value="' + val + '">' + val + '</option>' );*/

                       /*     select.append( '<option value="'+d+'">'+d+'</option>' )*/

                            select.append( '<option value="' + d + '">' + d + '</option>' );
                        } );
                    } );
                }
            });
        });
    });

    function fill(item) {

        var targetRow = $(item).closest('tr');
        var assetId = targetRow.data('asset-id');
        var assetOwner = targetRow.find('select.asset-input-owner').val();
        var assetCurrentOwner = targetRow.find('select.asset-input-owner').data('default-owner');
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
            a_newOwner: assetOwner,
            a_oldOwner: assetCurrentOwner,
            a_location: assetLocation
        };

        changedItems.push(asset);
    }

    function saveAssets() {
        if (changedItems == '') {
            console.log("no assets to save")
        } else {
            console.log(changedItems)
        }
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


