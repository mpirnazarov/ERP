package com.lgcns.erp.workflow.util;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.Mapper.RequestMapper;
import com.lgcns.erp.workflow.Mapper.ToDoMapper;
import com.lgcns.erp.workflow.ViewModel.RequestViewModel;
import com.lgcns.erp.workflow.ViewModel.ToDoViewModel;

import java.util.List;

/**
 * Created by DS on 25.01.2017.
 */
public class Filter {

    public static List<ToDoViewModel> toDoFilter(int formType, int status, int attribute, String attributeValue, String selectedDate, int controllerId, int userId){
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

        whereClause = check(formType, status, selectedDate, counter, whereClause, controllerId);

        if (formType==0&&status==0&&attribute==0&&selectedDate.equals("")){
            whereClause=" WHERE r.statusId<>5";
        }

        System.out.println(whereClause);
        return ToDoMapper.queryTotodoModel(WorkflowService.filter(whereClause), UserService.getAllUsers(), userId);
    }

    public static List<RequestViewModel> filterRequest(int formType, int status, String attributeValue, String selectedDate, int controllerId, int userId) {

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

        if (formType==0&&status==0&&selectedDate.equals("")&&attributeValue.equals("")){
            whereClause="";
        }

        whereClause = check(formType, status, selectedDate, counter, whereClause, controllerId);

        System.out.println(whereClause);
        return RequestMapper.queryTorequestModel(WorkflowService.filter(whereClause), UserService.getAllUsers(), userId);
    }

    private static String check(int formType, int status, String selectedDate, int counter, String whereClause, int controllerId){


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

        if (controllerId==1){
            if (status!=5)
                whereClause += " AND r.statusId<>5";
        }
        else {
            whereClause +="";
        }
        return whereClause;
    }
}
