package com.lgcns.erp.workflow.controller.editForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.ToDoEntity;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
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
public class BusinessTripEditController {

    @RequestMapping(value = "/EditForm/{reqId}", method = RequestMethod.POST, params = "submitBusinessTrip")
    public String BusinessTripPostSave(@ModelAttribute BusinessTripVM businessTripVM, Principal principal, @PathVariable int reqId) throws IOException {

        int userId = UserService.getIdByUsername(principal.getName());
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);
        System.out.println(businessTripVM);

        /* Pre initialize table, delete members and toDo table data */
        WorkflowService.deleteMembers(reqId);
        WorkflowService.deleteToDo(reqId);

        /* Insert to table Requests */
        WorkflowService.updateRequestBusinessTrip(BusinessTripMapper.requestMapper(businessTripVM, userId, 1,  4, false), reqId);
        businessTripVM.setId(reqId);

        /* Insert to table Members */
        for (MembersEntity member :
                businessTripVM.getMembersEntityList()) {
            WorkflowService.insertMembers(BusinessTripMapper.membersMapper(businessTripVM, member, userId));
        }

        /* Insert to table to_do */
        for (ToDoEntity todo :
                businessTripVM.getToDoEntityList()) {
            WorkflowService.insertToDo(BusinessTripMapper.toDoMapper(businessTripVM.getId(), todo));
        }

        System.out.println("FORM:   BUSINESS TRIP: " + businessTripVM.toString());

        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!businessTripVM.getFile()[0].isEmpty()) {
            multipartFiles = businessTripVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + reqId +"/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }

        /*  Insert attachments info to table Attachments */
        if(!businessTripVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    businessTripVM.getFile()) {
                WorkflowService.insertAttachments(BusinessTripMapper.attachmentsMapper(businessTripVM.getId(), attachment));
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
            msg = MailMessage.generateMessage(reqId, 3, 1);

            to = WorkflowEmailService.getInvolvementList(reqId, 1);
            mm.sendMail(to, subject, msg);

            /* Sending to references and executors */
            to = (int[]) ArrayUtils.addAll(WorkflowEmailService.getInvolvementList(reqId, 2), WorkflowEmailService.getInvolvementList(reqId, 3));
            mm.sendMail(to, subject, msg);
        }

        /* Sending to creator (Creator is not taking the message in case of editing) */
        /*subject = MailMessage.generateSubject(reqId, 3, 4);
        msg = MailMessage.generateMessage(reqId, 3, 4);
        to[0] = UserService.getIdByUsername(principal.getName());
        mm.sendMail(to, subject, msg);*/

        return "redirect: /Workflow/MyForms/details/2/"+reqId;
    }



}
