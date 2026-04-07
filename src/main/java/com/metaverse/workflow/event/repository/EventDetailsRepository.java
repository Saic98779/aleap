package com.metaverse.workflow.event.repository;

import com.metaverse.workflow.model.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> {
}