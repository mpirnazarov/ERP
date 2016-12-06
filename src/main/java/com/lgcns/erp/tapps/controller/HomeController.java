package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(Principal principal, ModelMap model) {
        int roleId = UserService.getUserByUsername(principal.getName()).getRoleId();
        if(roleId==1)
            return "forward: /CTO/Profile";
        else if(roleId==2)
            return "forward: /User/Profile";
        else
            return "forward: /Hr/Profile";
    }

}
