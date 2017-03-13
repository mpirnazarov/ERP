package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.DbContext.DepartmentService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.DepartmentLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.ToDoEntity;
import com.lgcns.erp.workflow.DBEntities.*;
import com.lgcns.erp.workflow.Model.Member;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
public class BusinessTripMapper {
    public static RequestsEntity requestMapper(BusinessTripVM businessTripVM, int idByUsername, int typeId, int statusId, boolean isViewed) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setUserFromId(idByUsername);
        requestsEntity.setSubject(businessTripVM.getSubject());
        requestsEntity.setDomestic(businessTripVM.isDomestic());

        if (businessTripVM.getTripType()!=0) requestsEntity.setTripTypeId(businessTripVM.getTripType());
        else requestsEntity.setTripTypeId(1);
        // Type of workflow. 1-Business trip
        requestsEntity.setTypeId(typeId);
        requestsEntity.setDateFrom(businessTripVM.getStart());
        requestsEntity.setDateTo(businessTripVM.getEnd());


        requestsEntity.setDescription(businessTripVM.getPurpose());
        // By default workflow status will be in progress = 1
        requestsEntity.setStatusId(statusId);
        requestsEntity.setDateCreated(new java.sql.Date(new java.util.Date().getTime()));
        requestsEntity.setDestination(businessTripVM.getDestination());
        requestsEntity.setViewed(isViewed);

        return requestsEntity;
    }

    public static MembersEntity membersMapper(BusinessTripVM businessTripVM, MembersEntity member, int userId) {
        MembersEntity membersEntity = new MembersEntity();

        membersEntity.setRequestId(businessTripVM.getId());
        membersEntity.setUserId(member.getUserId());
        membersEntity.setOrganizationName(member.getOrganizationName());
        membersEntity.setDateFrom(member.getDateFrom());
        membersEntity.setDateTo(member.getDateTo());
        membersEntity.setExpenseTransportation(member.getExpenseTransportation());
        membersEntity.setDailyAllowance(member.getDailyAllowance());
        membersEntity.setExpenseAccommodation(member.getExpenseAccommodation());
        membersEntity.setExpenseOther(member.getExpenseOther());
        membersEntity.setAccomodationCurrency(member.getAccomodationCurrency());
        return membersEntity;
    }

    public static ToDoEntity toDoMapper(int id, ToDoEntity todo) {
        ToDoEntity toDoEntity = new ToDoEntity();

        toDoEntity.setRequestId(id);
        toDoEntity.setDate(todo.getDate());
        toDoEntity.setDescription(todo.getDescription());

        return toDoEntity;
    }

    public static AttachmentsEntity attachmentsMapper(int id, MultipartFile attachment) {
        AttachmentsEntity attachmentsEntity = new AttachmentsEntity();

        attachmentsEntity.setRequestId(id);
        attachmentsEntity.setUrl("C:/files/documents/workflow/" + id + "/" + attachment.getOriginalFilename());
        attachmentsEntity.setFilename(attachment.getOriginalFilename());

        return attachmentsEntity;
    }

    public static StepsEntity stepsMapper(int requestId, int userId, int involvementTypeId, int sequence, int statusId, boolean isActive) {
        StepsEntity stepsEntity = new StepsEntity();

        stepsEntity.setRequestId(requestId);
        stepsEntity.setUserId(userId);
        stepsEntity.setInvolvementTypeId(involvementTypeId);
        stepsEntity.setStepSequence(sequence);
        stepsEntity.setStatusId(statusId);
        // stepsEntity.setStatusDate();
        stepsEntity.setActive(isActive);

        return stepsEntity;
    }


    ////SAAARVAR

    public static BusinessTripVM fromBusinessTrip(int id){

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(id);
        List<MembersEntity> membersEntityList = WorkflowService.getMembersByRequestId(id);
        List<ToDoEntity> toDoEntityList = WorkflowService.getToDoByRequestId(id);

        BusinessTripVM viewModel = new BusinessTripVM();
        viewModel.setStart(requestsEntity.getDateFrom());
        viewModel.setEnd(requestsEntity.getDateTo());
        viewModel.setSubject(requestsEntity.getSubject());
        viewModel.setDomestic(requestsEntity.getDomestic());
        viewModel.setTripType(requestsEntity.getTripTypeId());
        viewModel.setDestination(requestsEntity.getDestination());
        viewModel.setPurpose(requestsEntity.getDescription().replace("\n", "").replace("\r", ""));
        viewModel.setMembersEntityList(membersEntityList);
        viewModel.setToDoEntityList(toDoEntityList);
        viewModel.setMembers(getMembers(membersEntityList));
        return viewModel;
    }

    private static List<Member> getMembers(List<MembersEntity> membersEntityList){
        List<Member> members = new ArrayList<>();
        Member member;
        for (MembersEntity entity : membersEntityList) {
            UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(entity.getUserId(), 3);
            member = new Member();
            member.setId(userLoc.getUserId());
            member.setName(userLoc.getFirstName());
            member.setSurname(userLoc.getLastName());
            member.setJobTitle(UserController.getProfileByUsername(UserService.getUsernameById(userLoc.getUserId())).getJobTitle());
            member.setDepartment(getDepartmentNameByUserId(entity.getUserId()));

            members.add(member);
        }
        return members;
    }

    private static String getDepartmentNameByUserId(int id){
        int deptId = UserService.getUserById(id).getDepartmentId();

        DepartmentLocalizationsEntity entity = DepartmentService.getDepartmentLocsByDeptId(deptId, 3);

        return entity.getName();
    }
}
