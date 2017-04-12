package com.lgcns.erp.scheduleManagement.util;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;

import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class ParticipantUtil {
    public static int[] getParticipantIds(List<ParticipantInScheduleEntity> entities){
        int[] ids = new int[entities.size()];
        int counter = 0;

        for (ParticipantInScheduleEntity entity : entities) {
            ids[counter] = entity.getUserId();
            counter++;
        }

        return ids;
    }
}
