package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "documents", schema = "public", catalog = "LgErpSystem")
public class DocumentsEntity {
    private int id;
    private int userId;
    private BigInteger documentType;
    private String link;
    private String name;
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
    @Column(name = "document_type", nullable = false, precision = 0)
    public BigInteger getDocumentType() {
        return documentType;
    }

    public void setDocumentType(BigInteger documentType) {
        this.documentType = documentType;
    }

    @Basic
    @Column(name = "link", nullable = false, length = -1)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentsEntity that = (DocumentsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (documentType != null ? !documentType.equals(that.documentType) : that.documentType != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (documentType != null ? documentType.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
}
