package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcTopic;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 话题表 前端控制器
 * </p>
 *
 */
@RestController
@Slf4j
@RequestMapping("/corner")
@CrossOrigin(origins = "*")
public class CcTopicController {

    @Autowired
    private CcTopicService ccTopicService;

    /**
     * 根据话题ID获取话题的详细信息
     *
     * @param topicId 话题ID
     * @return 话题的详细信息
     */
    @GetMapping("/superWordNameConcern")
    public CommonResult<CcTopic> getSuperWordNameConcern(@RequestParam Long topicId) {
        log.info("Fetching topic details for topic ID: {}", topicId);
        try {
            CommonResult<?> result = ccTopicService.getTopicById(topicId);
            if (result.getCode() == 200) {
                log.info("Successfully fetched topic details for topic ID: {}", topicId);
            } else {
                log.warn("Failed to fetch topic details for topic ID: {}", topicId);
            }
            return (CommonResult<CcTopic>) result;
        } catch (Exception e) {
            log.error("Error fetching topic details for topic ID: {}", topicId, e);
            return CommonResult.error("获取话题详情异常");
        }
    }

    /**
     * 获取所有话题的列表
     *
     * @return 话题列表
     */
    @GetMapping("/topics")
    public CommonResult<List<CcTopic>> getAllTopics() {
        log.info("Fetching all topics");
        try {
            List<CcTopic> topics = ccTopicService.list();
            return CommonResult.success(topics);
        } catch (Exception e) {
            log.error("Error fetching all topics", e);
            return CommonResult.error("获取话题列表异常");
        }
    }

    /**
     * 创建新的话题
     *
     * @param ccTopic 话题对象
     * @return 创建结果
     */
    @PostMapping("/createTopic")
    public CommonResult<?> createTopic(@RequestBody CcTopic ccTopic) {
        log.info("Creating new topic with name: {}", ccTopic.getName());
        try {
            boolean result = ccTopicService.save(ccTopic);
            if (result) {
                log.info("Successfully created topic: {}", ccTopic.getName());
                return CommonResult.success("话题创建成功");
            } else {
                log.warn("Failed to create topic: {}", ccTopic.getName());
                return CommonResult.error("话题创建失败");
            }
        } catch (Exception e) {
            log.error("Error creating topic", e);
            return CommonResult.error("创建话题异常");
        }
    }

    /**
     * 更新话题信息
     *
     * @param ccTopic 话题对象
     * @return 更新结果
     */
    @PutMapping("/updateTopic")
    public CommonResult<?> updateTopic(@RequestBody CcTopic ccTopic) {
        log.info("Updating topic with ID: {}", ccTopic.getTopicId());
        try {
            boolean result = ccTopicService.updateById(ccTopic);
            if (result) {
                log.info("Successfully updated topic ID: {}", ccTopic.getTopicId());
                return CommonResult.success("话题更新成功");
            } else {
                log.warn("Failed to update topic ID: {}", ccTopic.getTopicId());
                return CommonResult.error("话题更新失败");
            }
        } catch (Exception e) {
            log.error("Error updating topic", e);
            return CommonResult.error("更新话题异常");
        }
    }

    /**
     * 删除话题
     *
     * @param topicId 话题ID
     * @return 删除结果
     */
    @DeleteMapping("/deleteTopic/{topicId}")
    public CommonResult<?> deleteTopic(@PathVariable Long topicId) {
        log.info("Deleting topic with ID: {}", topicId);
        try {
            boolean result = ccTopicService.removeById(topicId);
            if (result) {
                log.info("Successfully deleted topic ID: {}", topicId);
                return CommonResult.success("话题删除成功");
            } else {
                log.warn("Failed to delete topic ID: {}", topicId);
                return CommonResult.error("话题删除失败");
            }
        } catch (Exception e) {
            log.error("Error deleting topic", e);
            return CommonResult.error("删除话题异常");
        }
    }
}
