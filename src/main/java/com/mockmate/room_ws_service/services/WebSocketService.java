package com.mockmate.room_ws_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Send message to a specific topic
     *
     * @param destination The destination to send the message (e.g., /topic/chat)
     * @param message The message to send
     */
    @Scheduled(fixedDelay = 1000)
    public void sendMessageToTopic(String destination, String message) {
        messagingTemplate.convertAndSend(destination, message);
    }

    /**
     * Send message to a specific user
     *
     * @param username The username to send the message to
     * @param message The message to send
     */
    public void sendMessageToUser(String username, String message) {
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", message);
    }
}