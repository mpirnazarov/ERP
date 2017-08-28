<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: EvilDecision
  Date: 25.08.2017
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="ERP | Contacts"/>
<c:set var="pageTitle" scope="request" value="Projects"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>

<div class="mainBodyBlock">
    <h2 class="headerText"><span class="fa fa-bars" aria-hidden="true"></span> Asset Category</h2>
    <button class="btn btn-green" onclick="addCategory()">✚ Add Category</button>
    <table class="table sarTable table-bordered">
        <!-- here should go some titles... -->
        <tr>
            <th>id</th>
            <th>Category Name</th>
            <th></th>
        </tr>
        <c:forEach items="${categoryList}" var="category">
            <tr>
                <td>
                    <c:out value="${category.id}"/>
                </td>
                <td>
                    <c:out value="${category.assetCategoryName}"/>
                </td>
                <td>
                    <div id="buttonGroupcha" class="btn-group" role="group" aria-label="...">
                        <button onclick="updateCategory(${category.id},'${category.assetCategoryName}')" value="Edit" class="btn btn-blue">Edit</button>
                        <button onclick="deleteCategory(${category.id})" value="Delete" class="btn btn-red">Delete</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>


    <form:form modelAttribute="AssetCategory" method="post" enctype="multipart/form-data">
        <%--<form:input path="assetCategoryName" type="text" class="form-control"/>--%>
        <div class="btn-group">
            <input type="submit" name="submit" value="Submit">
            <input type="submit" name="update" value="Update">
            <input type="submit" name="delete" value="Delete">
        </div>
    </form:form>
</div>

<%--Modal--%>
<div class="modal fade" id="AssetCategoryModal" tabindex="-1" role="dialog" aria-labelledby="CalendarModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
            </div>
            <form:form modelAttribute="AssetCategory" method="post" id="categoryForm" enctype="multipart/form-data">
            <div class="modal-body">
                <form:input class="hidden" id="assetCategoryId" path="id"></form:input>
                <label for="categoryName">Category Name</label>
                <form:input id="categoryName" path="assetCategoryName" type="text" class="form-control"/>

            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal" style="float: left;">Close</button>
                <button id="catSaveBtn" class="btn btn-green" type="submit" name="submit">Save</button>
                <button id="catUpdateBtn" class="btn btn-blue" type="submit" name="update">Update</button>
            </div>
            </form:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script>

    function addCategory() {

        $('#categoryName').val('');
        $('#catUpdateBtn').hide();
        $('#catSaveBtn').show();
        $('#gridSystemModalLabel').html("✚ Add new Category");
        $('#AssetCategoryModal').modal('show');
    }

    function updateCategory(id,name) {

        $('#categoryName').val(name)
        $('#assetCategoryId').val(id)
        $('#catUpdateBtn').show();
        $('#catSaveBtn').hide();
        $('#gridSystemModalLabel').html("Edit Category")
        $('#AssetCategoryModal').modal('show');
    }

    function deleteCategory(id) {

        $.post("/Asset/Category/AssetCategoryFormDelete","assetCategoryId="+id);
    }

</script>
