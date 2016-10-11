package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.LoginViewModel;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class UserController {


    @RequestMapping(value = "/User/Login", method = RequestMethod.GET)
    public ModelAndView Login(){

        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");

        return model;
    }

    @RequestMapping(value = "/User/LoginAjax", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public JSONObject LoginAjax(@RequestBody String json) throws IOException {
        UserInfo curUser = new UserInfo();
        ObjectMapper mapper = new ObjectMapper();
        LoginViewModel requesValue = mapper.readValue(json, LoginViewModel.class);
        curUser.setUsername(requesValue.getUserName().trim().toLowerCase());
        curUser.setPassword(requesValue.getPassword());

        JSONObject response = new JSONObject();

        if(UserService.Authenticate(curUser)==1)
        {
            response.put("Url", "/");
        }

        return response;
    }

    @RequestMapping (value = "/User/Register", method = RequestMethod.GET)
    @ResponseBody public ModelAndView Register(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/register");
        RegistrationViewModel registrationViewModel = new RegistrationViewModel();
        mav.addObject("registrationVM", registrationViewModel);
        mav.addObject("heads", UserService.getDirectHeads());
        return mav;
    }
}
