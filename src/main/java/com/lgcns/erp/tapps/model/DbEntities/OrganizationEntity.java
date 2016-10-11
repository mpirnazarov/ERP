package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "organization", schema = "public", catalog = "LgErpSystem")
public class OrganizationEntity {
    private int id;
    private Integer tin;
    private Collection<ContactsEntity> contactsesById;
    private Collection<OrganizationLocalizationsEntity> organizationLocalizationsesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tin", nullable = true)
    public Integer getTin() {
        return tin;
    }

    public void setTin(Integer tin) {
        this.tin = tin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationEntity that = (OrganizationEntity) o;

        if (id != that.id) return false;
        if (tin != null ? !tin.equals(that.tin) : that.tin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tin != null ? tin.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "organizationByOrganizationId")
    public Collection<ContactsEntity> getContactsesById() {
        return contactsesById;
    }

    public void setContactsesById(Collection<ContactsEntity> contactsesById) {
        this.contactsesById = contactsesById;
    }

    @OneToMany(mappedBy = "organizationByOrganizationId")
    public Collection<OrganizationLocalizationsEntity> getOrganizationLocalizationsesById() {
        return organizationLocalizationsesById;
    }

    public void setOrganizationLocalizationsesById(Collection<OrganizationLocalizationsEntity> organizationLocalizationsesById) {
        this.organizationLocalizationsesById = organizationLocalizationsesById;
    }
}
