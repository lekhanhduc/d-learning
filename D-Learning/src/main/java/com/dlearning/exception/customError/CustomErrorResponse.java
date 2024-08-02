package com.dlearning.exception.customError;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CustomErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String error;
}
