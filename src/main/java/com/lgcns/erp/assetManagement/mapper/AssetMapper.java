package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.tapps.DbContext.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetMapper{

    public static AssetVM mapAssetEntityToModel(AssetEntity assetEntity){
        AssetVM assetVM = new AssetVM();

        assetVM.setId(assetEntity.getId());
        assetVM.setInventNumber(assetEntity.getInventNumber());
        assetVM.setCheckPublic(assetEntity.getPublic());
        assetVM.setRegDate(assetEntity.getRegDate());
        assetVM.setCategoryId(assetEntity.getCategoryId());
        assetVM.setUserId(assetEntity.getUserId());
        assetVM.setCategoryName(assetEntity.getAssetCategoryByCategoryId().getAssetItemName());
        assetVM.setUserNameSurname(UserService.getUserFullNameInLanguageById(assetEntity.getUserId(), 3));

        return assetVM;
    }

    public static List<AssetVM> mapAssetEntitiesToModels(List<AssetEntity> assetEntities){
        List<AssetVM> assetVMList = new ArrayList<>();

        for (AssetEntity assetEntity : assetEntities) {
            assetVMList.add(mapAssetEntityToModel(assetEntity));
        }

        return assetVMList;
    }

    public static AssetEntity mapAssetVMToEntity(AssetVM assetVM){
        AssetEntity assetEntity = new AssetEntity();

        assetEntity.setId(assetVM.getId());
        assetEntity.setInventNumber(assetVM.getInventNumber());
        /*assetEntity.setPublic(assetVM.isCheckPublic()==1?true:false);*/
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

        return assetEntity;
    }

    public static Date getCurrentDate(){
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        return currentDate;
    }

}
