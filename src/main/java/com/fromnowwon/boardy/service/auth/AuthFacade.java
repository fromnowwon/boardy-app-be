package com.fromnowwon.boardy.service.auth;

import org.springframework.stereotype.Service;

import com.fromnowwon.boardy.dto.auth.LoginResponse;
import com.fromnowwon.boardy.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthFacade {

  private final SignupService signupService;
  private final LoginService loginService;

  public User signup(String email, String password, String nickname) {
    return signupService.signup(email, password, nickname);
  }

  public LoginResponse login(String email, String password) {
    return loginService.login(email, password);
  }
}
