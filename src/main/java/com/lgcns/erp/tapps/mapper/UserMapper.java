package com.lgcns.erp.tapps.mapper;

import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.RegistrationLocInfo;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafatdin on 27.10.2016.
 */
public class UserMapper {
    public static UsersEntity mapRegModelToUserInfo(RegistrationViewModel model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //for hashing

        UsersEntity user = new UsersEntity();
        user.setDateOfBirth(new Date(model.getDateOfBirth().getTime()));
        user.setDepartmentId(model.getDepartmentId());
        user.setMobilePhone(model.getMobilePhone());
        user.setHomePhone(model.getHomePhone());
        user.seteMail(model.getCompanyEmail().toString());
        user.setUserName(model.getUserName());
        user.setStatusId(model.getStatusId());
        user.setPasswordHash(passwordEncoder.encode(model.getPassword()));
        user.setHiringDate(new Date(model.getHiringDate().getTime()));
        user.setChiefId(model.getChiefId());
        user.setPersonalEmail(model.getEmail().toString());
        user.setInPoliticalParty(model.isIsInPoliticalParty());
        user.setPassport(model.getPassportNumber());
        user.setEnabled(true);

        return user;
    }

    public static List<UserLocalizationsEntity> mapRegModelToUserLocInfo(RegistrationViewModel model, int userId)
    {
        List<UserLocalizationsEntity> returning = new ArrayList<UserLocalizationsEntity>();
        UserLocalizationsEntity locEntity;
        for(RegistrationLocInfo locInfo : model.getRegistrationLocInfos()){
            locEntity = new UserLocalizationsEntity();
            locEntity.setAddress(locInfo.getAddress());
            locEntity.setFirstName(locInfo.getFirstName());
            locEntity.setLastName(locInfo.getLastName());
            locEntity.setFatherName(locInfo.getFathersName());
            locEntity.setLanguageId(locInfo.getLanguageId());
            locEntity.setUserId(userId);

            returning.add(locEntity);
        }
        return returning;
    }
}