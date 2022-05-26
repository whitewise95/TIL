package com.sparta_spring.sparta_spring3.service;

import com.sparta_spring.sparta_spring3.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public void blogUpdate(Long id, RequestBlogDto requestBlogDto) {
        Blog blog = findOneBolg(id);
        if (!isPasswordCheck(blog.getPassword(), requestBlogDto.getPassword())) {
            throw new RuntimeException(("비밀번호가 일치하지 않습니다."));
        }
        if(Optional.ofNullable(requestBlogDto.getImgUrl()).isPresent()){
            if(!blog.getImgUrl().equals(requestBlogDto.getImgUrl())){
                blog.updateWiteImage(requestBlogDto);
                return;
            }
        }
        blog.update(requestBlogDto);
    }

    @Transactional
    public void blogDelete(Long id, RequestBlogDto requestBlogDto) {
        if (isPasswordCheck(findOneBolg(id).getPassword(), requestBlogDto.getPassword())) {
            blogRepository.deleteById(id);
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional
    public Blog findOneBolg(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
    }

    public boolean isPasswordCheck(String dbPassword, String requestPassword) {
        return dbPassword.equals(requestPassword);
    }
}
