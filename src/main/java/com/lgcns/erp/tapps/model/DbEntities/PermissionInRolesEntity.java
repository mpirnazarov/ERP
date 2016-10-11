package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "permission_in_roles", schema = "public", catalog = "LgErpSystem")
public class PermissionInRolesEntity {
    private int id;
    private int premissionId;
    private int roleId;
    private PermissionsEntity permissionsByPremissionId;
    private RolesEntity rolesByRoleId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "premission_id", nullable = false, insertable = false, updatable = false)
    public int getPremissionId() {
        return premissionId;
    }

    public void setPremissionId(int premissionId) {
        this.premissionId = premissionId;
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

        PermissionInRolesEntity that = (PermissionInRolesEntity) o;

        if (id != that.id) return false;
        if (premissionId != that.premissionId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + premissionId;
        result = 31 * result + roleId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "premission_id", referencedColumnName = "id", nullable = false)
    public PermissionsEntity getPermissionsByPremissionId() {
        return permissionsByPremissionId;
    }

    public void setPermissionsByPremissionId(PermissionsEntity permissionsByPremissionId) {
        this.permissionsByPremissionId = permissionsByPremissionId;
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
