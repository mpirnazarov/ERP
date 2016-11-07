package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "role_localizations", schema = "public", catalog = "LgErpSystem")
public class RoleLocalizationsEntity {
    private int id;
    private String name;
    private int lenguageId;
    private int roleId;
    private LanguagesEntity languagesByLenguageId;
    private RolesEntity rolesByRoleId;

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
    @Column(name = "lenguage_id", nullable = false, insertable = false, updatable = false)
    public int getLenguageId() {
        return lenguageId;
    }

    public void setLenguageId(int lenguageId) {
        this.lenguageId = lenguageId;
    }

    @Basic
    @Column(name = "role_id", nullable = false, insertable = false, updatable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleLocalizationsEntity that = (RoleLocalizationsEntity) o;

        if (id != that.id) return false;
        if (lenguageId != that.lenguageId) return false;
        if (roleId != that.roleId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + lenguageId;
        result = 31 * result + roleId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "lenguage_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLenguageId() {
        return languagesByLenguageId;
    }

    public void setLanguagesByLenguageId(LanguagesEntity languagesByLenguageId) {
        this.languagesByLenguageId = languagesByLenguageId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public RolesEntity getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(RolesEntity rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }
}
