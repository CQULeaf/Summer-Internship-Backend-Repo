package com.yexuhang.internship.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yexuhang.internship.bean.CcFollow;
import com.yexuhang.internship.bean.CcMessage;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.mapper.CcFollowMapper;
import com.yexuhang.internship.mapper.CcMessageMapper;
import com.yexuhang.internship.mapper.CcUserMapper;
import com.yexuhang.internship.service.CcMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
@Service
public class CcMessageServiceImpl extends ServiceImpl<CcMessageMapper, CcMessage> implements CcMessageService {
    @Autowired
    private CcUserMapper ccUserMapper;

    @Autowired
    private CcMessageMapper ccMessageMapper;

    @Autowired
    private CcFollowMapper ccFollowMapper;

    @Override
    public CommonResult<?> sendMessage(int senderId, int receiverId, String content) {
        // 检查是否为好友关系
        QueryWrapper<CcFollow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", senderId)
                .eq("followable_id", receiverId)
                .eq("followable_type", "user");
        CcFollow follow = ccFollowMapper.selectOne(queryWrapper);

        if (follow == null) {
            return CommonResult.error("你们还不是好友关系");
        }

        // 创建消息对象并保存到数据库
        CcMessage message = new CcMessage();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(content);
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        int result = ccMessageMapper.insert(message);

        if (result > 0) {
            return CommonResult.success("消息发送成功");
        } else {
            return CommonResult.error("消息发送失败");
        }
    }

    // 获取用户的所有聊天记录
    public CommonResult<?> getChatHistory(int userId) {
        // 获取所有与用户相关的聊天记录
        QueryWrapper<CcMessage> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper.eq("sender_id", userId)
                .or()
                .eq("receiver_id", userId)
                .orderByAsc("created_at");

        List<CcMessage> messages = ccMessageMapper.selectList(messageQueryWrapper);

        return CommonResult.success(messages);
    }

}
