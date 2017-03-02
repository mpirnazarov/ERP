package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Muslimbek Pirnazarov on 3/2/2017.
 */
public class WorkflowEmailService {
    public static int[] getInvolvementList(int reqId, int involvementType) {
        int[] involvementList;
        int i=0;
        List<Integer> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select userId from StepsEntity WHERE requestId=" + reqId + " AND involvementTypeId=" + involvementType);
            list = (List<Integer>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        involvementList = new int[list.size()];
        for (Integer num :
                list) {
            involvementList[i++] = num.intValue();
        }

        return involvementList;
    }
}
