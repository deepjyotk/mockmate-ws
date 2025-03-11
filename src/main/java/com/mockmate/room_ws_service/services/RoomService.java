package com.mockmate.room_ws_service.services;

import com.mockmate.room_ws_service.dto.ChangeInterviewRoleResponseDTO;

public interface RoomService {

    ChangeInterviewRoleResponseDTO changeInterviewRole(String roomHash, Long interviewID) ;
}
