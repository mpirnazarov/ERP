<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 03.10.2016
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Register"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpHeader.jsp"></jsp:include>
<form action="/User/Register" method="post">

    <div class="form-horizontal">
        <hr/>
        <div class="form-group">
            <label class="control-label col-md-2">First name <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" data-val="true"
                       data-val-length="Поле First name должно быть строкой с минимальной длиной 1 и максимальной 50."
                       data-val-length-max="50" data-val-length-min="1" data-val-regex="Use letters only please"
                       data-val-regex-pattern="^[a-zA-Z\s]+$" data-val-required="First name is compulsory field."
                       id="FirstName" name="FirstName" type="text" value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="FirstName"
                      data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2">Last name <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" data-val="true"
                       data-val-length="Поле Last name должно быть строкой с минимальной длиной 1 и максимальной 50."
                       data-val-length-max="50" data-val-length-min="1" data-val-regex="Use letters only please"
                       data-val-regex-pattern="^[a-zA-Z\s]+$" data-val-required="Last name is compulsory field."
                       id="LastName" name="LastName" type="text" value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="LastName"
                      data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Email <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" data-val="true"
                       data-val-length="Поле Email должно быть строкой с минимальной длиной 1 и максимальной 50."
                       data-val-length-max="50" data-val-length-min="1" data-val-required="Email is compulsory field."
                       id="Email" name="Email" type="email" value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="Email"
                      data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Phone <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" data-val="true"
                       data-val-regex="Not a valid Phone number. Format: +XXXXXXXXXX"
                       data-val-regex-pattern="^[+]([0-9]{12})$" data-val-required="Phone is compulsory field."
                       id="Phone" name="Phone" type="tel" value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="Phone"
                      data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="Address">Address</label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" id="Address" name="Address" type="text" value=""/>
                <span class="field-validation-valid" data-valmsg-for="Address" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2" for="Major">Major</label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" data-val="true"
                       data-val-regex="Use letters only please" data-val-regex-pattern="^[a-zA-Z\s]+$" id="Major"
                       name="Major" type="text" value=""/>
                <span class="field-validation-valid" data-valmsg-for="Major" data-valmsg-replace="true"></span>
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-md-2">System role <font color='red'>*</font></label>
            <div class="col-md-2">
                <select class="form-control" data-val="true"
                        data-val-number="The field System role must be a number."
                        data-val-required="Требуется поле System role." id="PositionId" name="PositionId">
                    <option value="1">Admin</option>
                    <option value="2">CTO</option>
                    <option value="3">Master</option>
                    <option value="4">User</option>
                    <option value="5">Monitor</option>
                </select>
                <span class="field-validation-valid" data-valmsg-for="PositionId" data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2" for="HasHead">Has head</label>
            <div class="col-md-2">
                <input data-val="true" data-val-required="Требуется поле Has head." id="HasHead" name="HasHead"
                       onclick="DisableList()" type="checkbox" value="true"/><input name="HasHead" type="hidden"
                                                                                    value="false"/>
                <span class="field-validation-valid" data-valmsg-for="HasHead" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2">Head</label>
            <div class="col-md-2">
                <select class="form-control" data-val="true" data-val-number="The field Head must selected."
                        id="HeadId" name="HeadId">
                    <c:forEach items="${heads}" var="thisHead">
                        <option value="${thisHead[0].id}">${thisHead[1].firstName} ${thisHead[1].lastName}</option>
                    </c:forEach>
                </select>
                <span class="field-validation-valid" data-valmsg-for="HeadId" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-2">Username <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line" data-val="true"
                       data-val-length="Поле Username должно быть строкой с минимальной длиной 1 и максимальной 50."
                       data-val-length-max="50" data-val-length-min="1" data-val-required="Требуется поле Username."
                       id="UserName" name="UserName" type="text" value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="UserName"
                      data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Password <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line password" data-val="true"
                       data-val-length="Поле Password должно быть строкой с минимальной длиной 1 и максимальной 100."
                       data-val-length-max="100" data-val-length-min="1"
                       data-val-required="Требуется поле Password." id="Password" name="Password" type="password"
                       value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="Password"
                      data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Repeat password <font color='red'>*</font></label>
            <div class="col-md-2">
                <input class="form-control text-box single-line password" data-val="true"
                       data-val-equalto="Repeat password mismatch" data-val-equalto-other="*.Password"
                       data-val-length="Поле Repeat password должно быть строкой с минимальной длиной 1 и максимальной 100."
                       data-val-length-max="100" data-val-length-min="1"
                       data-val-required="Требуется поле Repeat password." id="RepeatPassword" name="RepeatPassword"
                       type="password" value=""/>
                <span class="field-validation-valid text-danger" data-valmsg-for="RepeatPassword"
                      data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <input type="submit" value="Register" class="btn btn-default"/>
                <input type="button" onclick="location.href='/User'" value="Cancel" class="btn btn-default"/>
            </div>
        </div>
    </div>
</form>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
