package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.UserInPostsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow")
public class BusinessTripController {
    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.GET)
    public ModelAndView WorkflowGET(Principal principal){
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        UserLocalizationsEntity userLoc=null;
        for (UsersEntity user :
                UserService.getAllUsers()) {
            jsonObject = new JSONObject();
            if(user.isEnabled()==true) {
                jsonObject = new JSONObject();
                if(user.getId()!=0 || UserService.getUserLocByUserId(user.getId(), 3)!=null) {
                    try {
                        userLoc = UserService.getUserLocByUserId(user.getId(), 3);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Inserting Id as key, fullname as name, jobTitle as title
                jsonObject.put("id", user.getId());
                jsonObject.put("name", userLoc.getFirstName() + " " + userLoc.getLastName());
                /*jsonObject.put("job", userLoc.get)*/
                //jsonObject.put("title", UserController.getProfileByUsername(user.getUserName()).getJobTitle());
                // and chiefId if available

                // add object to array
                jsonArray.add(jsonObject);
            }
        }
        System.out.println("ARRAY: " + jsonArray);

        ModelAndView mav = new ModelAndView();
        // add JSON array to ModelAndView
        mav.addObject("jsonData", jsonArray);
        mav.setViewName("workflow/newForm/businessTripForm");
        mav = UP.includeUserProfile(mav, principal);

        return mav;
    }

    @RequestMapping(value = "/NewForm/BusinessTripForm", method = RequestMethod.POST)
    public @ResponseBody
    int[] WorkflowPost(@RequestParam(value="myArray") int[] a){
        for (int i:
                a) {
            System.out.println("ARRAY: " + i);
        }
        return a;
    }
}
