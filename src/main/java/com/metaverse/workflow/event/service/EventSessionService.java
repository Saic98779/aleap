package com.metaverse.workflow.event.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.event.repository.EventDetailsRepository;
import com.metaverse.workflow.event.repository.EventSessionRepository;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.EventDetails;
import com.metaverse.workflow.model.EventProgramSession;
import com.metaverse.workflow.model.Resource;
import com.metaverse.workflow.resouce.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventSessionService {

    private final EventSessionRepository sessionRepository;
    private final EventDetailsRepository eventDetailsRepository;
    private final ResourceRepository resourceRepository;

    public WorkflowResponse saveSession(EventSessionRequest request) throws DataException {

        EventDetails eventDetails = eventDetailsRepository.findById(request.getEventId())
                .orElseThrow(() -> new DataException(
                        "Event not found",
                        "EVENT_NOT_FOUND",
                        404));

        Resource resource = null;
        if (request.getResourceId() != null) {
            resource = resourceRepository.findById(request.getResourceId())
                    .orElseThrow(() -> new DataException(
                            "Resource not found",
                            "RESOURCE_NOT_FOUND",
                            404));
        }

        EventProgramSession session = EventProgramSession.builder()
                .sessionDate(request.getSessionDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .sessionTypeName(request.getSessionTypeName())
                .sessionTypeMethodology(request.getSessionTypeMethodology())
                .sessionDetails(request.getSessionDetails())
                .resource(resource)
                .sessionStreamingUrl(request.getSessionStreamingUrl())
                .image1(request.getImage1())
                .image2(request.getImage2())
                .image3(request.getImage3())
                .image4(request.getImage4())
                .image5(request.getImage5())
                .eventDetails(eventDetails)
                .build();

        sessionRepository.save(session);

        return WorkflowResponse.builder()
                .status(200)
                .message("Session saved successfully")
                .build();
    }

    public WorkflowResponse updateSession(Long sessionId, EventSessionRequest request) throws DataException {

        EventProgramSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new DataException(
                        "Session not found",
                        "SESSION_NOT_FOUND",
                        404));

        session.setSessionDate(request.getSessionDate());
        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());
        session.setSessionTypeName(request.getSessionTypeName());
        session.setSessionTypeMethodology(request.getSessionTypeMethodology());
        session.setSessionDetails(request.getSessionDetails());
        session.setSessionStreamingUrl(request.getSessionStreamingUrl());

        session.setImage1(request.getImage1());
        session.setImage2(request.getImage2());
        session.setImage3(request.getImage3());
        session.setImage4(request.getImage4());
        session.setImage5(request.getImage5());

        if (request.getResourceId() != null) {
            Resource resource = resourceRepository.findById(request.getResourceId())
                    .orElseThrow(() -> new DataException(
                            "Resource not found",
                            "RESOURCE_NOT_FOUND",
                            404));

            session.setResource(resource);
        }

        sessionRepository.save(session);

        return WorkflowResponse.builder()
                .status(200)
                .message("Session updated successfully")
                .build();
    }
    public WorkflowResponse deleteSession(Long sessionId) throws DataException {

        EventProgramSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new DataException(
                        "Session not found",
                        "SESSION_NOT_FOUND",
                        404));

        sessionRepository.delete(session);

        return WorkflowResponse.builder()
                .status(200)
                .message("Session deleted successfully")
                .build();
    }
    public WorkflowResponse getSessionsByEventId(Long eventId) {

        List<EventSessionResponse> sessions =
                sessionRepository.findByEventDetails_EventId(eventId)
                        .stream()
                        .map(EventSessionService::toResponse)
                        .toList();

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(sessions)
                .build();
    }

    public WorkflowResponse getSessionById(Long sessionId) throws DataException {

        EventProgramSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new DataException(
                        "Session not found",
                        "SESSION_NOT_FOUND",
                        404));

        EventSessionResponse response = EventSessionService.toResponse(session);
        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(response)
                .build();
    }
    public static EventSessionResponse toResponse(EventProgramSession entity) {

        return EventSessionResponse.builder()
                .programSessionId(entity.getProgramSessionId())
                .eventId(entity.getEventDetails() != null
                        ? entity.getEventDetails().getEventId()
                        : null)
                .sessionDate(entity.getSessionDate())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .sessionTypeName(entity.getSessionTypeName())
                .sessionTypeMethodology(entity.getSessionTypeMethodology())
                .sessionDetails(entity.getSessionDetails())
                .resourceId(entity.getResource() != null
                        ? entity.getResource().getResourceId()
                        : null)
                .resourceName(entity.getResource() != null
                        ? entity.getResource().getName()
                        : null)
                .sessionStreamingUrl(entity.getSessionStreamingUrl())
                .image1(entity.getImage1())
                .image2(entity.getImage2())
                .image3(entity.getImage3())
                .image4(entity.getImage4())
                .image5(entity.getImage5())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
