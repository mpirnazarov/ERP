package com.lgcns.erp.scheduleManagement.mapper;

import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.util.ParticipantUtil;
import com.lgcns.erp.scheduleManagement.util.ReferenceUtil;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
        //todo Add attachments
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
        scheduleEntity.setDateFrom(new java.sql.Timestamp(scheduleVM.getDateFrom().getTime()));
        scheduleEntity.setDateTo(new java.sql.Timestamp(scheduleVM.getDateTo().getTime()));
        scheduleEntity.setCompulsory(scheduleVM.isCompulsory());
        scheduleEntity.setToNotify(scheduleVM.isToNotify());
        scheduleEntity.setDraft(scheduleVM.isDraft());
        scheduleEntity.setAutherId(scheduleVM.getAuthorId());

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
}
