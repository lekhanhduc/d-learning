package com.dlearning.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreate {
    private String username;
    private String email;
    private String password;
}
