package com.mockmate.room_ws_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSwapMessageDTO {

    private String roomHash;
    private String interviewId;
    private String peerInterviewId;
}
