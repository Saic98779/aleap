package com.metaverse.workflow.model;

import com.metaverse.workflow.common.enums.EventType;
import com.metaverse.workflow.common.enums.ImplementingAgency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "event_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String eventTitle;
    @Column(length = 2000)
    private String description;
    private String projectName;
    private String fundingAgency;
    private String ministry;
    @Enumerated(EnumType.STRING)
    private ImplementingAgency implementingAgency;
    private String programCoordinatorName;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalDays;
    private String startTime;
    private String endTime;
    private String district;
    private String mandal;
    private String village;
    private String pinCode;
    private Integer totalParticipants;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
}
