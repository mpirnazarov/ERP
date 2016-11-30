<%@ page import="com.lgcns.erp.tapps.viewModel.ProfileViewModel" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 25-Oct-16
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<c:set var="pageTitle" scope="request" value="Home"/>--%>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid" id="page">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpEditLayout.jsp"></jsp:include>
        <div class="col-sm-9 col-md-offset-1">
            <div class="col-lg-8 col-lg-offset-2">
                <%--<h1 class="page-header">${userProfile.firstName[2]} ${userProfile.lastName[2]}'s profile</h1>--%>
            <form:form  modelAttribute="jobexpVM" cssClass="form-horizontal" method="post" >

                <!--General info/Family info Tab-->
                <h3>Job Experience</h3>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-md-3">Organization: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:input path="organization" placeholder="LG CNS"
                                                                  cssClass="form-control text-box single-line"/></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Position: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:input path="position" placeholder="Developer, BA"
                                                                  cssClass="form-control text-box single-line"/></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Start Date: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:input path="startDate" type="date"
                                                                  cssClass="form-control text-box single-line requiredDate"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">End Date: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:input path="endDate" type="date"
                                                                  cssClass="form-control text-box single-line requiredDate"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Contract type: <font color='red'>*</font></label>
                                <div class="col-lg-5"><form:select path="contractType" items="${jobexpVM.contracts}"
                                                                   cssClass="form-control text-box single-line"/></div>
                            </div>
                        </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-9">
                        <input type="submit" value="Add" class="btn btn-default"/>
                        <input type="button" onclick="location.href='/Hr/user/${id}/update/${path}'" value="Cancel"
                               class="btn btn-default"/>
                    </div>
                </div>
            </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#firuref").trigger('click');
        document.getElementById("firuref").click();
        $("#fienref").trigger('click');
        document.getElementById("fienref").click();
    });
</script>
<script>
    /*menu handler*/
    $(function(){
        function stripTrailingSlash(str) {
            if(str.substr(-1) == '/') {
                return str.substr(0, str.length - 1);
            }
            return str;
        }

        var url = window.location.pathname;
        var activePage = stripTrailingSlash(url);

        $('.nav li a').each(function(){
            var currentPage = stripTrailingSlash($(this).attr('href'));

            if (activePage == currentPage) {
                $(this).parent().addClass('active');
            }
        });
    });
</script>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
