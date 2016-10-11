package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "permissions", schema = "public", catalog = "LgErpSystem")
public class PermissionsEntity {
    private int id;
    private Collection<PermissionInRolesEntity> permissionInRolesById;
    private Collection<PermissionLocalizationsEntity> permissionLocalizationsesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsEntity that = (PermissionsEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "permissionsByPremissionId")
    public Collection<PermissionInRolesEntity> getPermissionInRolesById() {
        return permissionInRolesById;
    }

    public void setPermissionInRolesById(Collection<PermissionInRolesEntity> permissionInRolesById) {
        this.permissionInRolesById = permissionInRolesById;
    }

    @OneToMany(mappedBy = "permissionsByPermissionsId")
    public Collection<PermissionLocalizationsEntity> getPermissionLocalizationsesById() {
        return permissionLocalizationsesById;
    }

    public void setPermissionLocalizationsesById(Collection<PermissionLocalizationsEntity> permissionLocalizationsesById) {
        this.permissionLocalizationsesById = permissionLocalizationsesById;
    }
}
