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
<c:set var="pageTitle" scope="request" value="Business trip"/>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<spring:url value="/resources/core/js/jquery.tokeninput.js" var="tokenInputJs"/>
<script type="text/javascript" src="${tokenInputJs}"></script>

<style>
    body :not(a) {
        color: inherit;
    }
</style>


<div class="col-sm-10 col-md-offset-1">
    <div class="col-lg-offset-2">
        <h1><%= request.getAttribute("FullName") %>, <%= request.getAttribute("JobTitle") %>
        </h1>
        <p style="font-family: 'Oswald', sans-serif; font-size:x-large;"><%= request.getAttribute("External") %>
        </p>
        <h2 class="page-header">Business trip</h2>

        <div class="w3-container b3form">
            <div class="form-header">
                <div class="input-group" style="width: 100%">
                    <span class="input-group-addon" id="subject-addon" style="width: 25%">Subject:</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="subject-addon"
                           style="width: 40%">
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="saerchtype-addon"
                          style="width: 25%">Type of business trip:</span>
                    <select class="form-control" aria-describedby="saerchtype-addon" style="width: 40%">
                        <option>Conference or Seminar</option>
                        <option>Benching Marking or Vendor Visit</option>
                        <option>Project or Business Development</option>
                        <option>LG CNS HQ or Affiliate</option>
                        <option>Education or Certificate</option>
                    </select>
                    <label class="radio-inline" style="margin-left: 1%; margin-top: 1%">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Domestic
                    </label>
                    <label class="radio-inline" style="margin-top: 1%">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> Overseas
                    </label>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="destination-addon" style="width: 25%">Destination:</span>
                    <input type="text" class="form-control" placeholder="" aria-describedby="destination-addon"
                           style="width: 40%">
                </div>
                <div class="input-group" style="width: 100%; margin-top: 1%">
                    <span class="input-group-addon" id="purpose-addon"
                          style="width: 25%">Purpose of Business trip:</span>
                    <textarea class="form-control" rows="5" id="comment" aria-describedby="purpose-addon"
                              style="width: 40%"></textarea>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        List of Business Trip members:
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: inherit"
                           id="membersDynamicHead">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Employee ID</th>
                            <th>Job title</th>
                            <th>Name of (Organization/Project)</th>
                            <th>From</th>
                            <th>To</th>
                        </tr>
                        </thead>
                        <tbody id="membersDynamicBody">
                        <tr>
                            <td>Kamola HR</td>
                            <td>23</td>
                            <td>HR</td>
                            <td>LG CNS UZB</td>
                            <td>01/02/2017</td>
                            <td>19/02/2017</td>
                        </tr>
                        <tr>
                            <td>Sarvar Zokirov</td>
                            <td>21</td>
                            <td>Developer</td>
                            <td>LG CNS UZB</td>
                            <td>01/02/2017</td>
                            <td>19/02/2017</td>
                        </tr>
                        <tr>
                            <td>Jasur Shaykhov</td>
                            <td>13</td>
                            <td>Developer</td>
                            <td>LG CNS UZB</td>
                            <td>01/02/2017</td>
                            <td>19/02/2017</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        Detail scheadule and To-do list:
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: inherit"
                           id="toDoDynamicHead">
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody id="toDoDynamicBody">
                        <tr>
                            <td>01/02/2017</td>
                            <td>Meeting</td>
                        </tr>
                        <tr>
                            <td>02/02/2017</td>
                            <td>Some activity</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <label style="margin-top: 2%;">
                        Business trip expenses::
                    </label>
                    <table class="table table-bordered" style="background-color: #2b669a; color: inherit"
                           id="expensesDynamicHead">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Airfair(A)</th>
                            <th>Daily Allowance(B)</th>
                            <th>Accommodation (C)</th>
                            <th>Other(D)</th>
                            <th>Total (A+B+C+D)</th>
                        </tr>
                        </thead>
                        <tbody id="expensesDynamicBody">
                        <tr>
                            <td>Kamola HR</td>
                            <td>900</td>
                            <td>450</td>
                            <td>1500</td>
                            <td>200</td>
                            <td>sum</td>
                        </tr>
                        <tr>
                            <td>Sarvar Zokirov</td>
                            <td>900</td>
                            <td>450</td>
                            <td>1500</td>
                            <td>200</td>
                            <td>sum</td>
                        </tr>
                        <tr>
                            <td>Jasur Shaykhov</td>
                            <td>900</td>
                            <td>450</td>
                            <td>1500</td>
                            <td>200</td>
                            <td>sum</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form-footer" style="margin-bottom: 5%">
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="approvals-addon"
                          style="width: 25%">Approvals:</span>
                    <div class="tab-content">
                        <input type="text" id="demo-input-local" name="blah"/>
                        <%--<input type="button" value="Submit" />--%>
                    </div>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="executive-addon"
                          style="width: 25%">Executive:</span>
                    <select class="form-control" aria-describedby="executive-addon" style="width: 40%">
                        <option>Mr.1</option>
                        <option>Mr.2</option>
                        <option>Mr.3</option>
                        <option>Mr.4</option>
                        <option>Mr.5</option>
                    </select>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="reference-addon"
                          style="width: 25%">Reference:</span>
                    <select class="form-control" aria-describedby="reference-addon" style="width: 40%">
                        <option>Mr.1</option>
                        <option>Mr.2</option>
                        <option>Mr.3</option>
                        <option>Mr.4</option>
                        <option>Mr.5</option>
                    </select>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="attachment-addon" glyphicon glyphicon-open
                          style="width: 25%">Attachment:</span>
                    <button type="button" class="btn btn-default" aria-describedby="attachment-addon"><span
                            class="glyphicon glyphicon-open" aria-hidden="true"></span> Upload
                    </button>
                </div>
                <div class="input-group" style="width: 100%; margin-top: 2%">
                    <span class="input-group-addon" id="date-addon"
                          style="width: 25%">Date(Start/End):</span>
                    <input type="text" class="form-control" id="sandbox-container" style="width:36%"
                           placeholder="Start">
                    <input type="text" class="form-control" id="sandbox-container2" style="width:36%" placeholder="End">
                </div>
                <div class="btn-group" role="group" aria-label="..." style="margin-left: 40%; margin-top: 3%">
                    <button type="button" class="btn btn-default">Save</button>
                    <button type="button" class="btn btn-success">Sumbit</button>
                    <button type="button" class="btn btn-danger">Cancel</button>
                </div>

                <div class="row" style="width: 500px">
                    <div class="userImgBox col-md-6" style="background-color: red">
                        <img class="userimg" src="/image/00096.jpg" height="50px" width="50px"/>
                    </div>
                    <div class="col-md-6" style="background-color: blue">
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                    </div>

                </div>

                <%--<div class="input-group" style="width:100%; height: 100px; background-color: red">
                    <div class="input-group-addon" id="myimg" style="background-color: green; width: 40%; height: 100%">
                        <img class="userimg" src="/image/00096.jpg" height="50px" width="50px"/>
                    </div>
                    <div aria-describedby="myimg" style="background-color: darkslategrey;">
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                        <p>SARVAR</p>
                    </div>
                </div>--%>
            </div>
        </div>
    </div>

</div>

<script>
    $('#sandbox-container').datepicker({format: "dd/mm/yyyy"});
    $('#sandbox-container2').datepicker({format: "dd/mm/yyyy"});
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $("input[type=button]").click(function () {
            var a = [];
            a = $(this).siblings("input[type=text]").val();

            $.ajax({
                type: "POST",
                url: "/Workflow/NewForm/BusinessTripForm",
                data: {
                    myArray: a //notice that "myArray" matches the value for @RequestParam
                               //on the Java side
                },
                success: function (response) {
                    alert("Would submit: " + response.toString());
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });

        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#demo-input-local").tokenInput(${jsonData});
    });
</script>


<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>
