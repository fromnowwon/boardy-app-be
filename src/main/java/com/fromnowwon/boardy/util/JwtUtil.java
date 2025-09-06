package com.fromnowwon.boardy.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fromnowwon.boardy.enums.UserRole;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

/**
 * JWT 토큰 생성, 검증, 클레임 추출 등 JWT 관련 유틸리티 클래스
 */
@Component
public class JwtUtil {

  public final SecretKey key;
  public final long accessTokenExpiration = 1000 * 60 * 60; // 1시간
  public final long refreshTokenExpiration = 1000 * 60 * 60 * 24 * 7; // 7일

  public JwtUtil(@Value("${jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * 액세스 토큰 생성
   * 
   * @param userId 유저 고유 ID
   * @param role   유저 권한 (USER / ADMIN)
   * @return JWT 문자열
   */
  public String generateToken(Long userId, UserRole role) {
    return Jwts.builder()
        .setSubject(String.valueOf(userId))
        .claim("role", role)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
        .signWith(key)
        .compact();
  }

  /**
   * 리프레시 토큰 생성
   * 
   * @param userId 유저 고유 ID
   * @return JWT 문자열
   */
  public String generateRefreshToken(Long userId) {
    return Jwts.builder()
        .setSubject(String.valueOf(userId))
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
        .signWith(key)
        .compact();
  }

  /**
   * JWT에서 userId 추출
   * 
   * @param token JWT 문자열
   * @return userId(Long)
   */
  public Long getUserId(String token) {
    return Long.valueOf(Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject());
  }

  /**
   * JWT에서 role(claim) 추출
   * 
   * @param token JWT 문자열
   * @return role(String)
   */
  public String getRole(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("role", String.class);
  }

  /**
   * JWT 유효성 검증
   * - 서명 검증 및 만료 시간 확인
   * 
   * @param token JWT 문자열
   * @return true: 유효, false: 만료/서명 실패
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException
        | IllegalArgumentException e) {
      return false; // 유효하지 않음 (만료, 변조 등)
    }
  }

}
