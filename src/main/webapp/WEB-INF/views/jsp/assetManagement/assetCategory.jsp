<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DS
  Date: 15.08.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/commonHead.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container col-lg-10 col-lg-offset-1 col-sm-12">
    <div class="headerDiv">
        <div class="logoDiv" onclick="location='/login'"></div>
        <hr>
        <h1>Asset Management</h1>
    </div>
    <div class="modal-body">
        <%-------------------------------%>
        <div id="createBodyDiv">
            <form:form id="assetCategoryForm" modelAttribute="AssetCategory" method="post" enctype="multipart/form-data">
                <form:input path="assetItemName" type="text" class="form-control calOtherTypeInput"
                            name="other" placeholder="Type category name..."/>
                <div class="btn-group">
                    <input type="submit" value="Submit">
                </div>
           </form:form>

        </div>
    </div>
    </div>
</div>
</body>
</html>