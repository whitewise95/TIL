package com.sparta_spring.sparta_spring3.repository;

import com.sparta_spring.sparta_spring3.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByOrderByCreateDateDesc();
}
