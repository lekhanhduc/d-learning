package com.dlearning.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUtils {

//    private final JwtEncoder jwtEncoder;
//    public static MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;
//
//    @Value("${jwt.base64.secret}")
//    private String KEY_SECRET;
//
//    @Value("${jwt.token-validity-in-seconds}")
//    private long ACCESSION_EXPIRATION;
//
//    @Value("${jwt.refresh-token-validity-in-seconds}")
//    private long REFRESH_EXPIRATION;
//
//    private SecretKey getSecretKey(){
//        byte[] keyBytes = Base64.from(KEY_SECRET).decode();
//        return new SecretKeySpec(keyBytes, 0, keyBytes.length, JWT_ALGORITHM.getName());
//    }
//
//    public Jwt checkRefreshToken(String token){
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
//                .withSecretKey(getSecretKey())
//                .macAlgorithm(SecurityUtils.JWT_ALGORITHM).build();
//        try {
//            return jwtDecoder.decode(token);
//        }catch (Exception e){
//            System.out.println("Refresh token error");
//            throw e;
//        }
//    }
//
//
//    public String accessToken(String username, UserResponse response) {
//        Instant now = Instant.now();
//        Instant validity = now.plus(this.ACCESSION_EXPIRATION, ChronoUnit.SECONDS);
//
//        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
//
//        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                .subject(username) //subject thường được sử dụng để chỉ ra người dùng hoặc thực thể mà token đại diện
//                .issuer("khanhduc.com")
//                .issuedAt(now)
//                .expiresAt(validity)
//                .claim("username", response.getUsername())
//                .claim("email", response.getEmail())
//                .build();
//
//        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
//    }
//
//
//
//    public String refreshToken(String username, UserResponse response){
//        Instant now = Instant.now();
//        Instant validity = now.plus(this.REFRESH_EXPIRATION, ChronoUnit.SECONDS);
//
//        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
//
//        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                .subject(username)
//                .issuer("khanhduc.com")
//                .issuedAt(now)
//                .expiresAt(validity)
//                .claim("email", response.getEmail())
//                .claim("username", response.getUsername())
//                .build();
//
//        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
//    }
//
//
//
//    public static Optional<String> getCurrentUserLogin() {
//        SecurityContext contextHolder = SecurityContextHolder.getContext();
//        Authentication authentication = contextHolder.getAuthentication();
//        return Optional.ofNullable(extractPrincipal(authentication));
//    }
//
//    private static String extractPrincipal(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//            return null;
//        } else if (authentication.getPrincipal() instanceof UserDetails) {
//            return ((UserDetails) authentication.getPrincipal()).getUsername();
//        } else if (authentication.getPrincipal() instanceof Jwt) {
//            return ((Jwt) authentication.getPrincipal()).getSubject();
//        } else if (authentication.getPrincipal() instanceof String) {
//            return (String) authentication.getPrincipal();
//        }
//        return null;
//    }
//
//
//    public static Optional<String> getCurrentUserJwt(){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        return Optional.ofNullable(securityContext.getAuthentication())
//                .filter(authentication -> authentication.getCredentials() instanceof String)
//                .map(authentication -> (String) authentication.getCredentials());
//    }

}
