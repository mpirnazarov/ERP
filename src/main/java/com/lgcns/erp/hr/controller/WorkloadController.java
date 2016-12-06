package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.hr.viewModel.WorkloadViewModels.CalendarRequestDataJson;
import com.lgcns.erp.hr.viewModel.WorkloadViewModels.CalendarReturningModel;
import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.DbContext.WorkloadServices;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
@Controller
public class WorkloadController {

    @RequestMapping(value = "/Workload/Index", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Calendar(Principal principal) {
        return CalendarDefault(principal, new Date());
    }

    private ModelAndView CalendarDefault(Principal principal, Date monday){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workload/calendar");

        int userId = UserService.getUserByUsername(principal.getName()).getId();

        CalendarReturningModel model = new CalendarReturningModel();
        model.setMonday(getMonday(monday));
        model.setProjects(ProjectServices.getUsersAllProjects(userId, model.getMonday(), addDays(model.getMonday(), 7)));
        model.setWorkloads(WorkloadServices.getWorkloadsByUserid(userId, model.getMonday(), addDays(model.getMonday(), 7)));

        mav.addObject("model", model);
        mav.addObject("userRole", UserService.getUserByUsername(principal.getName()));
        mav.addObject("userProfile", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }

    @RequestMapping(value = "/Workload/ReceiveDataAjax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject ReceiveDataAjax(@RequestBody String json, Principal principal) throws IOException {

        JSONObject response = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        CalendarRequestDataJson jsonModel = mapper.readValue(json, CalendarRequestDataJson.class);

        int duration = -1;
        int pId = Integer.valueOf(jsonModel.getWorkloadName());

        if (jsonModel.getEnteredValue() != null) {
            duration = Integer.parseInt(jsonModel.getEnteredValue());
        }

        if (duration >= 0 && duration <= 24 && jsonModel.getWorkloadName() != null) {
            Date date = addDays(jsonModel.getMonday(), jsonModel.getWeekDate()-1);
            int userId = UserService.getUserByUsername(principal.getName()).getId();

            int wTypeID = 6;

            if (jsonModel.getWorkloadType().equals("Training")) {
                wTypeID = WorkloadType.Training.getValue();
            } else if (jsonModel.getWorkloadType().equals("Work")) {
                if (pId == 0) wTypeID = WorkloadType.Work_Administrative.getValue();
                else wTypeID = WorkloadType.Work_Project.getValue();
            } else if (jsonModel.getWorkloadType().equals("Sick leave")) {
                wTypeID = WorkloadType.Sick_leave.getValue();
            } else if (jsonModel.getWorkloadType().equals("Annual")) {
                wTypeID = WorkloadType.Annual_leave.getValue();
            } else if (jsonModel.getWorkloadType().equals("Unpaid")) {
                wTypeID = WorkloadType.Unpaid_leave.getValue();
            }

            WorkloadEntity workload = new WorkloadEntity();
            workload.setUserId(userId);
            workload.setDate(date);
            workload.setProjectId(pId);
            workload.setDuration(duration);
            workload.setWorkloadType(wTypeID);

            if (!WorkloadServices.isAccessibleOnDate(date, pId, userId) && pId!=0) {
                response.put("success", false);
                response.put("reason", "You are not appointed on project this project on " + date.toString() + ".");
            } else {
                boolean doneStatus = WorkloadServices.saveOrUpdateWorklad(workload);
                response.put("success", doneStatus);
                response.put("reason", 0);
                if(duration == 0 && !doneStatus) {
                    response.put("success", true);
                }
            }
        }
        else{
                response.put("success", false);
                response.put("reason", "Duration_wrong");
            }

            return response;
        }

    @RequestMapping(value = "/Workload/DiffCalendar", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView DiffCalendar(Principal principal, String today){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(today);

            return CalendarDefault(principal, date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/Workload/Index");

    }

    private Date getMonday(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    private Date addDays(Date day, int addDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.add(Calendar.DATE, addDays);
        return c.getTime();
    }
}
