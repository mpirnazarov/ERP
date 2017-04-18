package com.lgcns.erp.scheduleManagement.serviceImpl;

import com.lgcns.erp.scheduleManagement.DBContext.AttachmentContext;
import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.service.ScheduleUpdateService;

import java.io.File;
import java.io.IOException;

/**
 * Created by DS on 17.04.2017.
 */
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
        DeleteFiles2(scheduleId);
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

    /**
     * ToDo check if this is working
     * @param scheduleId
     * @throws IOException
     */
    public void DeleteFiles2(int scheduleId) throws IOException {
        File file = new File("C:/files/documents/schedule/" + scheduleId+"/");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }
}
