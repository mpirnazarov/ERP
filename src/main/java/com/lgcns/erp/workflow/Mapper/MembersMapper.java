package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.DbContext.DepartmentService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.Model.Member;

/**
 * Created by Muslimbek Pirnazarov on 3/15/2017.
 */
public class MembersMapper {

    public static Member getMember(int userId) {
        Member member = new Member();
        /* Getting user info from DB */
        UsersEntity usersEntity = UserService.getUserById(userId);
        UserLocalizationsEntity userLocalizationsEntity = UserService.getUserLocByUserId(userId, 3);

        /* Setting user info to members object */
        member.setId(userId);
        member.setFirstName(userLocalizationsEntity.getFirstName());
        member.setLastName(userLocalizationsEntity.getLastName());
        member.setDepartment(DepartmentService.getDepartmentLocsByDeptId(usersEntity.getDepartmentId(), 3).getName());
        /*try {
            member.setJobTitle(UserService.getUserJobTitle(userId));
        }catch (Exception e){
            e.printStackTrace();
        }*/

        return member;
    }
}
