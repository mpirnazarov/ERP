package com.lgcns.erp.scheduleManagement.mapper;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.viewModel.ParticipantVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class PartircipantMapper {
    public static List<ParticipantVM> mapParticipantEntityListToVMList(List<ParticipantInScheduleEntity> entities){
        if (entities.isEmpty())
            return null;

        List<ParticipantVM> participantVMList = new ArrayList<>();
        ParticipantVM participantVM = null;

        for (ParticipantInScheduleEntity entity : entities) {
            participantVM = new ParticipantVM();

            UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(entity.getUserId(), 3);

            participantVM.setName(userLoc.getFirstName());
            participantVM.setSurname(userLoc.getLastName());
            participantVM.setDepartmentName(BusinessTripMapper.getDepartmentNameByUserId(entity.getUserId()));
            participantVM.setJobTitle(UserService.getUserJobTitle(entity.getUserId()));
            participantVM.setReason(entity.getReason());
            participantVM.setStatus(entity.getStatus());
            participantVM.setUserId(entity.getUserId());

            participantVMList.add(participantVM);
        }

        return participantVMList;

    }
}
