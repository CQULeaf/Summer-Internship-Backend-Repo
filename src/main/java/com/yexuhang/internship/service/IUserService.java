package com.yexuhang.internship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.bean.User;
import com.yexuhang.internship.config.CommonResult;

/**
 * @author Xuhang Ye
 */
public interface IUserService extends IService<User> {

        User login(String username, String password);
        CommonResult<?> register(String username, String password);
}
