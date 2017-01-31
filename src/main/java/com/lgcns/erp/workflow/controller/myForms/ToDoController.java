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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    @RequestMapping(value = "/list/{page}", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getToDoModels(@PathVariable Integer page,
                                                           @RequestParam("selectedformType") int selectedformType,
                                                           @RequestParam("selectedStatus") int selectedStatus,
                                                           @RequestParam("selectedAttribute") int selectedAttribute,
                                                           @RequestParam("selectedDate") String selectedDate,
                                                           @RequestParam("attrValue")String attrValue){
        Map<String, Object> mav = new HashMap<>();
        //Pagination test
        System.out.println(page);
        PagedListHolder<ToDoViewModel> pagedListHolder = new PagedListHolder<>(Filter.
                                                                                filter(selectedformType, selectedStatus,
                                                                                        selectedAttribute, attrValue, selectedDate));

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

    @RequestMapping(value = "/filter/{selectedformType}/{selectedStatus}/{selectedAttribute}", method = RequestMethod.POST)
    public @ResponseBody List<ToDoViewModel> getfilters(@PathVariable int selectedformType,
                                                        @PathVariable int selectedStatus,
                                                        @PathVariable int selectedAttribute,
                                                        @RequestParam("selectedDate") String selectedDate,
                                                        @RequestParam("attrValue")String attrValue){

        List<ToDoViewModel> models = Filter.filter(selectedformType, selectedStatus, selectedAttribute, attrValue, selectedDate);

        return models;
    }

}
