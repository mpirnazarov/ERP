package com.lgcns.erp.scheduleManagement.controller;

import com.lgcns.erp.scheduleManagement.DBContext.AttachmentContext;
import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.mapper.AttachmentMapper;
import com.lgcns.erp.scheduleManagement.mapper.PartircipantMapper;
import com.lgcns.erp.scheduleManagement.mapper.ReferenceMapper;
import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.model.Attachment;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.service.ScheduleUpdateService;
import com.lgcns.erp.scheduleManagement.util.AttachmentUtil;
import com.lgcns.erp.scheduleManagement.util.ScheduleMainControllerUtil;
import com.lgcns.erp.scheduleManagement.viewModel.ParticipantVM;
import com.lgcns.erp.scheduleManagement.viewModel.ReferenceVM;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DS on 06.04.2017.
 */

@RequestMapping("/ScheduleDetails")
@Controller
public class ScheduleDetailsController {
    int[] participantsGlobal = null;
    int[] referencesGlobal = null;

    @Autowired
    ScheduleUpdateService service;

    @Autowired
    ScheduleMainService mainService;

    /**
     * ToDo logically finalize update of participants, references, Attachments
     */
    @RequestMapping(value = "/UpdateSchedule", method = RequestMethod.POST)
    public String updateSchedule(@ModelAttribute ScheduleVM scheduleVM, Principal principal) throws IOException {

        int scheduleId = scheduleVM.getScheduleId();

        service.deleteParticipant(scheduleId);
        service.deleteReference(scheduleId);
        service.deleteAttachment(scheduleId);

        mainService.updateSchedule(ScheduleMainMapper.mapScheduleFromVMToEntity(scheduleVM));
        //insert new participants, referenced and attachments
        if (participantsGlobal != null) {
            for (int participant : participantsGlobal) {
                mainService.insertParticipants(ScheduleMainMapper.mapParticipantInSchedule(scheduleId, participant));
            }
        }
        if (referencesGlobal != null) {
            for (int reference : referencesGlobal) {
                mainService.insertReference(ScheduleMainMapper.mapReferenceInSchedule(scheduleId, reference));
            }
        }
        ScheduleMainControllerUtil.uploadFile(scheduleVM, scheduleId, mainService);

        return "redirect: /ScheduleManagement/main";
    }

    /**
     * ToDo identify needed parameters and return type based on front end needs and capabilities
     */
    @RequestMapping(value = "/ParticipantDecision", method = RequestMethod.POST)
    public @ResponseBody HashMap<String, String> decide(@RequestParam("participantId")int participantId,
                       @RequestParam("scheduleId")int scheduleId,
                       @RequestParam("status")int status,
                       @RequestParam("reason")String reason){
        service.updateParticipantDecision(participantId, scheduleId, status,reason);
        HashMap<String, String> map = new HashMap<>();
        map.put("jovob", "");
        return map;
    }

    /**
     * Removes all the rows of a certain schedule todo
     * @param scheduleId
     * @throws IOException
     */
    @RequestMapping(value = "/DeleteSchedule", method = RequestMethod.POST)
    public String delete(@RequestParam("scheduleId")int scheduleId) throws IOException {
        service.deleteReference(scheduleId);
        service.deleteAttachment(scheduleId);
        service.deleteParticipant(scheduleId);
        service.deleteSchedule(scheduleId);

        return "redirect: /ScheduleManagement/main";
    }


    @RequestMapping(value = "/DeleteAttachment/{attachmentId}")
    public String deleteAttachment(@PathVariable int attachmentId) throws IOException {
        String path = service.getAttachmentById(attachmentId).getAttachmentPath();
        AttachmentUtil.deleteFileByPath(path);
        service.deleteAttachmentById(attachmentId);

        return "redirect: /ScheduleManagement/main";
    }

    @RequestMapping(value = "/ScheduleMembersAjax", method = RequestMethod.POST)
    public @ResponseBody
    int[] ScheduleMembersPostAjax(@RequestParam("participants") int[] participants, @RequestParam("references") int[] references){
        participantsGlobal = participants;
        referencesGlobal = references;

        return participants;
    }

    @RequestMapping(value = "/GetSchedule", method = RequestMethod.POST)
    public @ResponseBody
    HashMap<String, Object> ScheduleMembersPostAjax(@RequestParam("scheduleId") int scheduleId){
        List<ParticipantVM> participantVMList = PartircipantMapper.mapParticipantEntityListToVMList(ParticipantContext.getParticipantsByScheduleId(scheduleId));
        List<ReferenceVM> referenceVMList = ReferenceMapper.mapReferenceListToVMList(ReferenceContext.getReferencesByScheduleId(scheduleId));
        List<Attachment> attachmentList = AttachmentMapper.mapAttachmentEntitiesToAttachment(AttachmentContext.getAttachmentsByScheduleId(scheduleId));

        HashMap<String,Object> map = new HashMap<>();
        map.put("participants", participantVMList);
        map.put("references", referenceVMList);
        map.put("Attachments", attachmentList);

        return map;
    }
}
