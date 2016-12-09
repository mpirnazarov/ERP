package com.lgcns.erp.hr.mapper;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.hr.viewModel.MonitorViewModels.MonitorViewModel;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafatdin on 07.12.2016.
 */
public class MonitorMapper {

    public static Pair<List<MonitorViewModel>,Integer> mapWorkloadsToViewModel(List<WorkloadEntity> workloads, Map<Integer, String> users, Map<Integer, String> projects) {
        List<MonitorViewModel> viewModels = new ArrayList<MonitorViewModel>();
        int totalDuration = 0;
        for (WorkloadEntity w : workloads) {
            totalDuration += w.getDuration();

            MonitorViewModel temp = new MonitorViewModel();
            temp.setUserName(users.get(w.getUserId()));
            temp.setProjectName(projects.get(w.getProjectId()));
            temp.setDate(w.getDate());
            temp.setDuration(String.valueOf(w.getDuration()));
            for(WorkloadType wType : WorkloadType.values()) {
                if (wType.getValue() == w.getWorkloadType())
                    temp.setType(String.valueOf(w.getWorkloadType()));
            }

            viewModels.add(temp);
        }

        return new Pair<List<MonitorViewModel>, Integer>(viewModels,totalDuration);
    }
}
