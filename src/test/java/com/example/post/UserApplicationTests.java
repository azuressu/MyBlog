package com.example.post;

import com.example.post.dto.LoginRequestDto;
import com.example.post.dto.SignupRequestDto;
import com.example.post.dto.StatusResponseDto;
import com.example.post.repository.PostRepository;
import com.example.post.repository.UserRepository;
import com.example.post.security.JwtAuthenticationFilter;
import com.example.post.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    PostRepository postRepository;

    @Test
    @DisplayName("회원가입 테스트")
    void test1() {
        SignupRequestDto signupRequestDto = new SignupRequestDto();
        signupRequestDto.setUsername("sysysysy");
        signupRequestDto.setPassword("highlight1234");

        StatusResponseDto signup = userService.signup(signupRequestDto);
        System.out.println("signup.getMsg() = " + signup.getMsg());
        System.out.println("signup.getStatusCode() = " + signup.getStatusCode());
    }

    @Test
    @DisplayName("로그인 테스트")
    void test2() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("sysysysy");
        loginRequestDto.setPassword("highlight1234");

        System.out.println();
    }
}
