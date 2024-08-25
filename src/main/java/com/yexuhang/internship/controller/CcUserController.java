package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.dto.RegisterRequestDTO;
import com.yexuhang.internship.dto.updatePassword;
import com.yexuhang.internship.service.CcUserService;
import com.yexuhang.internship.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
@CrossOrigin(origins = "*")
public class CcUserController {
    @Autowired
    private CcUserService ccUserService;

    // 登录接口
    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody CcUser loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        log.info("Login attempt with username: {}", username);

        CcUser result = ccUserService.login(username, password);
        if (result != null) {
            log.info("User logged in successfully: {}", username);
            return CommonResult.success(result);
        } else {
            log.warn("Login failed for username: {}", username);
            return CommonResult.error("用户名或密码错误");
        }
    }


    // 注册接口
    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        String username = registerRequestDTO.getUsername();
        String password1 = registerRequestDTO.getPassword1();
        String password2 = registerRequestDTO.getPassword2();

        log.info("Register attempt with username: {}", username);

        return ccUserService.register(username, password1, password2);
    }


    // 修改个人信息
    @PostMapping("/updateInfo")
    public CommonResult<?> updateInfo(@RequestBody CcUser ccUser) {
        log.info("Update info attempt with username: {}", ccUser.getUsername());
        return ccUserService.updateInfo(ccUser);
    }

    // 修改密码
    @PostMapping("/updatePassword")
    public CommonResult<?> updatePassword(@RequestBody updatePassword updatePassword) {
        String username = updatePassword.getUsername();
        String oldPassword = updatePassword.getOldPassword();
        String newPassword1 = updatePassword.getNewPassword1();
        String newPassword2 = updatePassword.getNewPassword2();

        log.info("Update password attempt with username: {}", username);

        return ccUserService.updatePassword(username, oldPassword, newPassword1, newPassword2);
    }

    // 更新用户头像
    @PostMapping("/updateAvatar")
    public CommonResult<?> updateAvatar(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
        try {
            String avatar = UploadUtil.uploadImage(file);
            return ccUserService.updateAvatar(username, avatar);
        } catch (IOException e) {
            log.error("Failed to upload avatar for username: {}", username, e);
            return CommonResult.error("头像上传失败");
        }
    }


    // 获取用户好友列表接口
    @GetMapping("/getFriends")
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
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/getUserInfo")
    public CommonResult<CcUser> getUserInfo(@RequestParam("userId") Long userId) {
        return ccUserService.getUserById(userId);
    }
}