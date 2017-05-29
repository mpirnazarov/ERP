package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.RestorePasswordMessage;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muslimbek on 5/16/2017.
 */
@Controller
public class PasswordManagementController {

    @RequestMapping(value = "/restorePassword" , method = RequestMethod.POST)
    @ResponseBody
    public String RestorePasswordPost(Principal principal, @RequestParam("email") String email) {
        Map<Integer, String> errorMessage = new HashMap<Integer, String>();
        if(UserService.getEmailExists(email)){
            UsersEntity usersEntity = UserService.getUserByEmail(email);
            if(usersEntity.isEnabled()){
                /* Email send to users mail */
                if(sendEmailNewPassword(usersEntity.getId())){
                    return errorMessage.put(RestorePasswordMessage.Okey.getValue(), "");
                }else {
                    return errorMessage.put(RestorePasswordMessage.Email_Not_Sent.getValue(), "");
                }
            }else{
                return errorMessage.put(RestorePasswordMessage.User_Disabled.getValue(), "");
            }
        }else{
            return errorMessage.put(RestorePasswordMessage.Email_Not_Exist.getValue(), "");
        }
    }

    private Boolean sendEmailNewPassword(int id) {
        try {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            String pwd = RandomStringUtils.random( 6, characters );
            System.out.println( pwd );
            //String secret = bytes.toString();
            String secret = pwd;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //for hashing
            String subject = "Password changed";
            String message = "New password: " + secret;
            EmailService.sendHtmlMail(id, subject, message);
            UserService.updatePassword(UserService.getUserById(id), passwordEncoder.encode(secret));
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
