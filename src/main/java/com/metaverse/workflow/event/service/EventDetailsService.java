package com.metaverse.workflow.event.service;




import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.event.repository.EventDetailsRepository;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.EventDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventDetailsService {

    private final EventDetailsRepository repository;

    public WorkflowResponse create(EventDetailsDTO request) {

        EventDetails entity = EventDetailsMapper.mapToEventDetails(request);
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
}
