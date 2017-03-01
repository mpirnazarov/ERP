package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Enums.Type;
import com.lgcns.erp.workflow.ViewModel.RequestViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 23.01.2017.
 */
public class RequestMapper {
    public static List<RequestViewModel> queryTorequestModel(List<RequestsEntity> reqs, List<UsersEntity> users, int userId){

        List<RequestViewModel> models = new ArrayList<>();
        List<RequestsEntity> requestsEntities = getValidReqs(reqs, userId);
        for (RequestsEntity req : requestsEntities) {
            RequestViewModel model = new RequestViewModel();
            model.setRequest_id(req.getId());

            if (req.getTypeId()==Type.Leave.getValue()){
                for (LeaveType leaveType : LeaveType.values()) {
                    if (req.getLeaveTypeId()==leaveType.getValue())
                        model.setRequest_subject(leaveType.name().replace("_"," "));
                }
            }else {
                model.setRequest_subject(req.getSubject());
            }

            model.setRequest_description(req.getDescription());
            model.setDate_created(req.getDateCreated());

            for (Type type : Type.values()) {
                if (req.getTypeId()==type.getValue())
                    model.setForm_type(type.name().replace("_"," "));
            }

            for (UsersEntity user : users) {
                if (user.getId()==req.getUserFromId())
                    model.setUser_name(user.getUserName());
            }

            for (Status status : Status.values()) {
                if (status.getValue() == req.getStatusId())
                    model.setStatus(status.name().replace("_"," "));
            }

            models.add(model);
        }
        return models;
    }

    private static List<RequestsEntity> getValidReqs(List<RequestsEntity> reqs, int userId) {
        List<RequestsEntity> requestsEntities = new ArrayList<>();
        for (RequestsEntity req : reqs) {
            if (req.getUserFromId()==userId&&req.getStatusId()!=Status.Deleted.getValue()){
                requestsEntities.add(req);
            }
        }
        return requestsEntities;
    }
}
