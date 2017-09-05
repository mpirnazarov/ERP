package com.lgcns.erp.assetManagement.service;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;

import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public interface AssetService {
    List<AssetEntity> getAssetList();
    List<AssetEntity> getAssetListByUserID(int userId);
    void insertAsset(AssetEntity assetEntity);
    void updateAsset(AssetEntity assetEntity);
    void deleteAsset(int id);
}
