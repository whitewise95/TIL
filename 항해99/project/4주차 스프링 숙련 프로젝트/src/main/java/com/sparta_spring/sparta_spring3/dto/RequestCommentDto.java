package com.sparta_spring.sparta_spring3.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestCommentDto {
    private Long id;
    private String userId;
    private String comment;
    private String blogId;
}
