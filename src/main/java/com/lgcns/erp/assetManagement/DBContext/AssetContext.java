package com.lgcns.erp.assetManagement.DBContext;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by DS on 16.08.2017.
 */
public class AssetContext {

    public static List<AssetEntity> getAssetList(){
        List<AssetEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Boolean enabled = Boolean.TRUE;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AssetEntity where enabled=:val");
            query.setParameter("val", enabled);
            list = (List<AssetEntity>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return list;
    }

    public static List<AssetEntity> getAssetListByUserID(int userId){
        List<AssetEntity> list = null;
        Boolean enabled = Boolean.TRUE;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AssetEntity where userId = :userId and enabled=:val");
            query.setParameter("userId", userId);
            query.setParameter("val", enabled);
            list = (List<AssetEntity>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return list;
    }


    public static void insertAsset(AssetEntity assetEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            assetEntity.setUsersByUserId(session.load(UsersEntity.class, assetEntity.getUserId()));
            assetEntity.setAssetCategoryByCategoryId(session.
                    load(AssetCategoryEntity.class, assetEntity.getCategoryId()));

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


    public static void updateAsset(AssetEntity assetEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.
                    createQuery("update AssetEntity set " +
                    "inventNumber=:inventNumber," +
                    "public=:public," +
                    "regDate=:regDate," +
                    "categoryId=:categoryId," +
                    "userId=:userId where id=:id");

            query.setParameter("inventNumber", assetEntity.getInventNumber());
            query.setParameter("public", assetEntity.getPublic());
            query.setParameter("regDate", assetEntity.getRegDate());
            query.setParameter("categoryId", assetEntity.getCategoryId());
            query.setParameter("userId", assetEntity.getUserId());
            query.setParameter("id", assetEntity.getId());

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteAsset(int id){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        Boolean enabled = Boolean.FALSE;
        try {
            transaction = session.beginTransaction();
            Query query = session.
                    createQuery("update AssetEntity set " +
                            "enabled=:val where id =:id");

            query.setParameter("val", enabled);
            query.setParameter("id", id);

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateAssetOwner(int assetId, int userTo) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.
                    createQuery("update AssetEntity set " +
                            "userId=:userId where id=:id");

            query.setParameter("userId", userTo);
            query.setParameter("id", assetId);

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
