package com.lgcns.erp.hr.viewModel.ProjectViewModel;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rafatdin on 14.11.2016.
 */
public class ProjectCreateForm {
    @Valid
    private List<ProjectCreate> projects;

    public List<ProjectCreate> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectCreate> projects) {
        this.projects = projects;
    }
}
