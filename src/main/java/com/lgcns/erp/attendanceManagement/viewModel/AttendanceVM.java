package com.lgcns.erp.attendanceManagement.viewModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lgcns.erp.workflow.Model.Member;

import java.sql.Timestamp;

/**
 * Created by Muslimbek on 6/9/2017.
 */
public class AttendanceVM {
    private Member user;
    @JsonFormat(pattern="yyyy-MM-ddHH-mm-ss")
    private Timestamp attendDate;
    private String status;

    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public Timestamp getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Timestamp attendDate) {
        this.attendDate = attendDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
