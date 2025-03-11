package com.mockmate.room_ws_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("payload")
    private ChangeInterviewRoleResponseDTO payload;

    @JsonProperty("timestamp")
    private String timestamp; // You can use LocalDateTime if you prefer

    @JsonProperty("status")
    private String status;
}
