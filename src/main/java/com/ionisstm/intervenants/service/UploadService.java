package com.ionisstm.intervenants.service;

import com.ionisstm.intervenants.model.Speaker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

@Service
public class UploadService {
    public String uploadResume(MultipartFile file, Speaker s){
        try {
            //TODO: delete old file
            String uploadsDir = "/uploads/";
            String[] projectDir = System.getProperty("java.class.path").split(":")[0].split("/");
            String realPathtoUploads =  String.join("/", Arrays.copyOf(projectDir, projectDir.length - 2) ) + "/src/main/resources/static/uploads/";
            System.out.println("realPathtoUploads -> "+ realPathtoUploads);
            if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String orgName = timestamp.getTime() + "_" + file.getOriginalFilename();
            String filePath = realPathtoUploads + orgName;
            File dest = new File(filePath);
            file.transferTo(dest);
            return orgName;
        }catch (IOException e){
            System.out.println("IOException!");
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e){
            System.out.println("Error!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
