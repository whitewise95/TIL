package com.sparta_spring.sparta_spring3.controller;

import com.sparta_spring.sparta_spring3.domain.Comment;
import com.sparta_spring.sparta_spring3.dto.*;
import com.sparta_spring.sparta_spring3.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{blogId}")
    public List<Comment> commentFindAll(@PathVariable Long blogId) {
        return commentService.findByBlogIdOrderByCreateDateDesc(blogId);
    }

    @PostMapping("/{blogId}")
    public ResponseDto commentWrite(@RequestBody RequestCommentDto requestCommentDto,
                                    @PathVariable Long blogId,
                                    @RequestHeader(value = "Authorization", defaultValue = "token") String token) {

        return commentService.commentWrite(requestCommentDto, blogId, token);
    }

    @PutMapping("/{commentId}")
    public ResponseDto commentUpdate(@RequestBody RequestCommentDto requestCommentDto,
                                     @PathVariable Long commentId,
                                     @RequestHeader(value = "Authorization", defaultValue = "token") String token) {
        return commentService.commentUpdate(requestCommentDto, commentId, token);
    }

    @DeleteMapping("/{commentId}")
    public ResponseDto commentDelete(@PathVariable Long commentId,
                                     @RequestHeader(value = "Authorization", defaultValue = "token") String token) {
        return commentService.commentDelete(commentId, token);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto notFound(Exception e) {
        return ResponseDto.builder().statusCode(500).message(e.getMessage()).build();
    }

}
