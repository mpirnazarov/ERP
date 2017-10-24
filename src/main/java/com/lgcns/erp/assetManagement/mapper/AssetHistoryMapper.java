package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.model.AssetHistoryVM;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryMapper {

    /*public static AssetVM mapAssetHistoryEntityToModel(AssetHistoryEntity assetHistoryEntity){
        AssetVM assetVM = new AssetVM();
        try {
            int isPublicInt = assetEntity.getPublic()?1:0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assetVM.setId(assetEntity.getId());
        assetVM.setInventNumber(assetEntity.getInventNumber());
        *//*assetVM.setCheckPublic(isPublicInt);*//*
        assetVM.setCheckPublic(assetEntity.getPublic());
        assetVM.setRegDate(assetEntity.getRegDate());
        assetVM.setCategoryId(assetEntity.getCategoryId());
        assetVM.setUserId(assetEntity.getUserId());
        assetVM.setCategoryName(assetEntity.getAssetCategoryByCategoryId().getAssetItemName());
        assetVM.setUserNameSurname(UserService.getUserFullNameInLanguageById(assetEntity.getUserId(), 3));

        return assetVM;
    }*/

    /*public static List<AssetVM> mapAssetEntitiesToModels(List<AssetEntity> assetEntities){
        List<AssetVM> assetVMList = new ArrayList<>();

        for (AssetEntity assetEntity : assetEntities) {
            assetVMList.add(mapAssetEntityToModel(assetEntity));
        }

        return assetVMList;
    }*/

    /*public static AssetHistoryEntity mapAssetVMToEntity(AssetHistoryVM assetVM){
        AssetHistoryEntity assetHistoryEntity = new AssetHistoryEntity();

        assetHistoryEntity.setAssetInventNumber(assetVM.getInventNumber());
        assetHistoryEntity.setUserIdNew(assetVM.getUserToId());
        assetHistoryEntity.setUserIdOld(assetVM.getUserFromId());
        assetHistoryEntity.setRegDate(assetVM.getRegDate());

        return assetHistoryEntity;
    }*/

}
