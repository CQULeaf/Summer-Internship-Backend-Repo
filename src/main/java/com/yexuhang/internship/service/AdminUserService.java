package com.yexuhang.internship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.bean.AdminUser;
import com.yexuhang.internship.config.CommonResult;

/**
 * <p>
 * 后台管理员用户表 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-26
 */
public interface AdminUserService extends IService<AdminUser> {

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    CommonResult<?> login(String username, String password);

    /**
     * 管理员退出登录
     *
     * @param adminId 管理员ID
     * @return 退出结果
     */
    CommonResult<?> logout(Long adminId);

    /**
     * 添加管理员
     *
     * @param adminUser 管理员对象
     * @return 添加结果
     */
    CommonResult<?> addAdmin(AdminUser adminUser);

    /**
     * 删除管理员
     *
     * @param adminId 管理员ID
     * @return 删除结果
     */
    CommonResult<?> deleteAdmin(Long adminId);

    /**
     * 更新管理员信息
     *
     * @param adminUser 管理员对象
     * @return 更新结果
     */
    CommonResult<?> updateAdmin(AdminUser adminUser);
}
