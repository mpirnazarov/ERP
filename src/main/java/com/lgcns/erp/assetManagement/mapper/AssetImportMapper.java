package com.lgcns.erp.assetManagement.mapper;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.tapps.model.UserModel;
import org.apache.poi.ss.usermodel.Row;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssetImportMapper {

    /**
     * @description Converts excel row to assetVM with translate
     * @param row
     * @param userModel
     * @return AssetVM
     * @author Muslimbek Pirnazarov
     */
    public static AssetVM mapExcelRowToAssetVM(Row row, UserModel userModel) throws ParseException {
        AssetVM assetVM = new AssetVM();

        assetVM.setInventNum((int) row.getCell(2).getNumericCellValue());

        // Translate russian to English here
        assetVM.setNameRu(row.getCell(1).getStringCellValue());
        /* Not done yet for the reason that translator is NOT FREE */
        //assetVM.setNameEn(languageTranslateRuToEng(row.getCell(1).getStringCellValue()));
        assetVM.setInventNum((int)row.getCell(2).getNumericCellValue());
        assetVM.setRegInfo(row.getCell(3).getStringCellValue());
        assetVM.setRegDate(changeStringToDate(row.getCell(4).getStringCellValue()));
        assetVM.setCost(row.getCell(7).getNumericCellValue());

        /* Get HR which is enabled */
        //UserModel userModel = UserService.getHrEnabled();
        assetVM.setOwnerId(userModel.getID());
        assetVM.setOwnerFullName(userModel.getFullName());
        assetVM.setLocation("LG CNS Uzbekistan office");
        assetVM.setCheckPublic(true);
        assetVM.setCheckEnabled(true);
        assetVM.setCheckDeleted(false);

        return assetVM;
    }

    private static java.sql.Date changeStringToDate(String dateString) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date dateUtil = null;
        try {
            dateUtil = formatter.parse(dateString);
            return new java.sql.Date(dateUtil.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static AssetEntity mapAssetVMtoAssetEntityUpdate(AssetVM assetVM) {
        AssetEntity assetEntity = new AssetEntity();
        assetEntity.setInventNum(assetVM.getInventNum());
        assetEntity.setNameRu(assetVM.getNameRu());
        assetEntity.setNameEn(translateRuToEng(assetVM.getNameRu()));

        /* We don't change owner in update time */
        //assetEntity.setOwnerId(assetVM.getOwnerId());
        assetEntity.setRegDate(assetVM.getRegDate());
        assetEntity.setRegInfo(assetVM.getRegInfo());
        assetEntity.setCost(assetVM.getCost());

        /* We don't change location in update time */
        //assetEntity.setLocation(assetVM.getLocation());


        return assetEntity;
    }

    public static String translateRuToEng(String nameRu) {
        return "No translation";
    }

    public static AssetEntity mapAssetVMtoAssetEntityInsert(AssetVM assetVM) {
        AssetEntity assetEntity = new AssetEntity();

        assetEntity.setInventNum(assetVM.getInventNum());
        assetEntity.setNameRu(assetVM.getNameRu());
        assetEntity.setNameEn(translateRuToEng(assetVM.getNameRu()));
        assetEntity.setOwnerId(assetVM.getOwnerId());
        assetEntity.setRegDate(assetVM.getRegDate());
        assetEntity.setRegInfo(assetVM.getRegInfo());
        assetEntity.setCost(assetVM.getCost());
        assetEntity.setLocation(assetVM.getLocation());
        assetEntity.setCheckPublic(assetVM.isCheckPublic());
        assetEntity.setCheckDeleted(assetVM.isCheckDeleted());
        assetEntity.setCheckEnabled(assetVM.isCheckEnabled());

        return assetEntity;
    }
}
