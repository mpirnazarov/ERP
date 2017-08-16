package com.lgcns.erp.assetManagement.service;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.model.AssetCategoryVM;

import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */

public interface AssetCategoryService {
    List<AssetCategoryEntity> getAssetCategoryList();
    void saveAssetCategory(AssetCategoryEntity categoryEntity);
    void updateAssetCategory(AssetCategoryVM category);
    void deleteAssetCategory(int id);
}
