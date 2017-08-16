package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DS on 16.08.2017.
 */
@Controller
@RequestMapping("/Asset")
public class AssetController {

    @Autowired
    AssetService service;


}
