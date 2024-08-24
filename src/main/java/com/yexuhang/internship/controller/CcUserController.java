package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.dto.RegisterRequestDTO;
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


    // 
}
