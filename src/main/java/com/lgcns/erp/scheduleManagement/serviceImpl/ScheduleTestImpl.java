package com.lgcns.erp.scheduleManagement.serviceImpl;

import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;

import java.util.List;

/**
 * Created by DS on 05.04.2017.
 */
public class ScheduleTestImpl implements ScheduleMainService {

    @Override
    public List<ScheduleEntity> getScheduleList() {
        ScheduleMainContext.getScheduleList();
        return null;
    }
}
