package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.mapper.AssetCategoryMapper;
import com.lgcns.erp.assetManagement.model.AssetCategoryVM;
import com.lgcns.erp.assetManagement.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public String getAssetCategoryForm(Model model){

        AssetCategoryVM category = new AssetCategoryVM();
        model.addAttribute("AssetCategory", category);
        return "assetManagement/assetCategory";
    }

    @RequestMapping(value = "/AssetCategoryForm", method = RequestMethod.POST)
    public String saveAssetCategoryForm(@ModelAttribute AssetCategoryVM assetCategory){
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
