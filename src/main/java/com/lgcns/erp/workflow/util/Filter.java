package com.lgcns.erp.workflow.util;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Mapper.ToDoMapper;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 25.01.2017.
 */
public class Filter {

    public static List<ToDoViewModel> filter(final int formType, int status, int attribute, String attributeValue){
        List<RequestsEntity> requests = WorkflowService.getRequestList();
        /*List<RequestsEntity> requestsResult = requests.stream().filter(f->f.getTypeId()==formType).collect(Collectors.toList());*/

        if (formType==0&&status==0&&attribute==0&&attributeValue.equals(""))
            requests.addAll(WorkflowService.getRequestList());



        for (RequestsEntity request : WorkflowService.getRequestList()) {
            if (request.getTypeId()==formType)
                requests.add(request);
        }
        return ToDoMapper.queryTotodoModel(requests, UserService.getAllUsers());
    }
}
