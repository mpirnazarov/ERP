package com.lgcns.erp.scheduleManagement.util;

import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;

import java.util.List;

/**
 * Created by DS on 12.04.2017.
 */
public class ReferenceUtil {

    public static int[] getReferencedIds(List<ReferenceInCheduleEntity> entities){
        int[] ids = new int[entities.size()];
        int counter = 0;

        for (ReferenceInCheduleEntity entity : entities) {
            ids[counter] = entity.getUserId();
            counter++;
        }

        return ids;
    }
}
