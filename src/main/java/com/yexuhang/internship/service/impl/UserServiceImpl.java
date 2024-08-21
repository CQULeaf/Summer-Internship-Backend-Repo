package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.bean.User;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.UserMapper;
import com.yexuhang.internship.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Xuhang Ye
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 实现登录查询
    @Override
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return userMapper.selectOne(queryWrapper);
    }

    // 实现注册
    @Override
    public CommonResult<?> register(String username, String password) {
        // 检查用户名是否已经存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User existingUser = userMapper.selectOne(queryWrapper);

        if (existingUser != null) {
            return CommonResult.error("用户名已存在");
        }

        // 创建新用户对象并保存到数据库
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        int result = userMapper.insert(newUser);
        if (result > 0) {
            return CommonResult.success("注册成功");
        } else {
            return CommonResult.error("注册失败, 请稍后再试");
        }
    }
}
