package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcUserMapper;
import com.yexuhang.internship.service.CcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
        queryWrapper.eq("follower_id", userId).or().eq("followee_id", userId);

        // 查询好友列表
        List<CcUser> friendsList = ccUserMapper.selectList(queryWrapper);

        // 返回封装的结果
        if (friendsList != null && !friendsList.isEmpty()) {
            return CommonResult.success(friendsList);
        } else {
            return CommonResult.error("没有找到好友");
        }
    }


    @Override
    public CommonResult<?> passwordChange(Long userId, String currentPassword, String newPassword1, String newPassword2) {
        // 检查新密码是否为空
        if (!StringUtils.hasText(newPassword1) || !StringUtils.hasText(newPassword2)) {
            return CommonResult.error("新密码不能为空");
        }

        // 验证新密码是否一致
        if (!newPassword1.equals(newPassword2)) {
            return CommonResult.error("新密码输入不一致");
        }

        // 获取用户信息
        CcUser user = this.getById(userId);
        if (user == null) {
            return CommonResult.error("用户不存在");
        }

        // 验证当前密码是否正确
        if (!user.getPassword().equals(currentPassword)) {
            return CommonResult.error("当前密码不正确");
        }

        // 更新密码
        user.setPassword(newPassword1);
        user.setUpdateTime((int) (System.currentTimeMillis() / 1000)); // 更新用户的更新时间

        boolean updateResult = this.updateById(user);
        if (updateResult) {
            return CommonResult.success("密码修改成功");
        } else {
            return CommonResult.error("密码修改失败");
        }
    }


}
