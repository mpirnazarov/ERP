package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.service.ScheduleUpdateService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by DS on 06.04.2017.
 */

@RequestMapping("/ScheduleDetails")
@Controller
public class ScheduleDetailsController {
    @Autowired
    ScheduleUpdateService service;

    /**
     * ToDo logically finalize update of participants, references, Attachments
     */
    @RequestMapping(value = "/UpdateSchedule", method = RequestMethod.POST, params = "Update")
    public String updateSchedule(@RequestParam("deletedParticipants") int[]deletedParticipants,
                                 @RequestParam("deletedReferences")int[]deletedReferences,
                                 @RequestParam("scheduleId")int scheduleId){

        if (deletedParticipants!=null){
            for (int deletedParticipant : deletedParticipants) {
                service.deleteParticipant(deletedParticipant, scheduleId);
            }
        }
        if (deletedReferences!=null){
            for (int deletedReference : deletedReferences) {
                service.deleteReference(deletedReference, scheduleId);
            }
        }


        return "redirect: /ScheduleManagement/main";
    }

    /**
     * ToDo identify needed parameters and return type based on front end needs and capabilities
     */
    @RequestMapping(value = "/ParticipantDecision", method = RequestMethod.POST)
    public void decide(@RequestParam("participantId")int participantId,
                       @RequestParam("scheduleId")int scheduleId,
                       @RequestParam("status")int status,
                       @RequestParam("reason")String reason){
        service.updateParticipantDecision(participantId, scheduleId, status,reason);
    }
}
