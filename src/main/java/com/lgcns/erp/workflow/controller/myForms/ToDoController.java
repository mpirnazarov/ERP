package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.ToDoMapper;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
@RequestMapping(value = "/workflow")
public class ToDoController {

    @RequestMapping(value = "/todo")
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView("workflow/myForms/todo");
        mav.addObject("statusList", Status.values());
        mav.addObject("typeList", Type.values());
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<ToDoViewModel> getToDoModels(){
        List<ToDoViewModel> models = ToDoMapper.queryTotodoModel(WorkflowService.getRequestList(), UserService.getAllUsers());
        return models;
    }

    @RequestMapping(value = "/filter/{id}/{name}", method = RequestMethod.POST)
    public @ResponseBody List<ToDoViewModel> getfilters(@PathVariable Long id, @PathVariable String name){
        System.out.println(id);
        System.out.println(name);

        return null;
    }


}
