package com.dlearning.service;

import com.dlearning.dto.request.UserCreate;
import com.dlearning.dto.response.UserResponse;
import com.dlearning.entity.User;
import com.dlearning.mapper.UserMapper;
import com.dlearning.repository.UserRepository;
import com.dlearning.utils.enums.UserRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserResponse saveUser(UserCreate create){
        User userByUsername = userRepository.findByUsername(create.getUsername());
        User userByEmail = userRepository.findByEmail(create.getEmail());
        if(userByUsername != null || userByEmail != null){
            throw new DataIntegrityViolationException("Email or Username already exists");
        }
        User user = UserMapper.toUser(create);
        user.setRole(UserRole.STUDENT);
        user.setCreatedBy(create.getUsername());
        user.setPassword(passwordEncoder.encode(create.getPassword()));
        user.setCreatedBy(create.getUsername());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        return UserMapper.toUserResponse(user);
    }
}
