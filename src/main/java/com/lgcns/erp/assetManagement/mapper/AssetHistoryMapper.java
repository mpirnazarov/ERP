package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;
import com.lgcns.erp.assetManagement.model.AssetHistoryVM;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.UserModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryMapper {

    public static AssetHistoryVM mapAssetHistoryEntityToModel(AssetHistoryEntity assetHistoryEntity){
        AssetHistoryVM assetHistoryVM = new AssetHistoryVM();

        assetHistoryVM.setId(assetHistoryEntity.getId());
        assetHistoryVM.setInventNumber(assetHistoryEntity.getInventNum());
        assetHistoryVM.setRegDate(assetHistoryEntity.getUpdateDate());
        assetHistoryVM.setUserOldId(mapUserModel(assetHistoryEntity.getUserIdOld(), AssetMapper.getUserFullNameById(assetHistoryEntity.getUserIdOld())));
        assetHistoryVM.setUserNewId(mapUserModel(assetHistoryEntity.getUserIdNew(), AssetMapper.getUserFullNameById(assetHistoryEntity.getUserIdNew())));

        return assetHistoryVM;
    }

    private static UserModel mapUserModel(int userId, String userFullNameById) {
        return new UserModel(userId, userFullNameById);
    }

    public static List<AssetHistoryVM> mapAssetHistoryEntityListToModel(List<AssetHistoryEntity> assetHistoryEntityList) {
        List<AssetHistoryVM> assetHistoryVMList = new LinkedList<>();
        for (AssetHistoryEntity assetHistoryEntity:
             assetHistoryEntityList) {
            assetHistoryVMList.add(mapAssetHistoryEntityToModel(assetHistoryEntity));
        }

        return assetHistoryVMList;
    }

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
