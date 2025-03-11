package com.mockmate.room_ws_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomPayloadDTO {
    private RoomDetails roomDetails;
    private UserDetails userDetails;
    private PeerInfo peerInfo;

    @Data
    public static class RoomDetails {
        private String roomIDHash;
        private Long interviewID;
        private OffsetTime interviewStartTime ;
        private OffsetTime interviewEndTime ;
    }

    @Data
    public static class UserDetails {
        private Long userID;
        private String userName;
        private String fullName;
        private String interviewRole; // "Interviewer" or "Interviewee"
    }


    @Data
    public static class PeerInfo {
        private String peerRole; // "Interviewer" or "Interviewee"
        private QuestionResponseDto question;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionResponseDto {

        @JsonProperty("questionID")
        private Long questionId;

        @JsonProperty("questionS3Url")
        private String questionTextS3Url;

        @JsonProperty("questionTitle")
        private String questionTitle;

        @JsonProperty("interviewTypeId")
        private Long interviewTypeId;

        @JsonProperty("questionDifficultyID")
        private Long questionDifficultyId;

        @JsonProperty("tags")
        private List<String> tags;

        @JsonProperty("companies")
        private List<CompanyFrequencyDto> companies;
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public class CompanyFrequencyDto {
        @JsonProperty("companyID")
        private Long companyId;

        @JsonProperty("companyName")
        private String companyName;

        @JsonProperty("frequencyAsked")
        private Integer frequencyAsked;

        @JsonProperty("lastAskedDate")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime lastAskedDate; // Can be null; handle default date in service layer if needed
    }

}
