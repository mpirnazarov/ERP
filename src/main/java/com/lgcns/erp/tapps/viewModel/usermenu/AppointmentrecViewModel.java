package com.lgcns.erp.tapps.viewModel.usermenu;

import com.lgcns.erp.tapps.viewModel.usermenu.Appointment.AppointmentSummary;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muslimbek on 25-Oct-16.
 */

public class AppointmentrecViewModel {
    private List<AppointmentSummary> appointmentSummaries;

    public AppointmentrecViewModel() {
        appointmentSummaries = new LinkedList<AppointmentSummary>();
    }
    public void addAppointSummary(Date appointDate, String appointmentType, String department, String role, int id, int detId) {
        appointmentSummaries.add(new AppointmentSummary(appointDate, appointmentType, department, role, id, detId));
    }

    public List<AppointmentSummary> getAppointmentSummaries() {
        return appointmentSummaries;
    }

    public void setAppointmentSummaries(List<AppointmentSummary> appointmentSummaries) {
        this.appointmentSummaries = appointmentSummaries;
    }

    public void addAppointSummary(Date dateFrom, int contractType, int postId, int id, int departmentId, int roleId, int externalId) {
        AppointmentSummary app=new AppointmentSummary();
        app.setAppointDate(dateFrom);
        app.setContractType(contractType);
        app.setPostId(postId);
        app.setId(id);
        app.setDepartmentId(departmentId);
        app.setRoleId(roleId);
        app.setExternalId(externalId);
        appointmentSummaries.add(app);
    }
}
