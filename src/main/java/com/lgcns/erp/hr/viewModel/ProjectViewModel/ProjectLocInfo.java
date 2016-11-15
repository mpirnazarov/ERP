package com.lgcns.erp.hr.viewModel.ProjectViewModel;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Rafatdin on 10.11.2016.
 */
public class ProjectLocInfo {
    private Integer LanguageId;
    private String LanguageCode;

    @NotEmpty
    private String Name;

    private String Comments;

    public Integer getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(Integer languageId) {
        LanguageId = languageId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}
