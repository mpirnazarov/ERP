package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "work_localizations", schema = "public", catalog = "LgErpSystem")
public class WorkLocalizationsEntity {
    private int id;
    private String organization;
    private String post;
    private int workId;
    private int languageId;
    private WorksEntity worksByWorkId;
    private LanguagesEntity languagesByLanguageId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "organization", nullable = false, length = 100)
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Basic
    @Column(name = "post", nullable = false, length = 100)
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Basic
    @Column(name = "work_id", nullable = false, insertable = false, updatable = false)
    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    @Basic
    @Column(name = "language_id", nullable = false, insertable = false, updatable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkLocalizationsEntity that = (WorkLocalizationsEntity) o;

        if (id != that.id) return false;
        if (workId != that.workId) return false;
        if (languageId != that.languageId) return false;
        if (organization != null ? !organization.equals(that.organization) : that.organization != null) return false;
        if (post != null ? !post.equals(that.post) : that.post != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + workId;
        result = 31 * result + languageId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "work_id", referencedColumnName = "id", nullable = false)
    public WorksEntity getWorksByWorkId() {
        return worksByWorkId;
    }

    public void setWorksByWorkId(WorksEntity worksByWorkId) {
        this.worksByWorkId = worksByWorkId;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLanguageId() {
        return languagesByLanguageId;
    }

    public void setLanguagesByLanguageId(LanguagesEntity languagesByLanguageId) {
        this.languagesByLanguageId = languagesByLanguageId;
    }
}
