package com.mockmate.room_ws_service.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockmate.room_ws_service.dto.ApiResponse;
import com.mockmate.room_ws_service.dto.ChangeRoleRequest;
import com.mockmate.room_ws_service.dto.ChangeInterviewRoleResponseDTO;
import com.mockmate.room_ws_service.dto.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public RoomServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ChangeInterviewRoleResponseDTO changeInterviewRole(String roomHash, Long interviewID) {
        logger.info("Starting changeInterviewRole with roomHash: {} and interviewID: {}", roomHash, interviewID);

        ChangeRoleRequest payload = new ChangeRoleRequest();
        payload.setRoomHash(roomHash);
        payload.setInterviewID(interviewID);

        logger.debug("Payload created: {}", payload);

        // Make the POST request
        ChangeInterviewRoleResponseDTO response = null;
        try {
            ClientResponse clientResponse = webClient.post()
                    .uri("/room/changeRole")
                    .bodyValue(payload)
                    .exchange()
                    .block();

            if (clientResponse != null) {
                logger.info("Response status: {}", clientResponse.statusCode());

                if (clientResponse.statusCode().is2xxSuccessful()) {
                    String responseBody = clientResponse.bodyToMono(String.class).block(); // Get raw JSON string
                    logger.debug("Response body as raw JSON: {}", responseBody);

                    // Manually convert JSON to DTO using Jackson (or another library)
                    ObjectMapper objectMapper = new ObjectMapper();
                    var responseTemp = objectMapper.readValue(responseBody,
                            ApiResponse.class);

//                    var responseTemp = objectMapper.readValue(responseBody, ResponseDto.class);
                     response =  responseTemp.getPayload();
                    logger.info("Successfully parsed ChangeInterviewRoleResponseDTO: {}", response);
                } else {
                    logger.error("Non-successful response code: {}", clientResponse.statusCode());
                    throw new RuntimeException("Non-successful status code received: " + clientResponse.statusCode());
                }
            } else {
                logger.error("Client response is null. Something went wrong with the request.");
            }
        } catch (Exception e) {
            logger.error("Error occurred while making the POST request", e);
            throw new RuntimeException("Failed to change interview role", e);
        }

        return response;
    }
}
