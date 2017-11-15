package com.lgcns.erp.assetManagement.service;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;

import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public interface AssetHistoryService {
    List<AssetHistoryEntity> getAssetHistoryListByID(int inventNum);
    List<AssetHistoryEntity> getAssetHistoryList();
    void insertAssetHistory(AssetHistoryEntity assetEntity);
}
