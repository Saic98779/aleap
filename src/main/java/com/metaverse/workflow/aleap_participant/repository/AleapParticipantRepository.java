package com.metaverse.workflow.aleap_participant.repository;

import com.metaverse.workflow.model.AleapParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AleapParticipantRepository extends JpaRepository<AleapParticipant, Long> {
    Optional<AleapParticipant> findByContactNo(Long contactNo);
}
