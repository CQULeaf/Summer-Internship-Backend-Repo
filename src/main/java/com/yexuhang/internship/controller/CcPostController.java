package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcPost;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
@RestController
@RequestMapping("/ccPost")
public class CcPostController {

    @Autowired
    private CcPostService ccPostService;

    /**
     * 获取所有文章信息
     *
     * @return 所有文章信息
     */
    @GetMapping("/getAllPosts")
    public CommonResult<List<CcPost>> getAllPosts() {
        return ccPostService.getAllPosts();
    }

    /**
     * 根据用户ID获取用户发布的所有帖子信息
     *
     * @param userId 用户ID
     * @return 用户发布的所有帖子信息
     */
    @GetMapping("/mypost")
    public CommonResult<List<CcPost>> getMyPosts(@RequestParam("user_id") Long userId) {
        return ccPostService.getPostsByUserId(userId);
    }


    /**
     * 根据帖子ID获取帖子信息
     *
     * @param postId 帖子ID
     * @return 帖子信息
     */
    @GetMapping("/getPost")
    public CommonResult<CcPost> getPost(@RequestParam("postId") Long postId) {
        return ccPostService.getPostById(postId);
    }
}
