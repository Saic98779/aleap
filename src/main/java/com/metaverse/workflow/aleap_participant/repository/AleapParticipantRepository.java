package com.metaverse.workflow.aleap_participant.repository;

import com.metaverse.workflow.model.AleapParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface AleapParticipantRepository extends JpaRepository<AleapParticipant, Long> {
    Optional<AleapParticipant> findByContactNo(Long contactNo);

    Optional<AleapParticipant> findByAadharNo(String aadharNo);

    List<AleapParticipant> findByEvents_EventId(Long eventId);
    Page<AleapParticipant> findByEvents_EventId(Long eventId, Pageable pageable);
}
