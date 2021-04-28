package com.ebanking.ebanking.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
