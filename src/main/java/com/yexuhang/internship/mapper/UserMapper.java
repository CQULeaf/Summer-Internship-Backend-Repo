package com.yexuhang.internship.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.yexuhang.internship.bean.User;

/**
 * @author Xuhang Ye
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
