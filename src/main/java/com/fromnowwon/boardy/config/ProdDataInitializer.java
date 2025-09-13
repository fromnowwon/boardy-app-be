package com.fromnowwon.boardy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.enums.UserRole;
import com.fromnowwon.boardy.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdDataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  @Override
  public void run(String... args) {
    // 기본 관리자 계정(admin) 생성
    if (!userRepository.existsByEmail(adminEmail)) {
      userRepository.save(User.builder()
          .email(adminEmail)
          .password(passwordEncoder.encode(adminPassword))
          .nickname("Admin")
          .role(UserRole.ADMIN)
          .build());
      System.out.println("Admin account: " + adminEmail);
    }
  }
}