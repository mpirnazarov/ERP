package com.lgcns.erp.tapps.model.DbEntities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "contacts", schema = "public", catalog = "LgErpSystem")
public class ContactsEntity {
    private int id;
    private Integer organizationId;

    @Pattern(regexp="^\\(?(\\d{5})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$|",message="Phone number should be of (99893)123-4567 format")

    private String mobilePhone;
    @Pattern(regexp="^\\(?(\\d{5})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$|",message="Phone number should be of (99893)123-4567 format")

    private String workPhone;
    @NotEmpty
    private String name;
    private Collection<ContactInProjectsEntity> contactInProjectsesById;
    private OrganizationEntity organizationByOrganizationId;

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
    @Column(name = "organization_id", nullable = true, insertable = false, updatable = false)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "mobile_phone", nullable = true, length = 20)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "work_phone", nullable = true, length = 20)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsEntity that = (ContactsEntity) o;

        if (id != that.id) return false;
        if (organizationId != null ? !organizationId.equals(that.organizationId) : that.organizationId != null)
            return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (organizationId != null ? organizationId.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "contactsByContactId")
    public Collection<ContactInProjectsEntity> getContactInProjectsesById() {
        return contactInProjectsesById;
    }

    public void setContactInProjectsesById(Collection<ContactInProjectsEntity> contactInProjectsesById) {
        this.contactInProjectsesById = contactInProjectsesById;
    }

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    public OrganizationEntity getOrganizationByOrganizationId() {
        return organizationByOrganizationId;
    }

    public void setOrganizationByOrganizationId(OrganizationEntity organizationByOrganizationId) {
        this.organizationByOrganizationId = organizationByOrganizationId;
    }
}
