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
}
