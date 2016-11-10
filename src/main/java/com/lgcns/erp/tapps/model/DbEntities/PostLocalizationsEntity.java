package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "post_localizations", schema = "public", catalog = "LgErpSystem")
public class PostLocalizationsEntity {
    private int id;
    private String name;
    private int postId;
    private int languageId;
    private PostsEntity postsByPostId;

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
    @Column(name = "post_id", nullable = false, insertable = false, updatable = false)
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "language_id", nullable = false, insertable = false, updatable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostLocalizationsEntity that = (PostLocalizationsEntity) o;

        if (id != that.id) return false;
        if (postId != that.postId) return false;
        if (languageId != that.languageId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + postId;
        result = 31 * result + languageId;
        return result;
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
