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
        msg = generateHtmlCodeString(1,2);
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

        String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><!--[if IE]><html xmlns=\"http://www.w3.org/1999/xhtml\" class=\"ie-browser\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><![endif]--><!--[if !IE]><!--><html style=\"margin: 0;padding: 0;\" xmlns=\"http://www.w3.org/1999/xhtml\"><!--<![endif]--><head>\n" +
                "    <!--[if gte mso 9]><xml>\n" +
                "     <o:OfficeDocumentSettings>\n" +
                "      <o:AllowPNG/>\n" +
                "      <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "     </o:OfficeDocumentSettings>\n" +
                "    </xml><![endif]-->\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
                "    <title>Template Base</title>\n" +
                "    \n" +
                "    \n" +
                "    <style type=\"text/css\" id=\"media-query\">\n" +
                "      body {\n" +
                "  margin: 0;\n" +
                "  padding: 0; }\n" +
                "\n" +
                "table, tr, td {\n" +
                "  vertical-align: top;\n" +
                "  border-collapse: collapse; }\n" +
                "\n" +
                ".ie-browser table, .mso-container table {\n" +
                "  table-layout: fixed; }\n" +
                "\n" +
                "* {\n" +
                "  line-height: inherit; }\n" +
                "\n" +
                "a[x-apple-data-detectors=true] {\n" +
                "  color: inherit !important;\n" +
                "  text-decoration: none !important; }\n" +
                "\n" +
                "[owa] .img-container div, [owa] .img-container button {\n" +
                "  display: block !important; }\n" +
                "\n" +
                "[owa] .fullwidth button {\n" +
                "  width: 100% !important; }\n" +
                "\n" +
                ".ie-browser .col, [owa] .block-grid .col {\n" +
                "  display: table-cell;\n" +
                "  float: none !important;\n" +
                "  vertical-align: top; }\n" +
                "\n" +
                ".ie-browser .num12, .ie-browser .block-grid, [owa] .num12, [owa] .block-grid {\n" +
                "  width: 500px !important; }\n" +
                "\n" +
                ".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {\n" +
                "  line-height: 100%; }\n" +
                "\n" +
                ".ie-browser .mixed-two-up .num4, [owa] .mixed-two-up .num4 {\n" +
                "  width: 164px !important; }\n" +
                "\n" +
                ".ie-browser .mixed-two-up .num8, [owa] .mixed-two-up .num8 {\n" +
                "  width: 328px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.two-up .col, [owa] .block-grid.two-up .col {\n" +
                "  width: 250px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.three-up .col, [owa] .block-grid.three-up .col {\n" +
                "  width: 166px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.four-up .col, [owa] .block-grid.four-up .col {\n" +
                "  width: 125px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.five-up .col, [owa] .block-grid.five-up .col {\n" +
                "  width: 100px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.six-up .col, [owa] .block-grid.six-up .col {\n" +
                "  width: 83px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.seven-up .col, [owa] .block-grid.seven-up .col {\n" +
                "  width: 71px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.eight-up .col, [owa] .block-grid.eight-up .col {\n" +
                "  width: 62px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.nine-up .col, [owa] .block-grid.nine-up .col {\n" +
                "  width: 55px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.ten-up .col, [owa] .block-grid.ten-up .col {\n" +
                "  width: 50px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.eleven-up .col, [owa] .block-grid.eleven-up .col {\n" +
                "  width: 45px !important; }\n" +
                "\n" +
                ".ie-browser .block-grid.twelve-up .col, [owa] .block-grid.twelve-up .col {\n" +
                "  width: 41px !important; }\n" +
                "\n" +
                "@media only screen and (min-width: 520px) {\n" +
                "  .block-grid {\n" +
                "    width: 500px !important; }\n" +
                "  .block-grid .col {\n" +
                "    display: table-cell;\n" +
                "    Float: none !important;\n" +
                "    vertical-align: top; }\n" +
                "    .block-grid .col.num12 {\n" +
                "      width: 500px !important; }\n" +
                "  .block-grid.mixed-two-up .col.num4 {\n" +
                "    width: 164px !important; }\n" +
                "  .block-grid.mixed-two-up .col.num8 {\n" +
                "    width: 328px !important; }\n" +
                "  .block-grid.two-up .col {\n" +
                "    width: 250px !important; }\n" +
                "  .block-grid.three-up .col {\n" +
                "    width: 166px !important; }\n" +
                "  .block-grid.four-up .col {\n" +
                "    width: 125px !important; }\n" +
                "  .block-grid.five-up .col {\n" +
                "    width: 100px !important; }\n" +
                "  .block-grid.six-up .col {\n" +
                "    width: 83px !important; }\n" +
                "  .block-grid.seven-up .col {\n" +
                "    width: 71px !important; }\n" +
                "  .block-grid.eight-up .col {\n" +
                "    width: 62px !important; }\n" +
                "  .block-grid.nine-up .col {\n" +
                "    width: 55px !important; }\n" +
                "  .block-grid.ten-up .col {\n" +
                "    width: 50px !important; }\n" +
                "  .block-grid.eleven-up .col {\n" +
                "    width: 45px !important; }\n" +
                "  .block-grid.twelve-up .col {\n" +
                "    width: 41px !important; } }\n" +
                "\n" +
                "@media (max-width: 520px) {\n" +
                "  .block-grid, .col {\n" +
                "    min-width: 320px !important;\n" +
                "    max-width: 100% !important; }\n" +
                "  .block-grid {\n" +
                "    width: calc(100% - 40px) !important; }\n" +
                "  .col {\n" +
                "    width: 100% !important; }\n" +
                "    .col > div {\n" +
                "      margin: 0 auto; }\n" +
                "  img.fullwidth {\n" +
                "    max-width: 100% !important; } }\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<!--[if mso]>\n" +
                "<body class=\"mso-container\" style=\"background-color:#FFFFFF;\">\n" +
                "<![endif]-->\n" +
                "<!--[if !mso]><!-->\n" +
                "<body class=\"clean-body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #FFFFFF\">\n" +
                "<!--<![endif]-->\n" +
                "  <div class=\"nl-container\" style=\"min-width: 320px;Margin: 0 auto;background-color: #FFFFFF\">\n" +
                "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #FFFFFF;\"><![endif]-->\n" +
                "\n" +
                "    <div style=\"background-color:#DCDCDC;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:#DCDCDC;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 0px; padding-left: 0px; padding-top: 0px; padding-bottom: 0px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 0px; padding-left: 0px; padding-top: 0px; padding-bottom: 0px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;font-family:Verdana, Geneva, sans-serif;color:#555555;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\"><span style=\"color: rgb(128, 128, 128); font-size: 48px; line-height: 57px;\"><strong><span style=\"line-height: 57px; font-size: 48px;\">Smart Office</span></strong></span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\"><span style=\"color: rgb(255, 0, 0); font-size: 26px; line-height: 31px;\"><span style=\"line-height: 31px; font-size: 26px;\">\uFEFF<span style=\"font-size: 24px; line-height: 28px;\">LG CNS UZBEKISTAN</span></span></span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\">&nbsp;<br></p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>    <div style=\"background-color:transparent;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "  <!--[if (mso)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px;padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td><![endif]-->\n" +
                "  <div align=\"center\"><div style=\"border-top: 1px solid #BBBBBB; width:100%; line-height:0px;\">&nbsp;</div></div>\n" +
                "  <!--[if (mso)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "</div>\n" +
                "\n" +
                "                  \n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\"><span style=\"font-size: 18px; line-height: 21px; color: rgb(0, 0, 0);\"><strong><span style=\"color: rgb(153, 153, 153); font-size: 18px; line-height: 21px;\">Schedule management</span>&nbsp;</strong></span></p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                  \n" +
                "                    <div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "  <!--[if (mso)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px;padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td><![endif]-->\n" +
                "  <div align=\"center\"><div style=\"border-top: 1px solid #BBBBBB; width:100%; line-height:0px;\">&nbsp;</div></div>\n" +
                "  <!--[if (mso)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "</div>\n" +
                "\n" +
                "                  \n" +
                "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>    <div style=\"background-color:transparent;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 5px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 5px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px\"><span style=\"font-size: 16px; line-height: 19px;\">Dear,&nbsp;[cd.user]</span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px\">&nbsp;<br></p><p style=\"margin: 0;font-size: 12px;line-height: 14px\"><span style=\"font-size: 16px; line-height: 19px;\">We would like to notify you that, [cd.author] is created [cd.meetingType] at [cd.creation date].&nbsp;</span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px\"><span style=\"font-size: 16px; line-height: 19px;\"><br data-mce-bogus=\"1\"></span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px\"><span style=\"font-size: 16px; line-height: 19px;\">You are invited to participate</span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px\">&nbsp;<br></p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>    <div style=\"background-color:transparent;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px\">[cd.Content of schedule]</p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>    <div style=\"background-color:transparent;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    \n" +
                "<div align=\"center\" class=\"button-container center\" style=\"padding-right: 10px; padding-left: 10px; padding-top:15px; padding-bottom:10px;\">\n" +
                "  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top:15px; padding-bottom:10px;\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"SARVAR\" style=\"height:50px; v-text-anchor:middle; width:146px;\" arcsize=\"0%\" strokecolor=\"#1BBCCE\" fillcolor=\"#1BBCCE\"><w:anchorlock/><center style=\"color:#ffffff; font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size:16px;\"><![endif]-->\n" +
                "    <a href=\"SARVAR\" target=\"_blank\" style=\"display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #1BBCCE; border-radius: 0px; -webkit-border-radius: 0px; -moz-border-radius: 0px; max-width: 134px; width: 126px; width: 25%; border-top: 4px solid #1BBCCE; border-right: 4px solid #1BBCCE; border-bottom: 4px solid #1BBCCE; border-left: 4px solid #1BBCCE; padding-top: 5px; padding-right: 0px; padding-bottom: 5px; padding-left: 0px; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;mso-border-alt: none\">\n" +
                "      <span style=\"font-size:16px;line-height:32px;\"><strong><span style=\"font-size: 14px; line-height: 28px;\" data-mce-style=\"font-size: 14px; line-height: 28px;\">View</span></strong></span>\n" +
                "    </a>\n" +
                "  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\n" +
                "</div>\n" +
                "\n" +
                "                  \n" +
                "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>    <div style=\"background-color:#DADADA;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:#DADADA;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:10px; padding-bottom:10px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 15px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 15px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:18px;color:#959595;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 21px;text-align: center\"><strong>Thank you for your attention,</strong><br><strong>Best regards</strong><br><strong>Technical Department team</strong> <br></p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>   <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "  </div>\n" +
                "\n" +
                "\n" +
                "</body></html>";
        return html;
    }


}
