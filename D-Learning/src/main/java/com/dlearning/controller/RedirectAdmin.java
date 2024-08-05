package com.dlearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectAdmin {

    @GetMapping("/admin")
    public String homeAdmin() {
        return "admin/index";
    }
}
