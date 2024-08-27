package com.yexuhang.internship.service;

import com.yexuhang.internship.bean.CcComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.config.CommonResult;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
public interface CcCommentService extends IService<CcComment> {

    /**
     * 根据帖子ID获取所有评论信息
     *
     * @param postId 帖子ID
     * @return 所有评论信息
     */
    CommonResult<List<CcComment>> getCommentsByPostId(Integer postId);

    /**
     * 根据评论ID获取用户ID
     *
     * @param commentId 评论ID
     * @return 用户ID
     */
    CommonResult<Integer> getUserIdByCommentId(Integer commentId);

    CommonResult<?> addComment(CcComment comment);

    CommonResult<?> deleteCommentById(Integer commentId);

    /**
     * 统计总的评论数
     *
     * @return 总评论数
     */
    int countTotalComments();
}
