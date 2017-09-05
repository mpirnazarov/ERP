package com.lgcns.erp.assetManagement.DBContext;

import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryContext {

    public static void insertAssetHistory(AssetHistoryEntity assetEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            assetEntity.setUsersByUserIdNew(session.load(UsersEntity.class, assetEntity.getUserIdNew()));
            assetEntity.setUsersByUserIdOld(session.load(UsersEntity.class, assetEntity.getUserIdOld()));

            session.save(assetEntity);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

}
