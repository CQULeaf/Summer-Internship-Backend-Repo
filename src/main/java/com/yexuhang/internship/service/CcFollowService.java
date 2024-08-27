package com.yexuhang.internship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.bean.CcFollow;
import com.yexuhang.internship.config.CommonResult;

import java.util.List;

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


    /**
     * 根据用户ID获取用户关注的所有帖子ID
     *
     * @param userId 用户ID
     * @return 用户关注的所有帖子ID列表
     */
    CommonResult<List<Long>> getFollowedPostsByUserId(Long userId);

    CommonResult<List<Long>> getUsersFollowingPost(Long postId);

    // 关注或取消关注
    CommonResult<?> followOrUnfollow(Integer userId, Integer followableId, String followableType, boolean isFollow);

    /**
     * 判断用户是否关注某个目标
     *
     * @param userId 用户ID
     * @param followableId 关注目标ID
     * @param followableType 关注目标类型
     * @return 如果关注返回 true，否则返回 false
     */
    boolean isFollowing(Long userId, Long followableId, String followableType);
}
