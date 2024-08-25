package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcPost;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
