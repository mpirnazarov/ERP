package com.lgcns.erp.tapps.mapper;

import com.lgcns.erp.tapps.model.DbEntities.FamiliyInfoLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.FamilyInfosEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.RegistrationLocInfo;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.FamilyMember;
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
        user.seteMail(model.getCompanyEmail());
        user.setUserName(model.getUserName());
        user.setStatusId(model.getStatusId());
        user.setPasswordHash(passwordEncoder.encode(model.getPassword()));
        user.setHiringDate(new Date(model.getHiringDate().getTime()));
        user.setChiefId(model.getChiefId());
        user.setPersonalEmail(model.getEmail());
        user.setInPoliticalParty(model.isIsInPoliticalParty());
        user.setPassport(model.getPassportNumber());
        user.setRoleId(model.getRoleId());
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

    public static FamilyInfosEntity mapAddFamily(FamilyMember familyProfile, String userId) {
        FamilyInfosEntity familyInfosEntity = new FamilyInfosEntity();
        familyInfosEntity.setDateOfBirth(familyProfile.getDateOfBirth());
        familyInfosEntity.setUserId(Integer.parseInt(userId));
        System.out.println("USER ID MAPPER: " + familyInfosEntity.getUserId());
        return familyInfosEntity;
    }

    public static FamiliyInfoLocalizationsEntity mapAddFamilyLoc(FamilyMember familyProfile, int id, int langId) {
        FamiliyInfoLocalizationsEntity familiyInfoLocalizationsEntity = new FamiliyInfoLocalizationsEntity();
        familiyInfoLocalizationsEntity.setFirstName(familyProfile.getFirstName()[langId-1]);
        familiyInfoLocalizationsEntity.setLastName(familyProfile.getLastName()[langId-1]);
        familiyInfoLocalizationsEntity.setJobTitle(familyProfile.getJobTitle()[langId-1]);
        familiyInfoLocalizationsEntity.setRelation(familyProfile.getRelation()[langId-1]);
        familiyInfoLocalizationsEntity.setFamilyInfoid(id);
        familiyInfoLocalizationsEntity.setLanguageId(langId);
        System.out.println("MAPPER: "+id + " "+langId);
        return familiyInfoLocalizationsEntity;
    }
}
