package com.lgcns.erp.workflow.controller.myForms;

import com.google.common.io.Files;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;
import com.lgcns.erp.workflow.Mapper.DetailsMapper;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.ViewModel.DetailsViewModel;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;
import com.lgcns.erp.workflow.util.ContentType;
import com.lgcns.erp.workflow.util.DetailsAction;
import com.lgcns.erp.workflow.util.Filter;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String details(@RequestParam("comment")String comment, @RequestParam("status")int status, @RequestParam("reqId")int reqId){

        DetailsAction.doAction(comment, status, reqId);

        return "";
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") Long id) {
        String fullPath = WorkflowService.getAttachmentPathNameById(id);
        File file = new File(fullPath);

        String ext = Files.getFileExtension(fullPath);

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(ContentType.getContentType(ext)));
        respHeaders.setContentDispositionFormData("attachment", "");

        InputStreamResource isr = null;
        try {
            isr = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
    }


}
