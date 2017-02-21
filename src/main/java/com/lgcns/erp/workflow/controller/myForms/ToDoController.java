package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowNotificationService;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;
import com.lgcns.erp.workflow.util.Filter;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
@RequestMapping(value = "/Workflow/MyForms/todo")
public class ToDoController {

    @RequestMapping(value = "/load")
    public ModelAndView get(Principal principal){
        ModelAndView mav = new ModelAndView("workflow/myForms/todo");

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));

        Map<Integer, String> statusList = new HashMap<>();
        statusList.put(0,"");
        for (Status status : Status.values()) {
            if (status.getValue()==1||status.getValue()==2)
                    statusList.put(status.getValue(), status.name().replace('_',' '));
        }

        Map<Integer, String> typeList = new HashMap<>();
        typeList.put(0,"");
        for (Type type : Type.values()) {
            typeList.put(type.getValue(), type.name().replace('_',' '));
        }

        mav.addObject("statusList", statusList);
        mav.addObject("typeList", typeList);
        int id = UserService.getUserIdByUsername(principal.getName());
        System.out.println(id);
        return mav;
    }


    @RequestMapping(value = "/list/{page}", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getToDoModels(@PathVariable Integer page,
                                                           @RequestParam("selectedformType") int selectedformType,
                                                           @RequestParam("selectedStatus") int selectedStatus,
                                                           @RequestParam("selectedAttribute") int selectedAttribute,
                                                           @RequestParam("selectedDate") String selectedDate,
                                                           @RequestParam("attrValue")String attrValue, Principal principal){
        Map<String, Object> mav = new HashMap<>();

        int userId = UserService.getUserIdByUsername(principal.getName());


        PagedListHolder<ToDoViewModel> pagedListHolder = new PagedListHolder<>(Filter.toDoFilter(selectedformType, selectedStatus,
                selectedAttribute, attrValue, selectedDate, 1, userId));

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


    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public @ResponseBody int getNotification(Principal principal){
        int userId = UserService.getUserIdByUsername(principal.getName());

        int numberOfNotifications = WorkflowNotificationService.getToDoNotification(userId);
        return numberOfNotifications;
    }
}
