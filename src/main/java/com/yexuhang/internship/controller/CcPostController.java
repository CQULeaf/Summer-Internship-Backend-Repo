package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcPost;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据话题ID获取所有帖子信息
     *
     * @param topicId 话题ID
     * @return 对应话题的所有帖子信息
     */
    @GetMapping("/getPostsByTopicId")
    public CommonResult<List<CcPost>> getPostsByTopicId(@RequestParam("topicId") Integer topicId) {
        return ccPostService.getPostsByTopicId(topicId);
    }


    /**
     * 发布帖子
     *
     * @param ccPost 帖子对象
     * @return 发布结果
     */
    @PostMapping("/publish")
    public CommonResult<?> publishPost(@RequestBody CcPost ccPost) {
        return ccPostService.publishPost(ccPost);
    }

    /**
     * 删除帖子
     *
     * @param postId 帖子ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{postId}")
    public CommonResult<?> deletePost(@PathVariable Long postId) {
        return ccPostService.deletePost(postId);
    }


    /**
     * 更新文章信息
     *
     * @param ccPost 文章对象
     * @return 更新结果
     */
    @PutMapping("/update")
    public CommonResult<?> updatePost(@RequestBody CcPost ccPost) {
        boolean result = ccPostService.updatePost(ccPost);
        if (result) {
            return CommonResult.success("文章更新成功");
        } else {
            return CommonResult.error("文章更新失败");
        }
    }
}
