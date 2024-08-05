package com.dlearning.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    boolean success;
    private String avatar;
    private LocalDateTime createdAt;
    private String createdBy;
}
