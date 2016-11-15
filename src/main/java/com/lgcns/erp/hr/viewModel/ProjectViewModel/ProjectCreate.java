package com.lgcns.erp.hr.viewModel.ProjectViewModel;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.viewModel.RegistrationLocInfo;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafatdin on 10.11.2016.
 */
public class ProjectCreate {

    @NotEmpty(message = "Code cannot be empty")
    private String Code;
    @NotEmpty(message = "Name cannot be empty")
    private String Name;

    @NotNull(message = "Please enter the start date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date StartDate;

    @NotNull(message = "Please enter the end date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date EndDate;

    @NotNull(message = "The project type cannot be empty")
    private String Type;
    private Integer Status;

    private Float MoneyUzs;

    private Float MoneyUsd;
    @NotNull(message = "Customer is not chosen")
    private Integer CustomerId;
    @NotNull(message = "The PM is not chosen")
    private Integer ManagerId;

    public Integer getManagerId() {
        return ManagerId;
    }

    public void setManagerId(Integer managerId) {
        ManagerId = managerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Float getMoneyUzs() {
        return MoneyUzs;
    }

    public void setMoneyUzs(Float moneyUzs) {
        MoneyUzs = moneyUzs;
    }

    public Float getMoneyUsd() {
        return MoneyUsd;
    }

    public void setMoneyUsd(Float moneyUsd) {
        MoneyUsd = moneyUsd;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }
}
