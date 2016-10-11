package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "salary_histories", schema = "public", catalog = "LgErpSystem")
public class SalaryHistoriesEntity {
    private int id;
    private int userId;
    private int salaryIncTax;
    private double ndfl;
    private double inps;
    private double pf;
    private int currencyId;
    private UsersEntity usersByUserId;
    private CurrenciesEntity currenciesByCurrencyId;

    @Id
    @Column(name = "id", nullable = false)
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
    @Column(name = "salary_inc_tax", nullable = false)
    public int getSalaryIncTax() {
        return salaryIncTax;
    }

    public void setSalaryIncTax(int salaryIncTax) {
        this.salaryIncTax = salaryIncTax;
    }

    @Basic
    @Column(name = "ndfl", nullable = false, precision = 0)
    public double getNdfl() {
        return ndfl;
    }

    public void setNdfl(double ndfl) {
        this.ndfl = ndfl;
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
        if (salaryIncTax != that.salaryIncTax) return false;
        if (Double.compare(that.ndfl, ndfl) != 0) return false;
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
        result = 31 * result + salaryIncTax;
        temp = Double.doubleToLongBits(ndfl);
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
}
