package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "currencies", schema = "public", catalog = "LgErpSystem")
public class CurrenciesEntity {
    private int id;
    private Collection<CurrencyLocalizationsEntity> currnecyLocalizationsesById;
    private Collection<SalaryHistoriesEntity> salaryHistoriesById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrenciesEntity that = (CurrenciesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "currenciesByCurrencyId")
    public Collection<CurrencyLocalizationsEntity> getCurrnecyLocalizationsesById() {
        return currnecyLocalizationsesById;
    }

    public void setCurrnecyLocalizationsesById(Collection<CurrencyLocalizationsEntity> currnecyLocalizationsesById) {
        this.currnecyLocalizationsesById = currnecyLocalizationsesById;
    }

    @OneToMany(mappedBy = "currenciesByCurrencyId")
    public Collection<SalaryHistoriesEntity> getSalaryHistoriesById() {
        return salaryHistoriesById;
    }

    public void setSalaryHistoriesById(Collection<SalaryHistoriesEntity> salaryHistoriesById) {
        this.salaryHistoriesById = salaryHistoriesById;
    }
}
