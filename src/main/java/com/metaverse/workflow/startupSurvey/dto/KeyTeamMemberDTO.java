package com.metaverse.workflow.startupSurvey.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyTeamMemberDTO {

    private Long id;

    private Long startupSurveyId;

    private String name;

    private String designation;

    private String keyTasks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}
