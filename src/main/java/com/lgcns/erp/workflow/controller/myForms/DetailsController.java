package com.lgcns.erp.workflow.controller.myForms;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.workflow.DBContext.WorkflowDeleteService;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.DBContext.WorkflowProgressService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.TripTypesEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Mapper.*;
import com.lgcns.erp.workflow.Model.Approver;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.ViewModel.TerminationViewModel;
import com.lgcns.erp.workflow.ViewModel.DetailsViewModel;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import com.lgcns.erp.workflow.util.DetailsAction;
import com.lgcns.erp.workflow.util.ValidateRequestAccess;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.*;

/**
 * Created by DS on 15.02.2017.
 */
@Controller
@RequestMapping(value = "/Workflow/MyForms")
public class DetailsController {

    @RequestMapping(value = "/details/{controllerId}/{id}", method = RequestMethod.GET)
    public ModelAndView details(Principal principal, @PathVariable(value = "id")int id, @PathVariable(value = "controllerId") int controller) throws Exception {

        int userId = UserService.getIdByUsername(principal.getName());
        if (controller==1&&!ValidateRequestAccess.ValidateTodoAccess(id, userId)){
            return new ModelAndView("Error");
        }

        if (controller==2&&!ValidateRequestAccess.ValidRequestAccess(id, userId)){
            return new ModelAndView("Error");
        }

        ModelAndView mav = new ModelAndView("/workflow/myForms/details");

        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));

        RequestsEntity entity = WorkflowService.getRequestsEntityById(id);

        if (entity.getTypeId()==3) {
            UnformattedViewModel unformattedViewModel = UnformattedMapper.fromUnformatted(id);
            mav.addObject("umodel", unformattedViewModel);
        }
        else if (entity.getTypeId()==1){
            BusinessTripVM businessTripVM = BusinessTripMapper.fromBusinessTrip(id);
            mav.addObject("bmodel", businessTripVM);
        }

        else if (entity.getTypeId()==2){
            LeaveApproveVM leaveApproveVM = LeaveApproveMapper.fromLeaveApprove(id);
            mav.addObject("leavemodel", leaveApproveVM);
        }

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

        TerminationViewModel terminationViewModel = DetailsMapper.getTerminationViewModel(id);
        mav.addObject("termination", terminationViewModel);

        //Progress Bar
        List<Approver> approvers = ProgressMapper.getApprovers(WorkflowProgressService.getStepsByReqId(id));
        mav.addObject("approvers", approvers);


        //Set the request viewed true if controller is ToDo
        if (controller==1)
            WorkflowService.setIsViewed(id);

        mav.addObject("isViewed", entity.getViewed());

        return mav;
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String details(@RequestParam("comment")String comment, @RequestParam("status")int status, @RequestParam("reqId")int reqId) throws IOException {

        DetailsAction.doAction(comment, status, reqId);
        return "";
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {

        String fullPath = WorkflowService.getAttachmentPathNameById(id);

        File file = new File(fullPath);
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());

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
            if(user.isEnabled()==true && user.getUserName().compareTo(principal.getName())!=0) {
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
    public  String doCancel(@RequestParam("approvals") int[] approvals,
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
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, num, 1, count++, 1, true));
            else
                WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, num, 1, count++, 1, false));
        }

        for (int num :executives) {
            System.out.println("Executives: " + num);
            WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, num, 2, 0, 1, false));
        }

        for (int num :references) {
            System.out.println("References: " + num);
            WorkflowService.insertSteps(BusinessTripMapper.stepsMapper(new_req_Id, num, 3, 0, 1, false));
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/Delete/{id}")
    public String delete(@PathVariable("id")int id) throws IOException {
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(id);

        if (requestsEntity.getStatusId()!= Status.Draft.getValue())
            WorkflowDeleteService.DeleteRequest(id, 8);
        sendEmailAfterDelete(id);
        return "redirect:/Workflow/MyForms/Request";
    }

    private static void sendEmailAfterDelete(int id) throws IOException {

        // E-mail is sent here
        String subject = "";
        String msg = "";
        int[] to;


        /* Sending to approvals*/
        subject = MailMessage.generateSubject(id, 2, 1);

        to = WorkflowEmailService.getInvolvementList(id, 1);
        System.out.println(to.length);
        if (to.length!=0) {
            for (int userId :
                    to) {
                msg = MailMessage.generateMessage(id, 2, 1, userId);
                EmailService.sendHtmlMail(userId, subject, msg);
            }
        }
        to = null;

        /* Sending to references */
        subject = MailMessage.generateSubject(id, 2, 3);

        to = WorkflowEmailService.getInvolvementList(id, 3);
        if (to.length!=0){
            for (int userId :
                    to) {
                msg = MailMessage.generateMessage(id, 2, 3, userId);
                EmailService.sendHtmlMail(userId, subject, msg);

            }
        }
    }
}
