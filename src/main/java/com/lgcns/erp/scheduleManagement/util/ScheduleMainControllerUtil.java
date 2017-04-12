package com.lgcns.erp.scheduleManagement.util;

import com.lgcns.erp.scheduleManagement.DBContext.AttachmentContext;
import com.lgcns.erp.scheduleManagement.DBContext.ParticipantContext;
import com.lgcns.erp.scheduleManagement.DBContext.ReferenceContext;
import com.lgcns.erp.scheduleManagement.DBEntities.ScheduleAttachmentsEntity;
import com.lgcns.erp.scheduleManagement.mapper.AttachmentMapper;
import com.lgcns.erp.scheduleManagement.mapper.PartircipantMapper;
import com.lgcns.erp.scheduleManagement.mapper.ReferenceMapper;
import com.lgcns.erp.scheduleManagement.mapper.ScheduleMainMapper;
import com.lgcns.erp.scheduleManagement.model.Attachment;
import com.lgcns.erp.scheduleManagement.service.ScheduleMainService;
import com.lgcns.erp.scheduleManagement.viewModel.ParticipantVM;
import com.lgcns.erp.scheduleManagement.viewModel.ReferenceVM;
import com.lgcns.erp.scheduleManagement.viewModel.ScheduleVM;
import com.lgcns.erp.workflow.Mapper.RequestMapper;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DS on 12.04.2017.
 */
public class ScheduleMainControllerUtil {
    public static  List<HashMap<String, Object>> putScheduleEventsToMap(List<ScheduleVM> scheduleVMList){
        List<HashMap<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> map = null;

        for (ScheduleVM scheduleVM : scheduleVMList) {
            map = new HashMap<>();
            List<ParticipantVM> participantVMList =PartircipantMapper.mapParticipantEntityListToVMList(ParticipantContext.getParticipantsByScheduleId(scheduleVM.getScheduleId()));
            List<ReferenceVM> referenceVMList = ReferenceMapper.mapReferenceListToVMList(ReferenceContext.getReferencesByScheduleId(scheduleVM.getScheduleId()));
            List<Attachment> attachmentList = AttachmentMapper.mapAttachmentEntitiesToAttachment(AttachmentContext.getAttachmentsByScheduleId(scheduleVM.getScheduleId()));

            map.put("id", scheduleVM.getScheduleId()+"");
            map.put("title", scheduleVM.getTitle());
            map.put("descrition", scheduleVM.getDescription());
            map.put("place", scheduleVM.getPlace());
            map.put("s_type", scheduleVM.getsType()+"");
            map.put("other", scheduleVM.getOther());
            map.put("start", scheduleVM.getDateFrom()+"");
            map.put("end", scheduleVM.getDateTo()+"");
            map.put("is_compulsory", scheduleVM.isCompulsory()+"");
            map.put("to_notify", scheduleVM.isToNotify()+"");
            map.put("is_draft", scheduleVM.isDraft()+"");
            map.put("author_id", scheduleVM.getAuthorId()+"");
            map.put("participants", participantVMList);
            map.put("references", referenceVMList);
            map.put("Attachments", attachmentList);

            maps.add(map);
        }

        return maps;
    }

    //todo Create common Class to upload files
    public static void uploadFile(ScheduleVM scheduleVM, int scheduleId, ScheduleMainService service) throws IOException {
         /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!scheduleVM.getFile()[0].isEmpty()) {
            multipartFiles = scheduleVM.getFile();

            // Uploading files attached to C:/files/documents/workflow. Create folder if doesn't exist.
            File f = new File("C:/files/documents/schedule/" + scheduleVM.getScheduleId()+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file :
                    multipartFiles) {
                FileCopyUtils.copy(file.getBytes(), new File("C:/files/documents/schedule/" + scheduleId + "/" + file.getOriginalFilename()));
            }

            System.out.println("FILE WAS UPLOADED!");
        }
        if(!scheduleVM.getFile()[0].isEmpty()) {
            for(MultipartFile multipartFile: scheduleVM.getFile()){
                service.insertAttachment(ScheduleMainMapper.mapAttachmentInSchedule(scheduleId, multipartFile));
            }
        }
    }
}
