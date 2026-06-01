package com.metaverse.workflow.eventattendance.repository;

import com.metaverse.workflow.model.EventAttendance;
import com.metaverse.workflow.model.EventAttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventAttendanceRepository extends JpaRepository<EventAttendance, EventAttendanceId> {

    @Query("SELECT e FROM EventAttendance e WHERE e.eventAttendanceId.eventId = :eventId")
    List<EventAttendance> findByEventAttendances(@Param("eventId") Long eventId);

}