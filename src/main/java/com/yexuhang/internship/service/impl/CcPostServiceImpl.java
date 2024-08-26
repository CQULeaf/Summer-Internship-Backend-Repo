package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.bean.CcPost;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcPostMapper;
import com.yexuhang.internship.service.CcPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
@Service
public class CcPostServiceImpl extends ServiceImpl<CcPostMapper, CcPost> implements CcPostService {

    @Autowired
    private CcPostMapper ccPostMapper;

    @Override
    public CommonResult<List<CcPost>> getAllPosts() {
        // 查询所有文章信息
        List<CcPost> posts = ccPostMapper.selectList(null);
        return CommonResult.success(posts);
    }

    @Override
    public CommonResult<List<CcPost>> getPostsByUserId(Long userId) {
        // 使用 QueryWrapper 查询所有对应 userId 的帖子
        QueryWrapper<CcPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<CcPost> posts = ccPostMapper.selectList(queryWrapper);
        return CommonResult.success(posts);
    }

    @Override
    public CommonResult<CcPost> getPostById(Long postId) {
        // 使用 MyBatis-Plus 提供的 selectById 方法查询帖子信息
        CcPost post = ccPostMapper.selectById(postId);
        if (post != null) {
            return CommonResult.success(post);
        } else {
            return CommonResult.error("帖子不存在");
        }
    }

}
