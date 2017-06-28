package com.lgcns.erp.scheduleManagement.util;

import com.lgcns.erp.scheduleManagement.DBContext.AttachmentContext;
import com.lgcns.erp.scheduleManagement.DBContext.DetailsContext;
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
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.Mapper.RequestMapper;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DS on 12.04.2017.
 */
public class ScheduleMainControllerUtil {
    public static  List<HashMap<String, Object>> putScheduleEventsToMap(List<ScheduleVM> scheduleVMList) throws ParseException {
        List<HashMap<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> map = null;


        for (ScheduleVM scheduleVM : scheduleVMList) {
            map = new HashMap<>();
            map.put("id", scheduleVM.getScheduleId()+"");
            map.put("title", scheduleVM.getTitle());
            map.put("description", scheduleVM.getDescription());
            map.put("place", scheduleVM.getPlace());
            map.put("s_type", scheduleVM.getsType()+"");
            map.put("other", scheduleVM.getOther());
            map.put("start", formatDatesToView(scheduleVM.getDateFrom()));
            map.put("end", formatDatesToView(scheduleVM.getDateTo()));
            map.put("is_compulsory", scheduleVM.isCompulsory()+"");
            map.put("to_notify", scheduleVM.isToNotify()+"");
            map.put("is_draft", scheduleVM.isDraft()+"");
            map.put("author_id", scheduleVM.getAuthorId()+"");
            map.put("actionType_id", scheduleVM.getActionTypeId());
            map.put("authorInfo", WorkflowService.getUserJson(scheduleVM.getAuthorId()));
            map.put("is_private", scheduleVM.isPrivate()+"");

            maps.add(map);
        }

        return maps;
    }

    private static String formatDatesToView(Date date) throws ParseException {
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(date);
        int year = cDate.get(Calendar.YEAR);
        int month = cDate.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = cDate.get(Calendar.DAY_OF_MONTH);
        int hour = cDate.get(Calendar.HOUR_OF_DAY);
        int minute = cDate.get(Calendar.MINUTE);
        int second = cDate.get(Calendar.SECOND);

        String formattedDate = year+
                "-"+(month<10?("0"+month):(month))+
                "-"+(day<10?("0"+day):(day))+
                "T"+(hour<10?("0"+hour):(hour))+
                ":"+(minute<10?("0"+minute):(minute))+
                ":"+(second<10?("0"+second):(second));

        return formattedDate;
    }

    public static void uploadFile(ScheduleVM scheduleVM, int scheduleId, ScheduleMainService service) throws IOException {
         /* File upload */
        MultipartFile[] multipartFiles=null;
        if(!scheduleVM.getFile()[0].isEmpty()) {
            multipartFiles = scheduleVM.getFile();

            // Uploading files attached to C:/files/documents/schedule. Create folder if doesn't exist.
            File f = new File("C:/files/documents/schedule/" + scheduleId+"/");
            if (f.mkdir()) {
                System.out.println("DIRECTORY CREATED SECCESFULLY");
            }
            for (MultipartFile file : multipartFiles) {
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
