package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "departments", schema = "public", catalog = "LgErpSystem")
public class DepartmentsEntity {
    private int id;
    private Collection<DepartmentLocalizationsEntity> departmentLocalizationsesById;
    private Collection<UsersEntity> usersesById;

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

        DepartmentsEntity that = (DepartmentsEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "departmentsByDepartmentId")
    public Collection<DepartmentLocalizationsEntity> getDepartmentLocalizationsesById() {
        return departmentLocalizationsesById;
    }

    public void setDepartmentLocalizationsesById(Collection<DepartmentLocalizationsEntity> departmentLocalizationsesById) {
        this.departmentLocalizationsesById = departmentLocalizationsesById;
    }

    @OneToMany(mappedBy = "departmentsByDepartmentId")
    public Collection<UsersEntity> getUsersesById() {
        return usersesById;
    }

    public void setUsersesById(Collection<UsersEntity> usersesById) {
        this.usersesById = usersesById;
    }
}
