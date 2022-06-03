package com.sparta_spring.sparta_spring3.dto;

import lombok.*;

@Getter
@Setter
public class RequestUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
}
