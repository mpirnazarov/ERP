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


<html>
<head>
    <title>Title</title>
</head>
<body>

 <div class="mainBodyBlock" style="background-color: red">
    <h2 class="headerText"><span class="fa fa-pencil" aria-hidden="true"></span>Asset management</h2>

    <div class="w3-container">

    <div id="calFilterDiv">
        <div id="calFilterSelectGroup">
            <label for="calFilterSelectInputUsers">Search by user</label>
            <input id="calFilterSelectInputUsers">
        </div>
        <button type="button" class="btn btn-black"  id="clickModal">Search</button>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
             <!--   <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <p>Some text in the modal.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>-->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="/resources/images/ppicture.png" onerror="this.src='/resources/images/ppicture.png'" class="userImgBox img-thumbnail"/>
                            </div>
                            <div class="col-md-8">
                                <table class="table table-user-information">
                                    <tbody>
                                    <tr>
                                        <td><b>Name:</b></td>
                                        <td>Kamola</td>
                                    </tr>
                                    <tr>
                                        <td><b>Surname:</b></td>
                                        <td>Umirova</td>
                                    </tr>
                                    <tr>
                                        <td><b>Job title:</b></td>
                                        <td>CIO</td>
                                    </tr>
                                    <tr>
                                        <td><b>Department:</b></td>
                                        <td>Technical</td>
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
                                        <h4 class="panel-title">Responsive table</h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Table heading</th>
                                                    <th>Table heading</th>
                                                    <th>Table heading</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <td>Table cell</td>
                                                    <td>Table cell</td>
                                                    <td>Table cell</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">2</th>
                                                    <td>Table cell</td>
                                                    <td>Table cell</td>
                                                    <td>Table cell</td>
                                                </tr>
                                                <tr>
                                                    <th scope="row">3</th>
                                                    <td>Table cell</td>
                                                    <td>Table cell</td>
                                                    <td>Table cell</td>
                                                </tr>
                                                </tbody>
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


     <div  class=w3-container">
        <div class="col-md-12" style="margin-top:50px">
            <div class="row">
                <div class="col-lg-5">
                    <div class="row">
                        <div class="col-md-12">
                            <select path="users" class="form-control" id="userSelect">
                                <option ></option>
                            </select>

                           <% /*<form:select path="users" class="form-control" id="userSelect">
                                <c:forEach items="${availableUsers}" var="users">
                                    <option value="${users.userid}">${users.users}</option>
                                </c:forEach>
                            </form:select>*/ %>


                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="userItemsForm" action="#" method="post" style="margin-top: 10px;">
                                <select  path="itemlist"  multiple="multiple" class="form-control list_size" onchange="call()" id="userItemSelect" >
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
                                <button type="button" id="multiselect2_rightAll" class="btn btn-grey btn-df form-control"><i
                                        class="glyphicon glyphicon-forward"></i></button>
                            </div>
                        </div>
                        <div class="row btn-top">
                            <div class="col-md-12">
                                <button type="button" id="multiselect1_rightSelected" class="btn btn-grey btn-df form-control"><i
                                        class="glyphicon glyphicon-chevron-right "></i></button>
                            </div>
                        </div>
                        <div class="row btn-top">
                            <div class="col-md-12">
                                <button type="button" id="multiselect1_leftSelected" class="btn btn-grey btn-df form-control"><i
                                        class="glyphicon glyphicon-chevron-left "></i></button>
                            </div>
                        </div>
                        <div class="row btn-top">
                            <div class="col-md-12">
                                <button type="button" id="multiselect1_leftAll" class="btn btn-grey btn-df form-control"><i
                                        class="glyphicon glyphicon-backward"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="row">
                        <div class="col-md-12">
                            <select path="usersec" class="form-control" id="userSelectSec">
                                <option ></option>
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
                                <select path="itemsec"  multiple="multiple" class="form-control list_size" id="userItemSelectSec">
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
       </div>


    </div>




</div>

<script>
    $("#calFilterSelectInputUsers").tokenInput(${UserslistJson}, {tokenLimit: 1});

    $(document).ready(function(){
        $("#clickModal").click(function(){
            $("#myModal").modal();
        });
    });

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

    }
    var listItem;
    $(function () {
        function moveItems(origin, dest) {
            $(origin).find(':selected').appendTo(dest);
            var itemOptions =$("#userItemSelect option");
            var itemValues = $.map(itemOptions, function (option) {
                return option.value;
            });

            var itemSecOptions =$("#userItemSelectSec option");
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
    });*/

    /*combobox select*/

   /* $('#userSelect').change(function () {
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
</script>
</body>
</html>
