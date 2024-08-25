package com.yexuhang.internship.service.impl;

import com.yexuhang.internship.bean.CcPost;
import com.yexuhang.internship.mapper.CcPostMapper;
import com.yexuhang.internship.service.CcPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexuhang.internship.config.CommonResult;
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
}
