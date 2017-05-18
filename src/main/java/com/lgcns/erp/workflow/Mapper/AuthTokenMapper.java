package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.model.DbEntities.AuthTokenEntity;
import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by Muslimbek on 5/1/2017.
 */
public class AuthTokenMapper {
    public static AuthTokenEntity mapAuthToken(int userId, String token, DateTime expireDate, String secret) {
        AuthTokenEntity authTokenEntity = new AuthTokenEntity();
        authTokenEntity.setUserId(userId);
        authTokenEntity.setToken(token);
        authTokenEntity.setExpireDate(new Timestamp(expireDate.getMillis()));
        authTokenEntity.setSecretKey(secret);
        return authTokenEntity;
    }
}
