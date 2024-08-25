package com.yexuhang.internship.service;

import com.yexuhang.internship.bean.CcMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexuhang.internship.config.CommonResult;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
public interface CcMessageService extends IService<CcMessage> {

    CommonResult<?> sendMessage(int senderId, int receiverId, String content);

    CommonResult<?> getChatHistory(int userId);
}
