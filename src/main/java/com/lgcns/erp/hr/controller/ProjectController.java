package com.lgcns.erp.hr.controller;

import com.lgcns.erp.tapps.DbContext.ProjectServices;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by Rafatdin on 08.11.2016.
 */
@Controller
public class ProjectController
{
    @RequestMapping(value = "/Projects", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView Project(Principal principal) {
        ModelAndView mav = new ModelAndView("projects/index");
        UsersEntity user = UserService.getUserByUsername(principal.getName());
        List<UserInProjectsEntity> projects = ProjectServices.getUserInProjectsInfoByUserId(user.getId());
        mav.addObject("projects", projects);

        return mav;
    }
}
