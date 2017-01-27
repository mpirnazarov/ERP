package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Muslimbek Pirnazarov on 1/23/2017.
 */
@Entity
@Table(name = "members", schema = "workflow", catalog = "LgErpSystem")
public class MembersEntity {
    private int id;
    private int requestId;
    private int userId;
    private String organizationName;
    private Date dateFrom;
    private Date dateTo;
    private double expenseTransportation;
    private double dailyAllowance;
    private double expenseAccommodation;
    private double expenseOther;
    private RequestsEntity requestsByRequestId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "request_id")
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "organization_name")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Basic
    @Column(name = "date_from")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_to")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "expense_transportation")
    public double getExpenseTransportation() {
        return expenseTransportation;
    }

    public void setExpenseTransportation(double expenseTransportation) {
        this.expenseTransportation = expenseTransportation;
    }

    @Basic
    @Column(name = "daily_allowance")
    public double getDailyAllowance() {
        return dailyAllowance;
    }

    public void setDailyAllowance(double dailyAllowance) {
        this.dailyAllowance = dailyAllowance;
    }

    @Basic
    @Column(name = "expense_accommodation")
    public double getExpenseAccommodation() {
        return expenseAccommodation;
    }

    public void setExpenseAccommodation(double expenseAccommodation) {
        this.expenseAccommodation = expenseAccommodation;
    }

    @Basic
    @Column(name = "expense_other")
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
        if (requestId != that.requestId) return false;
        if (userId != that.userId) return false;
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
        result = 31 * result + requestId;
        result = 31 * result + userId;
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

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RequestsEntity getRequestsByRequestId() {
        return requestsByRequestId;
    }

    public void setRequestsByRequestId(RequestsEntity requestsByRequestId) {
        this.requestsByRequestId = requestsByRequestId;
    }
}
