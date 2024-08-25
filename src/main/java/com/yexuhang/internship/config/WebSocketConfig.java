package com.yexuhang.internship.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Xuhang Ye
 * @time 12:06 AM
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(new ChatWebSocketHandler(), "/chat").setAllowedOrigins("*");
        }
}