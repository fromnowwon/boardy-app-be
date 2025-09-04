package com.fromnowwon.boardy.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

  @Schema(description = "회원 이메일", example = "user@example.com")
  private String email;

  @Schema(description = "회원 비밀번호", example = "password123")
  private String password;

  @Schema(description = "닉네임", example = "user123")
  private String nickname;
}
