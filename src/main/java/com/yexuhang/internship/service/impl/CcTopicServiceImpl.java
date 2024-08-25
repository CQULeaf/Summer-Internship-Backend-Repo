package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcTopic;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcTopicMapper;
import com.yexuhang.internship.service.CcTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
