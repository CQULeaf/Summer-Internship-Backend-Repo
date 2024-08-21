package com.yexuhang.internship.controller;

import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author  Xuhang Ye
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class User {
    @Autowired
    private IUserService userService;

    // 登录接口
    @GetMapping("/login")
    public CommonResult<?> login(String username, String password) {
        try {
            Serializable user = (Serializable) userService.login(username, password);
            if (user != null) {
                log.info("User logged in successfully: {}", username);
                return CommonResult.success(user);
            } else {
                log.warn("Login failed for username: {}", username);
                return CommonResult.error("用户名或密码错误");
            }
        } catch (Exception e) {
            log.error("Login error for username: {}", username, e);
            return CommonResult.error("登录异常");
        }
    }

    // 注册接口
    @PostMapping("/register")
    public CommonResult<?> register(@RequestParam String username,
                                    @RequestParam String password) {
        log.info("Registration attempt with username: {}", username);

        CommonResult<?> result = userService.register(username, password);
        if (result.getCode() == 200) {
            log.info("User registered successfully: {}", username);
        } else {
            log.warn("Registration failed for username: {}", username);
        }
        return result;
    }
}

