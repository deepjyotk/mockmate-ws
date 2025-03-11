package com.mockmate.room_ws_service.controller;

import com.mockmate.room_ws_service.dto.UserRegistrationDTO;
import com.mockmate.room_ws_service.services.WebSocketEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class UserRegistrationController {

    @Autowired
    private WebSocketEventListener webSocketEventListener;

    /**
     * Endpoint for users to register their interviewId after connecting via WebSocket.
     *
     * @param registration The UserRegistration payload containing interviewId and roomId.
     * @param headerAccessor To access WebSocket session headers.
     */
    @MessageMapping("/register")
    public void registerUser(UserRegistrationDTO registration, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        Long interviewId = registration.getInterviewId();
        String roomId = registration.getRoomId();

        // Register the user in WebSocketEventListener
        webSocketEventListener.registerUser(interviewId, sessionId);

        // Optionally, send a message to the room that a new user has joined
        // This requires SimpMessagingTemplate, which can be autowired here if needed
    }
}
