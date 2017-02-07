package com.lgcns.erp.workflow.ViewModel;

import com.lgcns.erp.workflow.DBEntities.MembersEntity;
import com.lgcns.erp.workflow.DBEntities.ToDoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 1/30/2017.
 */
public class BusinessTripVM {
    int id, typeDomOver, tripType;
    private String subject, destination, purpose;
    private java.sql.Date start, end;
    private MultipartFile[] file;
    private List<MembersEntity> membersEntityList;
    private List<ToDoEntity> toDoEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeDomOver() {
        return typeDomOver;
    }

    public void setTypeDomOver(int typeDomOver) {
        this.typeDomOver = typeDomOver;
    }

    public int getTripType() {
        return tripType;
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<MembersEntity> getMembersEntityList() {
        return membersEntityList;
    }

    public void setMembersEntityList(List<MembersEntity> membersEntityList) {
        this.membersEntityList = membersEntityList;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    public List<ToDoEntity> getToDoEntityList() {
        return toDoEntityList;
    }

    public void setToDoEntityList(List<ToDoEntity> toDoEntityList) {
        this.toDoEntityList = toDoEntityList;
    }

    @Override
    public String toString() {
        return "BusinessTripVM{" +
                "id=" + id +
                ", typeDomOver=" + typeDomOver +
                ", tripType=" + tripType +
                ", subject='" + subject + '\'' +
                ", destination='" + destination + '\'' +
                ", purpose='" + purpose + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", file=" + file +
                ", membersEntityList=" + membersEntityList +
                ", toDoEntityList=" + toDoEntityList +
                '}';
    }
}