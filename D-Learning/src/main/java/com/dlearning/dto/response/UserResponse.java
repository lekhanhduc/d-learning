package com.dlearning.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @JsonProperty("access_token")
    String token;
}
