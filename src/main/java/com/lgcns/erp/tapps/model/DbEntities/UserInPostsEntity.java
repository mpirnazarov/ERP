package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Rafatdin on 31.10.2016.
 */
@Entity
@Table(name = "user_in_posts", schema = "public", catalog = "LgErpSystem")
public class UserInPostsEntity {
    private int id;
    private int userId;
    private Date dateFrom;
    private int appointType;
    private int postId;
    private Date dateEnd;
    private UsersEntity usersByUserId;
    private PostsEntity postsByPostId;

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

    @Basic
    @Column(name = "appoint_type", nullable = false)
    public int getAppointType() {
        return appointType;
    }

    public void setAppointType(int appointType) {
        this.appointType = appointType;
    }

    @Basic
    @Column(name = "post_id", nullable = false, insertable = false, updatable = false)
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "date_end", nullable = true)
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInPostsEntity that = (UserInPostsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (appointType != that.appointType) return false;
        if (postId != that.postId) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + appointType;
        result = 31 * result + postId;
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    public PostsEntity getPostsByPostId() {
        return postsByPostId;
    }

    public void setPostsByPostId(PostsEntity postsByPostId) {
        this.postsByPostId = postsByPostId;
    }
}
