package com.yexuhang.internship.service;

import com.yexuhang.internship.bean.CcTopic;
import com.yexuhang.internship.config.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
