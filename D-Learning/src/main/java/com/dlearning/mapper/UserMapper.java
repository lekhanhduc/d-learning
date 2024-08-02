package com.dlearning.mapper;

import com.dlearning.dto.request.UserCreate;
import com.dlearning.dto.response.UserResponse;
import com.dlearning.entity.User;

public class UserMapper {
    private UserMapper(){
    }

    public static User toUser(UserCreate create){
        return User.builder()
                .username(create.getUsername())
                .email(create.getEmail())
                .password(create.getPassword())
                .build();
    }

    public static UserResponse userResponse(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .success(true)
                .build();
    }
}
