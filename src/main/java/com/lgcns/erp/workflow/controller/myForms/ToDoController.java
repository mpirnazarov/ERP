package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
public class ToDoController {
    @RequestMapping(value = "/Workflow", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal){
        System.out.println("Workflow start");
        List<RequestsEntity> requestsEntityList = WorkflowService.getRequests();

        System.out.println("LIST OF REQUESTS: ");
        for (RequestsEntity req :
                requestsEntityList) {
            System.out.println(req.getId() + " "  + req.getSubject());
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("reqList", requestsEntityList);
        mav.setViewName("workflow/myForms/Request");
        mav = UP.includeUserProfile(mav, principal);

        return mav;
    }
}
