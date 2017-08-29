package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.mapper.AssetMapper;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.assetManagement.service.AssetService;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
@Controller
@RequestMapping("/Asset")
public class AssetController {

    @Autowired
    AssetService service;


    /**
     * Temprory
     * @param principal
     * @param model
     * @return
     */
    @RequestMapping(value = "/jsonTable", method = RequestMethod.GET)
    public @ResponseBody
    List<AssetVM> getTable(Principal principal, Model model){

        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetList());

        return assetVMS;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex(Principal principal, Model model){
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementList");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        AssetVM assetVM = new AssetVM();
        model.addAttribute("assetVM", assetVM);
        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetList());

        mav.addObject("AssetList", assetVMS);

        return mav;
    }



}
