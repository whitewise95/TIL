package com.sparta_spring.sparta_spring3.controller;

import com.sparta_spring.sparta_spring3.domain.Blog;
import com.sparta_spring.sparta_spring3.dto.RequestBlogDto;
import com.sparta_spring.sparta_spring3.repository.BlogRepository;
import com.sparta_spring.sparta_spring3.service.BlogService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    private final BlogService blogService;
    private final BlogRepository blogRepository;

    public BlogController(BlogService blogService, BlogRepository blogRepository) {
        this.blogService = blogService;
        this.blogRepository = blogRepository;
    }

    @Controller
    public class BlogViewController {

        @GetMapping("/")
        public String blogView(Model model,
                               @PageableDefault(size = 6, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
                               @RequestParam(value = "currentPage", defaultValue = "1") int page) {

            model.addAttribute("pageable", blogRepository.findAll(pageable));
            model.addAttribute("page", page);
            return "index";
        }
    }

    @GetMapping("/blog")
    public List<Blog> blogFindAll() {
        return blogRepository.findAllByOrderByCreateDateDesc();
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
