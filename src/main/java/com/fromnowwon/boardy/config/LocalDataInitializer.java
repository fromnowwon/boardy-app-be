package com.fromnowwon.boardy.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.enums.UserRole;
import com.fromnowwon.boardy.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalDataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) {
    if (userRepository.count() == 0) { // 중복 방지
      // 일반 사용자
      userRepository.save(User.builder()
          .email("user1@example.com")
          .password(passwordEncoder.encode("user123"))
          .nickname("user1")
          .role(UserRole.USER) // 기본 권한
          .build());

      userRepository.save(User.builder()
          .email("user2@example.com")
          .password(passwordEncoder.encode("user123"))
          .nickname("user2")
          .role(UserRole.USER)
          .build());

      // 관리자
      userRepository.save(User.builder()
          .email("admin@example.com")
          .password(passwordEncoder.encode("admin123"))
          .nickname("Admin")
          .role(UserRole.ADMIN)
          .build());
    }
  }
}
