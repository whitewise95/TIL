package com.sparta_spring.sparta_spring3.controller;

import com.sparta_spring.sparta_spring3.domain.*;
import com.sparta_spring.sparta_spring3.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;
    private final BlogRepository blogRepository;

    @GetMapping("/blog")
    public List<Blog> blogFindAll() {
        return blogRepository.findAllByOrderByCreateDateDesc();
    }

    @GetMapping("/blogByPage")
    public Page<Blog> blogFindAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @PostMapping("/blog")
    public Long blogWrite(@RequestBody Blog blog) {
        return blogRepository.save(blog).getId();
    }

    @PutMapping("/blog/{id}")
    public Long blogUpdate(@PathVariable Long id,
                           @RequestBody RequestBlogDto requestBlogDto) {
        blogService.blogUpdate(id, requestBlogDto);
        return id;
    }

    @DeleteMapping("/blog/{id}")
    public Long blogDelete(@PathVariable Long id,
                           @RequestBody RequestBlogDto requestBlogDto) {
        blogService.blogDelete(id, requestBlogDto);
        return id;
    }

}
