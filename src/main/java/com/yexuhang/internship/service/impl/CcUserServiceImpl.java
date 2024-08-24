package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcUserMapper;
import com.yexuhang.internship.service.CcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-23
 */
@Service
public class CcUserServiceImpl extends ServiceImpl<CcUserMapper, CcUser> implements CcUserService {

    @Autowired
    private CcUserMapper ccUserMapper;

    // 实现登录查询
    @Override
    public CcUser login(String username, String password) {
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return ccUserMapper.selectOne(queryWrapper);
    }

    // 实现注册并完成两次密码输入的校验
    @Override
    public CommonResult<?> register(String username, String password1, String password2) {
        // 检查用户名是否已经存在
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        CcUser existingUser = ccUserMapper.selectOne(queryWrapper);

        if (existingUser != null) {
            return CommonResult.error("用户名已存在");
        }

        // 检查两次密码是否一致
        if (!password1.equals(password2)) {
            return CommonResult.error("两次密码输入不一致, 请重新输入");
        }

        // 创建新用户对象并保存到数据库
        CcUser newUser = new CcUser();
        newUser.setUsername(username);
        newUser.setPassword(password1);

        int result = ccUserMapper.insert(newUser);
        if (result > 0) {
            return CommonResult.success("注册成功");
        } else {
            return CommonResult.error("注册失败, 请稍后再试");
        }
    }
}
