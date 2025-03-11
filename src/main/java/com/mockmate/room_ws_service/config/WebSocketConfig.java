package com.mockmate.room_ws_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enables a simple in-memory broker with destinations prefixed with /topic and /queue
        config.enableSimpleBroker("/topic", "/queue");
        // Sets the prefix for messages bound for @MessageMapping methods
        config.setApplicationDestinationPrefixes("/app");
        // Prefix for user-specific messages
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registers the /ws endpoint for WebSocket connections
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000", "http://localhost:9090", "https://mockmate-frontend.vercel.app", "https://room-ws-service-latest.onrender.com", "https://auth-service-latest-ttm5.onrender.com") // Adjust for security in production
                .withSockJS(); // Fallback options for browsers that don’t support WebSocket
    }

}