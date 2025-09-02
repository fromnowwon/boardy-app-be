package com.fromnowwon.boardy.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fromnowwon.boardy.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByNickname(String nickname);
}