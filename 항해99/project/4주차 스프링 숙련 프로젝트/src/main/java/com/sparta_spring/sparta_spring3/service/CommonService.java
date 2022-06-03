package com.sparta_spring.sparta_spring3.service;

import com.sparta_spring.sparta_spring3.components.Components;
import com.sparta_spring.sparta_spring3.components.fileUpadload.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class CommonService {

    public final FileUpload fileUpload;

    public CommonService(Components components) {
        this.fileUpload = components.getFileUpload();
    }

    public String fileUpload(MultipartFile attcFile) {
        String rtnVal = "";
        try {
            String storedFileName = makeFileName(attcFile);
            String folder = makeDir();

            File f = new File(folder);

            if (!f.exists()) {
                f.mkdirs();
            } //폴더가 존재하지 않으면 경로 생성

            attcFile.transferTo(new File(folder + fileUpload.getSlide() + storedFileName));
            rtnVal += fileUpload.getSrcUrl() + fileUpload.getSlide() + storedFileName;
        } catch (Exception e) {
            rtnVal = "";
        }
        return rtnVal;
    }

    public String makeDir() {
        String folder = "";
        if (fileUpload.getSlide().equals("\\")) {
            folder += new File("").getAbsolutePath();
        }
        folder += fileUpload.getBaseUrl() + fileUpload.getSlide() + fileUpload.getSrcUrl();
        return folder;
    }

    public String makeFileName(MultipartFile attcFile) {
        String attcFileNm = UUID.randomUUID().toString().replaceAll("-", "");
        String attcFileOriNm = attcFile.getOriginalFilename();
        String attcFileOriExt = attcFileOriNm.substring(attcFileOriNm.lastIndexOf("."));
        return attcFileNm + attcFileOriExt;
    }
}
