package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.mapper.AssetHistoryMapper;
import com.lgcns.erp.assetManagement.mapper.AssetMapper;
import com.lgcns.erp.assetManagement.model.AssetHistoryVM;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.assetManagement.service.AssetHistoryService;
import com.lgcns.erp.assetManagement.service.AssetService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
@Controller
@RequestMapping("/Asset/Owner")
public class AssetOwnerController {

    @Autowired
    AssetService service;

    @Autowired
    AssetHistoryService assetHistoryService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getIndex(Principal principal, Model model){
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementOwner");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        return mav;
    }

    @RequestMapping(value = "/getUserAssets", method = RequestMethod.POST)
    public @ResponseBody List<AssetVM> getUserAssets(Principal principal, Model model, @RequestParam("userId") int userId){
        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(service.getAssetListByUserID(userId));
        return assetVMS;
    }

    @RequestMapping(value = "/changeAssetOwner", method = RequestMethod.POST)
    public int changeAssetOwner(Principal principal, Model model, @RequestParam("userFrom") int userFrom, @RequestParam("userTo") int userTo, @RequestParam("assetNumber") int[] assetNumbers ) {

        AssetHistoryVM assetHistoryVM = new AssetHistoryVM();
        if(assetNumbers[0] == 0){

            for (AssetEntity assetNumber :
                    service.getAssetListByUserID(userFrom)) {
                assetHistoryVM.setInventNumber(assetNumber.getId());
                assetHistoryVM.setUserFromId(userFrom);
                assetHistoryVM.setUserToId(userTo);
                assetHistoryVM.setRegDate(AssetMapper.getCurrentDate());

                try {
                    assetHistoryService.insertAssetHistory(AssetHistoryMapper.mapAssetVMToEntity(assetHistoryVM));
                    service.updateAssetOwner(assetNumber.getId(), userTo);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            return 1;

        }else {

            for (int assetNumber :
                    assetNumbers) {
                assetHistoryVM.setInventNumber(assetNumber);
                assetHistoryVM.setUserFromId(userFrom);
                assetHistoryVM.setUserToId(userTo);
                assetHistoryVM.setRegDate(AssetMapper.getCurrentDate());

                try {
                    assetHistoryService.insertAssetHistory(AssetHistoryMapper.mapAssetVMToEntity(assetHistoryVM));
                    service.updateAssetOwner(assetNumber, userTo);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }
        return 1;
    }

}
