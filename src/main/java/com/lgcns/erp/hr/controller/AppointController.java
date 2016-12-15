package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.enums.ProjectRole;
import com.lgcns.erp.hr.mapper.ProjectMapper;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointCreate;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointViewModel;
import com.lgcns.erp.hr.viewModel.AppointViewModels.ProjectMembers;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreateForm;
import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Rafatdin on 07.12.2016.
 */
@Controller
@RequestMapping("/Appoint")
public class AppointController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Monitor(Principal principal) {
        ModelAndView mav = new ModelAndView("appoint/index");
        List<AppointViewModel> model = new ArrayList<AppointViewModel>();

        UsersEntity curUser = UserService.getUserByUsername(principal.getName());
        List<ProjectsEntity> list = ProjectServices.getProjectsByManager(curUser.getId());
        for(ProjectsEntity p : list){
            model.add(new AppointViewModel(p.getName(),p.getId(),p.getStartDate(),p.getEndDate(),p.getStatus()));
        }

        mav.addObject("viewModel", model);
        mav.addObject("users", getUsersIdAndName());
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }
    @RequestMapping(value="/Create", method = RequestMethod.POST)
    public ModelAndView AppointNewMember(@Valid @ModelAttribute AppointCreate viewModel, BindingResult bindingResult, Principal principal) throws ParseException {
        ModelAndView mav = new ModelAndView("appoint/index");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("createVM", viewModel);
        if (bindingResult.hasErrors()) {
            mav.addObject("org.springframework.validation.BindingResult.createVM", bindingResult);
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }
        ProjectRole role = ProjectRole.getRole(viewModel.getRoleId());
        if(role != null && viewModel.getEmpId() != 0) {
            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            ProjectServices.insertProjectMember(viewModel.getProjectId(), viewModel.getEmpId(), role,
                    (Date)formatter1.parse(viewModel.getDateFrom()),
                    (Date)formatter1.parse(viewModel.getDateTo()));
        }
        else {
            mav.addObject("errors", "Employee information was not added.\n Role/Project/Employee information is not bound in DB");
            return mav;
        }
        return new ModelAndView("redirect:/Appoint");
    }

    @RequestMapping(value = "/GetMembers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject GetMembers(@RequestParam String projectId) throws IOException {
        JSONObject response = new JSONObject();
        int pId = Integer.valueOf(projectId);
        List<ProjectMembers> members = ProjectServices.getProjectMemberForAppoint(pId);
        response.put("members",members);
        response.put("success", true);
        return response;
    }
    @RequestMapping(value = "/FillDropDowns", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject FillDropDowns(@RequestParam String projectId) throws IOException {
        JSONObject response = new JSONObject();
        int pId = Integer.valueOf(projectId);
        List<ProjectMembers> members = ProjectServices.getProjectMemberForAppoint(pId);
        Map<Integer, String> allUsers = getUsersIdAndName();
        for(ProjectMembers m : members){
            allUsers.remove(m.getUserId());
        }

        response.put("roles", getAllRoles());
        response.put("emps",allUsers);
        response.put("success", true);
        return response;
    }

    @RequestMapping(value = "/GetCurProjects", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JSONObject GetCurProjects(@RequestParam String userId) throws IOException {
        JSONObject response = new JSONObject();
        try{
            int uId = Integer.valueOf(userId);
            List<AppointViewModel> list  = ProjectServices.getAppointmentsByUserId(uId);
            Object arr = ConvertToArray(list);
            response.put("response",arr);
            response.put("success", true);
        }catch(Exception e){
            response.put("message", "Could not find data for user");
        }
        return response;
    }


    private JSONArray ConvertToArray(List<AppointViewModel> list) {
        String[] colors = new String[] { "#66CC99", "#CCFF66", "#99CCFF", "#CCCC33", "#99FF66", "#FFCC66", "#66CC99", "#CC9999", "#99CCFF", "#66CC99", "#CC9999", "#99CCFF" };
        Object[] arr = new Object[list.size()];
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++)
        {
            JSONObject object = new JSONObject();
            object.put("start", getDateIntoIso8601(list.get(i).getStartDate()));
            object.put("title", list.get(i).getProjectName());
            object.put("end", getDateIntoIso8601(list.get(i).getEndDate()));
            object.put("allDay", true);
            object.put("color", colors[i]);
            array.add(object);
        }
        return array;
    }

    private Map<Integer, String> getAllRoles() {
        Map<Integer,String> roles = new LinkedHashMap<Integer, String>();
        roles.put(2, "PMO");
        roles.put(3, "Developer");
        roles.put(4, "DBA");
        roles.put(5, "Designer/Analyst");
        roles.put(6, "QA officer");
        roles.put(7, "HW architect");
        roles.put(8, "SW architect");

        return roles;
    }


    private Map<Integer, String> getUsersIdAndName() {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        for (UserLocalizationsEntity loc : UserService.getAllUserLocs()) {
            if(loc.getUserId()!=0)
                users.put(loc.getUserId(), loc.getFirstName() + " " + loc.getLastName());
        }
        return users;
    }

    private Map<Integer, String> getProjectsIdAndName(Date dateFrom, Date dateTo) {
        Map<Integer, String> projects = new LinkedHashMap<Integer, String>();
        Calendar cal = Calendar.getInstance();
        for (ProjectsEntity project : ProjectServices.getAllProjects(dateFrom, dateTo)) {
            cal.setTime(project.getStartDate());
            int year = cal.get(Calendar.YEAR);
            projects.put(project.getId(), "PJ " + year + "-" + project.getCode() + "-" + project.getType());
        }
        return projects;
    }

    private String getDateIntoIso8601(Date date){
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);
        return df.format(date);

    }

}
