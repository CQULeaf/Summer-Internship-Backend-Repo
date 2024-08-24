package com.yexuhang.internship.controller;

import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-23
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class CcUserController {
    @Autowired
    private CcUserService ccUserService;

    // 登录接口
    @PostMapping("/login")
    public CommonResult<?> login(String username, String password) {
        try {
            Serializable user = (Serializable) ccUserService.login(username, password);
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

        CommonResult<?> result = ccUserService.register(username, password);
        if (result.getCode() == 200) {
            log.info("User registered successfully: {}", username);
        } else {
            log.warn("Registration failed for username: {}", username);
        }
        return result;
    }

    // 获取用户好友列表接口

    @GetMapping("/getFriend")
    public CommonResult<?> getUserFriends(@RequestParam Long userId) {
        log.info("Fetching friends for user ID: {}", userId);
        try {
            CommonResult<?> result = ccUserService.getUserFriends(userId);
            if (result.getCode() == 200) {
                log.info("Successfully fetched friends for user ID: {}", userId);
            } else {
                log.warn("Failed to fetch friends for user ID: {}", userId);
            }
            return result;
        } catch (Exception e) {
            log.error("Error fetching friends for user ID: {}", userId, e);
            // 使用默认错误返回
            return CommonResult.error("获取好友列表异常");
        }
    }

    /**
     * 用户密码更改接口
     * @param userId 用户ID
     * @param currentPassword 当前密码
     * @param newPassword1 新密码
     * @param newPassword2 确认新密码
     * @return 密码更改结果
     */
    @PostMapping("/passwordChange")
    public CommonResult<?> passwordChange(@RequestParam Long userId,
                                          @RequestParam String currentPassword,
                                          @RequestParam String newPassword1,
                                          @RequestParam String newPassword2) {
        log.info("Password change attempt for user ID: {}", userId);

        try {
            CommonResult<?> result = ccUserService.passwordChange(userId, currentPassword, newPassword1, newPassword2);
            if (result.getCode() == 200) {
                log.info("Password changed successfully for user ID: {}", userId);
            } else {
                log.warn("Password change failed for user ID: {}", userId);
            }
            return result;
        } catch (Exception e) {
            log.error("Error changing password for user ID: {}", userId, e);
            return CommonResult.error("密码更改异常");
        }
    }
}
