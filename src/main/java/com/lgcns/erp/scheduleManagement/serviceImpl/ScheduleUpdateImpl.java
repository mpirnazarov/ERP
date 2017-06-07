package com.lgcns.erp.scheduleManagement.serviceImpl;

import com.lgcns.erp.scheduleManagement.DBContext.*;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.service.ScheduleUpdateService;
import com.lgcns.erp.scheduleManagement.util.AttachmentUtil;
import com.lgcns.erp.workflow.DBEntities.AttachmentsEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by DS on 17.04.2017.
 */
@Service
public class ScheduleUpdateImpl implements ScheduleUpdateService {
    @Override
    public void deleteParticipant(int scheduleId) {
        ParticipantContext.deleteParticipant(scheduleId);
    }

    @Override
    public void deleteReference(int scheduleId) {
        ReferenceContext.deleteReference(scheduleId);
    }

    @Override
    public void deleteAttachment(int scheduleId) throws IOException {
        AttachmentUtil.deleteFilesByScheduleId(scheduleId);
        AttachmentContext.deleteAttachment(scheduleId);
    }

    @Override
    public void deleteSchedule(int scheduleId) {
        ScheduleMainContext.deleteSchedule(scheduleId);
    }

    @Override
    public void updateParticipantDecision(int participantId, int scheduleId, int status, String reason) {
        ParticipantContext.updateParticipantDecision(participantId, scheduleId, status, reason);
    }

    @Override
    public void deleteAttachmentById(int attachmentId) {
        AttachmentContext.deleteAttachmentById(attachmentId);
    }

    @Override
    public ScheduleAttachmentsEntity getAttachmentById(int id) {
        return AttachmentContext.getAttachmentById(id);
    }

    @Override
    public ScheduleEntity getSchedule(int scheduleId) {
        return DetailsContext.getScheduleById(scheduleId);
    }

    @Override
    public int[] getParticipantsByScheduleId(int scheduleId) {
        List<ParticipantInScheduleEntity> participantInScheduleEntityList = ParticipantContext.getParticipantsByScheduleId(scheduleId);
        int[] ids = new int[participantInScheduleEntityList.size()];
        int counter = 0;
        for (ParticipantInScheduleEntity entity : participantInScheduleEntityList) {
            ids[counter] = entity.getUserId();
            counter++;
        }

        return ids;
    }

    @Override
    public int[] getReferencesByScheduleId(int scheduleId) {
        List<ReferenceInCheduleEntity> entities = ReferenceContext.getReferencesByScheduleId(scheduleId);
        int[] ids = new int[entities.size()];
        int counter = 0;
        for (ReferenceInCheduleEntity entity : entities) {
            ids[counter] = entity.getUserId();
            counter++;
        }

        return ids;
    }
}
