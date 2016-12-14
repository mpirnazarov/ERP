package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String printWelcome(Principal principal) {
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        int roleId = user.getRoleId();
        System.out.println("ROLE ID: " + roleId);
        if(roleId==1)
            return "forward: /CTO/Profile";
        else if(roleId==3)
            return "forward: /Hr/Profile";
        else
            return "forward: /User/Profile";
    }

}
