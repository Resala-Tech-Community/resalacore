package org.resala.core.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController()
@RequestMapping("success")
public class SuccessLogin {

    @GetMapping()
    public String getSession(HttpSession session) {
        return "JSESSIONID=" + session.getId();
    }
}
