package com.lgcns.erp.workflow.controller.myForms;

import com.google.common.io.Files;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.workflow.DBContext.WorkflowProgressService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;
import com.lgcns.erp.workflow.Mapper.DetailsMapper;
import com.lgcns.erp.workflow.Mapper.LeaveApproveMapper;
import com.lgcns.erp.workflow.Mapper.ProgressMapper;
import com.lgcns.erp.workflow.Model.Approver;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.ViewModel.TerminationViewModel;
import com.lgcns.erp.workflow.ViewModel.DetailsViewModel;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import com.lgcns.erp.workflow.util.ContentType;
import com.lgcns.erp.workflow.util.DetailsAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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

        //Progress Bar
        List<Approver> approvers = ProgressMapper.getApprovers(WorkflowProgressService.getStepsByReqId(id));
        mav.addObject("approvers", approvers);

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

    //Cancel
    @RequestMapping(value = "/cancellation/{id}", method = RequestMethod.GET)
    public ModelAndView doCancel(Principal principal, @PathVariable("id")int id){
        ModelAndView mav = new ModelAndView("/workflow/myForms/termination");


        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));

        TerminationViewModel viewModel = DetailsMapper.getTerminationViewModel(id);
        mav.addObject("termination", viewModel);

        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        // Retrieving data of all users from DB
        UserLocalizationsEntity userLoc=null;
        for (UsersEntity user :
                UserService.getAllUsers()) {
            jsonObject = new JSONObject();
            if(user.isEnabled()==true) {
                jsonObject = new JSONObject();
                // Retrieving user localizations info from DB for all users and check for null
                if(user.getId()!=0 || UserService.getUserLocByUserId(user.getId(), 3)!=null) {
                    try {
                        userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Inserting user Id as id, fullname as name, jobTitle as jobTitle and department as department
                ProfileViewModel prof =  UserController.getProfileByUsername(user.getUserName());

                jsonObject.put("id", prof.getId());
                jsonObject.put("name", userLoc.getFirstName() + " " + userLoc.getLastName());
                jsonObject.put("jobTitle", prof.getJobTitle());
                jsonObject.put("department", prof.getDepartment());

                // add object to array
                jsonArray.add(jsonObject);

            }
        }

        mav.addObject("jsonData", jsonArray);

        return mav;
    }

    @RequestMapping(value = "/cancellation", method = RequestMethod.POST)
    public  ModelAndView doCancel(@RequestParam("approvals") int[] approvals,
                                  @RequestParam("executives") int[] executives,
                                  @RequestParam("references") int[] references,
                                  @RequestParam("description") String description,
                                  @RequestParam("old_id") int old_id,
                                  @RequestParam("subject")String subject){

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(old_id);

        TerminationViewModel viewModel = new TerminationViewModel();
        viewModel.setDescription(description);
        viewModel.setOld_req_id(old_id);
        viewModel.setSubject(subject);

        int new_req_Id = WorkflowService.insertRequests(DetailsMapper.getRequestFromViewModel(viewModel, requestsEntity));

        int count=1;
        /* Insert to table steps */
        for (int num :approvals) {
            System.out.println("Approvals: " + num);
            if(count==1)
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, requestsEntity.getUserFromId(), 1, count++, 1, true));
            else
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, requestsEntity.getUserFromId(), 1, count++, 1, false));
        }

        for (int num :executives) {
            System.out.println("Executives: " + num);
            WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, requestsEntity.getUserFromId(), 2, 0, 1, false));
        }

        for (int num :references) {
            System.out.println("References: " + num);
            WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, requestsEntity.getUserFromId(), 3, 0, 1, false));
        }


        return new ModelAndView("redirect:/Workflow/MyForms/Request");
    }
}