package com.lgcns.erp.assetManagement.service;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;

import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public interface AssetHistoryService {
    void insertAssetHistory(AssetHistoryEntity assetEntity);
}
