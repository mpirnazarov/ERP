package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "permission_localizations", schema = "public", catalog = "LgErpSystem")
public class PermissionLocalizationsEntity {
    private int id;
    private String name;
    private int permissionsId;
    private int languageId;
    private PermissionsEntity permissionsByPermissionsId;
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
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "permissions_id", nullable = false, insertable = false, updatable = false)
    public int getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(int permissionsId) {
        this.permissionsId = permissionsId;
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

        PermissionLocalizationsEntity that = (PermissionLocalizationsEntity) o;

        if (id != that.id) return false;
        if (permissionsId != that.permissionsId) return false;
        if (languageId != that.languageId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + permissionsId;
        result = 31 * result + languageId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "permissions_id", referencedColumnName = "id", nullable = false)
    public PermissionsEntity getPermissionsByPermissionsId() {
        return permissionsByPermissionsId;
    }

    public void setPermissionsByPermissionsId(PermissionsEntity permissionsByPermissionsId) {
        this.permissionsByPermissionsId = permissionsByPermissionsId;
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
