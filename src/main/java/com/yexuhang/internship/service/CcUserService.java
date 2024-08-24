package com.yexuhang.internship.service;

import com.yexuhang.internship.bean.CcUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.config.CommonResult;
import java.util.List;
/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-21
 */
public interface CcUserService extends IService<CcUser> {

        CcUser login(String username, String password);

    CommonResult<?> register(String username, String password);

    CommonResult<?> getUserFriends(Long userId);

    /**
     * 用户密码更改接口
     * @param userId 用户ID
     * @param currentPassword 当前密码
     * @param newPassword1 新密码
     * @param newPassword2 确认新密码
     * @return 密码更改结果
     */
    CommonResult<?> passwordChange(Long userId, String currentPassword, String newPassword1, String newPassword2);




}