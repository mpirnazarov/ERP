package com.lgcns.erp.workflow.controller.newForm;

import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
public class UnformattedController {

    @RequestMapping(value = "/Workflow/Create/Unformatted", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/newForm/Unformatted");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }
}
