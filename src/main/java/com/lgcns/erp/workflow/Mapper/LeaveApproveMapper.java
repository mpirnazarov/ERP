package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.ViewModel.LeaveApproveVM;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
public class LeaveApproveMapper {
    public static RequestsEntity requestMapper(LeaveApproveVM leaveApproveVM, int idByUsername, int typeId, int statusId) {
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setUserFromId(idByUsername);
        requestsEntity.setTripTypeId(leaveApproveVM.getAbsenceType());
        // Type of workflow. 1-Business trip
        requestsEntity.setTypeId(typeId);
        requestsEntity.setDateFrom(leaveApproveVM.getStart());
        requestsEntity.setDateTo(leaveApproveVM.getEnd());
        requestsEntity.setDescription(leaveApproveVM.getDescription());
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

    public static LeaveApproveVM fromLeaveApprove(int id){
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(id);

        LeaveApproveVM viewModel = new LeaveApproveVM();

        viewModel.setStart(requestsEntity.getDateFrom());
        viewModel.setEnd(requestsEntity.getDateTo());
        viewModel.setAbsenceType(requestsEntity.getLeaveTypeId());
        viewModel.setDescription(requestsEntity.getDescription());

        return viewModel;
    }
}
