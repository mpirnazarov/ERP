package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;

/**
 * Created by Muslimbek Pirnazarov on 2/9/2017.
 */
@Entity
@Table(name = "step_comments", schema = "workflow", catalog = "LgErpSystem")
public class StepCommentsEntity {
    private int id;
    private int statusId;
    private int stepsId;
    private String comments;
    private StepsEntity stepsByStepsId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status_id")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "steps_id")
    public int getStepsId() {
        return stepsId;
    }

    public void setStepsId(int stepsId) {
        this.stepsId = stepsId;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepCommentsEntity that = (StepCommentsEntity) o;

        if (id != that.id) return false;
        if (statusId != that.statusId) return false;
        if (stepsId != that.stepsId) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + statusId;
        result = 31 * result + stepsId;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "steps_id", referencedColumnName = "id", nullable = false)
    public StepsEntity getStepsByStepsId() {
        return stepsByStepsId;
    }

    public void setStepsByStepsId(StepsEntity stepsByStepsId) {
        this.stepsByStepsId = stepsByStepsId;
    }
}
