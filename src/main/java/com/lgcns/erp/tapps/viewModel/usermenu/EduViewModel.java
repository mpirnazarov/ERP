package com.lgcns.erp.tapps.viewModel.usermenu;

import com.lgcns.erp.tapps.viewModel.usermenu.Education.Certificates;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.Educations;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.LanguageSummary;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dell on 25-Oct-16.
 */
public class EduViewModel {
    List<Educations> educationsList = null;
    List<LanguageSummary> languageSummaryList = null;
    List<Certificates> certificateList = null;

    public List<Certificates> getCertificateList() {
        return certificateList;
    }

    public List<Educations> getEducationsList() {
        return educationsList;
    }

    public List<LanguageSummary> getLanguageSummaryList() {
        return languageSummaryList;
    }

    public EduViewModel() {
        educationsList = new LinkedList<Educations>();
        languageSummaryList = new LinkedList<LanguageSummary>();
        certificateList = new LinkedList<Certificates>();
    }

    public void addEducation(String name, String major, String degree, Date startDate, Date endDate, int id) {
        educationsList.add(new Educations(name, major, degree, startDate, endDate, id));
    }


    public void addLanguageSummary(String language, String listening, String reading, String writing, String speaking, int id) {
    languageSummaryList.add(new LanguageSummary(language, listening, reading, writing, speaking, id));
    }

    public void addCertificate(String name, String organization, String number, Date dateTime, String mark, int id, int type, String degree) {
        certificateList.add(new Certificates(name, organization, number, dateTime, mark, id, type, degree));
    }
}
