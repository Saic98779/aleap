package com.metaverse.workflow.executiondocumentation.service;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionDocumentationRequest {

    private Long programId;

    private Long eventId;

    private String programSchedulePath;

    private String participantListPath;

    private String participantAttendancePath;

    private String resourcePersonsPath;

    private String completionReportPath;

    private String completionReportPhotographPath;

    private String socialMediaLink1;

    private String socialMediaLink2;

    private String expenditureDocumentPath;

    private String userId;
}