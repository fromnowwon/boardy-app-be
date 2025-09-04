package com.fromnowwon.boardy.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fromnowwon.boardy.dto.user.UserRequest;
import com.fromnowwon.boardy.dto.user.UserResponse;
import com.fromnowwon.boardy.service.auth.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "회원가입", description = "이메일, 비밀번호, 닉네임으로 회원을 생성합니다.")
  @PostMapping("/signup")
  public UserResponse signup(@RequestBody UserRequest.Signup request) {
    return new UserResponse(
        authService.signup(request.getEmail(), request.getPassword(), request.getNickname()));
  }

  @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
  @PostMapping("/login")
  public UserResponse login(@RequestBody UserRequest.Login request) {
    return new UserResponse(
        authService.login(request.getEmail(), request.getPassword()));
  }
}
