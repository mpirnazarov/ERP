package com.lgcns.erp.tapps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World"); //Need to check whether the user is logged in or not
        return new ModelAndView("forward: /User/Profile");

    }

}
