package com.sparta_spring.sparta_spring3.dto;

import lombok.*;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String email;
}