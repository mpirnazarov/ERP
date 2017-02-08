package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Muslimbek Pirnazarov on 2/7/2017.
 */
@Entity
@Table(name = "steps", schema = "workflow", catalog = "LgErpSystem")
public class StepsEntity {
    private int id;
    private int requestId;
    private int userId;
    private int involvementTypeId;
    private Integer stepSequence;
    private int statusId;
    private Date statusDate;
    private Boolean isActive;
    private Collection<StepCommentsEntity> stepCommentssById;
    private RequestsEntity requestsByRequestId;
    private InvolvementTypesEntity involvementTypesByInvolvementTypeId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "request_id")
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "involvement_type_id")
    public int getInvolvementTypeId() {
        return involvementTypeId;
    }

    public void setInvolvementTypeId(int involvementTypeId) {
        this.involvementTypeId = involvementTypeId;
    }

    @Basic
    @Column(name = "step_sequence")
    public Integer getStepSequence() {
        return stepSequence;
    }

    public void setStepSequence(Integer stepSequence) {
        this.stepSequence = stepSequence;
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
    @Column(name = "status_date")
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepsEntity that = (StepsEntity) o;

        if (id != that.id) return false;
        if (requestId != that.requestId) return false;
        if (userId != that.userId) return false;
        if (involvementTypeId != that.involvementTypeId) return false;
        if (statusId != that.statusId) return false;
        if (stepSequence != null ? !stepSequence.equals(that.stepSequence) : that.stepSequence != null) return false;
        if (statusDate != null ? !statusDate.equals(that.statusDate) : that.statusDate != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + requestId;
        result = 31 * result + userId;
        result = 31 * result + involvementTypeId;
        result = 31 * result + (stepSequence != null ? stepSequence.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (statusDate != null ? statusDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "stepsByStepsId")
    public Collection<StepCommentsEntity> getStepCommentssById() {
        return stepCommentssById;
    }

    public void setStepCommentssById(Collection<StepCommentsEntity> stepCommentssById) {
        this.stepCommentssById = stepCommentssById;
    }

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RequestsEntity getRequestsByRequestId() {
        return requestsByRequestId;
    }

    public void setRequestsByRequestId(RequestsEntity requestsByRequestId) {
        this.requestsByRequestId = requestsByRequestId;
    }

    @ManyToOne
    @JoinColumn(name = "involvement_type_id", referencedColumnName = "id", nullable = false)
    public InvolvementTypesEntity getInvolvementTypesByInvolvementTypeId() {
        return involvementTypesByInvolvementTypeId;
    }

    public void setInvolvementTypesByInvolvementTypeId(InvolvementTypesEntity involvementTypesByInvolvementTypeId) {
        this.involvementTypesByInvolvementTypeId = involvementTypesByInvolvementTypeId;
    }
}
