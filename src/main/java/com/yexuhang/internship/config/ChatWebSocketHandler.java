package com.yexuhang.internship.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yexuhang.internship.bean.CcMessage;
import com.yexuhang.internship.service.CcMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xuhang Ye
 * @time 12:13 AM
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private CcMessageService ccMessageService;

    // 存储用户的WebSocketSession
    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    @Override
    // 当用户连接时
    public void afterConnectionEstablished(WebSocketSession session) {
        // 获取用户ID
        String userId = getUserIdFromSession(session);
        // 将用户ID和WebSocketSession存入Map
        SESSIONS.put(userId, session);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    // 处理用户发送的消息
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取消息内容
        CcMessage ccMessage = objectMapper.readValue(message.getPayload(), CcMessage.class);

        // 调用服务层方法发送消息
        CommonResult<?> result = ccMessageService.sendMessage(
                ccMessage.getSenderId(),
                ccMessage.getReceiverId(),
                ccMessage.getContent()
        );

        // 向发送者返回结果
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(result)));

        if (result.getCode() == 200) {
            // 如果消息发送成功，尝试通过 WebSocket 发送给目标用户
            String targetUserId = String.valueOf(ccMessage.getReceiverId());
            WebSocketSession targetSession = SESSIONS.get(targetUserId);
            if (targetSession != null && targetSession.isOpen()) {
                targetSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(ccMessage)));
            } else {
                session.sendMessage(new TextMessage("{\"status\": \"User not online\"}"));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 当 WebSocket 连接关闭时触发，通常用于清理会话信息
        String userId = getUserIdFromSession(session);
        SESSIONS.remove(userId, session);
    }

    // 从session中获取用户ID
    private String getUserIdFromSession(WebSocketSession session) {
        return (String) session.getAttributes().get("userId");
    }
}
