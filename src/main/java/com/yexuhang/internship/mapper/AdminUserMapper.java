package com.yexuhang.internship.mapper;

import com.yexuhang.internship.bean.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台管理员用户表 Mapper 接口
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-26
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

}
