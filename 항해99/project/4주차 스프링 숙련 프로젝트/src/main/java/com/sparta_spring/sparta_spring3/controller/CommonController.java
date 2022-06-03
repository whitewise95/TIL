package com.sparta_spring.sparta_spring3.controller;

import com.sparta_spring.sparta_spring3.domain.User;
import com.sparta_spring.sparta_spring3.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.sparta_spring.sparta_spring3.security.FormLoginSuccessHandler.AUTH_HEADER;

@Controller
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @ResponseBody
    @RequestMapping("/common/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        return commonService.fileUpload(file);
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(final HttpServletResponse response, User user) {
        if (Optional.ofNullable(response.getHeader(AUTH_HEADER)).isPresent()) {
            return "로그인되었습니다.";
        } else {
            return "로그인되었습니다.";
        }
    }

}
