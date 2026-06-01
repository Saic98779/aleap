package com.metaverse.workflow.event.service;




import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.event.repository.AleapMediaCoverageRepository;
import com.metaverse.workflow.event.repository.EventDetailsRepository;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.AleapMediaCoverage;
import com.metaverse.workflow.model.EventDetails;
import com.metaverse.workflow.model.ProjectDetails;
import com.metaverse.workflow.project.repository.ProjectDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class EventDetailsService {

    private final EventDetailsRepository repository;
    private final ProjectDetailsRepository projectRepository;
    private final AleapMediaCoverageRepository aleapMediaCoverageRepository;

    public WorkflowResponse create(EventDetailsDTO request) throws DataException {
       ProjectDetails  projectDetails  = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new DataException(
                        "Project not found with id " + request.getProjectId(),
                        "NOT_FOUND",
                        404
                ));
        EventDetails entity = EventDetailsMapper.mapToEventDetails(request);
        entity.setProject(projectDetails);
        EventDetails saved = repository.save(entity);

        return WorkflowResponse.builder()
                .status(200)
                .message("Event Created Successfully")
                .data(EventDetailsMapper.mapToEventDetailsRes(saved))
                .build();
    }

    public WorkflowResponse update(Long id, EventDetailsDTO request) throws DataException {

        EventDetails existing = repository.findById(id)
                .orElseThrow(() -> new DataException(
                        "Event not found with id " + id,
                        "NOT_FOUND",
                        404
                ));
        EventDetailsMapper.mapToEntityForUpdate(request, existing);
        EventDetails saved = repository.save(existing);
        return WorkflowResponse.builder()
                .status(200)
                .message("Event Updated Successfully")
                .data(EventDetailsMapper.mapToEventDetailsRes(saved))
                .build();
    }

    public WorkflowResponse getById(Long id) throws DataException {

        EventDetails entity = repository.findById(id)
                .orElseThrow(() -> new DataException(
                        "Event not found with id " + id,
                        "NOT_FOUND",
                        404
                ));

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(EventDetailsMapper.mapToEventDetailsRes(entity))
                .build();
    }

    public WorkflowResponse getAll() {

        List<EventDetailsDTO> list = repository.findAll()
                .stream()
                .map(EventDetailsMapper::mapToEventDetailsRes)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }


    public WorkflowResponse getEventsByProject(Long projectId) {

        List<EventDetailsDTO> list = repository.findByProject_Id(projectId)
                .stream()
                .map(EventDetailsMapper::mapToEventDetailsRes)
                .collect(Collectors.toList());

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(list)
                .build();
    }

    public WorkflowResponse delete(Long id) throws DataException {

        if (!repository.existsById(id)) {
            throw new DataException(
                    "Event not found with id " + id,
                    "NOT_FOUND",
                    404
            );
        }

        repository.deleteById(id);

        return WorkflowResponse.builder()
                .status(200)
                .message("Event Deleted Successfully")
                .build();
    }


    public WorkflowResponse saveMediaCoverage(AleapMediaCoverageRequest request) throws DataException {

        AleapMediaCoverage coverage;

        if (request.getMediaCoverageId() != null) {

            AleapMediaCoverage mediaCoverage = aleapMediaCoverageRepository
                    .findById(request.getMediaCoverageId())
                    .orElseThrow(() -> new DataException(
                            "Media Coverage not found",
                            "MEDIA_COVERAGE_NOT_FOUND",
                            404));

            mediaCoverage.setMediaCoverageType(request.getMediaCoverageType());
            mediaCoverage.setMediaCoverageUrl(request.getMediaCoverageUrl());
            mediaCoverage.setDate(DateUtil.stringToDate(request.getDate(), "dd-MM-yyyy"));

            mediaCoverage.setImage1(request.getImage1());
            mediaCoverage.setImage2(request.getImage2());
            mediaCoverage.setImage3(request.getImage3());

            coverage = aleapMediaCoverageRepository.save(mediaCoverage);

        } else {

            EventDetails eventDetails = repository.findById(request.getEventId())
                    .orElseThrow(() -> new DataException(
                            "Event not found",
                            "EVENT_NOT_FOUND",
                            404));

            AleapMediaCoverage mediaCoverage = AleapMediaCoverage.builder()
                    .mediaCoverageType(request.getMediaCoverageType())
                    .mediaCoverageUrl(request.getMediaCoverageUrl())
                    .date(DateUtil.stringToDate(request.getDate(), "dd-MM-yyyy"))
                    .image1(request.getImage1())
                    .image2(request.getImage2())
                    .image3(request.getImage3())
                    .eventDetails(eventDetails)
                    .build();

            coverage = aleapMediaCoverageRepository.save(mediaCoverage);
        }

        return WorkflowResponse.builder()
                .status(200)
                .message("Success")
                .data(EventDetailsService.toResponse(coverage))
                .build();
    }
    public static AleapMediaCoverageResponse toResponse(AleapMediaCoverage entity) {

        if (entity == null) {
            return null;
        }

        return AleapMediaCoverageResponse.builder()
                .mediaCoverageId(entity.getMediaCoverageId())
                .eventId(entity.getEventDetails() != null
                        ? entity.getEventDetails().getEventId()
                        : null)
                .mediaCoverageType(entity.getMediaCoverageType())
                .image1(entity.getImage1())
                .image2(entity.getImage2())
                .image3(entity.getImage3())
                .mediaCoverageUrl(entity.getMediaCoverageUrl())
                .date(entity.getDate())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
