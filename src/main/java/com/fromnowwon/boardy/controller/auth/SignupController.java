package com.fromnowwon.boardy.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fromnowwon.boardy.dto.auth.SignupRequest;
import com.fromnowwon.boardy.dto.user.UserResponse;
import com.fromnowwon.boardy.service.auth.AuthFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class SignupController {

  private final AuthFacade authFacade;

  @Operation(summary = "회원가입", description = "이메일, 비밀번호, 닉네임으로 회원을 생성합니다.")
  @PostMapping("/signup")
  public UserResponse signup(@RequestBody SignupRequest request) {
    return new UserResponse(
        authFacade.signup(request.getEmail(), request.getPassword(), request.getNickname()));
  }
}
