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
}
