package com.lgcns.erp.scheduleManagement.mapper;

import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.viewModel.ReferenceVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.workflow.Mapper.BusinessTripMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class ReferenceMapper {
    public static List<ReferenceVM> mapReferenceListToVMList(List<ReferenceInCheduleEntity> entities){

        System.out.println(entities);
        if (entities.isEmpty())
            return null;

        List<ReferenceVM> referenceVMList = new ArrayList<>();
        ReferenceVM referenceVM = null;

        for (ReferenceInCheduleEntity entity : entities) {
            referenceVM = new ReferenceVM();

            UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(entity.getUserId(), 3);

            referenceVM.setName(userLoc.getFirstName());
            referenceVM.setSurname(userLoc.getLastName());
            referenceVM.setDepartment(BusinessTripMapper.getDepartmentNameByUserId(entity.getUserId()));
            /*referenceVM.setJobTitle(UserController.getProfileByUsername(UserService.getUsernameById(entity.getUserId())).getJobTitle());*/
            referenceVM.setJobTitle(UserService.getUserJobTitle(entity.getUserId()));
            referenceVM.setUserId(entity.getUserId());

            referenceVMList.add(referenceVM);
        }

        return referenceVMList;
    }
}
