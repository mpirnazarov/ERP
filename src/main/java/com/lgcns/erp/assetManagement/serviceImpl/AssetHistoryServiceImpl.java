package com.lgcns.erp.assetManagement.serviceImpl;

import com.lgcns.erp.assetManagement.DBContext.AssetHistoryContext;
import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;
import com.lgcns.erp.assetManagement.service.AssetHistoryService;

import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryServiceImpl implements AssetHistoryService {

    @Override
    public List<AssetHistoryEntity> getAssetHistoryListByID(int inventNum) {
        return AssetHistoryContext.getAssetHistoryListById(inventNum);
    }

    @Override
    public List<AssetHistoryEntity> getAssetHistoryList() {
        return AssetHistoryContext.getAssetHistoryList();
    }

    @Override
    public void insertAssetHistory(AssetHistoryEntity assetEntity) {
        AssetHistoryContext.insertAssetHistory(assetEntity);
    }


}
