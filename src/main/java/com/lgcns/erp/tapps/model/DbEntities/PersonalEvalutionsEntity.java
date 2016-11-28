package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "personal_evalutions", schema = "public", catalog = "LgErpSystem")
public class PersonalEvalutionsEntity {
    private int id;
    private int userId;
    private int grade;
    private Date date;
    private int evaluatorId;
    private UsersEntity usersByUserId;

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
    @Column(name = "grade", nullable = false)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "evaluator_id", nullable = false, length = 10)
    public int getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalEvalutionsEntity that = (PersonalEvalutionsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (grade != that.grade) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (evaluatorId != that.evaluatorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + grade;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + evaluatorId;
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
