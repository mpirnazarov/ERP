package com.lgcns.erp.scheduleManagement.service;

import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;

import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */
public interface ScheduleMainService {
    List<ScheduleEntity> getScheduleList();
}
