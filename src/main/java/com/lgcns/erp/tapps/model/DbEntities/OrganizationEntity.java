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
    private String address;
    private String name;
    private Collection<ContactsEntity> contactsesById;

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
    @Column(name = "tin", nullable = true)
    public Integer getTin() {
        return tin;
    }

    public void setTin(Integer tin) {
        this.tin = tin;
    }

    @Basic
    @Column(name = "address", nullable = true)
    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    @Basic
    @Column(name = "name", nullable = true)
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

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

}
