package com.lgcns.erp.tapps.controller;

/**
 * Created by Dell on 04-Nov-16.
 */

import com.lgcns.erp.tapps.model.FileUpload;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileUploadController extends SimpleFormController{

    public FileUploadController(){
        setCommandClass(FileUpload.class);
        setCommandName("fileUploadForm");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        FileUpload file = (FileUpload)command;

        MultipartFile multipartFile = file.getFile();

        String fileName="";

        if(multipartFile!=null){
            fileName = multipartFile.getOriginalFilename();
            //do whatever you want
        }

        return new ModelAndView("FileUploadSuccess","fileName",fileName);
    }
}
