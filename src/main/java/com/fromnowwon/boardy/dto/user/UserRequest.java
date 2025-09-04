package com.fromnowwon.boardy.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {
  @Getter
  @NoArgsConstructor
  public static class Signup {
    @Schema(description = "사용자 이메일", example = "user@example.com", required = true)
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123", required = true)
    private String password;

    @Schema(description = "닉네임", example = "john_doe")
    private String nickname;
  }

  @Getter
  @NoArgsConstructor
  public static class Login {
    @Schema(description = "사용자 이메일", example = "user@example.com", required = true)
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123", required = true)
    private String password;
  }
}
