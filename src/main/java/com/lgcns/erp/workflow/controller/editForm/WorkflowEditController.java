package com.lgcns.erp.workflow.controller.editForm;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.*;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Mapper.LeaveApproveMapper;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import com.lgcns.erp.workflow.Model.Attachment;
import com.lgcns.erp.workflow.Model.Member;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import com.lgcns.erp.workflow.controller.newForm.LeaveApproveController;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by Muslimbek Pirnazarov on 24.02.2017.
 */
@Controller
@RequestMapping(value = "/Workflow/EditForm")
public class WorkflowEditController {

    @RequestMapping(value = "/{reqId}", method = RequestMethod.GET)
    public ModelAndView WorkflowEditGET(Principal principal, @PathVariable int reqId) {

        ModelAndView mav = new ModelAndView();
        UsersEntity usersEntity = UserService.getUserByUsername(principal.getName());
        mav = UP.includeUserProfile(mav, principal);

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);
        int type = requestsEntity.getTypeId();

        /* Business trip form editing */
        if (type == 1) {
            mav.setViewName("workflow/editForm/businessTrip");
            // Retrieving Leave approve ViewModel
            BusinessTripVM businessTripVM = getBusinessTrip(reqId);
            mav.addObject("businessTripVM", businessTripVM);

            // Retriving data about type of Business trip
            Map<Integer, String> tripTypeList = new HashMap<>();
            tripTypeList.put(0, "");
            for (TripTypesEntity tripTypesEntity :
                    WorkflowService.getTripTypes()) {
                tripTypeList.put(tripTypesEntity.getId(), tripTypesEntity.getName());
            }

            // Add trip type data to ModelAndView
            mav.addObject("tripTypeList", tripTypeList);

            Map<Integer, String> users = new HashMap<>();
            users.put(0, "");

            for (UsersEntity user :
                    UserService.getAllUsers()) {
                Member member = MembersMapper.getMember(user.getId());
                if (user.isEnabled() == true) {
                    users.put(member.getId(), member.getFirstName() + " " + member.getLastName() + " | " + member.getDepartment() + " | " + member.getJobTitle() + "      ");
                }
            }
            mav.addObject("users", users);

            Map<Integer, String> currency = new HashMap<>();
            currency.put(1, "UZS");
            currency.put(2, "USD");
            mav.addObject("currency", currency);

        } else if (type == 2) {
            mav.setViewName("workflow/editForm/leaveApprove");
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

            boolean sixMonthPassed = false;
            int vacationDaysAvailable = 0;
            int usedVacationNumber = LeaveApproveController.getUsedVacationsNumber(usersEntity);

            java.sql.Date hiringDate = usersEntity.getHiringDate();
            DateTime now = new DateTime();
            DateTime then = new DateTime(hiringDate.getTime());

            int hiringDateInterval = Math.abs(Months.monthsBetween(now, then).getMonths());

            if (hiringDateInterval >= 6) {
                System.out.println("6 month apart!");
                sixMonthPassed = true;
            } else {
                System.out.println("6 month NOT apart!");
                sixMonthPassed = false;
            }

            if (hiringDateInterval < 6) {
                vacationDaysAvailable = 0;
            } else if (hiringDateInterval >= 6 && hiringDateInterval <= 12) {
                vacationDaysAvailable = hiringDateInterval * 2 - usedVacationNumber;
            } else {
                vacationDaysAvailable = 24 - usedVacationNumber;
            }

            if (vacationDaysAvailable < 0) {
                vacationDaysAvailable = 0;
            }

            mav.addObject("vacationAvailable", vacationDaysAvailable);
            mav.addObject("sixMonthPassed", sixMonthPassed);


            // Add trip type data to ModelAndView
            mav.addObject("absenceType", absenceType);
        } else if (type == 3) {
            mav.setViewName("workflow/editForm/unformatted");
            mav = UP.includeUserProfile(mav, principal);

            // Creating Unformatted ViewModel
            UnformattedViewModel unformattedVM = getUnformattedVM(reqId);
            mav.addObject("unformattedVM", unformattedVM);
        }
        return mav;
    }

    private BusinessTripVM getBusinessTrip(int reqId) {
        BusinessTripVM businessTripVM = new BusinessTripVM();
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(reqId);

        /* Input data to Business Trip */
        businessTripVM.setSubject(requestsEntity.getSubject());
        businessTripVM.setTripType(requestsEntity.getTripTypeId());
        businessTripVM.setDomestic(requestsEntity.getDomestic());
        businessTripVM.setDestination(requestsEntity.getDestination());
        businessTripVM.setPurpose(requestsEntity.getDescription());
        businessTripVM.setStart(requestsEntity.getDateFrom());
        businessTripVM.setEnd(requestsEntity.getDateTo());

        List<MembersEntity> membersEntities = WorkflowService.getMembersByRequestId(reqId);
        businessTripVM.setMembersEntityList(membersEntities);

        List<ToDoEntity> toDoEntities = WorkflowService.getToDoByRequestId(reqId);
        businessTripVM.setToDoEntityList(toDoEntities);

        List<Attachment> attachments = new ArrayList<>();
        List<AttachmentsEntity> attachmentsEntities = WorkflowService.getRequestAttachmentsByReqId(reqId);

        if (attachmentsEntities != null) {
            for (AttachmentsEntity attachmentsEntity : attachmentsEntities) {
                attachments.add(new Attachment(attachmentsEntity.getId(), attachmentsEntity.getUrl(), attachmentsEntity.getFilename()));
            }
        }
        businessTripVM.setAttachments(attachments);


        return businessTripVM;
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
        if (attachmentsEntities.size() != 0) {
            for (AttachmentsEntity atEn :
                    attachmentsEntities) {
                if (atEn.getRequestId() == reqId)
                    attachments.add(new Attachment(atEn.getId(), atEn.getUrl(), atEn.getFilename()));
            }
        }
        unformattedVM.setAttachments(attachments);

        return unformattedVM;
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
        if (attachmentsEntities.size() != 0) {
            for (AttachmentsEntity atEn :
                    attachmentsEntities) {
                if (atEn.getRequestId() == reqId)
                    attachments.add(new Attachment(atEn.getId(), atEn.getUrl(), atEn.getFilename()));
            }
        }
        leaveApproveVM.setAttachments(attachments);

        return leaveApproveVM;
    }

    @RequestMapping(value = "/{reqId}", method = RequestMethod.POST, params = "Save")
    public String WorkflowPostSave(@ModelAttribute LeaveApproveVM leaveApproveVM, Principal principal, @PathVariable int reqId) throws IOException {
        int userId = UserService.getIdByUsername(principal.getName());

        leaveApproveVM.setId(reqId);

        /* Update table Requests */
        WorkflowService.updateRequestLeaveApprove(LeaveApproveMapper.requestMapperUpdate(leaveApproveVM));

        /*  Insert attachments info to table Attachments */
        if (!leaveApproveVM.getFile()[0].isEmpty()) {
            for (MultipartFile attachment :
                    leaveApproveVM.getFile()) {
                WorkflowService.insertAttachments(LeaveApproveMapper.attachmentsMapper(leaveApproveVM.getId(), attachment));
            }
        }

        MultipartFile[] multipartFiles = null;
        if (!leaveApproveVM.getFile()[0].isEmpty()) {
            multipartFiles = leaveApproveVM.getFile();

            // Uploading files attached to C:/files/documents/workflow/{id}/. Create folder if doesn't exist.
            File f = new File("C:/files/documents/workflow/" + leaveApproveVM.getId() + "/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/workflow/" + leaveApproveVM.getId() + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }
        return "redirect: /Workflow/MyForms/details/2/" + reqId;
    }
}
