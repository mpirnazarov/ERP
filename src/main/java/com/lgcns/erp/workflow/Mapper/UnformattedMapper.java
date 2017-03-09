package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.ViewModel.UnformattedViewModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
public class UnformattedMapper {
    public static RequestsEntity requestMapper(UnformattedViewModel unformattedVM, int idByUsername, int typeId, int statusId, boolean isViewed) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setUserFromId(idByUsername);
        requestsEntity.setSubject(unformattedVM.getSubject());
        // Type of workflow. 1-Business trip, 2-Leave approve, 3-Unformatted
        requestsEntity.setTypeId(typeId);
        requestsEntity.setTripTypeId(1);

        //if date is coming null
        if (unformattedVM.getStart().toString().equals("1111-11-11")){
            requestsEntity.setDateFrom(null);
        }else {
            requestsEntity.setDateFrom(unformattedVM.getStart());
        }

        if (unformattedVM.getEnd().toString().equals("1111-11-11")){
            requestsEntity.setDateTo(null);
        }
        else {
            requestsEntity.setDateTo(unformattedVM.getEnd());
        }

        requestsEntity.setDescription(unformattedVM.getDescription());
        // By default workflow status will be in progress = 1
        requestsEntity.setStatusId(statusId);
        requestsEntity.setDateCreated(new java.sql.Date(new java.util.Date().getTime()));
        requestsEntity.setViewed(isViewed);

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

    //Sarvaaaaar

    public static UnformattedViewModel fromUnformatted(int id){
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(id);

        UnformattedViewModel viewModel = new UnformattedViewModel();
        viewModel.setSubject(requestsEntity.getSubject());
        viewModel.setDescription(requestsEntity.getDescription());
        viewModel.setStart(requestsEntity.getDateFrom());
        viewModel.setEnd(requestsEntity.getDateTo());

        return viewModel;
    }


    public static RequestsEntity requestMapperUpdate(UnformattedViewModel unformattedVM) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setId(unformattedVM.getId());
        requestsEntity.setSubject(unformattedVM.getSubject());
        requestsEntity.setDescription(unformattedVM.getDescription());
        requestsEntity.setDateFrom(unformattedVM.getStart());
        requestsEntity.setDateTo(unformattedVM.getEnd());

        return requestsEntity;
    }
}
