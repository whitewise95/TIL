package com.sparta_spring.sparta_spring3.repository;

import com.sparta_spring.sparta_spring3.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogIdOrderByCreateDateDesc(Long blogId);
}
