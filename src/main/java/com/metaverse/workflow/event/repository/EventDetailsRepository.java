package com.metaverse.workflow.event.repository;

import com.metaverse.workflow.model.EventAttendance;
import com.metaverse.workflow.model.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
    List<EventDetails> findByProject_Id(Long projectId);
}