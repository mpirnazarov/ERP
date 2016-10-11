package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "posts", schema = "public", catalog = "LgErpSystem")
public class PostsEntity {
    private int id;
    private int userId;
    private Date dateFrom;
    private Collection<PostLocalizationsEntity> postLocalizationsesById;
    private UsersEntity usersByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "date_from", nullable = false)
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostsEntity that = (PostsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "postsByPostId")
    public Collection<PostLocalizationsEntity> getPostLocalizationsesById() {
        return postLocalizationsesById;
    }

    public void setPostLocalizationsesById(Collection<PostLocalizationsEntity> postLocalizationsesById) {
        this.postLocalizationsesById = postLocalizationsesById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
