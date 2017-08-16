package com.lgcns.erp.assetManagement.service;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */

public interface AssetCategoryService {
    List<AssetCategoryEntity> getAssetCategoryList();
}
