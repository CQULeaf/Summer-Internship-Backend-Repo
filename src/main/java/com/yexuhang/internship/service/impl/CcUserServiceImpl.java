package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcUserMapper;
import com.yexuhang.internship.service.CcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-21
 */
@Service
public class CcUserServiceImpl extends ServiceImpl<CcUserMapper, CcUser> implements CcUserService {

    private final CcUserMapper ccUserMapper;

    public CcUserServiceImpl(CcUserMapper ccUserMapper) {
        this.ccUserMapper = ccUserMapper;
    }

    // 实现登录查询
    @Override
    public CcUser login(String username, String password) {
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return ccUserMapper.selectOne(queryWrapper);
    }

    // 实现注册
    @Override
    public CommonResult<?> register(String username, String password) {
        // 检查用户名是否已经存在
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        CcUser existingUser = ccUserMapper.selectOne(queryWrapper);

        if (existingUser != null) {
            return CommonResult.error("用户名已存在");
        }

        // 创建新用户对象并保存到数据库
        CcUser newUser = new CcUser();
        newUser.setUsername(username);
        newUser.setPassword(password);

        int result = ccUserMapper.insert(newUser);
        if (result > 0) {
            return CommonResult.success("注册成功");
        } else {
            return CommonResult.error("注册失败, 请稍后再试");
        }
    }
    @Override
    public CommonResult<?> getUserFriends(Long userId) {
        // 构造查询条件，查找与当前用户互相关注的用户，即好友关系
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("follower_count", userId)
                .eq("followee_count", userId);

        // 查询好友列表
        List<CcUser> friendsList = ccUserMapper.selectList(queryWrapper);

        // 返回封装的结果
        if (friendsList != null && !friendsList.isEmpty()) {
            return CommonResult.success(friendsList);
        } else {
            return CommonResult.error("没有找到好友");
        }
    }

}
