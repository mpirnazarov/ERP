package com.lgcns.erp.assetManagement.serviceImpl;

import com.lgcns.erp.assetManagement.DBContext.AssetCategoryContext;
import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.service.AssetCategoryService;

import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */
public class AssetCategoryServiceImpl implements AssetCategoryService {
    @Override
    public List<AssetCategoryEntity> getAssetCategoryList() {
        return AssetCategoryContext.getAssetCategoryList();
    }
}
