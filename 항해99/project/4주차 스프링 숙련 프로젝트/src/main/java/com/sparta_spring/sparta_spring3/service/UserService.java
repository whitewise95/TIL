package com.sparta_spring.sparta_spring3.service;

import com.sparta_spring.sparta_spring3.domain.User;
import com.sparta_spring.sparta_spring3.dto.*;
import com.sparta_spring.sparta_spring3.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseDto registerUser(SignupRequestDto requestDto) {
        if (
                !Optional.ofNullable(requestDto).isPresent()
                        || !Optional.ofNullable(requestDto.getUsername()).isPresent()
                        || !Optional.ofNullable(requestDto.getPassword()).isPresent()
                        || !Optional.ofNullable(requestDto.getPasswordCheck()).isPresent()
                        || !Optional.ofNullable(requestDto.getEmail()).isPresent()
        ) {
            throw new RuntimeException("빈 입력칸 없이 입력해주세요");
        }

        // 회원 ID 중복 확인
        String username = requestDto.getUsername();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new RuntimeException("중복된 사용자 ID 가 존재합니다.");
        }

        if (!Pattern.matches("^([a-zA-Z0-9]{3,8})$", username) || Pattern.matches("\\s", username)) {
            throw new RuntimeException("UserName은 최소 3자 이상 8자 이하 및 공백을 제거 및 그리고 대소문자(a~z, A~Z), 숫자(0~9)로 입력해주세요");
        }

        if (requestDto.getPassword().length() < 3 && requestDto.getPassword().length() > 8 || requestDto.getPassword()
                .equals(username) || Pattern.matches("\\s", requestDto.getPassword())) {
            throw new RuntimeException("비밀번호는 최소 4자 이상 8자 이하 및 공백을 제거 및 userName과 동일하게 구성하실 수 없습니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        User user = new User(username, password, email);
        userRepository.save(user);

        return ResponseDto.builder()
                .statusCode(200)
                .message("회원가입을 축하드립니다.")
                .build();
    }

    public ResponseDto login(RequestUserDto requestUserDto) {
        User user = userRepository.findByUsername(requestUserDto.getUsername())
                .orElseThrow(() -> new RuntimeException("닉네임 또는 패스워드를 확인해주세요"));

        if (!passwordEncoder.matches(requestUserDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("닉네임 또는 패스워드를 확인해주세요");
        }
        return null;
    }
}