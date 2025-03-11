package com.mockmate.room_ws_service.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    // Map to store interviewId to sessionId
    private Map<String, String> interviewIdSessionMap = new ConcurrentHashMap<>();

    // Map to store sessionId to roomId
    private Map<String, String> sessionRoomMap = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        logger.info("Received a new WebSocket connection: " + event.getMessage());
    }

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headers.getSessionId();
        String destination = headers.getDestination();

        logger.info("Session " + sessionId + " subscribed to " + destination);

        // Assuming the destination has roomId, e.g., /topic/room/{roomId}
        if (destination != null && destination.startsWith("/topic/room/")) {
            String roomId = destination.substring("/topic/room/".length());
            sessionRoomMap.put(sessionId, roomId);
            logger.info("Session " + sessionId + " associated with room " + roomId);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        logger.info("WebSocket connection closed: " + sessionId);

        // Remove from sessionRoomMap
        String roomId = sessionRoomMap.remove(sessionId);
        if (roomId != null) {
            logger.info("Session " + sessionId + " removed from room " + roomId);
        }

        // Remove from interviewIdSessionMap
        String interviewId = null;
        for (Map.Entry<String, String> entry : interviewIdSessionMap.entrySet()) {
            if (entry.getValue().equals(sessionId)) {
                interviewId = entry.getKey();
                break;
            }
        }

        if (interviewId != null) {
            interviewIdSessionMap.remove(interviewId);
            logger.info("Interview ID " + interviewId + " disconnected and removed.");
        }
    }

    // Method to associate interviewId with sessionId
    public void registerUser(Long interviewId, String sessionId) {
        interviewIdSessionMap.put(interviewId.toString(), sessionId);
        logger.info("Registered interviewId " + interviewId + " with sessionId " + sessionId);
    }

    // Method to get sessionId by interviewId
    public String getSessionIdByInterviewId(String interviewId) {
        return interviewIdSessionMap.get(interviewId);
    }

    // Method to get roomId by sessionId
    public String getRoomIdBySessionId(String sessionId) {
        return sessionRoomMap.get(sessionId);
    }
}
