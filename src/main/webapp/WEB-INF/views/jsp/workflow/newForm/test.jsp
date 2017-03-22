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



<textarea style="width: 70%; height: 50%; position: absolute; top: 100px; left: 500px; color: #000"  id="testdiv">${msg}
</textarea>
<%
    String msg = String.format("<b>thereis a looooooooooooooooooooooooooooooooooooooot of stufff bold</b><br/>Fuck yall");
    /*out.print(msg);*/
%>

<script>

</script>



<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
