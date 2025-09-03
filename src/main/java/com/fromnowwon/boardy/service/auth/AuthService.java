package com.fromnowwon.boardy.service.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.enums.UserRole;
import com.fromnowwon.boardy.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User signup(String email, String password, String nickname) {
    if (userRepository.findByEmail(email).isPresent()) {
      throw new RuntimeException("이미 존재하는 이메일입니다.");
    }
    if (nickname != null && userRepository.findByNickname(nickname).isPresent()) {
      throw new RuntimeException("이미 존재하는 닉네임입니다.");
    }

    User user = User.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .nickname(nickname)
        .role(UserRole.USER) // 기본 권한 USER
        .build();
    return userRepository.save(user);
  }

  public User login(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("없는 이메일입니다."));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("비밀번호가 틀렸습니다.");
    }
    return user;
  }
}
