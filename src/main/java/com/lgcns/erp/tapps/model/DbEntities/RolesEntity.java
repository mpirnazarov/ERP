package com.lgcns.erp.tapps.model.DbEntities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "roles", schema = "public", catalog = "LgErpSystem")
public class RolesEntity {
    private int id;
    private Collection<PermissionInRolesEntity> permissionInRolesById;
    private Collection<RoleLocalizationsEntity> roleLocalizationsesById;
    private Collection<UserInRolesEntity> userInRolesById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        RolesEntity that = (RolesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<PermissionInRolesEntity> getPermissionInRolesById() {
        return permissionInRolesById;
    }

    public void setPermissionInRolesById(Collection<PermissionInRolesEntity> permissionInRolesById) {
        this.permissionInRolesById = permissionInRolesById;
    }

    @OneToMany(mappedBy = "rolesByRoleId", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<RoleLocalizationsEntity> getRoleLocalizationsesById() {
        return roleLocalizationsesById;
    }

    public void setRoleLocalizationsesById(Collection<RoleLocalizationsEntity> roleLocalizationsesById) {
        this.roleLocalizationsesById = roleLocalizationsesById;
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<UserInRolesEntity> getUserInRolesById() {
        return userInRolesById;
    }

    public void setUserInRolesById(Collection<UserInRolesEntity> userInRolesById) {
        this.userInRolesById = userInRolesById;
    }
}
