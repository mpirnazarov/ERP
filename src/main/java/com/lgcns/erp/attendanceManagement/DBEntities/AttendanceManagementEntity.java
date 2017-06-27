/*
package com.lgcns.erp.attendanceManagement.DBEntities;

import javax.persistence.*;
import java.sql.Timestamp;

*/
/**
 * Created by Muslimbek on 6/9/2017.
 *//*

@Entity
@Table(name = "attendance_management", schema = "attendance", catalog = "LgErpSystem")
public class AttendanceManagementEntity {
    private int id;
    private Integer userId;
    private Timestamp attendDate;
    private Integer status;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "attend_date")
    public Timestamp getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Timestamp attendDate) {
        this.attendDate = attendDate;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendanceManagementEntity that = (AttendanceManagementEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (attendDate != null ? !attendDate.equals(that.attendDate) : that.attendDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (attendDate != null ? attendDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }


}
*/
