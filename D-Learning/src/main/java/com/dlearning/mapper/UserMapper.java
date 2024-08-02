package com.dlearning.mapper;

import com.dlearning.dto.request.UserCreate;
import com.dlearning.dto.response.UserResponse;
import com.dlearning.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreate create);
    UserResponse userResponse(User user);
}
