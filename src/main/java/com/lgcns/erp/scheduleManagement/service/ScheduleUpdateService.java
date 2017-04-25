package com.lgcns.erp.scheduleManagement.service;

import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;

import java.io.IOException;

/**
 * Created by DS on 17.04.2017.
 */
public interface ScheduleUpdateService {
    void deleteParticipant(int scheduleId);
    void deleteReference(int scheduleId);
    void deleteAttachment(int scheduleId) throws IOException;
    void updateParticipantDecision(int participantId, int scheduleId, int status, String reason);
    void deleteSchedule(int scheduleId);
    void deleteAttachmentById(int attachmentId);
    ScheduleAttachmentsEntity getAttachmentById(int id);
    ScheduleEntity getSchedule(int scheduleId);
}
