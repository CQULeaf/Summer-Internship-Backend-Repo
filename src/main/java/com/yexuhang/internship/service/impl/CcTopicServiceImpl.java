package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.bean.CcFollow;
import com.yexuhang.internship.bean.CcTopic;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcFollowMapper;
import com.yexuhang.internship.mapper.CcTopicMapper;
import com.yexuhang.internship.service.CcTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 话题表 服务实现类
 * </p>
 *
 */
@Service
public class CcTopicServiceImpl extends ServiceImpl<CcTopicMapper, CcTopic> implements CcTopicService {

    @Autowired
    private CcTopicMapper ccTopicMapper;
    @Autowired
    private CcFollowMapper ccFollowMapper;

    @Override
    public CommonResult<CcTopic> getTopicById(Long topicId) {
        QueryWrapper<CcTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id", topicId);
        CcTopic topic = ccTopicMapper.selectOne(queryWrapper);
        if (topic != null) {
            return CommonResult.success(topic);
        } else {
            return CommonResult.error("话题不存在");
        }
    }

    @Override
    public CommonResult<List<CcTopic>> getAllTopics() {
        List<CcTopic> topics = ccTopicMapper.selectList(null);
        if (topics != null && !topics.isEmpty()) {
            return CommonResult.success(topics);
        } else {
            return CommonResult.error("没有找到任何话题");
        }
    }

    @Override
    public CommonResult<?> createTopic(CcTopic ccTopic) {
        int result = ccTopicMapper.insert(ccTopic);
        if (result > 0) {
            return CommonResult.success("话题创建成功");
        } else {
            return CommonResult.error("话题创建失败");
        }
    }

    @Override
    public CommonResult<?> updateTopic(CcTopic ccTopic) {
        int result = ccTopicMapper.updateById(ccTopic);
        if (result > 0) {
            return CommonResult.success("话题更新成功");
        } else {
            return CommonResult.error("话题更新失败");
        }
    }

    @Override
    public CommonResult<?> deleteTopic(Long topicId) {
        int result = ccTopicMapper.deleteById(topicId);
        if (result > 0) {
            return CommonResult.success("话题删除成功");
        } else {
            return CommonResult.error("话题删除失败");
        }
    }

    @Override
    public CommonResult<List<CcTopic>> getTopicsByFlag(String flag) {
        // 使用QueryWrapper构建查询条件
        QueryWrapper<CcTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("flag", flag);

        // 查询所有符合条件的话题
        List<CcTopic> topics = ccTopicMapper.selectList(queryWrapper);
        return CommonResult.success(topics);
    }


    @Override
    public CommonResult<List<CcTopic>> getTopicsByFlagAndUser(String flag, Long userId) {
        // 首先将userId转换为Integer类型
        Integer userIdInt = userId.intValue();

        // 查询当前用户关注的所有topic的ID列表
        QueryWrapper<CcFollow> followQueryWrapper = new QueryWrapper<>();
        followQueryWrapper.eq("user_id", userIdInt) // 使用Integer类型的userIdInt
                .eq("followable_type", "topic");
        List<Long> followedTopicIds = ccFollowMapper.selectList(followQueryWrapper)
                .stream()
                .map(follow -> follow.getFollowableId().longValue()) // 将ID转换为Long类型
                .collect(Collectors.toList());

        if (followedTopicIds.isEmpty()) {
            return CommonResult.error("用户没有关注任何话题");
        }

        // 根据话题ID列表和flag查询话题信息
        QueryWrapper<CcTopic> topicQueryWrapper = new QueryWrapper<>();
        topicQueryWrapper.in("topic_id", followedTopicIds)
                .eq("flag", flag);
        List<CcTopic> topics = ccTopicMapper.selectList(topicQueryWrapper);

        return CommonResult.success(topics);
    }


    @Override
    public CommonResult<List<Map<String, Object>>> getTopicAndPostCountByFlag() {
        // 使用 QueryWrapper 来分组查询各个flag下的topic数和帖子数
        QueryWrapper<CcTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("flag, COUNT(*) as topic_count, SUM(post_count) as total_post_count")
                .groupBy("flag");

        List<Map<String, Object>> result = this.listMaps(queryWrapper);

        return CommonResult.success(result);
    }
}
