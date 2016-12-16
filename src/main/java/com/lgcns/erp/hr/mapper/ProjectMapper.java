package com.lgcns.erp.hr.mapper;

import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointCreate;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointEdit;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreate;
import com.lgcns.erp.tapps.model.DbEntities.ProjectsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UserInProjectsEntity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Rafatdin on 27.10.2016.
 */
public class ProjectMapper {
    public static ProjectsEntity mapViewModelToEntity(ProjectCreate model){
        ProjectsEntity project = new ProjectsEntity();
        project.setCode(model.getCode());
        project.setStartDate(new Date(model.getStartDate().getTime()));
        project.setEndDate(new Date(model.getEndDate().getTime()));
        project.setType(model.getType());
        project.setStatus(model.getStatus());
        project.setMoneyUsd(model.getMoneyUsd());
        project.setMoneyUzs(model.getMoneyUzs());
        project.setName(model.getName());
        project.setCustomerId(model.getCustomerId());
        return project;
    }
    public static ProjectCreate mapEntityToViewModel(ProjectsEntity entity, int managerId){
        ProjectCreate viewModel = new ProjectCreate();
        viewModel.setId(entity.getId());
        viewModel.setCode(entity.getCode());
        viewModel.setCustomerId(entity.getCustomerId());
        viewModel.setManagerId(managerId);
        viewModel.setStartDate(entity.getStartDate());
        viewModel.setEndDate(entity.getEndDate());
        viewModel.setStatus(entity.getStatus());
        viewModel.setName(entity.getName());
        viewModel.setType(entity.getType());
        viewModel.setMoneyUzs(entity.getMoneyUzs());
        viewModel.setMoneyUsd(entity.getMoneyUsd());
        return viewModel;
    }

    public static AppointEdit mapAppointEdit(UserInProjectsEntity appointment) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        AppointEdit viewModel = new AppointEdit();
        viewModel.setId(appointment.getId());
        viewModel.setRoleId(appointment.getRoleId());
        viewModel.setProjectId(appointment.getProjectId());
        viewModel.setEmpId(appointment.getUserId());
        viewModel.setDateFrom(f.format(appointment.getDateFrom()));
        viewModel.setDateTo(f.format(appointment.getDateTo()));
        return viewModel;
    }
    public static UserInProjectsEntity mapAppointEdit(AppointEdit appointment) throws ParseException {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        UserInProjectsEntity viewModel = new UserInProjectsEntity();
        viewModel.setId(appointment.getId());
        viewModel.setRoleId(appointment.getRoleId());
        viewModel.setProjectId(appointment.getProjectId());
        viewModel.setUserId(appointment.getEmpId());
        viewModel.setDateFrom(f.parse(appointment.getDateFrom()));
        viewModel.setDateTo(f.parse(appointment.getDateTo()));
        return viewModel;
    }
}
