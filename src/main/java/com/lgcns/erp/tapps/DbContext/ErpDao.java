package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.model.UserInfo;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Component
public class ErpDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*public List<UserInfo> getAllUsers()
    {
        String query = "select * from UserInfo";

    }*/
}
