package com.lgcns.erp.scheduleManagement.util.email;

import com.lgcns.erp.scheduleManagement.DBContext.DetailsContext;
import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBContext.ScheduleMainContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ParticipantInScheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ReferenceInCheduleEntity;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleEntity;
import com.lgcns.erp.scheduleManagement.enums.ActionTypeId;
import com.lgcns.erp.scheduleManagement.enums.ScheduleEventInvolvement;
import com.lgcns.erp.scheduleManagement.enums.ScheduleType;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.service.ScheduleUpdateService;
import com.lgcns.erp.tapps.DbContext.EmailService;
import com.lgcns.erp.tapps.DbContext.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DS on 26.04.2017.
 */
@Service
public class EmailMessageContent {


    @Autowired
    ScheduleMainService scheduleMainService;

    public String generateSubject(int action) {
        String msg = "";

        if (action == ActionTypeId.Create.getValue()) {
            msg = "Creation of new event";
        } else if (action == ActionTypeId.Delete.getValue()) {
            msg = "Deletion of an event";
        } else if (action == ActionTypeId.Update.getValue()) {
            msg = "Update of an event";
        } else if (action == ActionTypeId.ParticipantDecide.getValue()) {
            msg = "A participant has decided";
        }
        return msg;
    }


    public String generateMessage(int scheduleId, int action, int roleType, int userId){
        if (ActionTypeId.Create.getValue()==action) {

        }else if(ActionTypeId.Send_Email.getValue()==action){

        }
        ScheduleEntity scheduleEntity =DetailsContext.getScheduleById(scheduleId);
        String participants="", references="";
        String calStart, calEnd, calLink , token, calAuthor, calUser, calCreationDate, calType, calTitle, calPlace, calDescription, msgCalInfo, msgCalParRef, msgCalForParticipant, msgCalForReference;

        calAuthor = UserService.getUserFullNameInLanguageById(scheduleEntity.getAutherId(), 3);
        calUser = "User";
        if(scheduleEntity.getStype()!= ScheduleType.Other.getValue()){
            calType = ScheduleType.values()[scheduleEntity.getStype()-1].name().replace("_", " ");
        }else{
            calType = scheduleEntity.getOther();
        }
        calTitle = scheduleEntity.getTitle();
        calPlace = scheduleEntity.getPlace();
        calDescription = scheduleEntity.getDescription();
        calCreationDate = "";
        calStart = scheduleEntity.getDateFrom().toString();
        calEnd = scheduleEntity.getDateTo().toString();

        /* Get all participants with their full name */
        for (ParticipantInScheduleEntity participant :
                ParticipantContext.getParticipantsByScheduleId(scheduleId)) {
            participants += UserService.getUserFullNameInLanguageById(participant.getUserId(), 3) + "<br>";
        }

        /* Get all references with their full name */
        for (ReferenceInCheduleEntity reference :
                ReferenceContext.getReferencesByScheduleId(scheduleId)) {
            participants += UserService.getUserFullNameInLanguageById(reference.getUserId(), 3) + "<br>";
        }

        /* Firstly generate redirect URL */
        String redirectUrl = "/ScheduleManagement/main";
        token = EmailService.generateToken(userId, redirectUrl, 168);
        calLink = "http://192.168.1.122/auth?token=" + token;


        int calRoleType = roleType;
        msgCalForParticipant = "And you were invitied to participate in this meeting";
        msgCalForReference = "And you are welcome to participate in this meeting";
        msgCalParRef = "";


        if (calRoleType == 1){
            msgCalParRef = msgCalForParticipant;
        }else if(calRoleType == 2){
            msgCalParRef = msgCalForReference;
        }

        msgCalInfo = "<p style=\"font-size:12px;\">Meeting details are as follows:</p>" +
                "Type: " + calType + "<br>"+
                "Title: " + calTitle + "<br>" +
                "Place: " + calPlace + "<br>" +
                "Description: " + calDescription + "<br>" +
                "Start: " + calStart + " | " + "End: " + calEnd + "<br>";

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
                "    <div style=\"background-color:transparent;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:20px; padding-bottom:20px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:20px; padding-bottom:20px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 0px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 0px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\"><span style=\"color: rgb(128, 128, 128); font-size: 72px; line-height: 86px;\"><strong><span style=\"line-height: 86px; font-size: 72px;\">Smart Office</span></strong></span></p><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\"><span style=\"font-size: 24px; line-height: 28px; color: rgb(255, 0, 0);\">LG CNS UZBEKISTAN</span></p></div>\n" +
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
                "    </div>    <div style=\"background-color:#EDEDED;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:#EDEDED;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 10px; padding-left: 10px; padding-top:10px; padding-bottom:10px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 10px; padding-left: 10px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:24px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 24px;text-align: center\"><span style=\"font-size: 12px; line-height: 24px;\"><strong>&nbsp; Schedule management</strong></span></p></div>\n" +
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
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:30px; padding-bottom:30px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:30px; padding-bottom:30px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 0px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 0px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px\">Dear,&nbsp;" + calUser + "</p><p style=\"margin: 0;font-size: 12px;line-height: 14px\">&nbsp;</p><p style=\"margin: 0;font-size: 12px;line-height: 14px\">We would like to notify you that, " + calAuthor + " created event of type " + calType + ".&nbsp;</p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 5px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 5px; padding-bottom: 5px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#777777;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px\">&nbsp;</p><p style=\"margin: 0;font-size: 12px;line-height: 14px\">" + msgCalParRef + "</p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 15px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 15px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#aaaaaa;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\">" + msgCalInfo + "</p></div>\n" +
                "</div>\n" +
                "<!--[if mso]></td></tr></table><![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                  \n" +
                "                    \n" +
                "<div align=\"center\" class=\"button-container center\" style=\"padding-right: 10px; padding-left: 10px; padding-top:15px; padding-bottom:10px;\">\n" +
                "  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top:15px; padding-bottom:10px;\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"" + calLink + "\" style=\"height:34px; v-text-anchor:middle; width:146px;\" arcsize=\"3%\" strokecolor=\"#41BEC2\" fillcolor=\"#41BEC2\"><w:anchorlock/><center style=\"color:#ffffff; font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size:14px;\"><![endif]-->\n" +
                "    <a href=\"" + calLink + "\" target=\"_blank\" style=\"display: inline-block;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #41BEC2; border-radius: 1px; -webkit-border-radius: 1px; -moz-border-radius: 1px; max-width: 126px; width: 86px; width: 25%; border-top: 0px solid transparent; border-right: 0px solid transparent; border-bottom: 0px solid transparent; border-left: 0px solid transparent; padding-top: 5px; padding-right: 20px; padding-bottom: 5px; padding-left: 20px; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;mso-border-alt: none\">\n" +
                "      <span style=\"font-size:12px;line-height:24px;\">View</span>\n" +
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
                "    </div>    <div style=\"background-color:#444444;\">\n" +
                "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 500px;width: 500px;width: calc(19000% - 98300px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n" +
                "        <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:#444444;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 500px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n" +
                "\n" +
                "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\" width:500px; padding-right: 0px; padding-left: 0px; padding-top:25px; padding-bottom:25px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 500px;width: 500px;width: calc(18000% - 89500px);background-color: transparent;\">\n" +
                "              <div style=\"background-color: transparent; width: 100% !important;\">\n" +
                "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:25px; padding-bottom:25px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n" +
                "\n" +
                "                  \n" +
                "                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n" +
                "<div style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n" +
                "\t<div style=\"font-size:12px;line-height:14px;color:#bbbbbb;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 12px;line-height: 14px;text-align: center\">Smart Office team</p></div>\n" +
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
