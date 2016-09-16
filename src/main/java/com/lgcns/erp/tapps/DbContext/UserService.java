package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Rafatdin on 16.09.2016.
 */
public class UserService {

    public static Integer Authenticate(UserInfo userInfo)
    {
        String curHashedPassword = BuildPassword(userInfo.getUsername(), userInfo.getPassword());

         Query query = PostgreSqlContext.getSession().createQuery("from UserInfoEntity where username = :username and password = :password");
        query.setParameter("username",userInfo.getUsername());
        query.setParameter("password",userInfo.getPassword());
        List list = query.list();

        return list.size();
    }
    private static String BuildPassword(String userName, String password)
    {
        if (userName.length() < 3)
            try {
                throw new Exception("Your Username is less than 3 characters");
            } catch (Exception e) {
                e.printStackTrace();
            }
        String result = userName.substring(0,3)+password+userName.substring(userName.length() - 3);
        return EncodeString(result);
    }
    private static String EncodeString(String curString) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] curByteArray = new byte[0];
        try {
            curByteArray = digest.digest(curString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return curByteArray.toString();
    }
}
