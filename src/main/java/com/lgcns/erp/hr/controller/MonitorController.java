package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.mapper.MonitorMapper;
import com.lgcns.erp.hr.viewModel.MonitorViewModels.MonitorViewModel;
import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.DbContext.WorkloadServices;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import javafx.util.Pair;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.Date;

/**
 * Created by Rafatdin on 07.12.2016.
 */
@Controller
@RequestMapping("/Monitor")
public class MonitorController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Monitor(Principal principal) {
        ModelAndView mav = new ModelAndView("monitor/index");
        Pair<List<MonitorViewModel>, Integer> values = getAllData(0, getDateRange().getKey(),getDateRange().getValue());
        mav.addObject("viewModel", values.getKey());
        mav.addObject("total", values.getValue());
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/ReceiveDataAjax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject ReceiveDataAjax(@RequestBody String json, Principal principal) throws IOException {
        JSONObject response = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        return response;
    }

    private Pair<List<MonitorViewModel>, Integer> getAllData(int userId, Date dateFrom, Date dateTo){
        List<WorkloadEntity> workloads = WorkloadServices.getWorkloadByPeriod(userId, dateFrom, dateTo);
        return MonitorMapper.mapWorkloadsToViewModel(workloads, getUsersIdAndName(), getProjectsIdAndName(dateFrom, dateTo));
    }

    private Map<Integer, String> getUsersIdAndName() {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        for (UserLocalizationsEntity loc : UserService.getAllUserLocs()) {
            users.put(loc.getUserId(), loc.getFirstName() + " " + loc.getLastName());
        }
        return users;
    }

    private Map<Integer, String> getProjectsIdAndName(Date dateFrom, Date dateTo){
        Map<Integer, String> projects = new LinkedHashMap<Integer, String>();
        Calendar cal = Calendar.getInstance();
        for (ProjectsEntity project : ProjectServices.getAllProjects(dateFrom,dateTo)) {
            cal.setTime(project.getStartDate());
            int year = cal.get(Calendar.YEAR);
            projects.put(project.getId(), "PJ " + year + "-" + project.getCode() + "-" + project.getType() +
                    "<br>" + project.getName());
        }
        return projects;
    }

    private Pair<Date, Date> getDateRange() {
        Date begining, end;

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        return new Pair<Date, Date>(begining, end);
    }

    private static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
