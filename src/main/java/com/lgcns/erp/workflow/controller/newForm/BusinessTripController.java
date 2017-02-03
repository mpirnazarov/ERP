package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow")
public class BusinessTripController {

    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.GET)
    public ModelAndView WorkflowGET(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/newForm/businessTripForm");
        mav = UP.includeUserProfile(mav, principal);

        // Creating Business Trip ViewModel
        BusinessTripVM businessTripVM = new BusinessTripVM();
        mav.addObject("businessTripVM", businessTripVM);

        // Create Json data about userlist for Approvals, reference and executive persons list
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        // Retrieving data of all users from DB
        UserLocalizationsEntity userLoc=null;
        for (UsersEntity user :
                UserService.getAllUsers()) {
            jsonObject = new JSONObject();
            if(user.isEnabled()==true) {
                jsonObject = new JSONObject();
                // Retrieving user localizations info from DB for all users and check for null
                if(user.getId()!=0 || UserService.getUserLocByUserId(user.getId(), 3)!=null) {
                    try {
                        userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Inserting user Id as id, fullname as name, jobTitle as jobTitle and department as department
                ProfileViewModel prof =  UserController.getProfileByUsername(user.getUserName());

                jsonObject.put("id", prof.getId());
                jsonObject.put("name", userLoc.getFirstName() + " " + userLoc.getLastName());
                jsonObject.put("jobTitle", prof.getJobTitle());
                jsonObject.put("department", prof.getDepartment());

                // add object to array
                jsonArray.add(jsonObject);
            }
        }

        // add JSON array to ModelAndView
        mav.addObject("jsonData", jsonArray);

        // Retriving data about type of Business trip
        Map<Integer, String> tripTypeList = new HashMap<>();
        for (TripTypesEntity tripType :
                WorkflowService.getTripTypes()) {
            tripTypeList.put(Integer.valueOf((int) tripType.getId()), tripType.getName());
        }
        // Add trip type data to ModelAndView
        mav.addObject("triptypeList", tripTypeList);


        return mav;
    }

    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.POST)
    public String WorkflowPost(@ModelAttribute BusinessTripVM businessTripVM){
        System.out.println("FORM:   BUSINESS TRIP: " + businessTripVM.toString());
        return "redirect: /Workflow/NewForm/BusinessTripForm";
    }

    @RequestMapping(value = "/NewForm/BusinessTripFormAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] WorkflowPostAjax(@RequestParam("approvals") int[] approvals, @RequestParam("executives") int[] executives, @RequestParam("references") int[] references){
        System.out.println("JSON: ");
        for (int num :
                approvals) {
            System.out.println("Approvals: " + num);
        }
        for (int num :
                executives) {
            System.out.println("Executives: " + num);
        }
        for (int num :
                references) {
            System.out.println("References: " + num);
        }


        return approvals;
    }

}
