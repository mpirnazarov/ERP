package com.lgcns.erp.tapps.viewModel.Manager;

import java.util.List;

/**
 * Created by Muslimbek on 11/28/2016.
 */
public class FormModel {
    private List<Form> forms;

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    @Override
    public String toString() {
        String s="";
        for (Form form :
                forms) {
            s +=  "Form{" +
                    "id='" + form.getId() + '\'' +
                    ", firstName='" + form.getFirstName() + '\'' +
                    ", lastName='" + form.getLastName() + '\'' +
                    ", comments='" + form.getComments() + '\'' +
                    ", grade='" + form.getGrade() + '\'' +
                    '}';
        }
        return s;
    }
}
