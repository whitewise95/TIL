package com.sparta_spring.sparta_spring3.controller;

import com.sparta_spring.sparta_spring3.dto.*;
import com.sparta_spring.sparta_spring3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String loginView() {
        return "login";
    }

    @ResponseBody
    @PostMapping("/user")
    public ResponseDto login(@RequestBody RequestUserDto requestUserDto) {
        return userService.login(requestUserDto);
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @ResponseBody
    @PostMapping("/user/signup")
    public ResponseDto registerUser(@RequestBody SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto notFound(Exception e) {
        return ResponseDto.builder()
                .statusCode(500)
                .message(e.getMessage())
                .build();
    }

}