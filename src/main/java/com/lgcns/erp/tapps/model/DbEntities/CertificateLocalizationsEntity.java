package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "certificate_localizations", schema = "public", catalog = "LgErpSystem")
public class CertificateLocalizationsEntity {
    private int id;
    private int languageId;
    private int certificateId;
    private String organization;
    private String name;
    private LanguagesEntity languagesByLanguageId;
    private CertificatesEntity certificatesByCertificateId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "language_id", nullable = false, insertable = false, updatable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "certificate_id", nullable = false, insertable = false, updatable = false)
    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    @Basic
    @Column(name = "organization", nullable = false, length = 50)
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CertificateLocalizationsEntity that = (CertificateLocalizationsEntity) o;

        if (id != that.id) return false;
        if (languageId != that.languageId) return false;
        if (certificateId != that.certificateId) return false;
        if (organization != null ? !organization.equals(that.organization) : that.organization != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + languageId;
        result = 31 * result + certificateId;
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLanguageId() {
        return languagesByLanguageId;
    }

    public void setLanguagesByLanguageId(LanguagesEntity languagesByLanguageId) {
        this.languagesByLanguageId = languagesByLanguageId;
    }

    @ManyToOne
    @JoinColumn(name = "certificate_id", referencedColumnName = "id", nullable = false)
    public CertificatesEntity getCertificatesByCertificateId() {
        return certificatesByCertificateId;
    }

    public void setCertificatesByCertificateId(CertificatesEntity certificatesByCertificateId) {
        this.certificatesByCertificateId = certificatesByCertificateId;
    }
}
