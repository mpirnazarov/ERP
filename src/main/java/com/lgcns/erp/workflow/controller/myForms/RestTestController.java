package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by DS on 15.03.2017.
 */
@Controller("/Workflow/api")
public class RestTestController {
    @RequestMapping(value = "/users")
    public List<UsersEntity> getUsers(){
        return UserService.getAllUsers();
    }
}
