package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.model.AssetCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */
public class AssetCategoryMapper {
    public static List<AssetCategory> mapAssetCategoryEntityListToModelList(List<AssetCategoryEntity> assetCategoryEntities){

        List<AssetCategory> categories = new ArrayList<>();
        for (AssetCategoryEntity assetCategoryEntity : assetCategoryEntities) {

            categories.add(mapAssetCategoryEntityToModel(assetCategoryEntity));

        }

        return categories;
    }

    public static AssetCategory mapAssetCategoryEntityToModel(AssetCategoryEntity assetCategoryEntity){
        AssetCategory category = new AssetCategory();
        category.setAssetItemName(assetCategoryEntity.getAssetItemName());

        return category;
    }

}
