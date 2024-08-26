package com.yexuhang.internship.service;

import com.yexuhang.internship.bean.CcPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.config.CommonResult;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
public interface CcPostService extends IService<CcPost> {

    /**
     * 获取所有文章信息
     *
     * @return 所有文章信息
     */
    CommonResult<List<CcPost>> getAllPosts();

    /**
     * 根据用户ID获取用户发布的所有帖子信息
     *
     * @param userId 用户ID
     * @return 用户发布的所有帖子信息
     */
    CommonResult<List<CcPost>> getPostsByUserId(Long userId);

    /**
     * 根据帖子ID获取帖子信息
     *
     * @param postId 帖子ID
     * @return 帖子信息
     */
    CommonResult<CcPost> getPostById(Long postId);


    /**
     * 根据话题ID获取所有帖子信息
     *
     * @param topicId 话题ID
     * @return 所有帖子信息
     */
    CommonResult<List<CcPost>> getPostsByTopicId(Integer topicId);

    /**
     * 发布帖子
     *
     * @param ccPost 帖子对象
     * @return 操作结果
     */
    CommonResult<?> publishPost(CcPost ccPost);

    /**
     * 删除帖子
     *
     * @param postId 帖子ID
     * @return 操作结果
     */
    CommonResult<?> deletePost(Long postId);
}
