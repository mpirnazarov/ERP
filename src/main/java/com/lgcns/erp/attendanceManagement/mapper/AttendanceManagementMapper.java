/*
package com.lgcns.erp.attendanceManagement.mapper;

import com.lgcns.erp.attendanceManagement.DBEntities.AttendanceManagementEntity;
import com.lgcns.erp.attendanceManagement.enums.AttendanceStatus;
import com.lgcns.erp.attendanceManagement.viewModel.AttendanceVM;
import com.lgcns.erp.workflow.Mapper.MembersMapper;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.List;

*/
/**
 * Created by Muslimbek on 6/9/2017.
 *//*

public class AttendanceManagementMapper {
    public static AttendanceManagementEntity mapAttendanceManagement(int userId) {
        AttendanceManagementEntity attendanceManagementEntity = new AttendanceManagementEntity();
        attendanceManagementEntity.setUserId(userId);
        attendanceManagementEntity.setAttendDate(new Timestamp(System.currentTimeMillis()));
        attendanceManagementEntity.setStatus(3);
        return attendanceManagementEntity;
    }

    public static AttendanceVM mapAttendanceVMFromEntity(AttendanceManagementEntity attendanceManagementEntity) {
        AttendanceVM attendanceVM = new AttendanceVM();
        attendanceVM.setUser(MembersMapper.getMember(attendanceManagementEntity.getUserId()));
        attendanceVM.setStatus(AttendanceStatus.values()[attendanceManagementEntity.getStatus()-1].name().replace("_", " "));
        //attendanceVM.setAttendDate(DateTime.parse(attendanceManagementEntity.getAttendDate().toString()));
        return attendanceVM;
    }
}
*/
