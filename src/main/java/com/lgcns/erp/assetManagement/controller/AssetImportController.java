package com.lgcns.erp.assetManagement.controller;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.mapper.AssetImportMapper;
import com.lgcns.erp.assetManagement.mapper.AssetMapper;
import com.lgcns.erp.assetManagement.model.AssetVM;
import com.lgcns.erp.assetManagement.service.AssetService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.controller.UP;
import com.lgcns.erp.tapps.controller.UserController;
import com.lgcns.erp.tapps.model.UserModel;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("AssetManagement")
public class AssetImportController {

    @Autowired
    AssetService assetService;

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public ModelAndView getImport(Principal principal, Model model) {
        int userId = UserService.getIdByUsername(principal.getName());

        ModelAndView mav = new ModelAndView("assetManagement/assetManagementImport");
        mav = UP.includeUserProfile(mav, principal);
        mav.addObject("UserProfileUser", UserController.getProfileByUsername(principal.getName()));
        mav.addObject("userRoleId", UserService.getUserByUsername(principal.getName()).getRoleId());
        mav.addObject("UserslistJson", WorkflowService.getUsersJson(principal));
        mav.addObject("userId", userId);

        return mav;
    }


    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String postImport(Principal principal, Model model, MultipartFile file) throws IOException, InvalidFormatException, ParseException {

        List<AssetVM> assetVMList = new LinkedList<>();
        AssetVM assetVM = new AssetVM();
        Workbook workbook = WorkbookFactory.create(new File("E://" + file.getOriginalFilename()));
        Sheet mySheet = workbook.getSheetAt(0);
        Iterator<Row> rowIter = mySheet.rowIterator();
        rowIter.next();
        rowIter.next();
        rowIter.next();
        rowIter.next();
        int count = 0;

        UserModel userModelList = UserService.getHrEnabled();

        List<AssetEntity> assetEntityListOld = assetService.getAssetList();

        /* CRUD operation on DB Asset*/


        System.out.println("-----------------------------------------------");
        System.out.println("PRINTING ASSET LIST:");
        /* Status of action 1=Create, 2=Update, 3=DeleteUpdate */
        int status = 0;

        while (rowIter.hasNext()) {
            if (count == 32) {
                rowIter.next();
                rowIter.next();
                rowIter.next();
                rowIter.next();
                rowIter.next();
                count = 0;
            } else {
                Row row = rowIter.next();
                assetVM = AssetImportMapper.mapExcelRowToAssetVM(row, userModelList);
                status = checkStatusNewAsset(assetEntityListOld, assetVM.getInventNum());
                /* check if asset is for update? */
                if(status == 1){
                  assetService.insertAsset(AssetImportMapper.mapAssetVMtoAssetEntityInsert(assetVM));
                } else if (status == 2) {
                    assetService.updateAsset(AssetImportMapper.mapAssetVMtoAssetEntityUpdate(assetVM));
                } else if (status == 3){
                    assetService.updateAsset(AssetImportMapper.mapAssetVMtoAssetEntityUpdate(assetVM));
                    /* 1 - checkDeleted = true, 2- checkDeleted = false*/
                    assetService.deleteAsset(assetVM.getInventNum(), false);
                }

                    System.out.println(assetVM);
                assetVMList.add(assetVM);
                //System.out.println(row.getCell(0) + " " + row.getCell(1) + " \t" + row.getCell(2) + " \t" + row.getCell(3) + " \t" + row.getCell(4) + " \t" + row.getCell(7));
                count++;
            }
        }

        boolean flag = false;
        for (AssetEntity assetOld :
                assetEntityListOld) {
            flag = false;
            for (AssetVM assetNew:
                 assetVMList) {
                if(assetOld.getInventNum() == assetNew.getInventNum()){
                    flag = true;
                }
            }
            if(flag == false){
                assetService.deleteAsset(assetOld.getInventNum(), true);
            }
        }
        System.out.println("-----------------------------------------------");
        //System.out.println(mySheet.getRow(1).getCell(0));


        // model.addAttribute("message", "File: " + file.getOriginalFilename()
        // + " has been uploaded successfully!");
        return "redirect:/AssetManagement/main";
    }

    /* Status of action 1=Create, 2=Update, 3=DeleteUpdate */
    private int checkStatusNewAsset(List<AssetEntity> assetEntityListOld, int inventNum) {
        for (AssetEntity asset :
                assetEntityListOld) {
            if (asset.getInventNum() == inventNum){
                if(!asset.isCheckDeleted())
                    return 2;
                else return 3;
            }
        }

        return 1;
    }

    private boolean checkAssetExistInDB(int inventNum, List<AssetEntity> assetEntityListOld) {
        for (AssetEntity assetEntity :
                assetEntityListOld) {
            if (assetEntity.getInventNum() == inventNum) {
                return true;
            }
        }
        return false;
    }


}
