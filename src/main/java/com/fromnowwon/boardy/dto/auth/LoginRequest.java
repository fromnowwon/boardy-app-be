package com.fromnowwon.boardy.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

  @Schema(description = "회원 이메일", example = "user1@example.com")
  private String email;

  @Schema(description = "회원 비밀번호", example = "user123")
  private String password;
}
