package com.lgcns.erp.assetManagement.DBContext;

/**
 * Created by Muslimbek on 05.09.2017.
 */
public class AssetHistoryContext {

    /*public static void insertAssetHistory(AssetHistoryEntity assetEntity){
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
    }*/

}
