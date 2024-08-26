package com.yexuhang.internship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
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

    CommonResult<?> getUserFriends(Long userId);

    CommonResult<CcUser> getUserById(Long userId);

    CommonResult<?> register(String username, String password, String password2);

    CommonResult<?> updateInfo(CcUser ccUser);

    CommonResult<?> updatePassword(String username, String oldPassword, String newPassword1, String newPassword2);

    CommonResult<?> updateAvatar(String username, String avatar);

    // 根据昵称获取用户信息
    CommonResult<CcUser> getUserByNickname(String nickname);
}

