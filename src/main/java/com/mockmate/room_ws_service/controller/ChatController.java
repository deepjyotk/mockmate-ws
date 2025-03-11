package com.mockmate.room_ws_service.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatMessage {
        private String content;
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {

        // Return a ChatMessage so it can be automatically converted to JSON.
        return new ChatMessage("HELLO FROM SERVER");
    }
}