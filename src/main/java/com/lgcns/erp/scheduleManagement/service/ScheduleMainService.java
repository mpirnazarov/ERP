package com.lgcns.erp.scheduleManagement.service;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */
public interface ScheduleMainService {
    List<ScheduleVM> getScheduleList(Timestamp start, Timestamp end, int userId);
    int insertSchedule(ScheduleEntity scheduleEntity);
    void insertParticipants(ParticipantInScheduleEntity participantInScheduleEntity);
    void insertReference(ReferenceInCheduleEntity referenceInScheduleEntity);
    void insertAttachment(ScheduleAttachmentsEntity scheduleAttachmentsEntity);
    void updateSchedule(ScheduleEntity schedule);
}
