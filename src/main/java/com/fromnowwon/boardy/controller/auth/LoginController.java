package com.fromnowwon.boardy.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fromnowwon.boardy.dto.auth.LoginRequest;
import com.fromnowwon.boardy.dto.auth.LoginResponse;
import com.fromnowwon.boardy.service.auth.AuthFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class LoginController {

  private final AuthFacade authFacade;

  @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    return authFacade.login(request.getEmail(), request.getPassword());
  }
}
