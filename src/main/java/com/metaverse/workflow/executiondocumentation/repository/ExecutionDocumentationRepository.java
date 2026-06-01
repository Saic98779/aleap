package com.metaverse.workflow.executiondocumentation.repository;

import com.metaverse.workflow.model.ExecutionDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExecutionDocumentationRepository
        extends JpaRepository<ExecutionDocumentation, Long> {

    Optional<ExecutionDocumentation> findByProgramId(Long programId);

    Optional<ExecutionDocumentation> findByEventId(Long eventId);

    List<ExecutionDocumentation> findByCreatedBy_UserId(String userId);
}