package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 1/27/2017.
 */
@Controller
@RequestMapping(value = "/Workflow")
public class Test {

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public ModelAndView Test(Principal principal){
        ModelAndView mav = new ModelAndView();
        // add JSON array to ModelAndView
        mav.setViewName("workflow/newForm/test");
        mav = UP.includeUserProfile(mav, principal);

        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setID(0);
        userInfo.setFirstName("Muslimbek");
        userInfo.setLastName("Pirnazarov");
        userInfos.add(userInfo);
        userInfo = new UserInfo();
        userInfo.setID(1);
        userInfo.setFirstName("Sergio");
        userInfo.setLastName("Nocco");
        userInfos.add(userInfo);
        userInfo = new UserInfo();
        userInfo.setID(2);
        userInfo.setFirstName("Maurisio");
        userInfo.setLastName("Marisio");
        userInfos.add(userInfo);

        mav.addObject("products", userInfos);
        return mav;
    }



}
