package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;
import com.lgcns.erp.workflow.Mapper.DetailsMapper;
import com.lgcns.erp.workflow.Mapper.RequestMapper;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.ViewModel.DetailsViewModel;
import com.lgcns.erp.workflow.ViewModel.RequestViewModel;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;
import com.lgcns.erp.workflow.util.Filter;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow")
public class RequestController {

    @RequestMapping(value = "/MyForms/Request", method = RequestMethod.GET)
    public ModelAndView Hrprofile(Principal principal) {
        ModelAndView mav = new ModelAndView("workflow/myForms/request");

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

  /*  @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<RequestViewModel> getRequestModels(){
        List<RequestViewModel> models = requestMapper.queryTorequestModel(WorkflowService.getRequestList(), UserService.getAllUsers());
        return models;
    }
*/
    @RequestMapping(value = "/list/{page}", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getRequestModels(@PathVariable Integer page,
                                                              @RequestParam("typeId") int selectedformType,
                                                              @RequestParam("statusId") int selectedStatus,
                                                              @RequestParam("reqsandBoxcontainer") String selectedDate,
                                                              @RequestParam("searchInput")String attrValue, Principal principal){
        Map<String, Object> mav = new HashMap<>();
        //Pagination test
        int userId = UserService.getUserIdByUsername(principal.getName());
        PagedListHolder<RequestViewModel> pagedListHolder = new PagedListHolder<>(Filter.
                                                            filterRequest(selectedformType, selectedStatus, attrValue, selectedDate, 2, userId));

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

    @RequestMapping(value = "/MyForms/Request/details/{id}", method = RequestMethod.GET)
    public ModelAndView details(Principal principal, @PathVariable(value = "id")int id){
        ModelAndView mav = new ModelAndView("/workflow/myForms/details");

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));

        BusinessTripVM businessTripVM = BusinessTripMapper.fromBusinessTrip(id);
        mav.addObject("bmodel", businessTripVM);
        System.out.println("MODEL: "+businessTripVM);
        Map<Integer, String> tripTypeName = new HashMap<Integer, String>();

        for(TripTypesEntity trip:
                WorkflowService.getTripTypes()){
            tripTypeName.put(trip.getId(), trip.getName());
        }

        mav.addObject("tripTypeName", tripTypeName);

        DetailsViewModel viewModel = DetailsMapper.toDetails(id);
        mav.addObject("model", viewModel);




        return mav;
    }

    @RequestMapping(value = "/MyForms/Request/details", method = RequestMethod.POST)
    public String details(@RequestParam("comment")String comment, @RequestParam("status")String status){

        return "";
    }
}
