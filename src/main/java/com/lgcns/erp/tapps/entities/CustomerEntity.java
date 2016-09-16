package com.lgcns.erp.tapps.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Entity
@javax.persistence.Table(name = "Customer", schema = "public", catalog = "ErpSystem")
public class CustomerEntity {
    private int id;

    @Id
    @javax.persistence.Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "Name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String tin;

    @Basic
    @javax.persistence.Column(name = "TIN", nullable = false, length = 9)
    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    private String address;

    @Basic
    @javax.persistence.Column(name = "Address", nullable = true, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String contactPhone;

    @Basic
    @javax.persistence.Column(name = "ContactPhone", nullable = true, length = 13)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    private String genDirector;

    @Basic
    @javax.persistence.Column(name = "GenDirector", nullable = true, length = -1)
    public String getGenDirector() {
        return genDirector;
    }

    public void setGenDirector(String genDirector) {
        this.genDirector = genDirector;
    }

    private String contactPerson;

    @Basic
    @javax.persistence.Column(name = "ContactPerson", nullable = true, length = -1)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tin != null ? !tin.equals(that.tin) : that.tin != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (contactPhone != null ? !contactPhone.equals(that.contactPhone) : that.contactPhone != null) return false;
        if (genDirector != null ? !genDirector.equals(that.genDirector) : that.genDirector != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tin != null ? tin.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
        result = 31 * result + (genDirector != null ? genDirector.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        return result;
    }
}
