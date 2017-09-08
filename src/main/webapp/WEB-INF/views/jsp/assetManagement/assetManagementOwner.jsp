<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LGCNS
  Date: 8/18/2017
  Time: 2:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>


<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>
<c:set var="pageTitle" scope="request" value="Manage Owners"/>


<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-cubes" aria-hidden="true"></span> Asset Management</h2>

    <div class="w3-container">

        <div id="calFilterDiv">
            <div id="calFilterSelectGroup">
                <label for="asset_ownerSearch">Select Owner</label>
                <input id="asset_ownerSearch">
            </div>
            <button type="button" class="btn btn-black" onclick="getUserAssets()" id="clickModal">Open Assets</button>
        </div>

        <h2 class="headerText" style="margin-bottom: -30px;"><span class="fa fa-arrow-circle-o-left" aria-hidden="true"></span> Switch Owner <span class="fa fa-arrow-circle-o-right" aria-hidden="true"></span></h2>

        <div class="row">
            <div class="col-md-12" style="margin-top:50px">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="row">
                            <div class="col-md-12">
                                <select class="form-control" id="asset_userSelectBox_1" onchange="loadAssets()"></select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                    <select multiple="multiple" class="form-control list_size" id="asset_userAssetList1"></select>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-2">
                        <div class="row">
                            <div class="row btn-top">
                                <div class="col-sm-12">
                                    <button type="button" class="btn btn-grey btn-df form-control" onclick="moveAllAssets('right')"><i
                                            class="glyphicon glyphicon-forward"></i></button>
                                </div>
                            </div>
                            <div class="row btn-top">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-grey btn-df form-control" onclick="moveSingleAsset('right')"><i
                                            class="glyphicon glyphicon-chevron-right "></i></button>
                                </div>
                            </div>
                            <div class="row btn-top">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-grey btn-df form-control" onclick="moveSingleAsset('left')"><i
                                            class="glyphicon glyphicon-chevron-left "></i></button>
                                </div>
                            </div>
                            <div class="row btn-top">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-grey btn-df form-control" onclick="moveAllAssets('left')"><i
                                            class="glyphicon glyphicon-backward"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="row">
                            <div class="col-md-12">
                                <select class="form-control" id="asset_userSelectBox_2" onchange="loadAssets()"></select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <select multiple="multiple" class="form-control list_size" id="asset_userAssetList2"></select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="row">
            <div class="col-md-12" style="margin-top:50px">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="row">
                            <div class="col-md-12">
                                <select path="users" class="form-control" id="userSelect">
                                    <option></option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form id="userItemsForm" action="#" method="post" style="margin-top: 10px;">
                                    <select multiple="multiple" class="form-control list_size" onchange="call()"
                                            id="userItemSelect">
                                        <option>Table</option>
                                        <option>Table2</option>
                                    </select>
                                    <% /*  <form:select path="itemlist" class="form-control list_size" onchange="call()"
                                         id="userItemSelect">
                                <c:forEach items="${availableItems}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </form:select>*/ %>
                                </form>

                            </div>
                        </div>

                    </div>
                    <div class="col-lg-2">
                        <div class="row">
                            <div class="row btn-top">
                                <div class="col-sm-12">
                                    <button type="button" id="multiselect2_rightAll"
                                            class="btn btn-grey btn-df form-control"><i
                                            class="glyphicon glyphicon-forward"></i></button>
                                </div>
                            </div>
                            <div class="row btn-top">
                                <div class="col-md-12">
                                    <button type="button" id="multiselect1_rightSelected"
                                            class="btn btn-grey btn-df form-control"><i
                                            class="glyphicon glyphicon-chevron-right "></i></button>
                                </div>
                            </div>
                            <div class="row btn-top">
                                <div class="col-md-12">
                                    <button type="button" id="multiselect1_leftSelected"
                                            class="btn btn-grey btn-df form-control"><i
                                            class="glyphicon glyphicon-chevron-left "></i></button>
                                </div>
                            </div>
                            <div class="row btn-top">
                                <div class="col-md-12">
                                    <button type="button" id="multiselect1_leftAll"
                                            class="btn btn-grey btn-df form-control"><i
                                            class="glyphicon glyphicon-backward"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="row">
                            <div class="col-md-12">
                                <select path="usersec" class="form-control" id="userSelectSec">
                                    <option></option>
                                </select>

                                <% /* <form:select path="usersec" class="form-control" id="userSelectSec">
                                <c:forEach items="${availableUsers}" var="users">
                                    <option value="${users.userid}">${users.users}</option>
                                </c:forEach>
                            </form:select>*/ %>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form id="demoform1" action="#" method="post" style="margin-top: 10px;">
                                    <select multiple="multiple" class="form-control list_size" id="userItemSelectSec">
                                        <option>Table</option>
                                        <option>Table2</option>
                                    </select>

                                    <% /* <form:select path="itemlist" class="form-control list_size" onchange="call()"
                                             id="userItemSelect">
                                    <c:forEach items="${availableItems}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </form:select>*/ %>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>--%>
    </div>
</div>

<!-- Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-4">
                            <img id="asset_ownerImage" class="img-thumbnail">
                        </div>
                        <div class="col-md-8">
                            <table class="table sartable table-bordered table-user-information">
                                <tbody >
                                <tr>
                                    <td><b>Name:</b></td>
                                    <td id="asset_ownerName"></td>
                                </tr>
                                <tr>
                                    <td><b>Job title:</b></td>
                                    <td id="asset_ownerJobTitle"></td>
                                </tr>
                                <tr>
                                    <td><b>Department:</b></td>
                                    <td id="asset_ownerDepartmen"></td>
                                </tr>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>

                <div id="main-wrapper">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-white">
                                <div class="panel-heading clearfix">
                                    <h4 class="panel-title"><span class="fa fa-cubes" aria-hidden="true"></span>Assets
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table sartable table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Inventory Number</th>
                                                <th>Category</th>
                                                <th>Public</th>
                                            </tr>
                                            </thead>
                                            <tbody id="asset_ownerAssetsTable"></tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!--Row-->
                </div><!--Main Wrapper-->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<%--<script>



    /*asset manage code*/

    /*var selectedItem;
     var selectedId;

     function call() {
     var x = document.getElementById("userItemSelect").selectedIndex;
     var y = document.getElementById("userItemSelect").options;
     selectedItem = y[x].text;
     selectedId = y[x].index;
     alert("Index: " + y[x].index + " is " + y[x].text);
     }

     function callsec() {

     var x = document.getElementById("userItemSelectSec").selectedIndex;
     var y = document.getElementById("userItemSelectSec").options;
     selectedItem = y[x].text;
     selectedId = y[x].index;
     alert("Index: " + y[x].index + " is " + y[x].text);
     }*/

    var listItem;
    $(function () {
        function moveItems(origin, dest) {
            $(origin).find(':selected').appendTo(dest);
            var itemOptions = $("#userItemSelect option");
            var itemValues = $.map(itemOptions, function (option) {
                return option.value;
            });

            var itemSecOptions = $("#userItemSelectSec option");
            var itemSecValues = $.map(itemSecOptions, function (option) {
                return option.value;
            });

            if (dest == '#userItemSelect') {
                $.ajax({
                    type: "POST",
                    url: "/move",
                    data: {
                        fromUserId: $("#userSelect").val(),
                        toUserId: $("#userSelectSec").val(),
                        fromItems: JSON.stringify(itemValues),
                        toItems: JSON.stringify(itemSecValues)
                    },
                    success: function (data) {
                        alert(data);
                    },
                    error: function (data) {
                    }
                });
            } else {

            }
        }

        function moveAllItems(origin, dest) {
            $(origin).children().appendTo(dest);
            //  alert(selectedItem);
            var listOfItems = $('#userItemSelect').children();
            for (var i = 0; i < listOfItems.length; i++) {
                alert(listOfItems[i].value);
            }
            //var childrenArray = $(origin).children().toArray();
            //alert(childrenArray.toString());
        }

        $('#multiselect1_leftSelected').click(function () {
            moveItems('#userItemSelectSec', '#userItemSelect');
        });

        $('#multiselect1_rightSelected').on('click', function () {
            moveItems('#userItemSelect', '#userItemSelectSec');
        });

        $('#multiselect1_leftAll').on('click', function () {
            moveAllItems('#userItemSelectSec', '#userItemSelect');
        });

        $('#multiselect2_rightAll').on('click', function () {
            moveAllItems('#userItemSelect', '#userItemSelectSec');
        });
    });

    /*$('#userSelect').change(function () {
        var userId = $('#userSelect option:selected').val();

        $.ajax({
            type: "POST",
            url: "/filter/" + userId,
            success: function (data) {
                $('#userItemSelect').empty();
                $.each(data, function (index, item) {
                    $('#userItemSelect').append($('<option>',
                        {
                            value: item.id,
                            text: item.name
                        }));
                });

            },
            error: function (data) {
                alert(data);
            }

        });
    });

    $('#userSelectSec').change(function () {
        var userId = $('#userSelectSec option:selected').val();

        $.ajax({
            type: "POST",
            url: "/filter/" + userId,
            success: function (data) {

                $('#userItemSelectSec').empty();
                $.each(data, function (index, item) {
                    $('#userItemSelectSec').append($('<option>',
                        {
                            value: item.id,
                            text: item.name
                        }));
                });

            },
            error: function (data) {
                alert(data);
            }

        });
    });*/

    /*---------------*/
</script>--%>

<script>

    $(document).ready(function () {
        var uSelect1 = $('#asset_userSelectBox_1');
        var uSelect2 = $('#asset_userSelectBox_2');
        uSelect1.empty();
        uSelect2.empty();

        $("#asset_ownerSearch").tokenInput(${UserslistJson}, {tokenLimit: 1});

        $.each(${UserslistJson},function (key, value) {
            uSelect1.append('<option value="' + value.id + '">' + value.name + '</option>')
            uSelect2.append('<option value="' + value.id + '">' + value.name + '</option>')
        });

        loadAssets();
    });

    function getUserAssets() {
        var userId = $('#asset_ownerSearch').val();

        if (userId != "") {
            var field_name = $('#asset_ownerName');
            var field_jobTitle = $('#asset_ownerJobTitle');
            var field_department = $('#asset_ownerDepartmen');

            $.each(${UserslistJson}, function (key, value) {

                if (value.id == userId) {
                    field_name.text(value.name);
                    field_department.text(value.department);
                    field_jobTitle.text(value.jobTitle);
                    $('#asset_ownerImage').attr('src', '/image/' + pad(userId, 5) + '.jpg');
                    $('#myModalLabel').html('Assets of ' + '<ins>' + value.name + '</ins>')


                    $.post("/Asset/Owner/getUserAssets", "userId=" + userId,
                        function (data, status) {
                            var tbody = $('#asset_ownerAssetsTable');
                            tbody.empty();

                            if (data == "") {
                                tbody.append('<div style="center-block">' + '<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>' + 'This user does not possess of any Assets' + '</div>');
                            } else {
                                $.each(data, function (key, value) {

                                    tbody.append('<tr>' +
                                        '<th>' + value.inventNumber + '</th>' +
                                        '<th>' + value.categoryName + '</th>' +
                                        '<th>' + value.checkPublic + '</th>' +
                                        '</tr>');
                                })
                            }

                        });
                }
            });
            $("#myModal").modal();
        } else {
            return false;
        }
    }

    function moveSingleAsset(direction) {
        var s1 = $('#asset_userSelectBox_1');
        var s2 = $('#asset_userSelectBox_2');
        var assetList1 = $('#asset_userAssetList1').val();
        var assetList2 = $('#asset_userAssetList2').val();
        var userFrom = 0;
        var userTo = 0;
        var trade = "";

        if (s1.val() == s2.val()){
            return false
        }


        if(direction === 'right'){
            userFrom = s1.val();
            userTo = s2.val();
            trade = "userFrom=" + userFrom + "&userTo=" + userTo + "&assetNumber=" + assetList1;

            $.post("/Asset/Owner/changeAssetOwner", trade ,function (data,status) {
                loadAssets();
                console.log("asset1 " + status);
            });


            console.log("User " + userFrom + " is sending assets to " + userTo + " Assets List: " + assetList1 );


        }else if (direction === 'left'){
            userFrom = s2.val();
            userTo = s1.val();
            trade = "userFrom=" + userFrom + "&userTo=" + userTo + "&assetNumber=" + assetList2;


            $.post("/Asset/Owner/changeAssetOwner", trade ,function (data,status) {
                loadAssets();
                console.log("asset2 " + status);
            });


        }else {
            return false
        }
    }

    function moveAllAssets(direction) {
        var s1 = $('#asset_userSelectBox_1');
        var s2 = $('#asset_userSelectBox_2');
        var userFrom = 0;
        var userTo = 0;
        var trade = "";

        if (s1.val() == s2.val()){
            return false
        }

        if(direction == "right"){

            userFrom = s1.val();
            userTo = s2.val();

            trade = "userFrom=" + userFrom + "&userTo=" + userTo + "&assetNumber=" + 0;

            $.post("/Asset/Owner/changeAssetOwner", trade ,function (data,status) {
                loadAssets();
            });

        }else if(direction == "left"){

            userFrom = s2.val();
            userTo = s1.val();

            trade = "userFrom=" + userFrom + "&userTo=" + userTo + "&assetNumber=" + 0;

            $.post("/Asset/Owner/changeAssetOwner", trade ,function (data,status) {
                loadAssets();
            });

        }else{
            return false
        }


    }

    /*function loadAssetsIntoList(select,assetsListTag) {

        /!*if(typeof select == 'undefined'){

        }*!/

        var selected_user = $(select).val();
        var assetsList = $('#' + assetsListTag);
        assetsList.empty();

        $.post("/Asset/Owner/getUserAssets", "userId=" + selected_user,function (data,status) {

            $.each(data, function (key, value) {

                assetsList.append('<option value="' + value.id + '">' + value.categoryName + " : №" + value.inventNumber +'</option>');

            });

        });
    }*/

    function loadAssets() {

        var s1 = $('#asset_userSelectBox_1').val();
        var s2 = $('#asset_userSelectBox_2').val();
        var assetList_1 = $('#asset_userAssetList1');
        var assetList_2 = $('#asset_userAssetList2');

        assetList_1.empty();
        assetList_2.empty();


        $.post("/Asset/Owner/getUserAssets", "userId=" + s1,function (data,status) {

            $.each(data, function (key, value) {

                assetList_1.append('<option value="' + value.id + '">' + value.categoryName + " : №" + value.inventNumber +'</option>');

            });

        });

        $.post("/Asset/Owner/getUserAssets", "userId=" + s2,function (data,status) {

            $.each(data, function (key, value) {

                assetList_2.append('<option value="' + value.id + '">' + value.categoryName + " : №" + value.inventNumber +'</option>');

            });

        });
    }

    function pad(num, size) {
        var s = num + "";
        while (s.length < size) s = "0" + s;
        return s;
    }


</script>
</body>
</html>
