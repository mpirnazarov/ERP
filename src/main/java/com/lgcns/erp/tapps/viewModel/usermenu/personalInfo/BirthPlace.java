package com.lgcns.erp.tapps.viewModel.usermenu.personalInfo;

/**
 * Created by DS on 11/4/2016.
 */
public class BirthPlace {
    private String birthPlace;
    private int languageId;

    public BirthPlace(String birthPlace, int languageId) {
        this.birthPlace = birthPlace;
        this.languageId = languageId;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
