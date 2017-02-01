package com.lgcns.erp.workflow.util;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.Mapper.RequestMapper;
import com.lgcns.erp.workflow.Mapper.ToDoMapper;
import com.lgcns.erp.workflow.ViewModel.RequestViewModel;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DS on 25.01.2017.
 */
public class Filter {

    public static List<ToDoViewModel> toDoFilter(int formType, int status, int attribute, String attributeValue, String selectedDate){
        int counter = 0;
        String whereClause = "";

        if (attribute!=0){
            if (attribute==1){
                    whereClause = ", UserLocalizationsEntity u WHERE r.userFromId=u.userId AND upper(u.firstName) LIKE "+"upper('"+attributeValue+"%') AND u.languageId=3";
                    counter++;
            }else {
                if (counter==0){
                    whereClause+= " WHERE upper(r.subject) LIKE "+"upper('"+attributeValue+"%')";
                    counter++;
                }
                else{
                    whereClause+=" AND upper(r.subject) LIKE "+"upper('"+attributeValue+"%')";
                    counter++;
                }
            }
        }

        whereClause = check(formType, status, selectedDate, counter, whereClause);

        if (formType==0&&status==0&&attribute==0&&selectedDate.equals("")){
            whereClause="";
        }

        System.out.println(whereClause);
        return ToDoMapper.queryTotodoModel(WorkflowService.filter(whereClause), UserService.getAllUsers());
    }

    public static List<RequestViewModel> filterRequest(int formType, int status, String attributeValue, String selectedDate) {

        int counter = 0;
        String whereClause = "";

        if (!attributeValue.equals("")){
                if (counter==0){
                    whereClause+= " WHERE upper(r.subject) LIKE "+"upper('"+attributeValue+"%')";
                    counter++;
                }
                else{
                    whereClause+=" AND upper(r.subject) LIKE "+"upper('"+attributeValue+"%')";
                    counter++;
                }
            }

        whereClause = check(formType, status, selectedDate, counter, whereClause);

        if (formType==0&&status==0&&selectedDate.equals("")&&attributeValue.equals("")){
            whereClause="";
        }

        System.out.println(whereClause);
        return RequestMapper.queryTorequestModel(WorkflowService.filter(whereClause), UserService.getAllUsers());
    }

    private static String check(int formType, int status, String selectedDate, int counter, String whereClause){


        if (formType!=0){
            if (counter==0){
                whereClause+= " WHERE r.typeId="+formType;
                counter++;
            }
            else{
                whereClause+=" AND r.typeId="+formType;
                counter++;
            }
        }

        if (status!=0){
            if (counter==0){
                whereClause+= " WHERE r.statusId="+status;
                counter++;
            }
            else{
                whereClause+=" AND r.statusId="+status;
                counter++;
            }
        }


        if (!selectedDate.equals("")){

            if (counter==0){
                whereClause+= " WHERE r.dateCreated="+"to_date('"+selectedDate+"', ('YYYY-MM-DD'))";
                counter++;
            }
            else{
                whereClause+=" AND r.dateCreated="+"to_date('"+selectedDate+"', ('YYYY-MM-DD'))";
                counter++;
            }
        }

        return whereClause;
    }
}
