package com.lgcns.erp.tapps.viewModel.usermenu;

import com.lgcns.erp.tapps.model.DbEntities.SalaryHistoriesEntity;
import com.lgcns.erp.tapps.viewModel.usermenu.Appointment.AppointmentSummary;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 25-Oct-16.
 */

public class AppointmentrecViewModel {
    private List<AppointmentSummary> appointmentSummaries;
    private List<SalaryHistoriesEntity> salaryDetails;

    public AppointmentrecViewModel() {
        appointmentSummaries = new LinkedList<AppointmentSummary>();
        salaryDetails = new LinkedList<SalaryHistoriesEntity>();
    }
    public void addAppointSummary(Date appointDate, String appointmentType, String department, String role) {
        appointmentSummaries.add(new AppointmentSummary(appointDate, appointmentType, department, role));
    }

    public List<AppointmentSummary> getAppointmentSummaries() {
        return appointmentSummaries;
    }

    public void setAppointmentSummaries(List<AppointmentSummary> appointmentSummaries) {
        this.appointmentSummaries = appointmentSummaries;
    }

    public List<SalaryHistoriesEntity> getSalaryDetails() {
        return salaryDetails;
    }

    public void setSalaryDetails(List<SalaryHistoriesEntity> salaryDetails) {
        this.salaryDetails = salaryDetails;
    }
}
