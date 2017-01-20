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
    private int contractType;
    private int postId;
    private int departmentId;
    private Date dateEnd;
    private UsersEntity usersByUserId;
    private PostsEntity postsByPostId;
    private ExternalLocalizationsEntity externalsByExternalId;
    private int externalId;

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
    @Column(name = "contract_type", nullable = false)
    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
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
    @Column(name = "external_id", insertable = false, updatable = false)
    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    @Basic
    @Column(name = "department_id")
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
        if (contractType != that.contractType) return false;
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
        result = 31 * result + contractType;
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
    @JoinColumn(name = "external_id", referencedColumnName = "id", nullable = false)
    public ExternalLocalizationsEntity getExternalsByExternalId() {
        return externalsByExternalId;
    }

    public void setExternalsByExternalId(ExternalLocalizationsEntity externalsByExternalsId) {
        this.externalsByExternalId = externalsByExternalsId;
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
