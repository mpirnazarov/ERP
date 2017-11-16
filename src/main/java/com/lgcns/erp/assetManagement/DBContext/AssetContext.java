package com.lgcns.erp.assetManagement.DBContext;

import com.lgcns.erp.assetManagement.DBEntities.AssetEntity;
import com.lgcns.erp.assetManagement.mapper.AssetImportMapper;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
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
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            // Query query = session.createQuery("from AssetEntity");
            Query query = session.createQuery("from AssetEntity where checkDeleted=:val");
            query.setParameter("val", false);

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

    public static void updateAsset(AssetEntity assetEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.
                    createQuery("update AssetEntity set " +
                            "nameRu=:nameRu," +
                            "nameEn=:nameEn," +
                            "regInfo=:regInfo," +
                            "regDate=:regDate," +
                            "cost=:cost where inventNum=:inventNumber");

            query.setParameter("inventNumber", assetEntity.getInventNum());
            query.setParameter("nameRu", assetEntity.getNameRu());
            query.setParameter("nameEn", AssetImportMapper.translateRuToEng(assetEntity.getNameRu()));
            query.setParameter("regInfo", assetEntity.getRegInfo());
            query.setParameter("regDate", assetEntity.getRegDate());
            query.setParameter("cost", assetEntity.getCost());

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void insertAsset(AssetEntity assetEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            assetEntity.setUsersByOwnerId(session.load(UsersEntity.class, assetEntity.getOwnerId()));

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

    public static void deleteAsset(int inventNum, boolean status){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.
                    createQuery("update AssetEntity set " +
                            "checkDeleted=:val where inventNum=:id");
            if(status == true){
                query.setParameter("val", Boolean.TRUE);
            }else{
                query.setParameter("val", Boolean.FALSE);
            }

            query.setParameter("id", inventNum);

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static AssetEntity getAssetByID(int inventNum) {
        AssetEntity assetEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AssetEntity where inventNum=:id and checkDeleted=:val");
            query.setParameter("id", inventNum);
            query.setParameter("val", false);
            assetEntity = (AssetEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return assetEntity;

    }

    /*

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
    }*/
}
