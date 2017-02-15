package com.lgcns.erp.workflow.controller.myForms;

import com.google.common.io.Files;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;
import com.lgcns.erp.workflow.Mapper.DetailsMapper;
import com.lgcns.erp.workflow.Mapper.LeaveApproveMapper;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.ViewModel.DetailsViewModel;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import com.lgcns.erp.workflow.util.ContentType;
import com.lgcns.erp.workflow.util.DetailsAction;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DS on 15.02.2017.
 */
@Controller
@RequestMapping(value = "/Workflow/MyForms")
public class DetailsController {
    @RequestMapping(value = "/details/{controllerId}/{id}", method = RequestMethod.GET)
    public ModelAndView details(Principal principal, @PathVariable(value = "id")int id, @PathVariable(value = "controllerId") int controller){
        ModelAndView mav = new ModelAndView("/workflow/myForms/details");


        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));

        BusinessTripVM businessTripVM = BusinessTripMapper.fromBusinessTrip(id);
        mav.addObject("bmodel", businessTripVM);

        LeaveApproveVM leaveApproveVM = LeaveApproveMapper.fromLeaveApprove(id);
        mav.addObject("leavemodel", leaveApproveVM);

        Map<Integer, String> leaveTypeName = new HashMap<Integer, String>();
        for (LeaveType lv : LeaveType.values()){
            leaveTypeName.put(lv.getValue(), lv.name().replace('_',' '));
        }
        mav.addObject("leaveTypeName", leaveTypeName);

        Map<Integer, String> tripTypeName = new HashMap<Integer, String>();
        for(TripTypesEntity trip:
                WorkflowService.getTripTypes()){
            tripTypeName.put(trip.getId(), trip.getName());
        }
        mav.addObject("tripTypeName", tripTypeName);

        DetailsViewModel viewModel = DetailsMapper.toDetails(id);
        mav.addObject("model", viewModel);

        mav.addObject("controllerId", controller);

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
