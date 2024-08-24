package com.yexuhang.internship.service;

import com.yexuhang.internship.bean.CcFollow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.config.CommonResult;

/**
 * <p>
 * 文章关注关系表 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
public interface CcFollowService extends IService<CcFollow> {
    CommonResult<?> getUserFollows(Long userId, String followableType);

    /**
     * 查询关注当前用户的用户列表
     * @param userId 当前用户ID
     * @return CommonResult<?> 包含关注当前用户的用户列表或错误信息
     */
    CommonResult<?> getFollowers(Long userId);

    /**
     * 查询好友列表
     * @param userId 当前用户ID
     * @return CommonResult<?> 包含好友列表或错误信息
     */
    CommonResult<?> getFriends(Long userId);

    /**
     * 获取用户关注的超话及其详细信息
     *
     * @param userId 用户ID
     * @return 用户关注的超话列表或错误信息
     */
    CommonResult<?> getUserFollowedTopics(Integer userId);
}
