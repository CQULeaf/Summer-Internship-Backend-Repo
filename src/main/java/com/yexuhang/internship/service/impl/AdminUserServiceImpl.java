package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.bean.AdminUser;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.AdminUserMapper;
import com.yexuhang.internship.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public CommonResult<?> login(String username, String password) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);

        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);
        if (adminUser != null) {
            // 更新最后登录时间
            adminUser.setLastLogin(Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());
            adminUserMapper.updateById(adminUser);
            return CommonResult.success("登录成功");
        } else {
            return CommonResult.error("用户名或密码错误");
        }
    }

    @Override
    public CommonResult<?> logout(Long adminId) {
        return CommonResult.success("退出成功");
    }

    @Override
    public CommonResult<?> addAdmin(AdminUser adminUser) {
        int result = adminUserMapper.insert(adminUser);
        if (result > 0) {
            return CommonResult.success("管理员添加成功");
        } else {
            return CommonResult.error("管理员添加失败");
        }
    }

    @Override
    public CommonResult<?> deleteAdmin(Long adminId) {
        int result = adminUserMapper.deleteById(adminId);
        if (result > 0) {
            return CommonResult.success("管理员删除成功");
        } else {
            return CommonResult.error("管理员删除失败");
        }
    }

    @Override
    public CommonResult<?> updateAdmin(AdminUser adminUser) {
        int result = adminUserMapper.updateById(adminUser);
        if (result > 0) {
            return CommonResult.success("管理员更新成功");
        } else {
            return CommonResult.error("管理员更新失败");
        }
    }
}
