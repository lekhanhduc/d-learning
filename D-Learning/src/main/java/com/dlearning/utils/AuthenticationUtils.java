//package com.dlearning.utils;
//
//
//import com.dlearning.dto.response.UserResponse;
//import com.dlearning.entity.User;
//import com.dlearning.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseCookie;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import static com.dlearning.mapper.UserMapper.userResponse;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationUtils {
//
//    @Value("${jwt.refresh-token-validity-in-seconds}")
//    private long REFRESH_EXPIRATION;
//
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//
//    private final UserService userService;
//    private final SecurityUtils securityUtils;
//
//    public Authentication authenticateUser(String username, String password) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(username, password);
//
//        return authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//    }
//
//    public ResponseCookie createResponseCookie(String refreshToken) {
//        return ResponseCookie.from("refresh_Token", refreshToken)
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(REFRESH_EXPIRATION)
//                .build();
//    }
//
//    public ResponseCookie removeCookies() {
//        return ResponseCookie.from("refresh_Token", "")
//                .httpOnly(true)
//                .secure(true)
//                .path("/login")
//                .maxAge(0)
//                .build();
//    }
//
//
//    public String createAndSaveRefreshToken(User currentUser) {
//        String refreshToken = securityUtils.refreshToken(currentUser.getUsername(), userResponse(currentUser));
//        userService.updateUserToken(refreshToken, currentUser.getUsername());
//        return refreshToken;
//    }
//
//    public UserResponse mapToDTOResponseWithToken(User user, String token) {
//        return UserResponse.builder()
//                .email(user.getEmail())
//                .username(user.getUsername())
//                .token(token)
//                .success(true)
//                .build();
//    }
//}
