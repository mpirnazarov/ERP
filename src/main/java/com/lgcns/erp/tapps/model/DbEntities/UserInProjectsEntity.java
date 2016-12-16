package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "user_in_projects", schema = "public", catalog = "LgErpSystem")
public class UserInProjectsEntity {
    private int id;
    private int userId;
    private int projectId;
    private int roleId;
    private Date dateFrom;
    private Date dateTo;
    private UsersEntity usersByUserId;
    private ProjectsEntity projectsByProjectId;

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
    @Column(name = "project_id", nullable = false, insertable = false, updatable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "date_from", nullable = false)
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date returning = new Date();
        try {
            returning = df.parse(dateFrom);
            String newDateString = df.format(returning);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dateFrom = returning;
    }
    @Basic
    @Column(name = "date_to", nullable = false)
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setDateTo(String dateTo) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date returning = new Date();
        try {
            returning = df.parse(dateTo);
            String newDateString = df.format(returning);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.dateFrom = returning;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInProjectsEntity that = (UserInProjectsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (projectId != that.projectId) return false;
        if (roleId != that.roleId) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + projectId;
        result = 31 * result + roleId;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectsEntity getProjectsByProjectId() {
        return projectsByProjectId;
    }

    public void setProjectsByProjectId(ProjectsEntity projectsByProjectId) {
        this.projectsByProjectId = projectsByProjectId;
    }


}
