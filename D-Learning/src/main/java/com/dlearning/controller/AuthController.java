//package com.dlearning.controller;
//
//import com.dlearning.dto.request.UserCreate;
//import com.dlearning.dto.response.UserResponse;
//import com.dlearning.entity.User;
//import com.dlearning.service.UserService;
//import com.dlearning.utils.AuthenticationUtils;
//import com.dlearning.utils.SecurityUtils;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import static com.dlearning.mapper.UserMapper.userResponse;
//
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class AuthController {
//
//    AuthenticationUtils authenticationUtils;
//    UserService userService;
//    SecurityUtils securityUtils;
//
//    @PostMapping("/auth/login")
//    public ResponseEntity<UserResponse> login(@RequestBody UserCreate request) {
//        if (request.getUsername() == null || request.getUsername().isEmpty() || request.getPassword() == null || request.getPassword().isEmpty()) {
//            throw new IllegalArgumentException("Username and password must not be null or empty");
//        }
//
//        System.out.println(request.getUsername());
//
//        Authentication authentication = authenticationUtils.authenticateUser(request.getUsername(), request.getPassword());
//        User userCurrent = userService.findUserByUsername(request.getUsername());
//
//        if (userCurrent == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        // Generate tokens
//        String accessToken = securityUtils.accessToken(authentication.getName(), userResponse(userCurrent));
//        String refreshToken = authenticationUtils.createAndSaveRefreshToken(userCurrent);
//
//        // Store the refresh token in the database
//        System.out.println(userCurrent.getUsername());
//        userService.updateUserToken(refreshToken, userCurrent.getUsername());
//
//        // Create cookies for the tokens
//        ResponseCookie responseCookie = authenticationUtils.createResponseCookie(refreshToken);
//        UserResponse responseDto = authenticationUtils.mapToDTOResponseWithToken(userCurrent, accessToken);
//
//        // Return response with cookies
//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
//                .body(responseDto);
//    }
//
//
////
////    @GetMapping("/auth/logout")
////    public ResponseEntity<Void> logout() {
////        String currentLogin = SecurityUtils.getCurrentUserLogin()
////                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
////
////        if (currentLogin != null) {
////            User user = userService.findUserByUsername(currentLogin);
////            if (user != null) {
////                userService.updateUserToken(null, user.getUsername());
////            } else {
////                throw new ResourceNotFoundException("User not found for email: " + currentLogin);
////            }
////        }
////
////        ResponseCookie responseCookie = authenticationUtils.removeCookies();
////        SecurityContextHolder.clearContext();
////        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).build();
////    }
////
////
////    @GetMapping("/auth/refresh")
////    public ResponseEntity<UserResponse> refreshToken(@CookieValue(name = "refresh_Token", required = false) String refresh_Token) {
////        if (refresh_Token == null || refresh_Token.isEmpty()) {
////            throw new IllegalArgumentException("Refresh token is missing or empty");
////        }
////
////        Jwt decodeToken = securityUtils.checkRefreshToken(refresh_Token);
////        String email = decodeToken.getSubject();
////
////        User currentUser = userService.findByRefreshTokenAndEmail(refresh_Token, email);
////        if (currentUser == null) {
////            throw new ResourceNotFoundException("User not found");
////        }
////
////        String accessToken = securityUtils.accessToken(email, userResponse(currentUser));
////        String newRefreshToken = authenticationUtils.createAndSaveRefreshToken(currentUser);
////        ResponseCookie responseCookie = authenticationUtils.createResponseCookie(newRefreshToken);
////
////        UserResponse responseDto = authenticationUtils.mapToDTOResponseWithToken(currentUser, accessToken);
////
////        return ResponseEntity.ok()
////                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
////                .body(responseDto);
////    }
//}
