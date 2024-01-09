package com.demo.observability.dto;

import java.time.Instant;

public record HttpExceptionHandlerResponseDto(Instant timestamp,
                                              Integer httpStatus,
                                              String error,
                                              String errorMessage,
                                              String path) {
}
