package com.dlearning.mapper;

import com.dlearning.dto.request.UserCreate;
import com.dlearning.dto.response.UserResponse;
import com.dlearning.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toUser(UserCreate create) {
        if (create == null) {
            return null;
        }
        return User.builder()
                .username(create.getUsername())
                .password(create.getPassword())
                .email(create.getEmail())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .username(user.getUsername())
                .success(true)
                .build();
    }
}
