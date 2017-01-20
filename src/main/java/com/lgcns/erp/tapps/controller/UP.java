package com.lgcns.erp.tapps.controller;


import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static com.lgcns.erp.tapps.controller.UserController.getMax;

/**
 * Created by Rafatdin on 05.12.2016.
 */
public class UP {
    public static ProfileViewModel userProfile;
    public static ModelAndView includeUserProfile(ModelAndView mav, Principal principal){
        if(userProfile!=null){
            if(userProfile.getUsername().equals(principal.getName())){
                mav.addObject("userProfile", userProfile);
                return mav;
            }
            else{
                getUserProfileInfo(principal.getName());
                mav.addObject("userProfile", userProfile);
                return mav;
            }
        }
        else{
            getUserProfileInfo(principal.getName());
            mav.addObject("userProfile", userProfile);
            return mav;
        }
    }
    private static void getUserProfileInfo(String username){
        UsersEntity user = UserService.getUserByUsername(username);
        String[] firstNames = new String [1], lastNames = new String [1], addresses = new String [1];
        for (UserLocalizationsEntity locInfo : UserService.getUserLocByUserId(user.getId())){
            if(locInfo.getLanguageId() == Language.eng.getCode()){
                firstNames[0] = locInfo.getFirstName();
                lastNames[0] = locInfo.getLastName();
                addresses[0] = locInfo.getAddress();
            }
        }
        userProfile = new ProfileViewModel();
        userProfile.setId(String.valueOf(String.format("%05d", user.getId())));
        userProfile.setUsername(user.getUserName());
        userProfile.setFirstName(firstNames);
        userProfile.setLastName(lastNames);
        userProfile.setAddress(addresses);
        userProfile.setEnabled(true);
        userProfile.setEntryDate(user.getHiringDate());
        userProfile.setPolitical(user.isInPoliticalParty());
        userProfile.setPosition(UserService.getRoleLoc(user).getName());

        /*if(UserService.getDepartments().get(3).getName()!=null)
            userProfile.setDepartment(UserService.getDepartments().get(3).getName());*/
        userProfile.setRoleId(user.getRoleId());
        int postId = getMax(UserService.getUserInPost(user)).getPostId();
        if(UserService.getJobTitle(postId, Language.eng.getCode())!=null)
            userProfile.setJobTitle(UserService.getJobTitle(postId, 3).getName());
        int externalId = getMax(UserService.getUserInPost(user)).getExternalId();
        if(externalId >= 1) {
            userProfile.setExternal(UserService.getExternalLoc(externalId));
            userProfile.setExternalId(externalId);
        }
    }
}
