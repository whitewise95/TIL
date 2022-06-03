package com.sparta_spring.sparta_spring3.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestBlogDto {
    private String content;
    private String writer;
    private String password;
    private String imgUrl;
}
