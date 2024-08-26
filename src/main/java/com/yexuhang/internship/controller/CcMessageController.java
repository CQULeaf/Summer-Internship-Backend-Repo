package com.yexuhang.internship.controller;

import com.yexuhang.internship.bean.CcMessage;
import com.yexuhang.internship.config.CommonResult;
import com.yexuhang.internship.service.CcMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.sql.Timestamp;
import java.util.Map;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author Xuhang Ye
 * @since 2024-08-24
 */
@Slf4j
@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "*")
public class CcMessageController {
    @Autowired
    private CcMessageService ccMessageService;

    @PostMapping("/send")
    public CommonResult<?> sendMessage(@RequestBody Map<String, Object> request) {
        int senderId = (Integer) request.get("senderId");
        int receiverId = (Integer) request.get("receiverId");
        String content = (String) request.get("content");

        log.info("Sending message from senderId: {} to receiverId: {}", senderId, receiverId);
        CommonResult<?> result = ccMessageService.sendMessage(senderId, receiverId, content);

        if (result.getCode() == 200) {
            log.info("Successfully sent message from senderId: {} to receiverId: {}", senderId, receiverId);
        } else {
            log.warn("Failed to send message from senderId: {} to receiverId: {}", senderId, receiverId);
        }

        return result;
    }

    @GetMapping("/history")
    public CommonResult<?> getChatHistory(@RequestParam int userId) {
        log.info("Fetching chat history for userId: {}", userId);
        CommonResult<?> result = ccMessageService.getChatHistory(userId);

        if (result.getCode() == 200) {
            log.info("Successfully fetched chat history for userId: {}", userId);
        } else {
            log.warn("Failed to fetch chat history for userId: {}", userId);
        }

        return result;
    }
}