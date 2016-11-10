package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "training_localizations", schema = "public", catalog = "LgErpSystem")
public class TrainingLocalizationsEntity {
    private int id;
    private String name;
    private String organization;
    private int trainingId;
    private int languageId;
    private TrainingsEntity trainingsByTrainingId;
    private LanguagesEntity languagesByLanguageId;

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
    @Column(name = "name", nullable = false, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "training_id", nullable = false, insertable = false, updatable = false)
    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
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

        TrainingLocalizationsEntity that = (TrainingLocalizationsEntity) o;

        if (id != that.id) return false;
        if (trainingId != that.trainingId) return false;
        if (languageId != that.languageId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (organization != null ? !organization.equals(that.organization) : that.organization != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        result = 31 * result + trainingId;
        result = 31 * result + languageId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "training_id", referencedColumnName = "id", nullable = false)
    public TrainingsEntity getTrainingsByTrainingId() {
        return trainingsByTrainingId;
    }

    public void setTrainingsByTrainingId(TrainingsEntity trainingsByTrainingId) {
        this.trainingsByTrainingId = trainingsByTrainingId;
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
