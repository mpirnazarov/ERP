package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
public class UnformattedMapper {
    public static RequestsEntity requestMapper(UnformattedViewModel unformattedVM, int idByUsername, int typeId, int statusId) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setUserFromId(idByUsername);
        requestsEntity.setSubject(unformattedVM.getSubject());
        // Type of workflow. 1-Business trip
        requestsEntity.setTypeId(typeId);
        requestsEntity.setDateFrom(unformattedVM.getStart());
        requestsEntity.setDateTo(unformattedVM.getEnd());
        requestsEntity.setDescription(unformattedVM.getDescription());
        // By default workflow status will be in progress = 1
        requestsEntity.setStatusId(statusId);
        requestsEntity.setDateCreated(new java.sql.Date(new java.util.Date().getTime()));

        return requestsEntity;
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
