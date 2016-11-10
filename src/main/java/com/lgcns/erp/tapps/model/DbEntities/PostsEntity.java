package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "posts", schema = "public", catalog = "LgErpSystem")
public class PostsEntity {
    private int id;
    private Collection<PostLocalizationsEntity> postLocalizationsesById;

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

        PostsEntity that = (PostsEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    @OneToMany(mappedBy = "postsByPostId")
    public Collection<PostLocalizationsEntity> getPostLocalizationsesById() {
        return postLocalizationsesById;
    }

    public void setPostLocalizationsesById(Collection<PostLocalizationsEntity> postLocalizationsesById) {
        this.postLocalizationsesById = postLocalizationsesById;
    }
}
