package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBContext.AssetContext;
import com.lgcns.erp.assetManagement.DBContext.AssetHistoryContext;
import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.assetManagement.service.AssetHistoryService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetMapper{

    @Autowired
    static
    AssetHistoryService assetHistoryService;

    public static List<AssetVM> mapAssetEntitiesToModels(List<AssetEntity> assetEntities, List<UserLocalizationsEntity> allUserLocs){
        List<AssetVM> assetVMList = new ArrayList<>();

        for (AssetEntity assetEntity : assetEntities) {
            assetVMList.add(mapAssetEntityToModelFromAll(assetEntity, allUserLocs));
        }

        return assetVMList;
    }

    private static AssetVM mapAssetEntityToModelFromAll(AssetEntity assetEntity, List<UserLocalizationsEntity> allUserLocs) {
        AssetVM assetVM = new AssetVM();

        assetVM.setId(assetEntity.getId());
        assetVM.setInventNum(assetEntity.getInventNum());
        assetVM.setNameEn(assetEntity.getNameEn());
        assetVM.setNameRu(assetEntity.getNameRu());
        assetVM.setOwnerId(assetEntity.getOwnerId());
        assetVM.setRegDate(assetEntity.getRegDate());
        assetVM.setRegInfo(assetEntity.getRegInfo());
        assetVM.setOwnerFullName(getUserFullNameByIdAll(assetEntity.getOwnerId(), allUserLocs));
        // Check if cost information should be visible
        assetVM.setCost(assetEntity.getCost());

        assetVM.setLocation(assetEntity.getLocation());
        assetVM.setCheckPublic(assetEntity.isCheckPublic());
        assetVM.setCheckEnabled(assetEntity.isCheckEnabled());
        assetVM.setCheckDeleted(assetEntity.isCheckDeleted());

        return assetVM;
    }

    public static AssetVM mapAssetEntityToModel(AssetEntity assetEntity){
        AssetVM assetVM = new AssetVM();

        assetVM.setId(assetEntity.getId());
        assetVM.setInventNum(assetEntity.getInventNum());
        assetVM.setNameEn(assetEntity.getNameEn());
        assetVM.setNameRu(assetEntity.getNameRu());
        assetVM.setOwnerId(assetEntity.getOwnerId());
        assetVM.setRegDate(assetEntity.getRegDate());
        assetVM.setRegInfo(assetEntity.getRegInfo());
        assetVM.setOwnerFullName(getUserFullNameById(assetEntity.getOwnerId()));
        // Check if cost information should be visible
        assetVM.setCost(assetEntity.getCost());

        assetVM.setLocation(assetEntity.getLocation());
        assetVM.setCheckPublic(assetEntity.isCheckPublic());
        assetVM.setCheckEnabled(assetEntity.isCheckEnabled());
        assetVM.setCheckDeleted(assetEntity.isCheckDeleted());
        List<AssetHistoryEntity> assetHistoryEntityList=null;
        try{
            assetHistoryEntityList = AssetHistoryContext.getAssetHistoryListById(assetEntity.getInventNum());
        }catch (Exception e){
            e.printStackTrace();
        }
        assetVM.setAssetHistoryVMList(AssetHistoryMapper.mapAssetHistoryEntityListToModel(assetHistoryEntityList));
        // Sarvars request


        return assetVM;
    }

    public static String getUserFullNameById(int ownerId) {
        UserLocalizationsEntity userLoc = null;
        try{
            userLoc = UserService.getUserLocByUserId(ownerId, 3);
            return userLoc.getFirstName() + " " + userLoc.getLastName();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    private static String getUserFullNameByIdAll(int ownerId, List<UserLocalizationsEntity> allUserLocs) {

        for (UserLocalizationsEntity userLoc :
                allUserLocs) {
            if(ownerId == userLoc.getUserId())
                return userLoc.getFirstName() + " " + userLoc.getLastName();
        }


        return null;
    }



    /*public static AssetEntity mapAssetVMToEntity(AssetVM assetVM){
        AssetEntity assetEntity = new AssetEntity();

        assetEntity.setId(assetVM.getId());
        assetEntity.setInventNumber(assetVM.getInventNumber());
        *//*assetEntity.setPublic(assetVM.isCheckPublic()==1?true:false);*//*
        try {
            if(assetVM.getCheckPublic() == null || assetVM.getCheckPublic() == true)
                assetEntity.setPublic(true);
            else
                assetEntity.setPublic(false);
        }catch (Exception e){
            e.printStackTrace();
        }

        assetEntity.setRegDate(getCurrentDate());
        assetEntity.setCategoryId(assetVM.getCategoryId());
        assetEntity.setUserId(assetVM.getUserId());
        assetEntity.setEnabled(Boolean.TRUE);
        return assetEntity;
    }

    public static Date getCurrentDate(){
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        return currentDate;
    }*/

}
