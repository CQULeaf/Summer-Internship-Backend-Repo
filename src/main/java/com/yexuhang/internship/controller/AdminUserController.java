package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.AdminUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 管理员用户表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 管理员登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @PostMapping("/login")
    public CommonResult<?> login(@RequestParam String username, @RequestParam String password) {
        return adminUserService.login(username, password);
    }

    /**
     * 管理员退出接口
     *
     * @param adminId 管理员ID
     * @return 退出结果
     */
    @PostMapping("/logout")
    public CommonResult<?> logout(@RequestParam Long adminId) {
        return adminUserService.logout(adminId);
    }

    /**
     * 添加管理员接口
     *
     * @param adminUser 管理员用户对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public CommonResult<?> addAdmin(@RequestBody AdminUser adminUser) {
        return adminUserService.addAdmin(adminUser);
    }

    /**
     * 删除管理员接口
     *
     * @param adminId 管理员ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{adminId}")
    public CommonResult<?> deleteAdmin(@PathVariable Long adminId) {
        return adminUserService.deleteAdmin(adminId);
    }

    /**
     * 更新管理员信息接口
     *
     * @param adminUser 管理员用户对象
     * @return 更新结果
     */
    @PutMapping("/update")
    public CommonResult<?> updateAdmin(@RequestBody AdminUser adminUser) {
        return adminUserService.updateAdmin(adminUser);
    }
}
