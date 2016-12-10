package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "salary_histories", schema = "public", catalog = "LgErpSystem")
public class SalaryHistoriesEntity {
    private int id;
    private int userId;
    private int salaryBefore;
    private int salaryAfter;
    private double pit;
    private double inps;
    private double pf;
    private int currencyId;
    private UsersEntity usersByUserId;
    private CurrenciesEntity currenciesByCurrencyId;
    private Date date;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "salary_before", nullable = false)
    public int getSalaryBefore() {
        return salaryBefore;
    }

    public void setSalaryBefore(int salaryIncTax) {
        this.salaryBefore = salaryIncTax;
    }

    @Basic
    @Column(name = "salary_after", nullable = false)
    public int getSalaryAfter() {
        return salaryAfter;
    }

    public void setSalaryAfter(int salaryIncTax) {
        this.salaryAfter = salaryIncTax;
    }

    @Basic
    @Column(name = "pit", nullable = false, precision = 0)
    public double getPit() {
        return pit;
    }

    public void setPit(double ndfl) {
        this.pit = ndfl;
    }

    @Basic
    @Column(name = "inps", nullable = false, precision = 0)
    public double getInps() {
        return inps;
    }

    public void setInps(double inps) {
        this.inps = inps;
    }

    @Basic
    @Column(name = "pf", nullable = false, precision = 0)
    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    @Basic
    @Column(name = "currency_id", nullable = false, insertable = false, updatable = false)
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryHistoriesEntity that = (SalaryHistoriesEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (salaryBefore != that.salaryBefore) return false;
        if (Double.compare(that.pit, pit) != 0) return false;
        if (Double.compare(that.inps, inps) != 0) return false;
        if (Double.compare(that.pf, pf) != 0) return false;
        if (currencyId != that.currencyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userId;
        result = 31 * result + salaryBefore;
        temp = Double.doubleToLongBits(pit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(inps);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pf);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + currencyId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    public CurrenciesEntity getCurrenciesByCurrencyId() {
        return currenciesByCurrencyId;
    }

    public void setCurrenciesByCurrencyId(CurrenciesEntity currenciesByCurrencyId) {
        this.currenciesByCurrencyId = currenciesByCurrencyId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SalaryHistoriesEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", salaryBefore=" + salaryBefore +
                ", salaryAfter=" + salaryAfter +
                ", pit=" + pit +
                ", inps=" + inps +
                ", pf=" + pf +
                ", currencyId=" + currencyId +
                ", usersByUserId=" + usersByUserId +
                ", currenciesByCurrencyId=" + currenciesByCurrencyId +
                ", date=" + date +
                '}';
    }
}
