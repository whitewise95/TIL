package com.sparta_spring.sparta_spring3.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBlogDto {
    private Long id;
    private String content;
    private String password;
    private String writer;
}
