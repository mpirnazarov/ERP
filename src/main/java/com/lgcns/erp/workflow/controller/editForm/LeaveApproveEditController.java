package com.lgcns.erp.workflow.controller.editForm;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Mapper.LeaveApproveMapper;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import com.lgcns.erp.workflow.controller.email.MailMail;
import com.lgcns.erp.workflow.controller.email.MailMessage;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by DScomputers3 on 20.01.2017.
 */
@Controller
@RequestMapping(value = "/Workflow")
public class LeaveApproveEditController {

    @RequestMapping(value = "/EditForm/{reqId}", method = RequestMethod.POST, params = "submitLeaveApprove")
    public String LeaveApprovePostSave(@ModelAttribute LeaveApproveVM leaveApproveVM, Principal principal, @PathVariable int reqId) throws Exception {

        int userId = UserService.getIdByUsername(principal.getName());
        leaveApproveVM.setId(reqId);
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);
        /* Update table Requests */
        WorkflowService.updateRequestLeaveApprove(LeaveApproveMapper.requestMapperUpdate(leaveApproveVM));

        /* File upload */
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

        /*  Insert attachments info to table Attachments */
        if(!leaveApproveVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    leaveApproveVM.getFile()) {
                WorkflowService.insertAttachments(LeaveApproveMapper.attachmentsMapper(leaveApproveVM.getId(), attachment));
            }
        }

        // E-mail is sent here
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        MailMail mm = (MailMail) context.getBean("mailMail");
        String subject = "";
        String msg = "";
        int[] to;


        if(requestsEntity.getViewed() == true){

            /* Sending to approvals*/
            subject = MailMessage.generateSubject(reqId, 3, 1);
            to = WorkflowEmailService.getInvolvementList(reqId, 1);
            if (to.length!=0){
                for (int uId :
                        to) {
                    msg = MailMessage.generateMessage(reqId, 3, 1, uId);
                    EmailService.sendHtmlMail(uId, subject, msg);
                }
            }
            to=null;

            /* Sending to executors */
            subject = MailMessage.generateSubject(reqId, 3, 2);
            to = WorkflowEmailService.getInvolvementList(reqId, 2);
            if (to.length!=0){
                for (int uId :
                        to) {
                    msg = MailMessage.generateMessage(reqId, 3, 2, uId);
                    EmailService.sendHtmlMail(uId, subject, msg);
                }
            }

            /* Sending to references */
            subject = MailMessage.generateSubject(reqId, 3, 3);
            to = WorkflowEmailService.getInvolvementList(reqId, 3);
            if (to.length!=0){
                for (int uId :
                        to) {
                    msg = MailMessage.generateMessage(reqId, 3, 3, uId);
                    EmailService.sendHtmlMail(uId, subject, msg);
                }
            }
        }

        /* Sending to creator (Creator is not taking the message in case of editing) */
        /*subject = MailMessage.generateSubject(reqId, 3, 4);
        msg = MailMessage.generateMessage(reqId, 3, 4);
        to[0] = UserService.getIdByUsername(principal.getName());
        mm.sendMail(to, subject, msg);*/

        return "redirect: /Workflow/MyForms/details/2/"+reqId;
    }
}
