package com.lgcns.erp.scheduleManagement.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by DS on 24.04.2017.
 */
public class AttachmentUtil {

    /**
     * ToDo check if this is working
     * @param scheduleId
     * @throws IOException
     */
    public static void deleteFilesByScheduleId(int scheduleId) throws IOException {
        File file = new File("C:/files/documents/schedule/" + scheduleId+"/");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }

    public static void deleteFileByPath(String path) throws IOException {
        Path path1 = FileSystems.getDefault().getPath(path);
        Files.delete(path1);
    }
}
