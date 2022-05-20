package com.sparta.sparta_spring.domain;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseRequestDto {
    private final String title;
    private final String tutor;

}
