package com.fromnowwon.boardy.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

  @Schema(description = "회원 이메일", example = "user3@example.com")
  private String email;

  @Schema(description = "회원 비밀번호", example = "user123")
  private String password;

  @Schema(description = "닉네임", example = "user3")
  private String nickname;
}
