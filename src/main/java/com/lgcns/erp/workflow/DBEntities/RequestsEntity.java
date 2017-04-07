package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by DS on 21.02.2017.
 */
@Entity
@Table(name = "requests", schema = "workflow", catalog = "LgErpSystem")
public class RequestsEntity {
    private int id;
    private int userFromId;
    private Integer leaveTypeId;
    private Integer leavingHours;
    private String subject;
    private Boolean isDomestic;
    private Integer tripTypeId;
    private Integer typeId;
    private Date dateFrom;
    private Date dateTo;
    private String description;
    private int statusId;
    private Date dateCreated;
    private String destination;
    private Boolean isViewed;
    private Integer reqLinkId;
    private Collection<AttachmentsEntity> attachmentssById;
    private Collection<MembersEntity> memberssById;
    private TripTypesEntity tripTypesByTripTypeId;
    private RequestsEntity requestsByReqLinkId;
    private Collection<RequestsEntity> requestssById;
    private Collection<StepsEntity> stepssById;
    private Collection<ToDoEntity> toDosById;

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
    @Column(name = "leaving_hours")
    public Integer getLeavingHours() {
        return leavingHours;
    }

    public void setLeavingHours(Integer leavingHours) {
        this.leavingHours = leavingHours;
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
    public Integer getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(Integer tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    @Basic
    @Column(name = "type_id")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
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

    @Basic
    @Column(name = "is_viewed")
    public Boolean getViewed() {
        return isViewed;
    }

    public void setViewed(Boolean viewed) {
        isViewed = viewed;
    }

    @Basic
    @Column(name = "req_link_id")
    public Integer getReqLinkId() {
        return reqLinkId;
    }

    public void setReqLinkId(Integer reqLinkId) {
        this.reqLinkId = reqLinkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestsEntity entity = (RequestsEntity) o;

        if (id != entity.id) return false;
        if (userFromId != entity.userFromId) return false;
        if (statusId != entity.statusId) return false;
        if (leaveTypeId != null ? !leaveTypeId.equals(entity.leaveTypeId) : entity.leaveTypeId != null) return false;
        if (subject != null ? !subject.equals(entity.subject) : entity.subject != null) return false;
        if (isDomestic != null ? !isDomestic.equals(entity.isDomestic) : entity.isDomestic != null) return false;
        if (tripTypeId != null ? !tripTypeId.equals(entity.tripTypeId) : entity.tripTypeId != null) return false;
        if (typeId != null ? !typeId.equals(entity.typeId) : entity.typeId != null) return false;
        if (dateFrom != null ? !dateFrom.equals(entity.dateFrom) : entity.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(entity.dateTo) : entity.dateTo != null) return false;
        if (description != null ? !description.equals(entity.description) : entity.description != null) return false;
        if (dateCreated != null ? !dateCreated.equals(entity.dateCreated) : entity.dateCreated != null) return false;
        if (destination != null ? !destination.equals(entity.destination) : entity.destination != null) return false;
        if (isViewed != null ? !isViewed.equals(entity.isViewed) : entity.isViewed != null) return false;
        if (reqLinkId != null ? !reqLinkId.equals(entity.reqLinkId) : entity.reqLinkId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userFromId;
        result = 31 * result + (leaveTypeId != null ? leaveTypeId.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (isDomestic != null ? isDomestic.hashCode() : 0);
        result = 31 * result + (tripTypeId != null ? tripTypeId.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (isViewed != null ? isViewed.hashCode() : 0);
        result = 31 * result + (reqLinkId != null ? reqLinkId.hashCode() : 0);
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
    @JoinColumn(name = "trip_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    public TripTypesEntity getTripTypesByTripTypeId() {
        return tripTypesByTripTypeId;
    }

    public void setTripTypesByTripTypeId(TripTypesEntity tripTypesByTripTypeId) {
        this.tripTypesByTripTypeId = tripTypesByTripTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "req_link_id", referencedColumnName = "id", insertable = false, updatable = false)
    public RequestsEntity getRequestsByReqLinkId() {
        return requestsByReqLinkId;
    }

    public void setRequestsByReqLinkId(RequestsEntity requestsByReqLinkId) {
        this.requestsByReqLinkId = requestsByReqLinkId;
    }

    @OneToMany(mappedBy = "requestsByReqLinkId")
    public Collection<RequestsEntity> getRequestssById() {
        return requestssById;
    }

    public void setRequestssById(Collection<RequestsEntity> requestssById) {
        this.requestssById = requestssById;
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
}
