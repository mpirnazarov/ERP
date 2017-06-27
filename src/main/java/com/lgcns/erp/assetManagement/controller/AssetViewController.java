package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Muslimbek on 6/23/2017.
 */
@RequestMapping("/Asset")
@Controller
public class AssetViewController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();

        /* JSP joyi i nomi korsatilishi kerak*/
        mav.setViewName("shared/Index");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }
}
