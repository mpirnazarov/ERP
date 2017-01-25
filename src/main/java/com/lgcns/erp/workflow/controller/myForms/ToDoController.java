package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.ToDoMapper;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;
import com.lgcns.erp.workflow.util.Filter;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
@RequestMapping(value = "/Workflow/MyForms")
public class ToDoController {

    @RequestMapping(value = "/todo")
    public ModelAndView get(Principal principal){
        ModelAndView mav = new ModelAndView("workflow/myForms/todo");

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));

        Map<Integer, String> statusList = new HashMap<>();
        statusList.put(0,"");
        for (Status status : Status.values()) {
            statusList.put(status.getValue(), status.name().replace('_',' '));
        }

        Map<Integer, String> typeList = new HashMap<>();
        typeList.put(0,"");
        for (Type type : Type.values()) {
            typeList.put(type.getValue(), type.name().replace('_',' '));
        }

        mav.addObject("statusList", statusList);
        mav.addObject("typeList", typeList);
        return mav;
    }

  /*  @RequestMapping(value = "/pagedList", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> pagedList(@RequestParam(required = false)Integer page){
        Map<String, Object> mav = new HashMap<>();
        //Pagination test

        PagedListHolder<ToDoViewModel> pagedListHolder = new PagedListHolder<>(ToDoMapper.queryTotodoModel(
                WorkflowService.getRequestList(), UserService.getAllUsers()));

        pagedListHolder.setPageSize(3);

        mav.put("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

        mav.put("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            mav.put("models", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            mav.put("models", pagedListHolder.getPageList());
        }
        return mav;
    }*/

    @RequestMapping(value = "/list/{page}", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getToDoModels(@PathVariable Integer page){
        Map<String, Object> mav = new HashMap<>();
        //Pagination test
        System.out.println(page);
        PagedListHolder<ToDoViewModel> pagedListHolder = new PagedListHolder<>(ToDoMapper.queryTotodoModel(
                WorkflowService.getRequestList(), UserService.getAllUsers()));

        pagedListHolder.setPageSize(2);

        mav.put("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())
            page=1;

        mav.put("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            mav.put("models", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            mav.put("models", pagedListHolder.getPageList());
        }
        return mav;
    }

    /*@RequestMapping(value = "/listcha", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> test(){
        Integer page = null;
        Map<String, Object> mav = new HashMap<>();
        //Pagination test

        PagedListHolder<ToDoViewModel> pagedListHolder = new PagedListHolder<>(ToDoMapper.queryTotodoModel(
                WorkflowService.getRequestList(), UserService.getAllUsers()));

        pagedListHolder.setPageSize(1);

        mav.put("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())
            page=1;

        mav.put("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            mav.put("models", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            mav.put("models", pagedListHolder.getPageList());
        }
        return mav;
    }
*/
    @RequestMapping(value = "/filter/{selectedformType}/{selectedStatus}/{selectedAttribute}", method = RequestMethod.POST)
    public @ResponseBody List<ToDoViewModel> getfilters(@PathVariable int selectedformType,
                                                        @PathVariable int selectedStatus,
                                                        @PathVariable int selectedAttribute,
                                                        @RequestParam("selectedDate") @DateTimeFormat(pattern="dd/mm/yyyy")Date selectedDate,
                                                        @RequestParam("attrValue")String attrValue){
        System.out.println(selectedformType);
        System.out.println(selectedStatus);
        System.out.println(selectedAttribute);
        System.out.println(selectedDate);
        System.out.println(attrValue);

        List<ToDoViewModel> models = Filter.filter(selectedformType, selectedStatus, selectedAttribute, attrValue);

        return models;
    }


}
