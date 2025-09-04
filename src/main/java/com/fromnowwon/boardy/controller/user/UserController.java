package com.fromnowwon.boardy.controller.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fromnowwon.boardy.dto.user.UserResponse;
import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "사용자 관련 API")
public class UserController {

  private final UserService userService;

  // 내 정보 조회
  @Operation(summary = "내 정보 조회", description = "로그인한 사용자의 정보를 조회합니다.")
  @GetMapping("/me")
  public UserResponse getMyInfo(
      @Parameter(description = "조회할 사용자 ID", required = true) @RequestParam Long userId) {
    User user = userService.getUserById(userId);
    return new UserResponse(user);
  }

  // 내 정보 수정
  @Operation(summary = "내 정보 수정", description = "로그인한 사용자의 닉네임과 비밀번호를 수정합니다.")
  @PostMapping("/me")
  public UserResponse updateMyInfo(
      @Parameter(description = "수정할 사용자 ID", required = true) @RequestParam Long userId,
      @Parameter(description = "변경할 닉네임", required = true) @RequestParam String nickname,
      @Parameter(description = "변경할 비밀번호", required = true) @RequestParam String password) {
    User updatedUser = userService.updateUser(userId, nickname, password);
    return new UserResponse(updatedUser);
  }

  // 회원 탈퇴
  @Operation(summary = "회원 탈퇴", description = "로그인한 사용자의 계정을 삭제합니다.")
  @DeleteMapping("/me")
  public String deleteMyAccount(
      @Parameter(description = "삭제할 사용자 ID", required = true) @RequestParam Long userId) {
    userService.deleteUser(userId);
    return "회원 탈퇴가 완료되었습니다.";
  }

  // 다른 회원 정보 조회
  @Operation(summary = "다른 회원 정보 조회", description = "ID로 다른 사용자의 정보를 조회합니다.")
  @GetMapping("/{id}")
  public UserResponse getUserInfo(
      @Parameter(description = "조회할 사용자 ID", required = true) @PathVariable Long id) {
    User user = userService.getUserById(id);
    return new UserResponse(user);
  }
}
