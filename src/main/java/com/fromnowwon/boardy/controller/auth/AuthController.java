package com.fromnowwon.boardy.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signup")
  public User signup(@RequestParam String email,
      @RequestParam String password,
      @RequestParam(required = false) String nickname) {
    return authService.signup(email, password, nickname);
  }

  @PostMapping("/login")
  public User login(@RequestParam String email,
      @RequestParam String password) {
    return authService.login(email, password);
  }
}
