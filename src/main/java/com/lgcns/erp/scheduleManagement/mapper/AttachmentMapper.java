package com.lgcns.erp.scheduleManagement.mapper;

import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.model.Attachment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class AttachmentMapper {
    public static List<Attachment> mapAttachmentEntitiesToAttachment(List<ScheduleAttachmentsEntity> entities){

        if (entities.isEmpty())
            return null;

        List<Attachment> attachmentList = new ArrayList<>();
        Attachment attachment = null;

        for (ScheduleAttachmentsEntity entity : entities) {
            attachment = new Attachment();
            attachment.setAttachmentId(entity.getAttachmentId());
            attachment.setAttachmentPath(entity.getAttachmentPath());

            attachmentList.add(attachment);
        }

        return attachmentList;
    }

}
