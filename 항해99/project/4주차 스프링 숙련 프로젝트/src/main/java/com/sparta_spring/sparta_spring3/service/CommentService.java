package com.sparta_spring.sparta_spring3.service;

import com.sparta_spring.sparta_spring3.domain.Comment;
import com.sparta_spring.sparta_spring3.dto.*;
import com.sparta_spring.sparta_spring3.repository.CommentRepository;
import com.sparta_spring.sparta_spring3.security.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentService {

    public final String HEADER_PREFIX = "Bearer ";

    private final JwtDecoder jwtDecoder;
    private final CommentRepository commentRepository;

    public CommentService(JwtDecoder jwtDecoder, CommentRepository commentRepository) {
        this.jwtDecoder = jwtDecoder;
        this.commentRepository = commentRepository;
    }

    @Transactional(readOnly = true)
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
    }

    @Transactional(readOnly = true)
    public List<Comment> findByBlogIdOrderByCreateDateDesc(Long blogId) {
        return commentRepository.findByBlogIdOrderByCreateDateDesc(blogId);
    }

    @Transactional
    public ResponseDto commentWrite(RequestCommentDto requestCommentDto, Long blogId, String token) {
        validCheck(token, requestCommentDto);

        commentRepository.save(
                Comment.builder()
                        .comment(requestCommentDto.getComment())
                        .blogId(blogId)
                        .userId(jwtDecoder.decodeUsername(tokenProcess(token)))
                        .build()
        );

        return ResponseDto.builder()
                .statusCode(200)
                .message("저장되었습니다.")
                .build();
    }

    @Transactional
    public ResponseDto commentUpdate(RequestCommentDto requestCommentDto, Long commentId, String token) {
        validCheck(token, requestCommentDto);
        Comment commend = findById(commentId);

        if (!commend.getUserId().equals(jwtDecoder.decodeUsername(tokenProcess(token)))) {
            throw new IllegalArgumentException("글쓴이가 아닙니다.");
        }

        commend.update(requestCommentDto);

        return ResponseDto.builder()
                .statusCode(200)
                .message("수정되었습니다.")
                .build();
    }

    @Transactional
    public ResponseDto commentDelete(Long commentId, String token) {
        if (!validToken(token)) {
            throw new IllegalArgumentException("로그인이 필요한 기능입니다.");
        }

        if (!findById(commentId).getUserId().equals(jwtDecoder.decodeUsername(tokenProcess(token)))) {
            throw new IllegalArgumentException("글쓴이가 아닙니다.");
        }

        commentRepository.deleteById(commentId);
        return ResponseDto.builder()
                .statusCode(200)
                .message("삭제되었습니다.")
                .build();
    }

    public String tokenProcess(String token) {
        String bearer = token.substring(
                0,
                HEADER_PREFIX.length() - 1
        );

        if (bearer.equals("BEARER")) {
            return token.substring(
                    HEADER_PREFIX.length()
            );
        }
        return token;
    }

    private void validCheck(String token, RequestCommentDto requestCommentDto) {
        if (!validToken(token)) {
            throw new IllegalArgumentException("로그인이 필요한 기능입니다.");
        }

        if (!validComment(requestCommentDto)) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요");
        }
    }

    public boolean validToken(String token) {
        if (token == null || token.equals("") || token.length() < HEADER_PREFIX.length()) {
            return false;
        }
        return true;
    }

    public boolean validComment(RequestCommentDto requestCommentDto) {
        if (!Optional.ofNullable(requestCommentDto.getComment()).isPresent() || Optional.ofNullable(requestCommentDto.getComment()).get().equals("")) {
            return false;
        }
        return true;
    }
}
