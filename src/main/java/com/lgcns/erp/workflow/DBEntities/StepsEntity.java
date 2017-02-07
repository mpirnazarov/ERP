package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Muslimbek Pirnazarov on 1/23/2017.
 */
@Entity
@Table(name = "steps", schema = "workflow", catalog = "LgErpSystem")
public class StepsEntity {
    private long id;
    private int requestId;
    private int userId;
    private int involvementTypeId;
    private Integer sequence;
    private int statusId;
    private Date statusDate;
    private RequestsEntity requestsByRequestId;
    private InvolvementTypesEntity involvementTypesByInvolvementTypeId;
    private Collection<StepCommentsEntity> stepCommentssById;
    private Boolean isActive;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "sequence")
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
        if (sequence != null ? !sequence.equals(that.sequence) : that.sequence != null) return false;
        if (statusDate != null ? !statusDate.equals(that.statusDate) : that.statusDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + requestId;
        result = 31 * result + userId;
        result = 31 * result + involvementTypeId;
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (statusDate != null ? statusDate.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "involvement_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public InvolvementTypesEntity getInvolvementTypesByInvolvementTypeId() {
        return involvementTypesByInvolvementTypeId;
    }

    public void setInvolvementTypesByInvolvementTypeId(InvolvementTypesEntity involvementTypesByInvolvementTypeId) {
        this.involvementTypesByInvolvementTypeId = involvementTypesByInvolvementTypeId;
    }

    @OneToMany(mappedBy = "stepsByStepsId")
    public Collection<StepCommentsEntity> getStepCommentssById() {
        return stepCommentssById;
    }

    public void setStepCommentssById(Collection<StepCommentsEntity> stepCommentssById) {
        this.stepCommentssById = stepCommentssById;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
