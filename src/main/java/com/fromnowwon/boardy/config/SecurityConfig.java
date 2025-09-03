package com.fromnowwon.boardy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // H2 콘솔과 Swagger는 모두 허용
            .requestMatchers("/h2-console/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()

            // ADMIN 전용 엔드포인트
            .requestMatchers("/api/admin/**").hasRole("ADMIN")

            // 나머지 USER/ADMIN 접근 허용
            .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")

            .anyRequest().authenticated())
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // H2 iframe 허용
        .formLogin(Customizer.withDefaults()); // 기본 로그인 폼 사용
    return http.build();
  }
}
