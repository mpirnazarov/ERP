package com.lgcns.erp.tapps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/uploadFile")
public class FileUploadController {
	private String saveDirectory = "C:/files/";
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleFileUpload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Home/editmenu/FileUploadForm");
		return mav;
	}


	
	@RequestMapping(method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request,
			@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
		System.out.println("111111111111111");
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload){
				
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
				}
			}
		}

		// returns to the view "Result"
		return "/";
	}
}