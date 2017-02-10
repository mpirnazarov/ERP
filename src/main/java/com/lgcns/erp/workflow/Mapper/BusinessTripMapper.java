package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.workflow.DBEntities.*;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
public class BusinessTripMapper {
    public static RequestsEntity requestMapper(BusinessTripVM businessTripVM, int idByUsername, int typeId, int statusId) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setUserFromId(idByUsername);
        requestsEntity.setSubject(businessTripVM.getSubject());
        requestsEntity.setDomestic(businessTripVM.isDomestic());
        requestsEntity.setTripTypeId(businessTripVM.getTripType());
        // Type of workflow. 1-Business trip
        requestsEntity.setTypeId(typeId);
        requestsEntity.setDateFrom(businessTripVM.getStart());
        requestsEntity.setDateTo(businessTripVM.getEnd());
        requestsEntity.setDescription(businessTripVM.getPurpose());
        // By default workflow status will be in progress = 1
        requestsEntity.setStatusId(statusId);
        requestsEntity.setDateCreated(new java.sql.Date(new java.util.Date().getTime()));
        requestsEntity.setDestination(businessTripVM.getDestination());

        return requestsEntity;
    }

    public static MembersEntity membersMapper(BusinessTripVM businessTripVM, MembersEntity member, int userId) {
        MembersEntity membersEntity = new MembersEntity();

        membersEntity.setRequestId(businessTripVM.getId());
        membersEntity.setUserId(userId);
        membersEntity.setOrganizationName(member.getOrganizationName());
        membersEntity.setDateFrom(member.getDateFrom());
        membersEntity.setDateTo(member.getDateTo());
        membersEntity.setExpenseTransportation(member.getExpenseTransportation());
        membersEntity.setDailyAllowance(member.getDailyAllowance());
        membersEntity.setExpenseAccommodation(member.getExpenseAccommodation());
        membersEntity.setExpenseOther(member.getExpenseOther());

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
}
