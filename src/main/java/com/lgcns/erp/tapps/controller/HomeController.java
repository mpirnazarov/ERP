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
        String subject = "";
        String msg = "";
        
        subject = generateSubject();
        msg = generateMsg(1, 2);
        try {
            EmailService.sendHtmlMail(1, subject, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String generateSubject() {
        String subject = "This is subject";
        return subject;
    }

    private String generateMsg(int num1, int num2) {
        String msg = "<div style='color:red'>Sarvar</div>This is message";
        return msg;
    }

    private String generateHtmlCodeString(int num1, int num2) {
        String test = generateMsg(1,2);

        String html = "<div style='color:red'>Sarvar</div>This is message";
        return html;
    }


}
