package com.mockmate.room_ws_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoleRequest {
    private String roomHash;
    private Long interviewID;
}
