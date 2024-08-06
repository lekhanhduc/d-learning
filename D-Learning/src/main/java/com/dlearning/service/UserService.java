package com.dlearning.service;

import com.dlearning.dto.request.UserCreate;
import com.dlearning.dto.response.PagedResponse;
import com.dlearning.dto.response.UserResponse;
import com.dlearning.entity.User;
import com.dlearning.mapper.UserMapper;
import com.dlearning.repository.UserRepository;
import com.dlearning.utils.enums.UserRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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


    public PagedResponse<UserResponse> getUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserResponse> userResponses = userPage.getContent().stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                userResponses,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalPages(),
                userPage.getTotalElements()
        );
    }
}

