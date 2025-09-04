package com.fromnowwon.boardy.dto.user;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.enums.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserResponse {

  @Schema(description = "회원 ID", example = "1")
  private final Long id;

  @Schema(description = "이메일", example = "user@example.com")
  private final String email;

  @Schema(description = "닉네임", example = "user123")
  private final String nickname;

  @Schema(description = "권한", example = "USER")
  private final UserRole role;

  // 엔티티 -> DTO 변환
  public UserResponse(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.nickname = user.getNickname();
    this.role = user.getRole();
  }
}
