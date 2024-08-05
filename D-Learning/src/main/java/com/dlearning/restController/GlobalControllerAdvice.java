package com.dlearning.restController;

import com.dlearning.entity.User;
import com.dlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final UserService userService;

    @Autowired
    public GlobalControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void setAccountInGlobal(@AuthenticationPrincipal OAuth2User oAuth2User, Principal principal, Model model) {
        User accountEntity = null;
        if (principal != null) {
            String username = principal.getName();
            accountEntity = userService.findUserByEmail(username);
        } else if (oAuth2User != null){
            accountEntity = userService.findUserByUsername(oAuth2User.getAttribute("email"));
        }
        model.addAttribute("account", accountEntity);
    }
}
