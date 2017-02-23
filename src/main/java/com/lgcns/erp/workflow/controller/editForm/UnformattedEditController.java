package com.lgcns.erp.workflow.controller.editForm;

import com.google.common.io.Files;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Mapper.UnformattedMapper;
import com.lgcns.erp.workflow.Model.Attachment;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import com.lgcns.erp.workflow.util.ContentType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DScomputers3 on 20.01.2017.
 */

@Controller
@RequestMapping(value = "/Workflow/EditForm")
public class UnformattedEditController {
    int[] approvalsGlobal = null;
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

        /* Input data to View Model */
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

    @RequestMapping(value = "/files/delete/{id}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable("id") Long id) {
        AttachmentsEntity attachmentsEntity = WorkflowService.getAttachmentById(id);
        File file = new File(attachmentsEntity.getUrl());

        if(file.delete()) {
            System.out.println(file.getName() + " is deleted!");
            WorkflowService.deleteAttachment(id);
        }
        else
            System.out.println("Delete operation is failed.");

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(attachmentsEntity.getRequestId());
        int type = requestsEntity.getTypeId();
        if(type == 1)
            return "redirect: /Workflow/EditForm/BusinessTrip/"+requestsEntity.getId();
        else if(type == 2)
            return "redirect: /Workflow/EditForm/LeaveApprove/"+requestsEntity.getId();
        else if(type == 3)
            return "redirect: /Workflow/EditForm/Unformatted/"+requestsEntity.getId();
        else
            return "redirect: /";
    }

    @RequestMapping(value = "Unformatted/{reqId}", method = RequestMethod.POST, params = "Save")
    public String Hrprofile(@ModelAttribute UnformattedViewModel unformattedVM, Principal principal, @PathVariable int reqId) throws IOException {

        int userId = UserService.getIdByUsername(principal.getName());

        /* Update table Requests */
        unformattedVM.setId(reqId);
        WorkflowService.updateRequestUnformatted(UnformattedMapper.requestMapperUpdate(unformattedVM));

        /* Insert attachments info to table Attachments */
        if(!unformattedVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    unformattedVM.getFile()) {
                WorkflowService.insertAttachments(UnformattedMapper.attachmentsMapper(unformattedVM.getId(), attachment));
            }
        }

        System.out.println("FORM:   LEAVE APPROVAL: " + unformattedVM.toString());
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

        return "redirect: /Workflow/MyForms/details/2/"+reqId;
    }

}
