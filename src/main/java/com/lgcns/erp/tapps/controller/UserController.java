package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.LoginViewModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class UserController {


    @RequestMapping(value = "/User/Login", method = RequestMethod.GET)
    public ModelAndView LoginAjax(ModelMap modelne){//RequestBody LoginViewModel query) {

        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");

        return model;
    }

    @RequestMapping(value = "/User/LoginAjax1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView LoginAjax1(@ModelAttribute LoginViewModel loginVm, @PathVariable String path){
        UserInfo curUser = new UserInfo();
        curUser.setUsername(loginVm.getUserName().trim().toLowerCase());
        curUser.setPassword(loginVm.getPassword());

        ModelAndView mav = new ModelAndView("jsonView");

        if(UserService.Authenticate(curUser)==1)
        {
            return mav.addObject("Url", path);
        }
        return mav.addObject("Url", "/");
    }

    @RequestMapping(value = "/User/LoginAjax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public ModelAndView LoginAjax(@RequestBody String json) throws IOException {
        UserInfo curUser = new UserInfo();
        ObjectMapper mapper = new ObjectMapper();
        LoginViewModel requesValue = mapper.readValue(json, LoginViewModel.class);
        curUser.setUsername(requesValue.getUserName().trim().toLowerCase());
        curUser.setPassword(requesValue.getPassword());


        ModelAndView mav = new ModelAndView("jsonView");

        if(UserService.Authenticate(curUser)==1)
        {
            return mav.addObject("Url", "/");
        }
        return mav.addObject("Url", "/");
    }

}
