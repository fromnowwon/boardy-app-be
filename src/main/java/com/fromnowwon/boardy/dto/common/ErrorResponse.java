package com.fromnowwon.boardy.dto.common;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
  private final LocalDateTime timestamp;
  private final int status;
  private final String error;
  private final String message;
  private final String path;
}
