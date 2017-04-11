package com.lgcns.erp.scheduleManagement.service;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;

import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */
public interface ScheduleMainService {
    List<ScheduleEntity> getScheduleList();
    int insertSchedule(ScheduleEntity scheduleEntity);
    void insertParticipants(ParticipantInScheduleEntity participantInScheduleEntity);
    void insertReference(ReferenceInScheduleEntity referenceInScheduleEntity);

    void insertAttachment(ScheduleAttachmentsEntity scheduleAttachmentsEntity);
}
