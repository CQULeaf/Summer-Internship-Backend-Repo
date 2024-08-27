package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcComment;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CcCommentController {

    @Autowired
    private CcCommentService ccCommentService;

    /**
     * 根据帖子ID获取所有评论信息
     *
     * @param postId 帖子ID
     * @return 对应帖子的所有评论信息
     */
    @GetMapping("/getReply")
    public CommonResult<List<CcComment>> getReply(@RequestParam("postId") Integer postId) {
        return ccCommentService.getCommentsByPostId(postId);
    }

    /**
     * 根据评论ID获取用户ID
     *
     * @param commentId 评论ID
     * @return 用户ID
     */
    @GetMapping("/getUserIdByCommentId")
    public CommonResult<Integer> getUserIdByCommentId(@RequestParam("commentId") Integer commentId) {
        return ccCommentService.getUserIdByCommentId(commentId);
    }

    /**
     * 添加评论
     *
     * @param comment 评论内容
     * @return 操作结果
     */
    @PostMapping("/add")
    public CommonResult<?> addComment(@RequestBody CcComment comment) {
        return ccCommentService.addComment(comment);
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 操作结果
     */
    @DeleteMapping("/delete/{commentId}")
    public CommonResult<?> deleteComment(@PathVariable Integer commentId) {
        return ccCommentService.deleteCommentById(commentId);
    }
}
