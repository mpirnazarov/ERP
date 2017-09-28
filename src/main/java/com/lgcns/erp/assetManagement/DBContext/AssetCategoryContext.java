package com.lgcns.erp.assetManagement.DBContext;

import com.lgcns.erp.assetManagement.DBEntities.AssetCategoryEntity;
import com.lgcns.erp.assetManagement.model.AssetCategoryVM;
import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by DS on 15.08.2017.
 */
public class AssetCategoryContext {

    public static List<AssetCategoryEntity> getAssetCategoryList(){
        List<AssetCategoryEntity> list = null;
        Boolean enabled = Boolean.TRUE;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AssetCategoryEntity where enabled=:val");
            query.setParameter("val", enabled);
            list = (List<AssetCategoryEntity>)query.list();
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

    public static void insertAssetCategory(AssetCategoryEntity categoryEntity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(categoryEntity);
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

    public static void updateAssetCategory(AssetCategoryVM category){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update AssetCategoryEntity set assetItemName=:itemName where id=:id");
            query.setParameter("itemName", category.getAssetCategoryName());
            query.setParameter("id", category.getId());

            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteAssetCategory(int id){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        Boolean enabled = Boolean.FALSE;
        try {
            transaction = session.beginTransaction();
            Query query = session.
                    createQuery("update AssetCategoryEntity set " +
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
}
