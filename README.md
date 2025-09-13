# Boardy Backend

Spring Boot + JPA 기반으로 사용자 인증, 권한 관리, 사용자 CRUD 기능을 제공합니다.

## 개발 환경

- Java: 17
- Build Tool: Gradle (Groovy)
- Spring Boot: 3.5.5
- 로컬 DB: H2 (in-memory)
- 운영 DB: PostgreSQL
- Documentation: Swagger (Springdoc OpenAPI)
- JPA/Hibernate: Entity 중심 설계
  - 개발(local): ddl-auto=update
  - 운영(prod): ddl-auto=validate

## 실행 방법

(기본) `./gradlew bootRun`

(개발) `./gradlew bootRun --args="--spring.profiles.active=local"`

(운영) `./gradlew bootRun --args="--spring.profiles.active=prod"`

## 계층형 패키지 구조 (Layered Architecture)

```bash
boardy-backend/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/fromnowwon/boardy/
│  │  │      ├─ BoardyApplication.java
│  │  │      ├─ config/                         # Security, Swagger 등 설정
│  │  │      │  ├─ security/                    # Spring Security 설정
│  │  │      │  │  ├─ LocalSecurityConfig.java
│  │  │      │  │  ├─ ProdSecurityConfig.java
│  │  │      │  │  └─ SecurityConfig.java
│  │  │      │  ├─ DataInitializer.java         # 샘플 데이터 초기화
│  │  │      │  └─ OpenApiConfig.java           # Swagger 설정
│  │  │      ├─ controller/                     # REST API Controller
│  │  │      │  ├─ auth/
│  │  │      │  │  ├─ SignupController.java
│  │  │      │  │  └─ LoginController.java
│  │  │      │  └─ user/
│  │  │      │      ├─ AdminController.java
│  │  │      │      └─ UserController.java
│  │  │      ├─ dto/                            # 요청/응답 DTO
│  │  │      │  ├─ auth/
│  │  │      │  │  ├─ SignupRequest.java
│  │  │      │  │  ├─ LoginRequest.java
│  │  │      │  │  └─ LoginResponse.java
│  │  │      │  ├─ common/
│  │  │      │  │  └─ ErrorResponse.java
│  │  │      │  └─ user/
│  │  │      │      ├─ UserRequest.java
│  │  │      │      └─ UserResponse.java
│  │  │      ├─ entity/                          # JPA Entity
│  │  │      │  └─ User.java
│  │  │      ├─ enums/                           # 열거형 (권한 등)
│  │  │      │  └─ UserRole.java
│  │  │      ├─ exception/                       # 전역 예외 처리
│  │  │      │  └─ GlobalExceptionHandler.java
│  │  │      ├─ repository/                      # DB 접근 계층
│  │  │      │  └─ user/
│  │  │      │      └─ UserRepository.java
│  │  │      ├─ service/
│  │  │      │  ├─ auth/
│  │  │      │  │  ├─ AuthFacade.java            # auth 비즈니스 로직 진입점
│  │  │      │  │  ├─ SignupService.java
│  │  │      │  │  └─ LoginService.java
│  │  │      │  └─ user/
│  │  │      │      └─ UserService.java
│  │  │      └─ util/
│  │  │          └─ JwtUtil.java
│  │  └─ resources/
│  │      ├─ application.yml                        # 공통 설정
│  │      ├─ application-local.yml                  # 로컬 설정
│  │      ├─ application-prod.yml                   # 운영 설정
│  │      └─ static/                                # 정적 리소스 (필요 시)
└─ ...
```

## 예외 처리

- 정상 응답: HTTP 200~299 → 데이터 DTO 반환
- 에러 응답: HTTP 4xx/5xx → ErrorResponse 반환
