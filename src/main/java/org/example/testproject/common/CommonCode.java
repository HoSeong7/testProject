package org.example.testproject.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
public class CommonCode {


    public String filePath(MultipartFile uploadfile, String uploadPath) {

        String url = "";
        String uuid = UUID.randomUUID().toString();
        String originalName = uploadfile.getOriginalFilename();
        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
        String savefileName;

        log.error("파일 경로 확인 -----" + uploadPath);
        savefileName = uploadPath + File.separator +
                File.separator + uuid + "_" + fileName;
        url = uuid + "_" + fileName;
        Path savePath = Paths.get(savefileName);
        try {
            uploadfile.transferTo(savePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
