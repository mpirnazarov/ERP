package com.lgcns.erp.workflow.controller.editForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Mapper.LeaveApproveMapper;
import com.lgcns.erp.workflow.Model.Attachment;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
@RequestMapping(value = "/Workflow/EditForm")
public class LeaveApproveEditController {

    int[] approvalsGlobal = null;
    int[] executivesGlobal = null;
    int[] referencesGlobal = null;

    @RequestMapping(value = "/LeaveApprove/{reqId}", method = RequestMethod.GET)
    public ModelAndView LeaveApproveEditGET(Principal principal, @PathVariable int reqId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/editForm/leaveApprove");
        mav = UP.includeUserProfile(mav, principal);

        // Retrieving Leave approve ViewModel
        LeaveApproveVM leaveApproveVM = getLeaveApprove(reqId);
        mav.addObject("leaveApproveVM", leaveApproveVM);

        // Retriving data about type of Business trip
        Map<Integer, String> absenceType = new HashMap<>();
        absenceType.put(0, "");
        for (LeaveType leaveType :
                LeaveType.values()) {
            absenceType.put(leaveType.getValue(), leaveType.name().replace("_", " "));
        }

        // Add trip type data to ModelAndView
        mav.addObject("absenceType", absenceType);


        return mav;
    }

    private LeaveApproveVM getLeaveApprove(int reqId) {

        LeaveApproveVM leaveApproveVM = new LeaveApproveVM();
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);

        leaveApproveVM.setId(reqId);
        leaveApproveVM.setAbsenceType(requestsEntity.getLeaveTypeId());
        leaveApproveVM.setDescription(requestsEntity.getDescription());
        leaveApproveVM.setStart(requestsEntity.getDateFrom());
        leaveApproveVM.setEnd(requestsEntity.getDateTo());

        Collection<AttachmentsEntity> attachmentsEntities = WorkflowService.getAttachmentList();
        List<Attachment> attachments = new LinkedList<>();
        if(attachmentsEntities.size()!=0) {
            for (AttachmentsEntity atEn :
                    attachmentsEntities) {
                if(atEn.getRequestId() == reqId)
                    attachments.add(new Attachment(atEn.getId(), atEn.getUrl(), atEn.getFilename()));
            }
        }
        leaveApproveVM.setAttachments(attachments);

        return leaveApproveVM;
    }

    @RequestMapping(value = "/LeaveApprove/{reqId}", method = RequestMethod.POST, params = "Save")
    public String LeaveApprovePostSave(@ModelAttribute LeaveApproveVM leaveApproveVM, Principal principal, @PathVariable int reqId) throws IOException {

        int userId = UserService.getIdByUsername(principal.getName());
        leaveApproveVM.setId(reqId);

        /* Update table Requests */
        WorkflowService.updateRequestLeaveApprove(LeaveApproveMapper.requestMapperUpdate(leaveApproveVM));

        /*  Insert attachments info to table Attachments */
        if(!leaveApproveVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    leaveApproveVM.getFile()) {
                WorkflowService.insertAttachments(LeaveApproveMapper.attachmentsMapper(leaveApproveVM.getId(), attachment));
            }
        }

        MultipartFile[] multipartFiles=null;
        if(!leaveApproveVM.getFile()[0].isEmpty()) {
            multipartFiles = leaveApproveVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/"+leaveApproveVM.getId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + leaveApproveVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }
        return "redirect: /Workflow/MyForms/details/2/"+reqId;
    }
}
