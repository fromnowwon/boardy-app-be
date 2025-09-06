package com.fromnowwon.boardy.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
  private final String accessToken;
  private final String refreshToken;
}
