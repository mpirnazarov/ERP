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
<c:set var="pageTitle" scope="request" value="Appointment Record"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>


<script type="text/javascript">
    $(document).ready(function() {
        $("input[type=submit]").click(function () {
            var a = [];
            a=$(this).siblings("input[type=text]").val();
            $.ajax({
                type : "POST",
                url : "/Workflow/Test",
                data : {
                    myArray: a //notice that "myArray" matches the value for @RequestParam
                               //on the Java side
                },
                success : function(response) {
                    alert(a);
                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            });
            alert("Would submit: " + $(this).siblings("input[type=text]").val());
        });
    });
</script>

<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
    <input type="text" id="demo-input-local" name="blah" />
    <input type="submit" value="Submit" />
    <script type="text/javascript">
        $(document).ready(function() {
            $("#demo-input-local").tokenInput([
                {id: 7, name: "Ruby"},
                {id: 11, name: "Python"},
                {id: 13, name: "JavaScript"},
                {id: 17, name: "ActionScript"},
                {id: 19, name: "Scheme"},
                {id: 23, name: "Lisp"},
                {id: 29, name: "C#"},
                {id: 31, name: "Fortran"},
                {id: 37, name: "Visual Basic"},
                {id: 41, name: "C"},
                {id: 43, name: "C++"},
                {id: 47, name: "Java"}
            ]);
        });
    </script>
    </div>
</div>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
