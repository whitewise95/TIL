package com.sparta_spring.sparta_spring3.domain;

import com.sparta_spring.sparta_spring3.domain.resultType.Timestamped;
import com.sparta_spring.sparta_spring3.dto.RequestCommentDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long blogId;

    @Column(nullable = false)
    private String UserId;

    @Builder
    public Comment(String comment, Long blogId, String userId) {
        this.comment = comment;
        this.blogId = blogId;
        this.UserId = userId;
    }

    public void update(RequestCommentDto requestCommentDto) {
        this.comment = requestCommentDto.getComment();
    }
}
