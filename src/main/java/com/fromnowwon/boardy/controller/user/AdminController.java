package com.fromnowwon.boardy.controller.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "관리자 전용 API")
public class AdminController {

  private final UserService userService;

  @Operation(summary = "모든 사용자 조회", description = "ADMIN 권한만 접근 가능")
  @GetMapping("/all-users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
