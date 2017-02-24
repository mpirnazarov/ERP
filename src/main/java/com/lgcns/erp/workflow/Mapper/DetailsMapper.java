package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.Model.Attachment;
import com.lgcns.erp.workflow.ViewModel.DetailsViewModel;
import com.lgcns.erp.workflow.ViewModel.TerminationViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 01.02.2017.
 */
public class DetailsMapper {
    public static DetailsViewModel toDetails(int id){

        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(id);
        DetailsViewModel viewModel = new DetailsViewModel();

        viewModel.setDate_created(requestsEntity.getDateCreated());
        viewModel.setRequest_id(requestsEntity.getId());
        viewModel.setRequest_subject(requestsEntity.getSubject());
        viewModel.setForm_type_id(requestsEntity.getTypeId());
        viewModel.setUser_id(requestsEntity.getUserFromId());
        viewModel.setApprovals(getInvolvedUsers(requestsEntity, 1));
        viewModel.setReferences(getInvolvedUsers(requestsEntity, 3));
        viewModel.setExecutives(getInvolvedUsers(requestsEntity, 2));
        viewModel.setAttachments(attachments(requestsEntity));
        viewModel.setComments(CommentMapper.MapToStepComment(requestsEntity));

        for (Type type : Type.values()) {
            if (requestsEntity.getTypeId()==type.getValue())
                viewModel.setForm_type(type.name().replace("_"," "));
        }

        for (UsersEntity user : UserService.getAllUsers()) {
            if (user.getId()==requestsEntity.getUserFromId()){
                UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                viewModel.setUser_name(userLoc.getLastName() + " " + userLoc.getFirstName());
            }
        }

        for (Status status : Status.values()) {
            if (status.getValue() == requestsEntity.getStatusId())
                viewModel.setStatus(status.name().replace("_", " "));
        }

        return viewModel;
    }

    private static List<Attachment> attachments(RequestsEntity requestsEntity){
        Attachment attachment;
        List<Attachment> attachs = new ArrayList<>();

        for (AttachmentsEntity entity : WorkflowService.getAttachmentList()) {
            if (entity.getRequestId()==requestsEntity.getId()){
                attachment = new Attachment();
                attachment.setId(entity.getId());
                attachment.setUrl(entity.getUrl());
                attachment.setFileName(entity.getFilename());
                attachs.add(attachment);
            }
        }

        if (!attachs.isEmpty()){
            return attachs;
        }
        else {
            attachment = new Attachment();
            attachment.setId(0);
            attachment.setFileName("No attachment");
            attachment.setUrl("");
            attachs.add(attachment);
        }

        return attachs;
    }

    public static String getInvolvedUsers(RequestsEntity entity, int involvementType){
        List<StepsEntity> listOfApprovals = new ArrayList<>();
        List<StepsEntity> stepsEntities = WorkflowService.getStepsList();
        List<UserLocalizationsEntity> userlocs = new ArrayList<>();
        String listApprovals = "";

        for (StepsEntity stepsEntity : stepsEntities) {
            if (stepsEntity.getRequestId()==entity.getId()&&stepsEntity.getInvolvementTypeId()==involvementType){
                listOfApprovals.add(stepsEntity);
            }
        }

            for (StepsEntity listOfApproval : listOfApprovals) {
                for (UsersEntity usersEntity : UserService.getAllUsers()) {
                    if (usersEntity.getId()==listOfApproval.getUserId()){
                        UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(usersEntity.getId(), 3);
                        userlocs.add(userLoc);
                    }
                }
            }



        if (userlocs.size()!=0){
            for (UserLocalizationsEntity userloc : userlocs) {
                listApprovals+=userloc.getFirstName()+" "+userloc.getLastName()+", ";
            }

            return listApprovals.substring(0, listApprovals.length()-2);
        }else {
            String msg = "";
            if (involvementType==1)
                msg = "No Approval";
            else if (involvementType==2)
                msg = "No Executive";
            else {
                msg = "No Reference";
            }
            return msg;
        }
    }

    public static TerminationViewModel getTerminationViewModel(int id){
         RequestsEntity entity = WorkflowService.getRequestsEntityById(id);
        TerminationViewModel viewModel = new TerminationViewModel();
        viewModel.setSubject(entity.getSubject());
        viewModel.setOld_req_id(entity.getId());
        viewModel.setApproves(getInvolvedUsers(entity, 1));
        viewModel.setExecutives(getInvolvedUsers(entity, 2));
        viewModel.setReferences(getInvolvedUsers(entity, 3));
        if (entity.getTypeId()==Type.Termination.getValue())
            viewModel.setDescription(entity.getDescription());
        else
            viewModel.setDescription("");

         return viewModel;
    }



    public static RequestsEntity getRequestFromViewModel(TerminationViewModel viewModel, RequestsEntity old_requestsEntity){
        RequestsEntity requestsEntity = new RequestsEntity();

        requestsEntity.setSubject(viewModel.getSubject());
        requestsEntity.setDescription(viewModel.getDescription());
        requestsEntity.setDateCreated(new java.sql.Date(new java.util.Date().getTime()));
        requestsEntity.setUserFromId(old_requestsEntity.getUserFromId());
        requestsEntity.setReqLinkId(old_requestsEntity.getId());
        requestsEntity.setTypeId(4);
        requestsEntity.setStatusId(1);
        requestsEntity.setViewed(false);
        requestsEntity.setDomestic(true);
        requestsEntity.setTripTypeId(old_requestsEntity.getTripTypeId());
        requestsEntity.setDestination(old_requestsEntity.getDestination());

        return requestsEntity;
    }
}
