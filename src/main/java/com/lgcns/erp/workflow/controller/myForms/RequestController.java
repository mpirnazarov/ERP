package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.ViewModel.RequestViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow")
public class RequestController {

    @RequestMapping(value = "/MyForms/Request", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("statusList", Status.values());
        mav.addObject("typeList", Type.values());
        mav.setViewName("workflow/myForms/request");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<RequestViewModel> getRequestModels(){
        List<RequestViewModel> models = com.lgcns.erp.workflow.Mapper.RequestMapper.queryTorequestModel(WorkflowService.getRequestList(), UserService.getAllUsers());
        return models;
    }

    @RequestMapping(value = "/filter/{id}/{name}", method = RequestMethod.POST)
    public @ResponseBody List<RequestViewModel> getfilters(@PathVariable Long id, @PathVariable String name){
        System.out.println(id);
        System.out.println(name);

        return null;
    }
    @RequestMapping(value = "/MyForms/Details", method = RequestMethod.GET)
    public ModelAndView ViewTest(Principal principal) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/myForms/details");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        return mav;
    }
}
