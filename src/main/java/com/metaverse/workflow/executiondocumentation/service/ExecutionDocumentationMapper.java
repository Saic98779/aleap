package com.metaverse.workflow.executiondocumentation.service;

import com.metaverse.workflow.model.ExecutionDocumentation;
import com.metaverse.workflow.model.User;

public class ExecutionDocumentationMapper {

    public static ExecutionDocumentation toEntity(
            ExecutionDocumentationRequest request,
            User user) {

        return ExecutionDocumentation.builder()
                .programId(request.getProgramId())
                .eventId(request.getEventId())
                .programSchedulePath(request.getProgramSchedulePath())
                .participantListPath(request.getParticipantListPath())
                .participantAttendancePath(request.getParticipantAttendancePath())
                .resourcePersonsPath(request.getResourcePersonsPath())
                .completionReportPath(request.getCompletionReportPath())
                .completionReportPhotographPath(request.getCompletionReportPhotographPath())
                .socialMediaLink1(request.getSocialMediaLink1())
                .socialMediaLink2(request.getSocialMediaLink2())
                .expenditureDocumentPath(request.getExpenditureDocumentPath())
                .createdBy(user)
                .build();
    }

    public static void updateEntity(
            ExecutionDocumentation entity,
            ExecutionDocumentationRequest request) {

        entity.setProgramId(request.getProgramId());
        entity.setEventId(request.getEventId());
        entity.setProgramSchedulePath(request.getProgramSchedulePath());
        entity.setParticipantListPath(request.getParticipantListPath());
        entity.setParticipantAttendancePath(request.getParticipantAttendancePath());
        entity.setResourcePersonsPath(request.getResourcePersonsPath());
        entity.setCompletionReportPath(request.getCompletionReportPath());
        entity.setCompletionReportPhotographPath(request.getCompletionReportPhotographPath());
        entity.setSocialMediaLink1(request.getSocialMediaLink1());
        entity.setSocialMediaLink2(request.getSocialMediaLink2());
        entity.setExpenditureDocumentPath(request.getExpenditureDocumentPath());
    }
}