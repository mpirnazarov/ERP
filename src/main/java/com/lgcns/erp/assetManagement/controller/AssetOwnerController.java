package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.mapper.AssetMapper;
import com.lgcns.erp.assetManagement.model.AssetVM;
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
import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
@Controller
@RequestMapping("/Asset/Owner")
public class AssetOwnerController {

    @Autowired
    AssetService service;


    /**
     * Temprory
     * @param principal
     * @param model
     * @return
     */
    /*@RequestMapping(value = "/jsonTable", method = RequestMethod.GET)
    public @ResponseBody
    List<AssetVM> getTable(Principal principal, Model model){

        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetList());

        return assetVMS;
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(Principal principal, Model model){
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementOwner");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        return mav;
    }






}
