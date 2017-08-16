package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.mapper.AssetCategoryMapper;
import com.lgcns.erp.assetManagement.model.AssetCategory;
import com.lgcns.erp.assetManagement.service.AssetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */
@Controller
@RequestMapping("/Asset/Category")
public class AssetCategoryController {

    @Autowired
    AssetCategoryService service;

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public @ResponseBody
    List<AssetCategory> getAssetCategoryList(){

        List<AssetCategory> categories = AssetCategoryMapper.
                mapAssetCategoryEntityListToModelList(service.getAssetCategoryList());

        return categories;
    }



}
