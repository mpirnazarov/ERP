package com.lgcns.erp.assetManagement.service;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;

import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public interface AssetService {

    List<AssetEntity> getAssetList();
    void updateAsset(AssetEntity assetEntity);
    void insertAsset(AssetEntity assetEntity);
    void deleteAsset(int inventNum, boolean status);
    /*
    List<AssetEntity> getAssetListByUserID(int userId);

    void updateAssetOwner(int assetId, int userTo);*/
}
