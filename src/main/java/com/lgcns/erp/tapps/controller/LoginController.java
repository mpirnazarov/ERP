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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Date;

/**
 * Created by Muslimbek on 10/26/2016.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView Login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            /* The user is logged in :) */
            return new ModelAndView("forward:/");
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");

        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/auth")
    public ModelAndView authWithToken(Principal principal, @RequestParam("token") String token) {
        ModelAndView mav = new ModelAndView();

        /* Getting UserId and Expire date from taken */
        AuthTokenEntity authTokenEntity = null;
        UsersEntity usersEntity = null;
        String redirectUrl = "/";
        try {
            authTokenEntity = UserService.getAuthTokenEntityByToken(token);
            Algorithm algorithm = Algorithm.HMAC256(authTokenEntity.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            redirectUrl = jwt.getClaim("redirectUrl").asString();
            System.out.println("REDIRECT URL: " + redirectUrl);


            System.out.println("TOKEN HAS BEEN CHECKED");


            if (new Date().getTime() < authTokenEntity.getExpireDate().getTime()) {
                System.out.println("DATE NOT EXPIRED YET");
            } else {
                /* Date Expired redirect to /auth/error with corresponding error code*/

                /* Token should be deleted here */
                //UserService.deleteAuthTokenWithId(authTokenEntity.getId());

                mav.setViewName("forward: /auth/error");
                mav.addObject("errorCode", AuthError.Date_Expired);
                return mav;
            }
        } catch (UnsupportedEncodingException exception) {
            System.out.println("UTF-8 ENCODING NOT SUPPORTED");
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception) {
            System.out.println("INVALID SIGNATURE");
            //Invalid signature/claims
            mav.setViewName("forward: /auth/error");
            mav.addObject("errorCode", AuthError.Invalid_Token);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (authTokenEntity != null) {
            try {
                usersEntity = UserService.getUserById(authTokenEntity.getUserId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (usersEntity != null) {
            UserDetails userDetails = new User(usersEntity.getUserName(), usersEntity.getPasswordHash(), AuthorityUtils.createAuthorityList(usersEntity.getRoleId().toString()));

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            /* Add user login record to database */
            // Should be implemented

            return new ModelAndView("forward: " + redirectUrl);
        }

        return new ModelAndView("forward: /login");

    }

}
