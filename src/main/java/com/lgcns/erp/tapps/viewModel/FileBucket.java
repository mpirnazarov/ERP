package com.lgcns.erp.tapps.viewModel;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by DS on 12/6/2016.
 */
public class FileBucket {

    private MultipartFile file;
    private String name;
    private int type;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
