package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by Muslimbek Pirnazarov on 1/23/2017.
 */
@Entity
@Table(name = "requests", schema = "workflow", catalog = "LgErpSystem")
public class RequestsEntity {
    private long id;
    private int userFromId;
    private Integer leaveTypeId;
    private String subject;
    private Boolean isDomestic;
    private int tripTypeId;
    private int typeId;
    private Date dateFrom;
    private Date dateTo;
    private String description;
    private int statusId;
    private Date dateCreated;
    private String destination;
    private Collection<AttachmentsEntity> attachmentssById;
    private Collection<MembersEntity> memberssById;
    private TripTypesEntity tripTypesByTripTypeId;
    private Collection<StepsEntity> stepssById;
    private Collection<ToDoEntity> toDosById;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_from_id")
    public int getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(int userFromId) {
        this.userFromId = userFromId;
    }

    @Basic
    @Column(name = "leave_type_id")
    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "is_domestic")
    public Boolean getDomestic() {
        return isDomestic;
    }

    public void setDomestic(Boolean domestic) {
        isDomestic = domestic;
    }

    @Basic
    @Column(name = "trip_type_id")
    public int getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(int tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    @Basic
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "date_from")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "date_to")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "date_created")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestsEntity that = (RequestsEntity) o;

        if (id != that.id) return false;
        if (userFromId != that.userFromId) return false;
        if (tripTypeId != that.tripTypeId) return false;
        if (typeId != that.typeId) return false;
        if (statusId != that.statusId) return false;
        if (leaveTypeId != null ? !leaveTypeId.equals(that.leaveTypeId) : that.leaveTypeId != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (isDomestic != null ? !isDomestic.equals(that.isDomestic) : that.isDomestic != null) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + userFromId;
        result = 31 * result + (leaveTypeId != null ? leaveTypeId.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (isDomestic != null ? isDomestic.hashCode() : 0);
        result = 31 * result + tripTypeId;
        result = 31 * result + typeId;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "requestsByRequestId")
    public Collection<AttachmentsEntity> getAttachmentssById() {
        return attachmentssById;
    }

    public void setAttachmentssById(Collection<AttachmentsEntity> attachmentssById) {
        this.attachmentssById = attachmentssById;
    }

    @OneToMany(mappedBy = "requestsByRequestId")
    public Collection<MembersEntity> getMemberssById() {
        return memberssById;
    }

    public void setMemberssById(Collection<MembersEntity> memberssById) {
        this.memberssById = memberssById;
    }

    @ManyToOne
    @JoinColumn(name = "trip_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TripTypesEntity getTripTypesByTripTypeId() {
        return tripTypesByTripTypeId;
    }

    public void setTripTypesByTripTypeId(TripTypesEntity tripTypesByTripTypeId) {
        this.tripTypesByTripTypeId = tripTypesByTripTypeId;
    }

    @OneToMany(mappedBy = "requestsByRequestId")
    public Collection<StepsEntity> getStepssById() {
        return stepssById;
    }

    public void setStepssById(Collection<StepsEntity> stepssById) {
        this.stepssById = stepssById;
    }

    @OneToMany(mappedBy = "requestsByRequestId")
    public Collection<ToDoEntity> getToDosById() {
        return toDosById;
    }

    public void setToDosById(Collection<ToDoEntity> toDosById) {
        this.toDosById = toDosById;
    }

    @Override
    public String toString() {
        return "RequestsEntity{" +
                "id=" + id +
                ", userFromId=" + userFromId +
                ", leaveTypeId=" + leaveTypeId +
                ", subject='" + subject + '\'' +
                ", isDomestic=" + isDomestic +
                ", tripTypeId=" + tripTypeId +
                ", typeId=" + typeId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", description='" + description + '\'' +
                ", statusId=" + statusId +
                ", dateCreated=" + dateCreated +
                ", destination='" + destination + '\'' +
                ", attachmentssById=" + attachmentssById +
                ", memberssById=" + memberssById +
                ", tripTypesByTripTypeId=" + tripTypesByTripTypeId +
                ", stepssById=" + stepssById +
                ", toDosById=" + toDosById +
                '}';
    }
}
