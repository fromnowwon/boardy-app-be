package com.fromnowwon.boardy.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("prod") // 운영 환경
public class ProdSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // Swagger 접근 시 ROLE_ADMIN 또는 로그인한 사용자만 허용
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")

            // H2 콘솔 차단
            .requestMatchers("/h2-console/**").denyAll()

            // 회원가입, 로그인은 인증 없이 허용
            .requestMatchers("/api/auth/**").permitAll()

            // ADMIN 전용 API
            .requestMatchers("/api/admin/**").hasRole("ADMIN")

            // 일반 사용자와 관리자 접근 허용
            .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")

            // 나머지는 인증 필요
            .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults()) // 로그인 폼 사용
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // H2 iframe 허용
    return http.build();
  }
}
