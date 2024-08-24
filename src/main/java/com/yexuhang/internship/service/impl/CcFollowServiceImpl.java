package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcFollow;
import com.yexuhang.internship.bean.CcTopic;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcFollowMapper;
import com.yexuhang.internship.mapper.CcTopicMapper;
import com.yexuhang.internship.service.CcFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章关注关系表 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
@Service
public class CcFollowServiceImpl extends ServiceImpl<CcFollowMapper, CcFollow> implements CcFollowService {
    @Autowired
    private CcFollowMapper ccFollowMapper;

    @Autowired
    private CcTopicMapper ccTopicMapper;

    @Override
    public CommonResult<?> getUserFollows(Long userId, String followableType) {
        QueryWrapper<CcFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("followable_type", followableType);
        List<CcFollow> follows = ccFollowMapper.selectList(queryWrapper);
        if (follows != null && !follows.isEmpty()) {
            return CommonResult.success(follows);
        } else {
            return CommonResult.error("没有找到关注的内容");
        }
    }

    @Override
    public CommonResult<?> getFollowers(Long userId) {
        QueryWrapper<CcFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("followable_id", userId);
        List<CcFollow> followers = ccFollowMapper.selectList(queryWrapper);
        if (followers != null && !followers.isEmpty()) {
            return CommonResult.success(followers);
        } else {
            return CommonResult.error("没有找到关注你的用户");
        }
    }

    @Override
    public CommonResult<?> getFriends(Long userId) {
        // 将 userId 转换为 Integer 以匹配数据库中的 int unsigned 类型
        Integer userIdInt = userId.intValue();

        // 查询当前用户关注的所有用户的 ID 列表
        QueryWrapper<CcFollow> followingsQuery = new QueryWrapper<>();
        followingsQuery.eq("user_id", userIdInt)
                .eq("followable_type", "user");
        List<CcFollow> followings = ccFollowMapper.selectList(followingsQuery);

        // 提取当前用户关注的所有用户的 ID
        List<Integer> followingIds = followings.stream()
                .map(CcFollow::getFollowableId)
                .collect(Collectors.toList());

        if (followingIds.isEmpty()) {
            return CommonResult.error("没有找到好友");
        }

        // 查询这些用户中也关注了当前用户的用户
        QueryWrapper<CcFollow> followersQuery = new QueryWrapper<>();
        followersQuery.eq("followable_type", "user")
                .eq("followable_id", userIdInt)
                .in("user_id", followingIds);

        List<CcFollow> friends = ccFollowMapper.selectList(followersQuery);

        // 提取互相关注的用户 ID
        List<Long> friendIds = friends.stream()
                .map(follower -> follower.getUserId().longValue())
                .distinct()
                .collect(Collectors.toList());

        if (!friendIds.isEmpty()) {
            return CommonResult.success(friendIds);
        } else {
            return CommonResult.error("没有找到好友");
        }
    }


    @Override
    public CommonResult<?> getUserFollowedTopics(Integer userId) {
        // 查询用户关注的超话ID
        QueryWrapper<CcFollow> followQuery = new QueryWrapper<>();
        followQuery.eq("user_id", userId)
                .eq("followable_type", "topic");

        List<CcFollow> followList = ccFollowMapper.selectList(followQuery);

        if (followList.isEmpty()) {
            return CommonResult.error("没有找到用户关注的超话");
        }

        // 提取关注的超话ID
        List<Integer> topicIds = followList.stream()
                .map(CcFollow::getFollowableId)
                .collect(Collectors.toList());

        // 根据超话ID获取超话详细信息
        QueryWrapper<CcTopic> topicQuery = new QueryWrapper<>();
        topicQuery.in("topic_id", topicIds);

        List<CcTopic> topics = ccTopicMapper.selectList(topicQuery);

        if (!topics.isEmpty()) {
            return CommonResult.success(topics);
        } else {
            return CommonResult.error("没有找到超话详细信息");
        }
    }
}
