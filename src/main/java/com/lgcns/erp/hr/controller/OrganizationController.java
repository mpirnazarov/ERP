package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.enums.ProjectRole;
import com.lgcns.erp.hr.mapper.ProjectMapper;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointCreate;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointEdit;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointViewModel;
import com.lgcns.erp.hr.viewModel.AppointViewModels.ProjectMembers;
import com.lgcns.erp.tapps.DbContext.ContactServices;
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
@RequestMapping("/Organizations")
public class OrganizationController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Index(Principal principal) {
        ModelAndView mav = new ModelAndView("tapps/organization/index");
        List<OrganizationEntity> model = ContactServices.getAllOrganizations();
        Collections.sort(model, new Comparator<OrganizationEntity>() {
            @Override
            public int compare(OrganizationEntity o1, OrganizationEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        mav.addObject("viewModel", model);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public ModelAndView Create(Principal principal){
        ModelAndView mav = new ModelAndView("tapps/organization/create");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", new OrganizationEntity());
        return mav;
    }
    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    public ModelAndView Create(@Valid @ModelAttribute("viewModel") OrganizationEntity viewModel, BindingResult bindingResult, Principal principal) throws ParseException {
        ModelAndView mav = new ModelAndView("tapps/organization/create");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", new OrganizationEntity());
        if(bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }
        try{
            ContactServices.insert(viewModel);
        }catch (Exception e){
            mav.addObject("error", "Error occured during the Customer Organization creation. " +
                    "\n please contact developers");
            return mav;
        }
        return new ModelAndView("redirect:/Organizations");

    }

    @RequestMapping(value = "/Edit/{id}", method = RequestMethod.GET)
    public ModelAndView Edit(@PathVariable int id, Principal principal) {
        OrganizationEntity model = ContactServices.getOrganizationtById(id);
        ModelAndView mav = new ModelAndView("tapps/organization/edit");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", model);
        return mav;
    }

    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
    public ModelAndView Edit(@Valid @ModelAttribute("viewModel") OrganizationEntity model, BindingResult bindingResult, Principal principal) {
        ModelAndView mav = new ModelAndView("tapps/organization/edit");
        mav = UP.includeUserProfile(mav, principal);

        if(bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }
        //updating organization info
        try {
            ContactServices.update(model);
        } catch (Exception e) {
            mav.addObject("error", "Could not update the data. \n" +
                    e.getMessage()+".\nPlease contact the developers");
            return mav;
        }
        return new ModelAndView("redirect:/Organizations");
    }

    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.GET)
    public ModelAndView Delete(@PathVariable int id, Principal principal) {
        OrganizationEntity model = ContactServices.getOrganizationtById(id);
        ModelAndView mav = new ModelAndView("tapps/organization/delete");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", model);
        return mav;
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public ModelAndView Delete(@ModelAttribute("viewModel") OrganizationEntity viewModel, Principal principal) {
        try {
            ContactServices.delete(viewModel);
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("tapps/organization/delete");
            mav = UP.includeUserProfile(mav, principal);
            mav.addObject("viewModel", viewModel);
            mav.addObject("error", e.getMessage());
            return mav;
        }
        return new ModelAndView("redirect:/Organizations");
    }
}
