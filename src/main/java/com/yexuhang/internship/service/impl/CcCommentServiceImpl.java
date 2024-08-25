package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcComment;
import com.yexuhang.internship.mapper.CcCommentMapper;
import com.yexuhang.internship.service.CcCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.config.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-25
 */
@Service
public class CcCommentServiceImpl extends ServiceImpl<CcCommentMapper, CcComment> implements CcCommentService {

    @Autowired
    private CcCommentMapper ccCommentMapper;

    @Override
    public CommonResult<List<CcComment>> getCommentsByPostId(Integer postId) {
        // 使用 QueryWrapper 查询所有对应 postId 的评论
        QueryWrapper<CcComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        List<CcComment> comments = ccCommentMapper.selectList(queryWrapper);
        return CommonResult.success(comments);
    }
}
