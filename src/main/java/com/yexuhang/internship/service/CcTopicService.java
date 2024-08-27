package com.yexuhang.internship.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.bean.CcTopic;
import com.yexuhang.internship.config.CommonResult;

import java.util.List;
import java.util.Map;

public interface CcTopicService extends IService<CcTopic> {

    /**
     * 根据话题ID获取话题的详细信息
     *
     * @param topicId 话题ID
     * @return 话题的详细信息
     */
    CommonResult<CcTopic> getTopicById(Long topicId);

    /**
     * 获取所有话题的列表
     *
     * @return 所有话题的列表
     */
    CommonResult<List<CcTopic>> getAllTopics();

    /**
     * 创建新的话题
     *
     * @param ccTopic 话题对象
     * @return 创建结果
     */
    CommonResult<?> createTopic(CcTopic ccTopic);

    /**
     * 更新话题信息
     *
     * @param ccTopic 话题对象
     * @return 更新结果
     */
    CommonResult<?> updateTopic(CcTopic ccTopic);

    /**
     * 删除话题
     *
     * @param topicId 话题ID
     * @return 删除结果
     */
    CommonResult<?> deleteTopic(Long topicId);

    /**
     * 根据flag标签获取所有话题信息
     *
     * @param flag 标签
     * @return 所有话题信息
     */
    CommonResult<List<CcTopic>> getTopicsByFlag(String flag);

    /**
     * 根据flag标签和用户ID获取用户关注的所有话题信息
     *
     * @param flag 标签
     * @param userId 用户ID
     * @return 用户关注的所有话题信息
     */
    CommonResult<List<CcTopic>> getTopicsByFlagAndUser(String flag, Long userId);

    /**
     * 获取各个flag下的topic数和帖子数
     *
     * @return 各个flag下的topic数和帖子数
     */
    CommonResult<List<Map<String, Object>>> getTopicAndPostCountByFlag();
}
