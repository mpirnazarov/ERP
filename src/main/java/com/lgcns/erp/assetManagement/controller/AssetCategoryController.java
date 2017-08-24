package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.mapper.AssetCategoryMapper;
import com.lgcns.erp.assetManagement.model.AssetCategoryVM;
import com.lgcns.erp.assetManagement.service.AssetCategoryService;
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
 * Created by DS on 15.08.2017.
 */
@Controller
@RequestMapping("/Asset/Category")
public class AssetCategoryController {

    @Autowired
    AssetCategoryService service;

    @RequestMapping(value = "/AssetCategoryForm", method = RequestMethod.GET)
    public ModelAndView getAssetCategoryForm(Principal principal){
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetCategoryList");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        return mav;
    }

    @RequestMapping(value = "/AssetCategoryForm", method = RequestMethod.POST)
    public String saveAssetCategoryForm(@RequestBody AssetCategoryVM assetCategory){
        service.saveAssetCategory(AssetCategoryMapper.mapAssetCategoryToEntity(assetCategory));
        return "redirect:/Asset/Category/List";
    }

    @RequestMapping(value = "/AssetCategoryUpdateForm", method = RequestMethod.POST)
    public String updateAssetCategoryForm(@ModelAttribute AssetCategoryVM assetCategory){
        service.updateAssetCategory(assetCategory);
        return "redirect:/Asset/Category/List";
    }

    @RequestMapping(value = "/AssetCategoryDelete", method = RequestMethod.DELETE)
    public String deleteAssetCategory(@ModelAttribute AssetCategoryVM assetCategory){
        service.deleteAssetCategory(assetCategory.getId());

        return "redirect:/Asset/Category/List";
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public ModelAndView getAssetCategoryList(){

        List<AssetCategoryVM> categories = AssetCategoryMapper.
                mapAssetCategoryEntityListToModelList(service.getAssetCategoryList());

        ModelAndView mav = new ModelAndView("assetManagement/assetCategoryList");
        mav.addObject("categoryList", categories);
        return mav;
    }

    @RequestMapping(value = "/JsonList", method = RequestMethod.GET)
    public @ResponseBody List<AssetCategoryVM> getAssetCategoryJsonList(){

        List<AssetCategoryVM> categories = AssetCategoryMapper.
                mapAssetCategoryEntityListToModelList(service.getAssetCategoryList());

        return categories;
    }
}
