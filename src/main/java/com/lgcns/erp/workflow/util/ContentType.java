package com.lgcns.erp.workflow.util;

/**
 * Created by DS on 06.02.2017.
 */
public class ContentType {
    public static String getContentType(String ext){
        String contentType = "";
        if (ext.equals("pdf"))
            contentType = "application/pdf";
        else if (ext.equals("docx"))
            contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        else if (ext.equals("xls"))
            contentType = "application/vnd.ms-excel";
        else if (ext.equals("doc")||ext.equals("dot"))
            contentType = "application/msword";
        else if (ext.equals("ppt")||ext.equals("pot")||ext.equals("pps")||ext.equals("ppa"))
            contentType ="application/vnd.ms-powerpoint";
        else if (ext.equals("jpeg")||ext.equals("jpe")||ext.equals("jfif")||ext.equals("pjpeg")||ext.equals("pjp"))
            contentType = "image/jpeg";
        else if (ext.equals("png"))
            contentType = "image/png";
        else contentType="";

        return contentType;
    }
}
