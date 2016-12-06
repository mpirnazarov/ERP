<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.Format" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.lgcns.erp.hr.viewModel.WorkloadViewModels.CalendarReturningModel" %>
<%@ page import="com.lgcns.erp.hr.enums.WorkloadType" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity" %>
<%@ page import="com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafatdin
  Date: 31.10.2016
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Manage Workload"/>

<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserHeader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpUserLayout.jsp"></jsp:include>
        <div class="col-md-offset-1 col-sm-10">
            <div class=" col-lg-offset-2 col-lg-10">
                <h1>Calendar</h1>

                <span class="hidden" id="mondayHidden"><fmt:formatDate value="${model.monday}"
                                                                       pattern="yyyy-MM-dd"/></span>


                <div class="table-responsive">
                    <%
                        int tabindex = 1;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        CalendarReturningModel model = (CalendarReturningModel) request.getAttribute("model");
                        Calendar c = Calendar.getInstance();
                        c.setTime(model.getMonday());
                        c.add(Calendar.DATE, -7);
                        Date tempDatePrev = c.getTime();
                        c.setTime(model.getMonday());
                        c.add(Calendar.DATE, 7);
                        Date tempDateNext = c.getTime();

                    %>

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th colspan="2">
                                <div style="text-align:center"><h3>Week</h3></div>
                            </th>

                            <th id="previous">
                                <div style="text-align:center">
                                    <form action="/Workload/DiffCalendar" method="get">

                                        <input type="hidden" name="today" value="<%= sdf.format(tempDatePrev) %>"/>
                                        <button type="submit" class="btn btn-default"><i
                                                class="fa fa-caret-left fa-3x"></i></button>

                                    </form>
                                </div>
                            </th>
                            <th colspan="5">
                                <div style="text-align:center"><h3><fmt:formatDate value="${model.monday}"
                                                                                   pattern="dd MMM yyy"/></h3></div>
                            </th>
                            <th id="next">
                                <div style="text-align:center">
                                    <form action="/Workload/DiffCalendar" method="get">

                                        <input type="hidden" name="today" value="<%= sdf.format(tempDateNext) %>"/>
                                        <button type="submit" class="btn btn-default"><i
                                                class="fa fa-caret-right fa-3x"></i></button>

                                    </form>
                                </div>
                            </th>
                        </tr>
                        <tr id="weekDays" class="headers">
                            <td colspan="2" align="center">Days</td>
                            <%!
                                String addDaysAndFormat(Date day, int daysToAdd, String format) {
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(day);
                                    c.add(Calendar.DATE, daysToAdd);
                                    SimpleDateFormat sdf = new SimpleDateFormat(format);

                                    return sdf.format(c.getTime());
                                }

                                String generateProject(CalendarReturningModel model, String projectCode, String projectName, String projectType, Integer projectId, int tabindex, boolean defaultProject) {
                                    String returning = "" +
                                            "<tr>\n" +
                                            "   <td class=\"td-head-default\" id=\"" + projectId + "\" rowspan=\"5\" style=\"vertical-align:middle;\">" + projectCode;
                                    if(projectType.length()!=0)
                                        returning += " - "+ projectType;

                                    returning+="<br>" + projectName + "</td>\n" +
                                            "</tr>";

                                    for (int i = 1; i < 5; i++) {
                                        int typeCode;
                                        String typeName;
                                        switch (i) {
                                            case 1: {
                                                if (!defaultProject)
                                                    typeCode = WorkloadType.Work_Project.getValue();
                                                else
                                                    typeCode = WorkloadType.Work_Administrative.getValue();
                                                typeName = "Work";
                                                break;
                                            }
                                            case 2: {
                                                typeCode = WorkloadType.Training.getValue();
                                                typeName = "Training";
                                                break;
                                            }
                                            case 3: {
                                                typeCode = WorkloadType.Sick_leave.getValue();
                                                typeName = "Sick leave";
                                                break;
                                            }
                                            case 4: {
                                                typeCode = WorkloadType.Annual_leave.getValue();
                                                typeName = "Annual";
                                                break;
                                            }
                                            default: {
                                                typeCode = WorkloadType.Work_Administrative.getValue();
                                                typeName = "Default";
                                            }
                                        }
                                        returning += "<tr>\n" +
                                                " <td class=\"hidden\"id=\"" + projectId + "\"></td>\n" +
                                                " <td class=\"td-head-default\" id=\"" + typeName + "\">" + typeName + "</td>\n";

                                        for (int ii = 0; ii < 7; ii++) {
                                            returning += "" +
                                                    "<td class=\"duration\" data-tabindex=\"" + tabindex + "\" id=\"" + getDayId(ii) + "\">";

                                            Calendar c = Calendar.getInstance();
                                            c.setTime(model.getMonday());
                                            c.add(Calendar.DATE, ii);
                                            Date date1 = c.getTime();
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                            for (WorkloadEntity wl : model.getWorkloads()) {
                                                if (sdf.format(wl.getDate()).equals(sdf.format(date1)) && wl.getWorkloadType().equals(typeCode) && wl.getProjectId() == projectId) {
                                                    returning += String.valueOf(wl.getDuration());
                                                    break;
                                                }
                                            }
                                            returning += "</td>\n";

                                            tabindex++;
                                        }
                                        returning += "\n" +
                                                "</tr>";
                                    }


                                    return returning;
                                }

                                String getDayId(int day) {
                                    switch (day) {
                                        case 0: {
                                            return "monForTotal";
                                        }
                                        case 1: {
                                            return "tueForTotal";
                                        }
                                        case 2: {
                                            return "wedForTotal";
                                        }
                                        case 3: {
                                            return "thuForTotal";
                                        }
                                        case 4: {
                                            return "friForTotal";
                                        }
                                        case 5: {
                                            return "satForTotal";
                                        }
                                        case 6: {
                                            return "sunForTotal";
                                        }
                                        default:
                                            return "noForTotal";
                                    }
                                }

                                String getEmptyRow() {
                                    return "<tr>\n" +
                                            "    <td class=\"td-space\" colspan=\"9\"></td>\n" +
                                            "</tr>";
                                }
                            %>
                            <td id="Mon" align="center">Mon<br/><%=addDaysAndFormat(model.getMonday(), 0, "dd MMMM")%>
                            </td>
                            <td id="Tue" align="center">Tue<br/><%=addDaysAndFormat(model.getMonday(), 1, "dd MMMM")%>
                            </td>
                            <td id="Wed" align="center">Wed<br/><%=addDaysAndFormat(model.getMonday(), 2, "dd MMMM")%>
                            </td>
                            <td id="Thu" align="center">Thu<br/><%=addDaysAndFormat(model.getMonday(), 3, "dd MMMM")%>
                            </td>
                            <td id="Fri" align="center">Fri<br/><%=addDaysAndFormat(model.getMonday(), 4, "dd MMMM")%>
                            </td>
                            <td id="Sat" align="center">Sat<br/><%=addDaysAndFormat(model.getMonday(), 5, "dd MMMM")%>
                            </td>
                            <td id="Sun" align="center">Sun<br/><%=addDaysAndFormat(model.getMonday(), 6, "dd MMMM")%>
                            </td>
                        </tr>
                        </thead>
                        <tbody id="tbody">
                        <%
                            out.print(generateProject(model, "Team project", "", "", 0, 1, true));
                            tabindex += 28;
                            for (ProjectsEntity project : model.getProjects()) {
                                out.print(getEmptyRow());
                                out.print(generateProject(model, project.getCode(), project.getName(), project.getType(), project.getId(), tabindex, false));
                                tabindex += 28;
                            }
                        %>
                        <tr class="headers">
                            <td colspan="2" align="right">Total worked hours</td>
                            <td id="totalMon">0</td>
                            <td id="totalTue">0</td>
                            <td id="totalWed">0</td>
                            <td id="totalThu">0</td>
                            <td id="totalFri">0</td>
                            <td id="totalSat">0</td>
                            <td id="totalSun">0</td>
                        </tr>
                        </tbody>
                    </table>
                    <span class="hidden" id="lastTabIndex" data-tabindex=@(tabindex)></span>
                </div>

                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">TAPPS</h4>
                            </div>
                            <div class="modal-body">
                                <span id="message">Your input cannot be more than 24 or less than 0.5 hours. Please try again.</span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        calculateTotals();
    });
    var globalTabIndex = 1;

    $(window).keydown(function (event) {
        //ловим событие нажатия клавиши


        if (event.keyCode == 9) {
            blurCheck();

            globalTabIndex++;

            var current = $('[data-tabindex=' + globalTabIndex + ']')[0];
            var curText = current.innerText;
            $(current).empty().html(curText);

            var code = '<input type="text" id="edit" value="' + curText + '" min="0.5" max="24" step="0.5" style="width: 4em" />';
            $(current).empty().append(code);
            var inputElement = document.getElementById("edit");
            inputElement.focus();
            $('#edit').focusout(function () {
                var val = current.children.edit.value;
                if (checkInput(val)) {
                    var w = current.parentElement.children[0].id;
                    var w2 = current.parentElement.children[1].id;

                    var day = 0;
                    if (w == "Training" || w == "Work (Administrative)" || w == "Sick leave" || w == "Annual" || w == "Unpaid")
                        day = current.cellIndex;
                    else
                        day = current.cellIndex - 1;
                    if (sendData(val, day, w, w2) == false) {
                        $(current).empty().html(valu);
                        return false;
                    }

                    if (val == "0")
                        val = "";
                    $(current).empty().html(val);

                }
                else {
                    val = "";
                    $(current).empty().html(val);
                }
                calculateTotals();
            });
            return false;
        }
        if (event.keyCode == 13) {	//если это Enter
            $('#edit').blur();	//снимаем фокус с поля ввода
        }

    });

    $(function () {
        $('#tbody').on('keydown', '#edit', function (e) {
            -1 !== $.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) || /65|67|86|88/.test(e.keyCode) && (!0 === e.ctrlKey || !0 === e.metaKey) || 35 <= e.keyCode && 40 >= e.keyCode || (e.shiftKey || 48 > e.keyCode || 57 < e.keyCode) && (96 > e.keyCode || 105 < e.keyCode) && e.preventDefault()
        });
    })

    function blurCheck() {
        var temp = $('#lastTabIndex').attr('data-tabindex');
        if (parseInt(globalTabIndex) > parseInt(temp))
            globalTabIndex = 1;
        $('#edit').blur();
        var current = $('[data-tabindex=' + globalTabIndex + ']')[0];
        var curText = current.innerText;
        $(current).empty().html(curText);
    }

    $(function () {
        $('.duration').click(function (e) {
            blurCheck();

            //ловим элемент, по которому кликнули
            var t = e.target || e.srcElement;
            var tabindex = $(t).attr("data-tabindex");
            if (tabindex != null)
                globalTabIndex = tabindex;
            //получаем название тега
            var elm_name = t.tagName.toLowerCase();
            //если это инпут - ничего не делаем
            if (elm_name == 'input') {
                return false;
            }
            var valu = $(this).html().trim().replace(",", ".");
            var code = '<input type="text" id="edit" value="' + valu + '" min="0.5" max="24" step="0.5" style="width: 4em" />';
            $(this).empty().append(code);
            $('#edit').focus();
            $('#edit').blur(function () {
                var val = $(this).val();
                if (checkInput(val)) {
                    var w = t.parentElement.children[0].id;
                    var w2 = t.parentElement.children[1].id;
                    var day = 0;
                    if (w == "Training" || w == "Work (Administrative)" || w == "Sick leave" || w == "Annual" || w == "Unpaid")
                        day = t.cellIndex;
                    else
                        day = t.cellIndex - 1;
                    if (sendData(val, day, w, w2) == false) {
                        $(this).parent().empty().html(valu);
                        return false;
                    }

                    if (val == "0")
                        val = "";
                    $(this).parent().empty().html(val);

                }
                else {
                    val = "";
                    $(this).parent().empty().html(val);
                }
                calculateTotals();
            });
        });
    });

    function checkInput(hours) {
        if (hours > 24) {
            $('#message').text("Your input cannot be more than 24 hours per day. Please try again.");
            $('#myModal').modal('show');
            return false
        }
        else if (hours < 0) {
            $('#message').text("Your input cannot be less than 0. Please try again");
            $('#myModal').modal('show');
            return false;
        }
        return true;
    }

    $(function () {
        $('input').on('focus', function (e) {
            $(this)
                    .one('mouseup', function () {
                        $(this).select();
                        return false;
                    })
                    .select();
        });
    })

    function sendData(value, day, w, w2) {
        if (value == "") {
            value = 0;
        }
        if (!$.isNumeric(w)) {
            $.notify("Sorry, something went wrong. Please, refresh the page");
            return false;
        }

        var total = 0;
        switch (day) {
            case 1:
                total += parseFloat($('#totalMon').text().replace(",", "."));
                break;
            case 2:
                total += parseFloat($('#totalTue').text().replace(",", "."));
                break;
            case 3:
                total += parseFloat($('#totalWed').text().replace(",", "."));
                break;
            case 4:
                total += parseFloat($('#totalThu').text().replace(",", "."));
                break;
            case 5:
                total += parseFloat($('#totalFri').text().replace(",", "."));
                break;
            case 6:
                total += parseFloat($('#totalSat').text().replace(",", "."));
                break;
            case 7:
                total += parseFloat($('#totalSun').text().replace(",", "."));
                break;
        }

        if (checkTotalSum(day)) {
            if ((total + parseInt(value)) > 24) {
                $('#message').text("Your cannot work more than 24 hours per day. Please try again");
                $('#myModal').modal('show');
                return false;
            }
        }

        var requestData = {
            WorkloadName: w,
            WorkloadType: w2,
            EnteredValue: value,
            WeekDate: day,
            Monday: $('#mondayHidden').text()
        };

        var GlobalResponse;
        $.ajax({
            url: '/Workload/ReceiveDataAjax',
            type: 'POST',
            data: JSON.stringify(requestData),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            error: function (xhr) {
                $('#message').text(xhr.statusText);
                $('#myModal').modal('show');
                return false;
                //alert('Error: ' + xhr.statusText);
            },
            success: function (response) {
                if (!response.success) {
                    if (response.reason == 0) {
                        return false;
                    }
                    $('#message').text(response.reason);
                    $('#myModal').modal('show');
                    return false;
                }

            },
            async: false,
            processData: false
        });

    }

    function checkTotalSum(day) {
        var dayText = "";
        day--;
        if (day == 0)
            dayText = "mon";
        else if (day == 1)
            dayText = "tue";
        else if (day == 2)
            dayText = "wed";
        else if (day == 3)
            dayText = "thu";
        else if (day == 4)
            dayText = "fri";
        else if (day == 5)
            dayText = "sat";
        else if (day == 6)
            dayText = "sun";
        else
            return false;
        var temp = 0;
        $("[id=" + dayText + "ForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        if (temp > 0 && temp <= 24)
            return true;
        else
            return false;
    }

    function calculateTotals() {
        $('#totalMon').text("");
        $('#totalTue').text("");
        $('#totalWed').text("");
        $('#totalThu').text("");
        $('#totalFri').text("");
        $('#totalSat').text("");
        $('#totalSun').text("");

        var temp = 0;
        $("[id=monForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalMon').text(temp);

        temp = 0;
        $("[id=tueForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalTue').text(temp);

        temp = 0;
        $("[id=wedForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalWed').text(temp);

        temp = 0;
        $("[id=thuForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalThu').text(temp);

        temp = 0;
        $("[id=friForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalFri').text(temp);

        temp = 0;
        $("[id=satForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalSat').text(temp);

        temp = 0;
        $("[id=sunForTotal]").each(function (i, el) {
            var tempValue = el.textContent.trim();
            if (tempValue != "")
                temp += parseFloat(tempValue.replace(",", "."));
        });
        $('#totalSun').text(temp);


    }

    function setAllDates(monday) {
        $('#Mon').text("Mon " + monday);
        monday.addDays(1);
        $('#Tue').text("Tue " + monday);
        monday.addDays(1);
        $('#Wed').text("Wed " + monday);
        monday.addDays(1);
        $('#Thu').text("Thu " + monday);
        monday.addDays(1);
        $('#Fri').text("Fri " + monday);
        monday.addDays(1);
        $('#Sat').text("Sat " + monday);
        monday.addDays(1);
        $('#Sun').text("Sun " + monday);
    }

    function DrawCalendar() {

    }

    function Previous(date) {
        window.location.href = "/Workload/Calendar?date=" + date
    }

    function getMonday(date) {
        var day = date.getDay() || 7;
        if (day !== 1)
            date.setHours(-24 * (day - 1));
        return date;
    }

    Date.prototype.addDays = function (days) {
        var dat = new Date(this.valueOf());
        dat.setDate(dat.getDate() + days);
        return dat;
    }
</script>
<style>
    table {
        border-collapse: collapse;
        text-align: center;
    }

    td {
        border: 1px solid #999;
        padding: 0;
    }

    .duration {
        max-width: 8em;
        min-width: 7em;
    }

    .headers td, .td-head, .td-head-default {
        padding: 1px 3px;
        font-weight: bold;
        vertical-align: middle;
    }
    .td-head, .td-head-default {
        font-weight: lighter;
    }
    input {
        border: none;
        border-color: #1a6ecc;
        font-size: 14px;
        padding: 0;
        width: inherit;
        color: #0c0c0c;
        text-align: center;
    }

    input:hover {
        background-color: #ffffff;
        border: none;
        border-color: #ffffff;
    }

    input:focus {
        background-color: #ffffff;
        outline: 0;
        width: inherit;
    }

    input:not(:focus) {
        text-align: end;
    }

    input[type="text"] {
        width: 100%;
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
    }


</style>
<jsp:include flush="true" page="/WEB-INF/views/jsp/shared/erpFooter.jsp"></jsp:include>