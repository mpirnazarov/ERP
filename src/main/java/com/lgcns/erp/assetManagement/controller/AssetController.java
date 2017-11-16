package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.mapper.AssetMapper;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.assetManagement.service.AssetHistoryService;
import com.lgcns.erp.assetManagement.service.AssetService;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
@Controller
@RequestMapping("/AssetManagement")
public class AssetController {

    @Autowired
    AssetService service;

    @Autowired
    AssetHistoryService assetHistoryService;

    private String fileLocation;

    /**
     * @param principal
     * @param model
     * @return ModelAndView
     * @detail Opens main page of assets with assets list
     * @author Muslimbek Pirnazarov
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView getIndex(Principal principal, Model model) {
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementMain");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("userRoleId", UserService.getUserByUsername(principal.getName()).getRoleId());
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        /*AssetVM assetVM = new AssetVM();
        model.addAttribute("assetVM", assetVM);
        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetList());

        mav.addObject("AssetList", assetVMS);*/

        return mav;
    }

    /**
     * @param principal
     * @param model
     * @return
     * @detail Returns all assets in JSON format
     * @author Muslimbek Pirnazarov
     */
    @RequestMapping(value = "/AllAssetJSON", method = RequestMethod.GET)
    public @ResponseBody
    List<AssetVM> getTable(Principal principal, Model model) {

        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetList(), UserService.getAllUserLocs(3));

        return assetVMS;
    }

    /*@RequestMapping(value = "/testSubmit", method = RequestMethod.POST)
    public String controllerMethod(@RequestParam(value="myArray[]") List<Object> myArray){

        String someSt = "sdfasdf";
        return "Good";
    }*/



    /*@RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String controllerMethod(@RequestParam(value="myArray[]") Integer[] myArray){

    }*/

/*
    *

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex(Principal principal, Model model){
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementList");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("userRoleId", UserService.getUserByUsername(principal.getName()).getRoleId());
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        AssetVM assetVM = new AssetVM();
        model.addAttribute("assetVM", assetVM);
        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetList());

        mav.addObject("AssetList", assetVMS);

        return mav;
    }

    @RequestMapping(value = "/MyAsset", method = RequestMethod.GET)
    public ModelAndView getMyAsset(Principal principal, Model model){
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementMyAsset");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        *//*mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));*//*
        mav.addObject("userId", userId);

        List<AssetVM> assetVMS = AssetMapper.mapAssetEntitiesToModels(
                service.getAssetListByUserID(userId));

        mav.addObject("myAssets", assetVMS);

        return mav;
    }



    @RequestMapping(value = "/index", method = RequestMethod.POST, params = "submit")
    public String submitAsset(@ModelAttribute AssetVM assetVM){
        service.insertAsset(AssetMapper.mapAssetVMToEntity(assetVM));

        return "redirect:/Asset/index";
    }



    @RequestMapping(value = "/index", method = RequestMethod.POST, params = "update")
    public String updateAsset(@ModelAttribute AssetVM assetVM){
        service.updateAsset(AssetMapper.mapAssetVMToEntity(assetVM));

        return "redirect:/Asset/index";
    }
*/


}
