package com.lgcns.erp.scheduleManagement.util.email;

import com.lgcns.erp.scheduleManagement.DBContext.DetailsContext;
import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.enums.ActionTypeId;
import com.lgcns.erp.scheduleManagement.enums.ScheduleEventInvolvement;
import com.lgcns.erp.tapps.DbContext.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by DS on 26.04.2017.
 */
public class EmailMessageContent {

    public String generateSubject(int action) {
        String msg = "";

        if (action == ActionTypeId.Create.getValue()) {
            msg = "Creation of new event";
        } else if (action == ActionTypeId.Delete.getValue()) {
            msg = "Deletion of an event";
        } else if (action == ActionTypeId.Update.getValue()) {
            msg = "Update of an event";
        } else if (action == ActionTypeId.ParticipantDecide.getValue()) {
            msg = "A participant has decided";
        }
        return msg;
    }

    public String generateMessage(int scheduleId, int action, int involvementType, String message, int sentToId) {
        /* Message creation */
        String msg = "";
        ScheduleEntity scheduleEntity = DetailsContext.getScheduleById(scheduleId);
        String creator = UserService.getUserFullNameInLanguageById(scheduleEntity.getAutherId(), 3);
        String sentTo = UserService.getUserFullNameInLanguageById(sentToId, 3);

        if (involvementType == ScheduleEventInvolvement.Author.getValue()) {
            if (action == ActionTypeId.Create.getValue())
                msg = String.format(message, creator, scheduleEntity.getTitle());
            else if (action == ActionTypeId.Update.getValue())
                msg = String.format(message, creator, scheduleEntity.getTitle());
            else if (action == ActionTypeId.ParticipantDecide.getValue())
                msg = String.format(message, creator, sentTo);
            else
                msg = String.format(message, creator, scheduleEntity.getTitle());
        } else if (involvementType == ScheduleEventInvolvement.Participant.getValue() || involvementType == ScheduleEventInvolvement.Referenced.getValue()) {
            if (action == ActionTypeId.Create.getValue())
                msg = String.format(message, creator, scheduleEntity.getTitle(), sentTo);
            else if (action == ActionTypeId.Update.getValue())
                msg = String.format(message, sentTo, scheduleEntity.getTitle(), creator);
            else if (action == ActionTypeId.Delete.getValue())
                msg = String.format(message, sentTo, scheduleEntity.getTitle(), creator);
        }

        return msg;
    }
}
