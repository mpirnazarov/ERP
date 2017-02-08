package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.ToDoEntity;
import com.lgcns.erp.workflow.ViewModel.BusinessTripVM;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
public class BusinessTripMapper {
    public static RequestsEntity RequestMapper(BusinessTripVM businessTripVM, int idByUsername) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setUserFromId(idByUsername);
        requestsEntity.setSubject(businessTripVM.getSubject());
        requestsEntity.setDomestic(businessTripVM.isDomestic());
        requestsEntity.setTripTypeId(businessTripVM.getTripType());
        // Type of workflow. 1-Business trip
        requestsEntity.setTypeId(1);
        requestsEntity.setDateFrom(businessTripVM.getStart());
        requestsEntity.setDateTo(businessTripVM.getEnd());
        requestsEntity.setDescription(businessTripVM.getPurpose());
        // By default workflow status will be in progress = 1
        requestsEntity.setStatusId(1);
        //requestsEntity.setDateCreated(new java.sql.Date());
        requestsEntity.setDestination(businessTripVM.getDestination());

        return requestsEntity;
    }

    public static MembersEntity MembersMapper(BusinessTripVM businessTripVM, MembersEntity member, int userId) {
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

    public static ToDoEntity ToDoMapper(int id, ToDoEntity todo) {
        ToDoEntity toDoEntity = new ToDoEntity();

        toDoEntity.setRequestId(id);
        toDoEntity.setDate(todo.getDate());
        toDoEntity.setDescription(todo.getDescription());

        return toDoEntity;
    }

    public static AttachmentsEntity AttachmentsMapper(int id, MultipartFile attachment) {
        AttachmentsEntity attachmentsEntity = new AttachmentsEntity();

        attachmentsEntity.setRequestId(id);
        attachmentsEntity.setUrl("C:/files/documents/workflow/" + attachment.getOriginalFilename());
        attachmentsEntity.setFilename(attachment.getOriginalFilename());

        return attachmentsEntity;
    }
}
