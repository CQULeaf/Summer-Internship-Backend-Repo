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
@RequestMapping("/home")
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
}
