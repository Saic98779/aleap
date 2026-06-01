package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name="event_session")
public class EventProgramSession {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long programSessionId;

    private Date sessionDate;

    private String startTime;

    private String endTime;

    private String sessionTypeName;

    private String sessionTypeMethodology;

    private String sessionDetails;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private String sessionStreamingUrl;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String image5;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
    @ManyToOne
    @JoinColumn(name = "event_details_id")
    private EventDetails eventDetails;
}
