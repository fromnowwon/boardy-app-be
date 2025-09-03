package com.fromnowwon.boardy.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  // 회원 조회
  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
  }

  // 회원 정보 수정
  public User updateUser(Long id, String nickname, String password) {
    User user = getUserById(id);

    if (nickname != null) {
      // 닉네임 중복 체크
      if (userRepository.findByNickname(nickname).isPresent()) {
        throw new RuntimeException("중복된 닉네임입니다.");
      }
      user.setNickname(nickname);
    }

    if (password != null) {
      user.setPassword(passwordEncoder.encode(password));
    }

    return userRepository.save(user);
  }

  // 회원 탈퇴
  public void deleteUser(Long id) {
    User user = getUserById(id);
    userRepository.delete(user);
  }
}
