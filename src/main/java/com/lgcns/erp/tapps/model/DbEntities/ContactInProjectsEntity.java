package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "contact_in_projects", schema = "public", catalog = "LgErpSystem")
public class ContactInProjectsEntity {
    private int id;
    private int contactId;
    private int projectId;
    private ContactsEntity contactsByContactId;
    private ProjectsEntity projectsByProjectId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "contact_id", nullable = false, insertable = false, updatable = false)
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "project_id", nullable = false, insertable = false, updatable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInProjectsEntity that = (ContactInProjectsEntity) o;

        if (id != that.id) return false;
        if (contactId != that.contactId) return false;
        if (projectId != that.projectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + contactId;
        result = 31 * result + projectId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    public ContactsEntity getContactsByContactId() {
        return contactsByContactId;
    }

    public void setContactsByContactId(ContactsEntity contactsByContactId) {
        this.contactsByContactId = contactsByContactId;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectsEntity getProjectsByProjectId() {
        return projectsByProjectId;
    }

    public void setProjectsByProjectId(ProjectsEntity projectsByProjectId) {
        this.projectsByProjectId = projectsByProjectId;
    }
}
