package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "statuses", schema = "public", catalog = "LgErpSystem")
public class StatusesEntity {
    private int id;
    private Collection<StatusLocalizationsEntity> statusLocalizationsesById;
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

        StatusesEntity that = (StatusesEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "statusesByStatusId")
    public Collection<StatusLocalizationsEntity> getStatusLocalizationsesById() {
        return statusLocalizationsesById;
    }

    public void setStatusLocalizationsesById(Collection<StatusLocalizationsEntity> statusLocalizationsesById) {
        this.statusLocalizationsesById = statusLocalizationsesById;
    }

    @OneToMany(mappedBy = "statusesByStatusId")
    public Collection<UsersEntity> getUsersesById() {
        return usersesById;
    }

    public void setUsersesById(Collection<UsersEntity> usersesById) {
        this.usersesById = usersesById;
    }
}
