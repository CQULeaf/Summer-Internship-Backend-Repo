package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcMessage;
import com.yexuhang.internship.service.CcMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.sql.Timestamp;

/**
 * @author Xuhang Ye
 */
@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private CcMessageService ccMessageService;

    @MessageMapping("/chat")
    public void handlePrivateMessage(CcMessage message) {
        // 保存消息到数据库
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ccMessageService.save(message);

        // 发送消息给特定用户
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getReceiverId()), "/queue/messages", message);
    }
}
