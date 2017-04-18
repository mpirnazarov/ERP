package com.lgcns.erp.scheduleManagement.serviceImpl;

import com.lgcns.erp.scheduleManagement.DBContext.AttachmentContext;
import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */
public class ScheduleMainmpl implements ScheduleMainService {

    @Override
    public List<ScheduleVM> getScheduleList(Timestamp start, Timestamp end) {
        List<ScheduleVM> scheduleVMList = ScheduleMainMapper.mapFromScheduleEntityListToVM(ScheduleMainContext.getScheduleList(start, end));
        return scheduleVMList;
    }

    @Override
    public int insertSchedule(ScheduleEntity scheduleEntity) {
        return ScheduleMainContext.insertSchedule(scheduleEntity);
    }

    @Override
    public void insertParticipants(ParticipantInScheduleEntity participantInScheduleEntity) {
        ParticipantContext.insertParticipants(participantInScheduleEntity);
    }

    @Override
    public void insertReference(ReferenceInCheduleEntity referenceInScheduleEntity) {
        ReferenceContext.insertReference(referenceInScheduleEntity);
    }

    @Override
    public void insertAttachment(ScheduleAttachmentsEntity scheduleAttachmentsEntity) {
        AttachmentContext.insertAttachment(scheduleAttachmentsEntity);
    }

    @Override
    public void updateSchedule(ScheduleEntity scheduleEntity) {
        ScheduleMainContext.updateSchedule(scheduleEntity);
    }
}
