package com.dlearning.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public static String[] PUBLIC_ENDPOINT = {
            "/", "/home", "/addCourse", "/course-detail/**",
            "/register", "/api/v1/courses/**", "/api/v1/users",
            "/user/**", "/css/**", "/js/**", "/images/**", "/fonts/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll()
                        .requestMatchers("/addCourse").authenticated()
                        .requestMatchers("/shop-cart").authenticated()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        // .sessionCreationPolicy : dùng always luôn luôn tạo mới, nếu người dùng chưa có session thì tạo mới
                        .invalidSessionUrl("/logout?expired")  // hết hạn session thì logout ra
                        .maximumSessions(1)                   // giới hạn tại 1 thời điểm được phép đăng nhập bao nhiêu thiết bị
                        .maxSessionsPreventsLogin(false)     // để là false nó có nghĩa: nếu người thứ 2 đăng nhập vào thì người thứ nhất sẽ bị đá ra, bay ra
                                                            // còn nếu để true, thì người thứ 2 phải đợi người thứ 1 hết phiên session thì mới vào được
                )
                .rememberMe(remember -> remember.rememberMeServices(rememberMeServices()))
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/403"));

        return httpSecurity.build();
    }
}
