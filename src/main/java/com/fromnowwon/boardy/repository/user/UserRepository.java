package com.fromnowwon.boardy.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fromnowwon.boardy.entity.User;
import com.fromnowwon.boardy.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByNickname(String nickname);

  Optional<User> findByRole(UserRole role);

  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);

}