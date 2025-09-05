# 1. Build stage
FROM gradle:8.3-jdk17 AS builder
WORKDIR /app

# Gradle 캐시를 위해 소스 일부만 먼저 복사
COPY build.gradle settings.gradle gradle.properties ./
COPY gradle ./gradle

# 의존성만 먼저 빌드
RUN gradle build -x test --stacktrace || return 0

# 나머지 소스 코드 복사
COPY src ./src

# 프로젝트 빌드
RUN gradle bootJar -x test --stacktrace

# 2. Run stage
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Build stage에서 생성된 jar 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 설정
EXPOSE 8080

# 컨테이너 시작 시 실행
ENTRYPOINT ["java", "-jar", "app.jar"]