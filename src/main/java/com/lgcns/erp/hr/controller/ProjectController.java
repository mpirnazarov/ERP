package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.enums.ProjectRole;
import com.lgcns.erp.hr.mapper.ProjectMapper;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreate;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreateForm;
import com.lgcns.erp.tapps.DbContext.ContactServices;
import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.mapper.UserMapper;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
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
        List<UserInProjectsEntity> projects = ProjectServices.getUserInProjectsInfoByUserId(user.getId());
        mav.addObject("projects", projects);

        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Create() {
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

        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView CreatePost(@Valid @ModelAttribute("createVM") ProjectCreateForm viewModel, BindingResult bindingResult, Principal principal,
                                   RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("projects/create");
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


    @RequestMapping(value="/Edit/{id}",method = RequestMethod.GET)
    public ModelAndView Edit(@PathVariable int id){
        ProjectsEntity existingProject = ProjectServices.getProjectById(id);
        int managersId = ProjectServices.getManagerIdByProjectId(id);
        if(existingProject != null && managersId != 0){
            ProjectCreate model = ProjectMapper.mapEntityToViewModel(existingProject,managersId);
            return new ModelAndView("projects/edit", "viewModel", model);
        }
        return new ModelAndView("redirect:/Projects");
    }

    @RequestMapping(value="/Edit/{id}",method = RequestMethod.POST)
    public ModelAndView Edit(@Valid @ModelAttribute("viewModel") ProjectCreate viewModel, BindingResult bindingResult, @PathVariable int id){
        ModelAndView mav = new ModelAndView("projects/edit");
        mav.addObject("viewModel", viewModel);
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

    private Map<Integer, String> getUsersIdAndName() {
        Map<Integer, String> users = new LinkedHashMap<Integer, String>();
        for (UserLocalizationsEntity loc : UserService.getAllUserLocs()) {
            users.put(loc.getId(), loc.getFirstName() + " " + loc.getLastName());
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
