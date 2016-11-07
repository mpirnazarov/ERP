package com.lgcns.erp.tapps.controller;

/**
 * Created by Dell on 04-Nov-16.
 */

import com.lgcns.erp.tapps.model.FileUpload;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileUploadValidator implements Validator{

    @Override
    public boolean supports(Class clazz) {
        //just validate the FileUpload instances
        return FileUpload.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        FileUpload file = (FileUpload)target;

        if(file.getFile().getSize()==0){
            errors.rejectValue("file", "required.fileUpload");
        }
    }
}
