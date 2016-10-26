package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.model.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rafatdin on 16.09.2016.
 */
public class UserService {

    public static Integer Authenticate(UserInfo userInfo) {
        String curHashedPassword = null;
        try {
            curHashedPassword = BuildPassword(userInfo.getUsername(), userInfo.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        List list = null;
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); //!This is the wrong way
        Session session = HibernateUtility.getSessionFactory().openSession();                    //!This is the singleton way
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity where userName = :username and passwordHash = :password");
            query.setParameter("username",userInfo.getUsername());
            query.setParameter("password",curHashedPassword);
            list = (List<UserInfo>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return list.size();
    }

    private static String BuildPassword(String userName, String password) throws NoSuchAlgorithmException {
        if (userName.length() < 3)
            try {
                throw new Exception("Your Username is less than 3 characters");
            } catch (Exception e) {
                e.printStackTrace();
            }
        String result = userName.substring(0,3)+password+userName.substring(userName.length() - 3);
        return sha256(result);
    }

    static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    static List<UsersEntity> getAllUsers(){
        List<UsersEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity ");
            list = (List<UsersEntity>)query.list();
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

    public static Collection<UsersEntity> getDirectHeads()
    {
        Collection list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity userLoc join userLoc.userLocalizationsesById");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            //query.setParameter("languageId", Language.eng.getCode());
            list = (Collection<UsersEntity>)query.list();
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


}
