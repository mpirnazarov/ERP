package com.lgcns.erp.tapps.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.AuthTokenEntity;
import com.lgcns.erp.tapps.model.DbEntities.UsersEntity;
import com.lgcns.erp.workflow.Enums.AuthError;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Date;

/**
 * Created by Muslimbek on 10/26/2016.
 */
@Controller
public class ErrorPageController {
    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal principal) {

        ModelAndView model = new ModelAndView();

        if (principal != null) {
            model.addObject("msg", "Hi " + principal.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }
}
