package com.mockmate.room_ws_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String statusCode;
    private String message;
    private T payload;
    private String timestamp;

    // Constructor with all fields
    public ResponseDto(T payload, String statusCode, String message ) {
        this.payload = payload;
        this.statusCode = statusCode ;
        this.message = message != null ? message : "success"; // Default to "success" if null
        this.timestamp = Instant.now().toString();
    }



    public ResponseDto(T payload, String statusCode ) {
        this(payload, statusCode, "success") ;
    }
}
