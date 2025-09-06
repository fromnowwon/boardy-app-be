package com.fromnowwon.boardy.entity;

import com.fromnowwon.boardy.enums.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Schema(description = "사용자 정보 엔티티")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "사용자 ID", example = "1", required = true)
  private Long id;

  @Column(unique = true, nullable = false)
  @Schema(description = "사용자 이메일", example = "user123@example.com", required = true)
  private String email;

  @Column(nullable = false)
  @Schema(description = "비밀번호", example = "password123", required = true)
  private String password;

  @Column(nullable = false, unique = true, length = 50)
  @Schema(description = "닉네임", example = "user123", required = true)
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Schema(description = "사용자 권한", example = "USER", required = true)
  private UserRole role;
}
