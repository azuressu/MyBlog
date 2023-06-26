package com.example.post.service;

import com.example.post.dto.LoginRequestDto;
import com.example.post.dto.SignupRequestDto;
import com.example.post.dto.SignupResponseDto;
import com.example.post.entity.User;
import com.example.post.entity.UserRoleEnum;
import com.example.post.jwt.JwtUtil;
import com.example.post.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        String inputpassword = requestDto.getPassword();

        if (Pattern.matches("^[a-zA-Z0-9]*$", inputpassword) && (inputpassword.length()>=8 && inputpassword.length()<=15)) {
            String password = passwordEncoder.encode(requestDto.getPassword());

            // 회원 중복 확인
            Optional<User> checkUsername = userRepository.findByUsername(username);
            if (checkUsername.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            }

            // 사용자 ROLE 확인
            UserRoleEnum role = UserRoleEnum.USER;
            if (requestDto.isAdmin()) {
                if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                    throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
                }
                role = UserRoleEnum.ADMIN;
            }

            // 사용자 등록
            User user = new User(username, password, role);
            userRepository.save(user);

        } else {
            System.out.println("비밀번호 패턴에 맞지 않습니다.");
        }

//        String password = passwordEncoder.encode(requestDto.getPassword());

        SignupResponseDto signupResponseDto = new SignupResponseDto();
        signupResponseDto.setMsg("회원가입 성공");
        signupResponseDto.setStatusCode(200);

        return signupResponseDto;

    }
}
