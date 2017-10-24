package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.tapps.DbContext.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetMapper{

    public static List<AssetVM> mapAssetEntitiesToModels(List<AssetEntity> assetEntities){
        List<AssetVM> assetVMList = new ArrayList<>();

        for (AssetEntity assetEntity : assetEntities) {
            assetVMList.add(mapAssetEntityToModel(assetEntity));
        }

        return assetVMList;
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
        assetVM.setOwnerFullName(UserService.getUserFullNameInLanguageById(assetEntity.getOwnerId(), 3));
        // Check if cost information should be visible
        assetVM.setCost(assetEntity.getCost());

        assetVM.setLocation(assetEntity.getLocation());
        assetVM.setCheckPublic(assetEntity.isCheckPublic());
        assetVM.setCheckEnabled(assetEntity.isCheckEnabled());
        assetVM.setCheckDeleted(assetEntity.isCheckDeleted());

        return assetVM;
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
