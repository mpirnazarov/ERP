package com.lgcns.erp.scheduleManagement.mapper;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by DS on 06.04.2017.
 */
public class ScheduleMainMapper {
    public static List<ScheduleVM> mapFromScheduleEntityListToVM(List<ScheduleEntity> scheduleEntities){

        return null;
    }

    public static ScheduleVM mapFromScheduleEntityToVM(ScheduleEntity scheduleEntity){

        return null;
    }

    /**
     *
     * @param scheduleVM
     * @return
     */
    public static ScheduleEntity mapScheduleFromVMToEntity(ScheduleVM scheduleVM){

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setTitle(scheduleVM.getTitle());
        scheduleEntity.setDescription(scheduleVM.getDescription());
        scheduleEntity.setPlace(scheduleVM.getPlace());
        scheduleEntity.setsType(scheduleVM.getsType());
        scheduleEntity.setOther(scheduleVM.getOther());
        scheduleEntity.setDateFrom(new java.sql.Timestamp(scheduleVM.getDateFrom().getTime()));
        scheduleEntity.setDateTo(new java.sql.Timestamp(scheduleVM.getDateTo().getTime()));
        scheduleEntity.setCompulsory(scheduleVM.isCompulsory());
        scheduleEntity.setToNotify(scheduleVM.isToNotify());
        scheduleEntity.setDraft(scheduleVM.isDraft());
        scheduleEntity.setAuthorId(scheduleVM.getAuthorId());

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
    public static ReferenceInScheduleEntity mapReferenceInSchedule(int scheduleId, int reference) {
        ReferenceInScheduleEntity referenceInScheduleEntity = new ReferenceInScheduleEntity();
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
