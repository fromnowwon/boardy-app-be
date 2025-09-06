package com.fromnowwon.boardy.service.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fromnowwon.boardy.dto.auth.LoginResponse;
import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.repository.user.UserRepository;
import com.fromnowwon.boardy.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final BCryptPasswordEncoder passwordEncoder;

  /**
   * 로그인 처리
   */
  public LoginResponse login(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("비밀번호가 맞지 않습니다.");
    }

    // 1. Access Token 발급
    String accessToken = jwtUtil.generateToken(user.getId(), user.getRole());

    // 2. Refresh Token 발급
    String refreshToken = jwtUtil.generateRefreshToken(user.getId());

    // 3. Access Token과 Refresh Token 반환
    return new LoginResponse(accessToken, refreshToken);
  }
}