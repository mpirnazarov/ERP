package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Model.Approver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 15.02.2017.
 */
public class ProgressMapper {
    public static List<Approver> getApprovers(List<StepsEntity> entities){
        List<Approver> approvers = new ArrayList<>();
        Approver approver;

        for (StepsEntity entity : entities) {
            approver = new Approver();
            UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(entity.getUserId(), 3);

            approver.setName(userLoc.getFirstName());
            approver.setSurname(userLoc.getLastName());
            approver.setId(entity.getId());
            approver.setJobTitle("CTO");
            approver.setActive(entity.getActive());

            approvers.add(approver);
        }
        return approvers;
    }
}
