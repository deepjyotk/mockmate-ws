package com.mockmate.room_ws_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwapRoleRequestDTO {
    private String roomId;
    private Long interviewId;
}