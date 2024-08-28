package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.bean.CcUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcUserMapper;
import com.yexuhang.internship.service.CcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    // 个人信息修改
    @Override
    public CommonResult<?> updateInfo(CcUser ccUser) {
        int result = ccUserMapper.updateById(ccUser);
        if (result > 0) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.error("修改失败, 请稍后再试");
        }
    }

    // 修改密码
    @Override
    public CommonResult<?> updatePassword(String username, String oldPassword, String newPassword1, String newPassword2) {
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", oldPassword);
        CcUser existingUser = ccUserMapper.selectOne(queryWrapper);

        if (existingUser == null) {
            return CommonResult.error("原密码错误");
        }

        // 检查两次新密码是否一致
        if (!newPassword1.equals(newPassword2)) {
            return CommonResult.error("两次新密码输入不一致, 请重新输入");
        }

        existingUser.setPassword(newPassword1);
        int result = ccUserMapper.updateById(existingUser);
        if (result > 0) {
            return CommonResult.success("修改密码成功");
        } else {
            return CommonResult.error("修改密码失败, 请稍后再试");
        }
    }

    // 更新用户头像
    @Override
    public CommonResult<?> updateAvatar(String username, String avatar) {
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        CcUser existingUser = ccUserMapper.selectOne(queryWrapper);

        if (existingUser == null) {
            return CommonResult.error("用户不存在");
        }

        existingUser.setAvatar(avatar);
        int result = ccUserMapper.updateById(existingUser);
        if (result > 0) {
            return CommonResult.success("头像更新成功");
        } else {
            return CommonResult.error("头像更新失败, 请稍后再试");
        }
    }

    @Override
    public CommonResult<CcUser> getUserById(Long userId) {
        // 使用 MyBatis-Plus 提供的 selectById 方法查询用户信息
        CcUser user = ccUserMapper.selectById(userId);
        if (user != null) {
            return CommonResult.success(user);
        } else {
            return CommonResult.error("用户不存在");
        }
    }

    @Override
    public CommonResult<CcUser> getUserByNickname(String nickname) {
        // 使用 QueryWrapper 来构建查询条件
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", nickname);

        // 查询用户信息
        CcUser user = ccUserMapper.selectOne(queryWrapper);

        if (user != null) {
            return CommonResult.success(user);
        } else {
            return CommonResult.error("用户不存在");
        }
    }

    @Override
    public CommonResult<?> addUser(CcUser ccUser) {
        int result = ccUserMapper.insert(ccUser);
        if (result > 0) {
            return CommonResult.success("用户添加成功");
        } else {
            return CommonResult.error("用户添加失败");
        }
    }

    @Override
    public CommonResult<?> deleteUser(Long userId) {
        int result = ccUserMapper.deleteById(userId);
        if (result > 0) {
            return CommonResult.success("用户删除成功");
        } else {
            return CommonResult.error("用户删除失败");
        }
    }

    @Override
    public CommonResult<?> updateUser(CcUser ccUser) {
        int result = ccUserMapper.updateById(ccUser);
        if (result > 0) {
            return CommonResult.success("用户信息更新成功");
        } else {
            return CommonResult.error("用户信息更新失败");
        }
    }


    @Override
    public int getTotalUserCount() {
        // 使用 MyBatis-Plus 提供的 count 方法获取用户总数
        return (int) this.count();
    }


    @Override
    public CommonResult<List<Map<String, Object>>> getUserCountByHometown() {
        // 使用 MyBatis-Plus 的 QueryWrapper 进行分组查询
        QueryWrapper<CcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("hometown, COUNT(*) as total")
                .groupBy("hometown");

        List<Map<String, Object>> result = this.listMaps(queryWrapper);

        return CommonResult.success(result);
    }

    @Override
    public CommonResult<List<CcUser>> getAllUsers() {
        List<CcUser> users = this.list();
        return CommonResult.success(users);
    }
}
