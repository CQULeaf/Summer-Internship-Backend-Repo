package com.yexuhang.internship.controller;

import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcFollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户关注 前端控制器
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-23
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class CcFollowController {

    @Autowired
    private CcFollowService ccFollowService;

    /**
     * 获取用户关注列表
     *
     * @param userId          用户ID
     * @param followableType  关注类型
     * @return 用户关注列表的封装结果
     */
    @GetMapping("/following")
    public CommonResult<?> getUserFollows(@RequestParam Long userId,
                                          @RequestParam String followableType) {
        try {
            log.info("Fetching follows for userId: {} with followableType: {}", userId, followableType);
            CommonResult<?> result = ccFollowService.getUserFollows(userId, followableType);

            if (result.getCode() == 200) {
                log.info("Successfully fetched follows for userId: {}", userId);
            } else {
                log.warn("No follows found for userId: {} with followableType: {}", userId, followableType);
            }

            return result;
        } catch (Exception e) {
            log.error("Error fetching follows for userId: {}", userId, e);
            return CommonResult.error("获取关注列表时发生异常");
        }
    }

    /**
     * 获取关注当前用户的用户列表
     *
     * @param userId 当前用户的ID
     * @return 关注当前用户的用户列表的封装结果
     */
    @GetMapping("/followers")
    public CommonResult<?> getFollowers(@RequestParam Long userId) {
        try {
            log.info("Fetching followers for userId: {}", userId);
            CommonResult<?> result = ccFollowService.getFollowers(userId);

            if (result.getCode() == 200) {
                log.info("Successfully fetched followers for userId: {}", userId);
            } else {
                log.warn("No followers found for userId: {}", userId);
            }

            return result;
        } catch (Exception e) {
            log.error("Error fetching followers for userId: {}", userId, e);
            return CommonResult.error("获取关注者列表时发生异常");
        }
    }

    /**
     * 获取好友列表
     *
     * @param userId 当前用户的ID
     * @return 好友列表的封装结果
     */
    @GetMapping("/friends")
    public CommonResult<?> getFriends(@RequestParam Long userId) {
        try {
            log.info("Fetching friends for userId: {}", userId);
            CommonResult<?> result = ccFollowService.getFriends(userId);

            if (result.getCode() == 200) {
                log.info("Successfully fetched friends for userId: {}", userId);
            } else {
                log.warn("No friends found for userId: {}", userId);
            }

            return result;
        } catch (Exception e) {
            log.error("Error fetching friends for userId: {}", userId, e);
            return CommonResult.error("获取好友列表时发生异常");
        }
    }

    /**
     * 获取用户关注的超话及其详细信息
     *
     * @param userId 用户ID
     * @return 用户关注的超话列表或错误信息
     */
    @GetMapping("/topicConcern")
    public CommonResult<?> getSuperWordNameConcern(@RequestParam Integer userId) {
        try {
            log.info("Fetching followed topics for userId: {}", userId);
            return ccFollowService.getUserFollowedTopics(userId);
        } catch (Exception e) {
            log.error("Error fetching followed topics for userId: {}", userId, e);
            return CommonResult.error("获取关注超话时发生异常");
        }
    }


    /**
     * 根据用户ID获取用户关注的所有帖子ID
     *
     * @param userId 用户ID
     * @return 用户关注的所有帖子ID列表
     */
    @GetMapping("/getFollowedPosts")
    public CommonResult<List<Long>> getFollowedPosts(@RequestParam("userId") Long userId) {
        return ccFollowService.getFollowedPostsByUserId(userId);
    }



    /**
     * 根据帖子ID获取所有关注该帖子的用户ID
     *
     * @param postId 帖子ID
     * @return 关注该帖子的所有用户ID列表
     */
    @GetMapping("/getUsersFollowingPost")
    public CommonResult<List<Long>> getUsersFollowingPost(@RequestParam("postId") Long postId) {
        return ccFollowService.getUsersFollowingPost(postId);
    }


    /**
     * 关注或取消关注接口
     *
     * @param userId 用户ID
     * @param followableId 被关注对象ID
     * @param followableType 关注对象类型（user、question、post、topic）
     * @param isFollow 是否关注（true：关注，false：取消关注）
     * @return 操作结果
     */
    @PostMapping("/followOrUnfollow")
    public CommonResult<?> followOrUnfollow(@RequestParam("userId") Integer userId,
                                            @RequestParam("followableId") Integer followableId,
                                            @RequestParam("followableType") String followableType,
                                            @RequestParam("isFollow") boolean isFollow) {
        return ccFollowService.followOrUnfollow(userId, followableId, followableType, isFollow);
    }
}