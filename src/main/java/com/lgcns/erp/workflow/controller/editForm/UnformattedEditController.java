package com.lgcns.erp.workflow.controller.editForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowEmailService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Mapper.UnformattedMapper;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
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
@RequestMapping(value = "/Workflow/EditForm")
public class UnformattedEditController {
    /*int[] approvalsGlobal = null;
    int[] executivesGlobal = null;
    int[] referencesGlobal = null;

    @RequestMapping(value = "Unformatted/{reqId}", method = RequestMethod.GET)
    public ModelAndView UnformattedEditGet(Principal principal, @PathVariable int reqId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("workflow/editForm/unformatted");
        mav = UP.includeUserProfile(mav, principal);

        // Creating Unformatted ViewModel
        UnformattedViewModel unformattedVM = getUnformattedVM(reqId);
        mav.addObject("unformattedVM", unformattedVM);

        return mav;
    }

    private UnformattedViewModel getUnformattedVM(int reqId) {
        UnformattedViewModel unformattedVM = new UnformattedViewModel();
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);

        *//* Input data to View Model *//*
        unformattedVM.setSubject(requestsEntity.getSubject());
        unformattedVM.setDescription(requestsEntity.getDescription());
        unformattedVM.setStart(requestsEntity.getDateFrom());
        unformattedVM.setEnd(requestsEntity.getDateTo());

        Collection<AttachmentsEntity> attachmentsEntities = WorkflowService.getAttachmentList();
        List<Attachment> attachments = new LinkedList<>();
        if(attachmentsEntities.size()!=0) {
            for (AttachmentsEntity atEn :
                    attachmentsEntities) {
                if(atEn.getRequestId() == reqId)
                    attachments.add(new Attachment(atEn.getId(), atEn.getUrl(), atEn.getFilename()));
            }
        }
        unformattedVM.setAttachments(attachments);

        return unformattedVM;
    }*/

    @RequestMapping(value = "/files/delete/{id}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable("id") int id) {
        AttachmentsEntity attachmentsEntity = WorkflowService.getAttachmentById(Long.parseLong(""+id));
        File file = new File(attachmentsEntity.getUrl());

        if(file.delete()) {
            System.out.println(file.getName() + " is deleted!");

        }
        else
            System.out.println("Delete operation is failed.");

        WorkflowService.deleteAttachment(id);
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(attachmentsEntity.getRequestId());
        int type = requestsEntity.getTypeId();

        return "redirect: /Workflow/EditForm/"+requestsEntity.getId();

    }

    @RequestMapping(value = "/{reqId}", method = RequestMethod.POST, params = "submitUnformatted")
    public String Hrprofile(@ModelAttribute UnformattedViewModel unformattedVM, Principal principal, @PathVariable int reqId) throws IOException {

        int userId = UserService.getIdByUsername(principal.getName());
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);
        /* Update table Requests */
        unformattedVM.setId(reqId);
        WorkflowService.updateRequestUnformatted(UnformattedMapper.requestMapperUpdate(unformattedVM));

        System.out.println("FORM:   LEAVE APPROVAL: " + unformattedVM.toString());

        /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!unformattedVM.getFile()[0].isEmpty()) {
            multipartFiles = unformattedVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/"+unformattedVM.getId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + unformattedVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }

        /* Insert attachments info to table Attachments */
        if(!unformattedVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    unformattedVM.getFile()) {
                WorkflowService.insertAttachments(UnformattedMapper.attachmentsMapper(unformattedVM.getId(), attachment));
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
