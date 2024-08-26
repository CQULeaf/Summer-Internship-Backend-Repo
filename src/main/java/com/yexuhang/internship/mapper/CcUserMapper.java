package com.yexuhang.internship.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcFollow;
import com.yexuhang.internship.bean.CcUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-23
 */
@Mapper
public interface CcUserMapper extends BaseMapper<CcUser> {

}
