package com.mockmate.room_ws_service.controller;

import com.mockmate.room_ws_service.dto.ChangeInterviewRoleResponseDTO;
import com.mockmate.room_ws_service.dto.ResponseDto;
import com.mockmate.room_ws_service.dto.SwapRoleRequestDTO;
import com.mockmate.room_ws_service.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/room")
@CrossOrigin(origins = "*")
public class RoomWebSocketController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;




    //INFO: Some Context: interviewID1, interviewID2
    /**
     * Endpoint to swap roles of a user in a room.
     *
     * @param request The SwapRoleRequest containing roomId and interviewId.
     * @return The updated UpcomingInterview object.
     */
    @PostMapping("/swap-role")
    public ResponseEntity<ResponseDto<ChangeInterviewRoleResponseDTO>> swapRole(@RequestBody SwapRoleRequestDTO request) {
        var roomResponseDTO = roomService.changeInterviewRole(request.getRoomId(), request.getInterviewId());

        // Send the message to all users in the room
        String destination = "/topic/room/" + request.getRoomId();
        messagingTemplate.convertAndSend(destination, roomResponseDTO);


        return new ResponseEntity(new ResponseDto<ChangeInterviewRoleResponseDTO>(roomResponseDTO, HttpStatus.OK.toString()) , HttpStatus.OK );
    }
}