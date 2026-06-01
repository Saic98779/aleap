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
@Table(name="aleap_media_coverage")
public class AleapMediaCoverage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long mediaCoverageId;

    private String mediaCoverageType;

    private String image1;

    private String image2;

    private String image3;

    private String mediaCoverageUrl;

    private Date date;
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
