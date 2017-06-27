package com.lgcns.erp.scheduleManagement.mapper;

import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.enums.ParticipantStatus;
import com.lgcns.erp.scheduleManagement.enums.ScheduleType;
import com.lgcns.erp.scheduleManagement.util.ParticipantUtil;
import com.lgcns.erp.scheduleManagement.util.ReferenceUtil;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Enums.LeaveType;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by DS on 06.04.2017.
 */
public class ScheduleMainMapper {
    public static List<ScheduleVM> mapFromScheduleEntityListToVM(List<ScheduleEntity> scheduleEntities){
        List<ScheduleVM> scheduleVMList = new ArrayList<>();

        for (ScheduleEntity entity: scheduleEntities) {
            scheduleVMList.add(mapFromScheduleEntityToVM(entity));
        }
        return scheduleVMList;
    }

    public static ScheduleVM mapFromScheduleEntityToVM(ScheduleEntity entity){
        ScheduleVM scheduleVM = new ScheduleVM();

        scheduleVM.setScheduleId(entity.getScheduleId());
        scheduleVM.setDateTo(entity.getDateTo());
        scheduleVM.setAuthorId(entity.getAutherId());
        scheduleVM.setCompulsory(entity.getCompulsory());
        scheduleVM.setDateFrom(entity.getDateFrom());
        scheduleVM.setDescription(entity.getDescription());
        scheduleVM.setOther(entity.getOther());
       /* scheduleVM.setParticipants(ParticipantUtil.getParticipantIds(ParticipantContext.getParticipantsByScheduleId(entity.getScheduleId())));
        scheduleVM.setReferences(ReferenceUtil.getReferencedIds(ReferenceContext.getReferencesByScheduleId(entity.getScheduleId())));*/
        scheduleVM.setPlace(entity.getPlace());
        scheduleVM.setsType(entity.getStype());
        scheduleVM.setTitle(entity.getTitle());
        scheduleVM.setToNotify(entity.getToNotify());
        scheduleVM.setDraft(entity.getDraft());

        return scheduleVM;
    }

    /**
     *
     * @param scheduleVM
     * @return
     */
    public static ScheduleEntity mapScheduleFromVMToEntity(ScheduleVM scheduleVM){

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setScheduleId(scheduleVM.getScheduleId());
        scheduleEntity.setTitle(scheduleVM.getTitle());
        scheduleEntity.setDescription(scheduleVM.getDescription());
        scheduleEntity.setPlace(scheduleVM.getPlace());
        scheduleEntity.setStype(scheduleVM.getsType());
        scheduleEntity.setOther(scheduleVM.getOther());
        if (scheduleVM.getDateFrom()!=null)
            scheduleEntity.setDateFrom(new java.sql.Timestamp(scheduleVM.getDateFrom().getTime()));
        if (scheduleVM.getDateTo()!=null)
            scheduleEntity.setDateTo(new java.sql.Timestamp(scheduleVM.getDateTo().getTime()));
        scheduleEntity.setCompulsory(scheduleVM.isCompulsory());
        scheduleEntity.setToNotify(scheduleVM.isToNotify());
        scheduleEntity.setDraft(scheduleVM.isDraft());
        scheduleEntity.setAutherId(scheduleVM.getAuthorId());
        scheduleEntity.setPrivate(scheduleVM.isPrivate());

        return scheduleEntity;
    }

    /**
     *  @param scheduleId
     * @param participant
     */
    public static ParticipantInScheduleEntity mapParticipantInSchedule(int scheduleId, int participant) {
        ParticipantInScheduleEntity participantInScheduleEntity = new ParticipantInScheduleEntity();
        participantInScheduleEntity.setScheduleId(scheduleId);
        participantInScheduleEntity.setUserId(participant);
        participantInScheduleEntity.setStatus(ParticipantStatus.Not_decided.getValue());
        return participantInScheduleEntity;
    }

    /**
     *  @param scheduleId
     * @param reference
     */
    public static ReferenceInCheduleEntity mapReferenceInSchedule(int scheduleId, int reference) {
        ReferenceInCheduleEntity referenceInScheduleEntity = new ReferenceInCheduleEntity();
        referenceInScheduleEntity.setScheduleId(scheduleId);
        referenceInScheduleEntity.setUserId(reference);
        return referenceInScheduleEntity;
    }

    /**
     *  @param scheduleId
     * @param multipartFile
     */
    public static ScheduleAttachmentsEntity mapAttachmentInSchedule(int scheduleId, MultipartFile multipartFile) {
        ScheduleAttachmentsEntity scheduleAttachmentsEntity = new ScheduleAttachmentsEntity();
        scheduleAttachmentsEntity.setScheduleId(scheduleId);
        scheduleAttachmentsEntity.setAttachmentPath("C:/files/documents/schedule/" + scheduleId + "/" + multipartFile.getOriginalFilename());
        return scheduleAttachmentsEntity;
    }

    public static ScheduleEntity mapScheduleFromWorkflowToEntity(int requestId) {
        RequestsEntity requestsEntity = WorkflowService.getRequestsEntityById(requestId);

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setTitle(LeaveType.values()[requestsEntity.getLeaveTypeId()-1].name().replace("_", " "));
        scheduleEntity.setDescription(requestsEntity.getDescription());
        /* No schedule place */
        scheduleEntity.setStype(ScheduleType.Other.getValue());
        /* Should be reviewed */
        scheduleEntity.setOther("Busy");
        if(requestsEntity.getDateFrom()==null || requestsEntity.getDateTo()==null){
            return null;
        }
        scheduleEntity.setDateFrom(getTimestamp(requestsEntity.getDateFrom()));
        scheduleEntity.setDateTo(getTimestamp(addDays(requestsEntity.getDateTo(), 1)));
        scheduleEntity.setCompulsory(Boolean.TRUE);
        scheduleEntity.setToNotify(Boolean.FALSE);
        scheduleEntity.setDraft(Boolean.FALSE);
        scheduleEntity.setAutherId(requestsEntity.getUserFromId());



        return scheduleEntity;
    }

    public static Timestamp getTimestamp(java.util.Date date){
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
