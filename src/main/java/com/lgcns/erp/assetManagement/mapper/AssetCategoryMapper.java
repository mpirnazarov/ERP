package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.model.AssetCategoryVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */
public class AssetCategoryMapper {
    public static List<AssetCategoryVM> mapAssetCategoryEntityListToModelList(List<AssetCategoryEntity> assetCategoryEntities){

        List<AssetCategoryVM> categories = new ArrayList<>();
        for (AssetCategoryEntity assetCategoryEntity : assetCategoryEntities) {

            categories.add(mapAssetCategoryEntityToModel(assetCategoryEntity));

        }

        return categories;
    }

    public static AssetCategoryVM mapAssetCategoryEntityToModel(AssetCategoryEntity assetCategoryEntity){
        AssetCategoryVM category = new AssetCategoryVM();
        category.setAssetCategoryName(assetCategoryEntity.getAssetItemName());
        category.setId(assetCategoryEntity.getId());

        return category;
    }

    public static AssetCategoryEntity mapAssetCategoryToEntity(AssetCategoryVM category){
        AssetCategoryEntity categoryEntity = new AssetCategoryEntity();
        categoryEntity.setAssetItemName(category.getAssetCategoryName());

        return categoryEntity;
    }

}
