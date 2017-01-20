package com.lgcns.erp.workflow.mapper;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Muslimbek on 1/20/2017.
 */
@Entity
@Table(name = "members", schema = "workflow", catalog = "LgErpSystem")
public class MembersEntity {
    private int id;
    private String organizationName;
    private Date dateFrom;
    private Date dateTo;
    private double expenseTransportation;
    private double dailyAllowance;
    private double expenseAccommodation;
    private double expenseOther;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "organization_name", nullable = false, length = 200)
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Basic
    @Column(name = "date_from", nullable = false)
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_to", nullable = false)
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "expense_transportation", nullable = false, precision = 0)
    public double getExpenseTransportation() {
        return expenseTransportation;
    }

    public void setExpenseTransportation(double expenseTransportation) {
        this.expenseTransportation = expenseTransportation;
    }

    @Basic
    @Column(name = "daily_allowance", nullable = false, precision = 0)
    public double getDailyAllowance() {
        return dailyAllowance;
    }

    public void setDailyAllowance(double dailyAllowance) {
        this.dailyAllowance = dailyAllowance;
    }

    @Basic
    @Column(name = "expense_accommodation", nullable = false, precision = 0)
    public double getExpenseAccommodation() {
        return expenseAccommodation;
    }

    public void setExpenseAccommodation(double expenseAccommodation) {
        this.expenseAccommodation = expenseAccommodation;
    }

    @Basic
    @Column(name = "expense_other", nullable = false, precision = 0)
    public double getExpenseOther() {
        return expenseOther;
    }

    public void setExpenseOther(double expenseOther) {
        this.expenseOther = expenseOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembersEntity that = (MembersEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.expenseTransportation, expenseTransportation) != 0) return false;
        if (Double.compare(that.dailyAllowance, dailyAllowance) != 0) return false;
        if (Double.compare(that.expenseAccommodation, expenseAccommodation) != 0) return false;
        if (Double.compare(that.expenseOther, expenseOther) != 0) return false;
        if (organizationName != null ? !organizationName.equals(that.organizationName) : that.organizationName != null)
            return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (organizationName != null ? organizationName.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        temp = Double.doubleToLongBits(expenseTransportation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dailyAllowance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(expenseAccommodation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(expenseOther);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
