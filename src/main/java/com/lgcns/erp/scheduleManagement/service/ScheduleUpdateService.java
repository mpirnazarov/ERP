package com.lgcns.erp.scheduleManagement.service;

/**
 * Created by DS on 17.04.2017.
 */
public interface ScheduleUpdateService {
    void deleteParticipant(int userId, int scheduleId);
    void deleteReference(int userId, int scheduleId);
    void updateParticipantDecision(int participantId, int scheduleId, int status, String reason);
}
