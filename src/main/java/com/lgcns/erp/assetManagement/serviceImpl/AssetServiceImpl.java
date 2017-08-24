package com.lgcns.erp.assetManagement.serviceImpl;

import com.lgcns.erp.assetManagement.DBContext.AssetContext;
import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.service.AssetService;

import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetServiceImpl implements AssetService {
    @Override
    public List<AssetEntity> getAssetList() {
        return AssetContext.getAssetList();
    }

    @Override
    public void insertAsset(AssetEntity assetEntity) {
        AssetContext.insertAsset(assetEntity);
    }

    @Override
    public void updateAsset(AssetEntity assetEntity) {
        AssetContext.updateAsset(assetEntity);
    }

    @Override
    public void deleteAsset(int id) {
        AssetContext.deleteAsset(id);
    }
}
