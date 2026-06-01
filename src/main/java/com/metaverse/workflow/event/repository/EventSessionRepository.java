package com.metaverse.workflow.event.repository;

import com.metaverse.workflow.model.EventProgramSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventSessionRepository extends JpaRepository<EventProgramSession, Long> {

    List<EventProgramSession> findByEventDetails_EventId(Long eventId);
}
