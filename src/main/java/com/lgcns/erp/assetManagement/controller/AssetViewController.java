package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;
import com.lgcns.erp.assetManagement.mapper.AssetMapper;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.assetManagement.service.AssetHistoryService;
import com.lgcns.erp.assetManagement.service.AssetService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by Muslimbek on 6/23/2017.
 */
@RequestMapping("/AssetManagement")
@Controller
public class AssetViewController {

    @Autowired
    AssetService assetService;

    @Autowired
    AssetHistoryService assetHistoryService;

    @RequestMapping(value = "/GetAssetByIDJson", method = RequestMethod.GET)
    public @ResponseBody
    AssetVM GetAssetByIDJson(Principal principal, @RequestParam("inventNum") int inventNum){

        AssetVM assetVM = null;

        try {
            AssetEntity assetEntity = assetService.getAssetByID(inventNum);
            /*List<AssetHistoryEntity> assetHistoryEntityList = assetHistoryService.getAssetHistoryListByID(inventNum);
            List<UserLocalizationsEntity> userLocalizationsEntityList = UserService.getAllUserLocs(3);*/
            assetVM = AssetMapper.mapAssetEntityToModel(assetEntity);
        }catch (Exception e){
            e.printStackTrace();
        }

        return assetVM;
    }
}
