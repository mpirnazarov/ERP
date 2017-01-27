package com.lgcns.erp.workflow.util;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Mapper.ToDoMapper;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DS on 25.01.2017.
 */
public class Filter {

    public static List<ToDoViewModel> filter(final int formType, int status, int attribute, String attributeValue, String selectedDate) {
        List<RequestsEntity> requests = WorkflowService.getRequestList();
        List<RequestsEntity> filteredRequestsResult = new ArrayList<>();

        if (formType==0&&status==0&&attribute==0&&selectedDate.equals(""))
            return ToDoMapper.queryTotodoModel(requests, UserService.getAllUsers());

        if (formType!=0){
            filteredRequestsResult = requests.stream().filter(f->f.getTypeId()==formType).collect(Collectors.toList());
            requests.clear();
            requests.addAll(filteredRequestsResult);
            filteredRequestsResult.clear();
        }
        if (status!=0){
            filteredRequestsResult = requests.stream().filter(f->f.getStatusId()==status).collect(Collectors.toList());
            requests.clear();
            requests.addAll(filteredRequestsResult);
            filteredRequestsResult.clear();
        }
        if (attribute!=0){
            if (attribute==1){
                for (RequestsEntity request : requests) {
                    for (UsersEntity user : UserService.getAllUsers()) {
                        if (user.getId()==request.getUserFromId()){
                            if (UserService.getUserLocByUserId(user.getId(), 3).getFirstName().equals(attributeValue)){
                                filteredRequestsResult.add(request);
                            }
                        }
                    }
                }
                requests.clear();
                requests.addAll(filteredRequestsResult);
                filteredRequestsResult.clear();
            }else {
                filteredRequestsResult = requests.stream().filter(f->f.getSubject().equals(attributeValue)).collect(Collectors.toList());
                requests.clear();
                requests.addAll(filteredRequestsResult);
                filteredRequestsResult.clear();
            }
        }

        if (selectedDate!=null){

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            for (RequestsEntity request : requests) {
                String t = df.format(request.getDateCreated());
                System.out.println(t);
                if (t.equals(selectedDate))
                    filteredRequestsResult.add(request);
            }

            /*filteredRequestsResult = requests.stream().filter(f->f.getDateCreated()).collect(Collectors.toList());*/

            requests.clear();
            requests.addAll(filteredRequestsResult);
            filteredRequestsResult.clear();
        }
        return ToDoMapper.queryTotodoModel(requests, UserService.getAllUsers());
    }
}
