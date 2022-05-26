package com.sparta_spring.sparta_spring3.controller;

import com.sparta_spring.sparta_spring3.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class CommonController {

    private final CommonService commonService;

    @ResponseBody
    @RequestMapping("/common/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        return commonService.fileUpload(file);
    }
}
