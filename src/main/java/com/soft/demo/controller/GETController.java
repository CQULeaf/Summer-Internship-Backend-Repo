package com.soft.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class GETController {
    @GetMapping("/login")
    public String login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return "login";
    }Apifox Helper: Remote host terminated the handshake

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
