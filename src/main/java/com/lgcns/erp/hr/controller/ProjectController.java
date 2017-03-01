package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.enums.ProjectRole;
import com.lgcns.erp.hr.enums.ProjectStatus;
import com.lgcns.erp.hr.mapper.ProjectMapper;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreate;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreateForm;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectHistoryModel;
import com.lgcns.erp.tapps.DbContext.ContactServices;
import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * Created by Rafatdin on 08.11.2016.
 */
@Controller
@RequestMapping("/Projects")
public class ProjectController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Project(Principal principal) {
        ModelAndView mav = new ModelAndView("projects/index");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
            if(user.getRoleId() != 0)
                return new ModelAndView("redirect:/Projects/all_projects");
        List<UserInProjectsEntity> projects = ProjectServices.getUserInProjectsInfoByUserId(user.getId());
        if(projects.isEmpty())
            return new ModelAndView("redirect:/Projects/Error");
        mav.addObject("projects", projects);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/all_projects", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView AllProjects(Principal principal){
        ModelAndView mav = new ModelAndView("projects/indexForManager");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<ProjectsEntity> projects = null;
        if(user.getRoleId() == 1) //if user is in role MANAGER
            projects = ProjectServices.getAllProjects();
        else
            return new ModelAndView("redirect:/Projects");

        mav.addObject("projects", projects);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Create(Principal principal) {
        ModelAndView mav = new ModelAndView("projects/create");

        ProjectCreateForm createVM = new ProjectCreateForm();
        List<ProjectCreate> viewModelList = new ArrayList<ProjectCreate>(4);
        ProjectCreate viewModel;
        String nextProjectCode = ProjectServices.getNextCode();
        for (int i = 0; i < 4; i++) {
            viewModel = new ProjectCreate();
            viewModel.setCode(nextProjectCode);
            viewModel.setType(getCodeOrder(i));
            viewModelList.add(viewModel);
        }
        createVM.setProjects(viewModelList);

        mav.addObject("createVM", createVM);
        mav.addObject("customers", getContactsIdAndName());
        mav.addObject("users", getUsersIdAndName());

        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView CreatePost(@Valid @ModelAttribute("createVM") ProjectCreateForm viewModel, BindingResult bindingResult, Principal principal,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("projects/create");
        mav = UP.includeUserProfile(mav, principal);

        mav.addObject("createVM", viewModel);
        if (bindingResult.hasErrors()) {
            mav.addObject("customers", getContactsIdAndName());
            mav.addObject("users", getUsersIdAndName());
            mav.addObject("org.springframework.validation.BindingResult.createVM", bindingResult);
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }
        //adding project and projectLocalization info into DB
        for (ProjectCreate projectModel : viewModel.getProjects()) {
            int projectId = ProjectServices.insertProject(ProjectMapper.mapViewModelToEntity(projectModel));
            ProjectServices.insertProjectMember(projectId, projectModel.getManagerId(), ProjectRole.Manager, projectModel.getStartDate(), projectModel.getEndDate());
        }
        return new ModelAndView("redirect:/Projects");
    }

    private Map<Integer, String> getContactsIdAndName() {
        Map<Integer, String> contacts = new LinkedHashMap<Integer, String>();        //Getting contacts and to add to model and view
        for (ContactsEntity contact : ContactServices.getAllContacts()) {
            contacts.put(contact.getId(), contact.getName());
        }
        return contacts;
    }


    @RequestMapping(value = "/Edit/{id}", method = RequestMethod.GET)
    public ModelAndView Edit(@PathVariable int id, Principal principal) {
        ModelAndView mav = new ModelAndView("projects/edit");
        mav = UP.includeUserProfile(mav, principal);
        ProjectsEntity existingProject = ProjectServices.getProjectById(id);
        int managersId = ProjectServices.getManagerIdByProjectId(id);
        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        for (ProjectStatus status : ProjectStatus.values()) {
            statusMap.put(status.getValue(), status.toString());
        }
        if (existingProject != null && managersId != 0) {
            ProjectCreate model = ProjectMapper.mapEntityToViewModel(existingProject, managersId);
            mav.addObject("viewModel", model);
            mav.addObject("customers", getContactsIdAndName());
            mav.addObject("managers", getUsersIdAndName());
            mav.addObject("projectStatuses", statusMap);
            return mav;
        }
        return new ModelAndView("redirect:/Projects");
    }

    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
    public ModelAndView Edit(@Valid @ModelAttribute("viewModel") ProjectCreate viewModel, BindingResult bindingResult, Principal principal) {
        ModelAndView mav = new ModelAndView("projects/edit");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", viewModel);
        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        for (ProjectStatus status : ProjectStatus.values()) {
            statusMap.put(status.getValue(), status.toString());
        }
        mav.addObject("projectStatuses", statusMap);

        if (bindingResult.hasErrors()) {
            mav.addObject("customers", getContactsIdAndName());
            mav.addObject("users", getUsersIdAndName());
            mav.addObject("org.springframework.validation.BindingResult.viewModel", bindingResult);
            mav.addObject("errors", bindingResult.getAllErrors());

            return mav;
        }
        //updating project and manager info
        ProjectServices.updateProject(viewModel.getId(), viewModel);
        ProjectServices.updateManager(viewModel.getId(), viewModel.getManagerId());

        return new ModelAndView("redirect:/Projects");
    }
    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.GET)
    public ModelAndView Delete(@PathVariable int id, Principal principal) {
        ProjectsEntity existingProject = ProjectServices.getProjectById(id);
        int managersId = ProjectServices.getManagerIdByProjectId(id);
        ProjectCreate model = ProjectMapper.mapEntityToViewModel(existingProject, managersId);

        ModelAndView mav = new ModelAndView("projects/delete");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", model);
        mav.addObject("customer", getContactsIdAndName().get(model.getCustomerId()));
        mav.addObject("manager", getUsersIdAndName().get(model.getManagerId()));
        return mav;
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public ModelAndView Delete(@ModelAttribute("viewModel") ProjectCreate viewModel, Principal principal) {
        try {
            ProjectServices.deleteProject(viewModel.getId());
        }catch (Exception e){
            ModelAndView mav = new ModelAndView("projects/delete");
            mav.addObject("viewModel", viewModel);
            mav.addObject("customer", getContactsIdAndName().get(viewModel.getCustomerId()));
            mav.addObject("manager", getUsersIdAndName().get(viewModel.getManagerId()));
            mav = UP.includeUserProfile(mav, principal);
            return mav;
        }
        return new ModelAndView("redirect:/Projects");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Error")
    public ModelAndView Error(Principal principal) {
        ModelAndView mav = new ModelAndView("projects/error");
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/ProjectHistory", method = RequestMethod.GET)
    public ModelAndView ProjectHistory(Principal principal) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shared/menu/Project");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<ProjectHistoryModel> model = ProjectServices.getProjectHistoryInfoByUserId(user.getId());

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", model);
        return mav;
    }

    private Map<Integer, String> getUsersIdAndName() {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        for (UserLocalizationsEntity loc : UserService.getAllUserLocs()) {
            users.put(loc.getUserId(), loc.getFirstName() + " " + loc.getLastName());
        }
        return users;
    }

    private String getCodeOrder(int i) {
        switch (i) {
            case 0:
                return "R";
            case 1:
                return "H";
            case 2:
                return "M";
            case 3:
                return "B";
            default:
                return "-";
        }
    }

}
