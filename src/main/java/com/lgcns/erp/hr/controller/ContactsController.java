package com.lgcns.erp.hr.controller;

import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointEdit;
import com.lgcns.erp.tapps.DbContext.ContactServices;
import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.ContactsEntity;
import com.lgcns.erp.tapps.model.DbEntities.OrganizationEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Rafatdin on 07.12.2016.
 */
@Controller
@RequestMapping("/Contacts")
public class ContactsController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView Index(Principal principal) {
        ModelAndView mav = new ModelAndView("tapps/contact/index");
        List<ContactsEntity> model = ContactServices.getAllContacts();
        Collections.sort(model, new Comparator<ContactsEntity>() {
            @Override
            public int compare(ContactsEntity o1, ContactsEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        mav.addObject("viewModel", model);
        mav = UP.includeUserProfile(mav, principal);
        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public ModelAndView Create(Principal principal){
        ModelAndView mav = new ModelAndView("tapps/contact/create");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", new ContactsEntity());
        mav.addObject("organizations", getAllOrganizations());
        return mav;
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    public ModelAndView Create(@Valid @ModelAttribute("viewModel") ContactsEntity viewModel, BindingResult bindingResult, Principal principal) throws ParseException {
        ModelAndView mav = new ModelAndView("tapps/contact/create");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", new ContactsEntity());
        mav.addObject("organizations", getAllOrganizations());
        if(bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }
        try{
            ContactServices.insert(viewModel);
        }catch (Exception e){
            mav.addObject("errors", "Error occured during the Customer Organization creation. " +
                    "\n please contact developers");
            return mav;
        }
        return new ModelAndView("redirect:/Contacts");

    }

    @RequestMapping(value = "/Edit/{id}", method = RequestMethod.GET)
    public ModelAndView Edit(@PathVariable int id, Principal principal) {
        ContactsEntity model = ContactServices.getContactById(id);
        ModelAndView mav = new ModelAndView("tapps/contact/edit");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("organizations", getAllOrganizations());
        mav.addObject("viewModel", model);
        return mav;
    }

    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
    public ModelAndView Edit(@Valid @ModelAttribute("viewModel") ContactsEntity entity, BindingResult bindingResult, Principal principal) {
        ModelAndView mav = new ModelAndView("tapps/contact/edit");
        mav = UP.includeUserProfile(mav, principal);
        if(bindingResult.hasErrors()) {
            mav.addObject("errors", bindingResult.getAllErrors());
            return mav;
        }
        try {
            ContactServices.update(entity);
        } catch (Exception e) {
            mav.addObject("organizations", getAllOrganizations());
            mav.addObject("viewModel", entity);
            mav.addObject("error", "Could not update the data. \n" +
                    e.getMessage()+".\nPlease contact the developers");
            return mav;
        }
        return new ModelAndView("redirect:/Contacts");
    }

    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.GET)
    public ModelAndView Delete(@PathVariable int id, Principal principal) {
        ContactsEntity model = ContactServices.getContactById(id);
        ModelAndView mav = new ModelAndView("tapps/contact/delete");
        mav.addObject("organizations", getAllOrganizations());
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("viewModel", model);
        return mav;
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public ModelAndView Delete(@ModelAttribute("viewModel") ContactsEntity viewModel, Principal principal) {
        try {
            ContactServices.delete(viewModel);
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("tapps/contact/delete");
            mav.addObject("organizations", getAllOrganizations());
            mav = UP.includeUserProfile(mav, principal);
            mav.addObject("viewModel", viewModel);
            mav.addObject("errors", e.getMessage());
            return mav;
        }
        return new ModelAndView("redirect:/Contacts");
    }

    private Map<Integer, String> getAllOrganizations(){
        Map<Integer, String> returning = new LinkedHashMap<Integer, String>();
        List<OrganizationEntity> organizations = ContactServices.getAllOrganizations();
        Collections.sort(organizations, new Comparator<OrganizationEntity>() {
            @Override
            public int compare(OrganizationEntity o1, OrganizationEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for(OrganizationEntity o : organizations){
            returning.put(o.getId(), o.getName());
        }
        return returning;
    }
}
