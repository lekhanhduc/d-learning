package com.dlearning.restController;

import com.dlearning.dto.request.UserCreate;
import com.dlearning.dto.response.PagedResponse;
import com.dlearning.dto.response.UserResponse;
import com.dlearning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserCreate create, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(create));
    }

    @GetMapping("/users")
    public ResponseEntity<PagedResponse<UserResponse>> getUsers(@RequestParam(name = "page") Optional<Integer> pageOptional,
                                                                @RequestParam(name = "size", defaultValue = "1") int size) {
        int page = pageOptional.orElse(1);
        PagedResponse<UserResponse> users = userService.getUsers(page - 1, size);
        if (users.getContent().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}
