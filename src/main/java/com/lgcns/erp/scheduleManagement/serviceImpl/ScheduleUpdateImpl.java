package com.lgcns.erp.scheduleManagement.serviceImpl;

import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.service.ScheduleUpdateService;

/**
 * Created by DS on 17.04.2017.
 */
public class ScheduleUpdateImpl implements ScheduleUpdateService {
    @Override
    public void deleteParticipant(int userId, int scheduleId) {
        ParticipantContext.deleteParticipant(userId, scheduleId);
    }

    @Override
    public void deleteReference(int userId, int scheduleId) {
        ReferenceContext.deleteReference(userId, scheduleId);
    }

    @Override
    public void updateParticipantDecision(int participantId, int scheduleId, int status, String reason) {
        ParticipantContext.updateParticipantDecision(participantId, scheduleId, status, reason);
    }
}
