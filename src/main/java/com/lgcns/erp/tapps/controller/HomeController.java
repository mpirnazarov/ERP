package com.lgcns.erp.tapps.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.workflow.controller.email.MailMail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

/**
 * Created by Rafatdin on 15.09.2016.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String printWelcome(Principal principal) {
        int roleId = UserService.getRoleByUserName(principal.getName());
        System.out.println("ROLE: " + roleId);
        if(roleId==1)
            return "forward: /Manager/Profile";
        else if(roleId==3)
            return "forward: /Hr/Profile";
        else
            return "forward: /User/Profile";
    }

    /* Test side. Must be deleted. */
    @RequestMapping(value = "/htmlEmail")
    public void sendTestHTML(Principal principal) {
        String subject = "Schedule management";
        String msgBody = generateMessage(1);


        try {
            EmailService.sendHtmlMail(1, subject, msgBody);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String generateMessage(int type){
        String msg = "";


        if (type == 1){
            msg = "";

        }else  if (type == 2){
            //Notification message
        }


        return msg;
    }

    private String generateHtmlCode(){
        String msg = "<div style=\"background-color: #787878; width: 400px; height: 400px\">\n" +
                "\" +\n" +
                "                    \"<h1 class=\\\"inset-text\\\">Smart Office</h1>\\n\" +\n" +
                "                    \"<h2 class=\\\"inset-text-red\\\">LG CNS Uzbekistan</h2>\\n\" +\n" +
                "                    \"</div>";



        return msg;
    }


}
