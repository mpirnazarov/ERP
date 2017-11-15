package com.lgcns.erp.assetManagement.DBContext;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.DBEntities.AssetHistoryEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryContext {
    public static List<AssetHistoryEntity> getAssetHistoryListById(int inventNum) {
        List<AssetHistoryEntity> assetHistoryEntityList = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AssetHistoryEntity where inventNum=:id");
            query.setParameter("id", inventNum);
            assetHistoryEntityList = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return assetHistoryEntityList;
    }

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

    public static List<AssetHistoryEntity> getAssetHistoryList() {

        List<AssetHistoryEntity> assetHistoryEntityList = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AssetHistoryEntity");

            assetHistoryEntityList = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return assetHistoryEntityList;
    }
}
