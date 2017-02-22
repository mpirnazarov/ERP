package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 23.01.2017.
 */
public class ToDoMapper {
    public static List<ToDoViewModel> queryTotodoModel(List<RequestsEntity> reqs, List<UsersEntity> users, int userId){

        List<ToDoViewModel> models = new ArrayList<>();
        List<RequestsEntity> validReqs = getValidReqs(reqs, userId);

        for (RequestsEntity req : validReqs) {
            ToDoViewModel model = new ToDoViewModel();
            model.setRequest_id(req.getId());
            model.setRequest_subject(req.getSubject());
            model.setDate_created(req.getDateCreated());

            for (Type type : Type.values()) {
                if (req.getTypeId()==type.getValue())
                    model.setForm_type(type.name().replace("_"," "));
            }
            for (UsersEntity user : users) {
                if (user.getId()==req.getUserFromId()){
                    UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(user.getId(), 3);
                    model.setUser_name(userLoc.getLastName() + " " + userLoc.getFirstName());
                }
            }

            for (Status status : Status.values()) {
                if (status.getValue() == req.getStatusId())
                    model.setStatus(status.name().replace("_"," "));
            }

            models.add(model);
        }
        return models;
    }

    private static List<RequestsEntity> getValidReqs(List<RequestsEntity> requestsEntities, int userId){
        List<StepsEntity> steps = WorkflowService.getStepsList();
        List<RequestsEntity> validReqs = new ArrayList<>();
        for (StepsEntity step : steps) {
            for (RequestsEntity req : requestsEntities) {
                if (req.getId() == step.getRequestId()&&req.getStatusId()==1&&step.getUserId()==userId&&step.getInvolvementTypeId()==1&&step.getActive()){
                    validReqs.add(req);
                }
            }
        }

        return validReqs;
    }
}
