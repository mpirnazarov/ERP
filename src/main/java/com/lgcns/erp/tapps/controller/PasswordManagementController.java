package com.lgcns.erp.tapps.controller;

import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.Enums.RestorePasswordMessage;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.tapps.model.MessageResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Muslimbek on 5/16/2017.
 */
@Controller
public class PasswordManagementController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/restorePassword" , method = RequestMethod.POST)
    public @ResponseBody
    MessageResponse RestorePasswordPost(Principal principal, @RequestParam("email") String email) {
        if(UserService.getEmailExists(email)){
            UsersEntity usersEntity = UserService.getUserByEmail(email);
            if(usersEntity.isEnabled()){
                /* Email send to users mail */
                if(sendEmailNewPassword(usersEntity.getId())){
                    return new MessageResponse(RestorePasswordMessage.Okey.getValue(), "Mail has been sent");
                }else {
                    return new MessageResponse(RestorePasswordMessage.Email_Not_Sent.getValue(), "Delivery failed");
                }
            }else{
                return new MessageResponse(RestorePasswordMessage.User_Disabled.getValue(), "Your account is disabled");
            }
        }else{
            return new MessageResponse(RestorePasswordMessage.Email_Not_Exist.getValue(), "Email does not exist");
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
            InetAddress internetAddress = null;
            String ip = null;

            ip = request.getRemoteAddr();
            /*internetAddress = InetAddress.getByName(ip);
            System.out.println("INTERNET ADDRESS: " + ip + " | " + InetAddress.getByName(ip).toString());*/

            String message = "New password: " + secret + "<br>"
                    + "Password changed from: " + "<br>" +
                    "IP: " + ip + "<br>";
            EmailService.sendHtmlMail(id, subject, message);
            UserService.updatePassword(UserService.getUserById(id), passwordEncoder.encode(secret));
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
