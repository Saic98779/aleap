package com.metaverse.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class EventAttendanceId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "aleap_participant_id", nullable = false)
    private Long aleapParticipantId;
}
