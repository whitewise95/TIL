package com.sparta_spring.sparta_spring3.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class ResponseDto {

    private int statusCode;
    private String message;

    @Builder
    public ResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
