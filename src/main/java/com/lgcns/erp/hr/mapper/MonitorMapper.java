package com.lgcns.erp.hr.mapper;

import com.lgcns.erp.hr.enums.WorkloadType;
import com.lgcns.erp.hr.viewModel.MonitorViewModels.MonitorResponseViewModel;
import com.lgcns.erp.hr.viewModel.MonitorViewModels.MonitorViewModel;
import com.lgcns.erp.tapps.model.DbEntities.WorkloadEntity;
import javafx.util.Pair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            temp.setUserId(w.getUserId());
            temp.setProjectName(projects.get(w.getProjectId()));
            temp.setProjectId(w.getProjectId());
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

    public static List<MonitorResponseViewModel> mapValuesToAjaxModel(List<MonitorViewModel> values) {
        List<MonitorResponseViewModel> returning = new ArrayList<MonitorResponseViewModel>();
        for(MonitorViewModel monitor : values){
            MonitorResponseViewModel temp = new MonitorResponseViewModel();
            temp.setEmployee(monitor.getUserName());
            temp.setProject(monitor.getProjectName());
            temp.setType(setMonitorModelsType(monitor.getType()));
            temp.setDate(monitor.getDate().toString());
            temp.setDuration(monitor.getDuration());
            returning.add(temp);
        }
        Collections.sort(returning, new Comparator<MonitorResponseViewModel>() {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            @Override
            public int compare(MonitorResponseViewModel o1, MonitorResponseViewModel o2) {
                try {
                    return f.parse(o1.getDate()).compareTo(f.parse(o2.getDate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        return returning;
    }

    private static String setMonitorModelsType(String typeId){
        int id = Integer.valueOf(typeId);
        for (WorkloadType wType : WorkloadType.values()){
            if(wType.getValue() == id)
                return wType.name().replace('_', ' ');
        }
        return "Work Administrative";
    }
}
