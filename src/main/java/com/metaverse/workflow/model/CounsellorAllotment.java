package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "counsellor_allotment")
public class CounsellorAllotment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counsellor_allotment_id")
    private Long counsellorAllotmentId;

    @ManyToOne
    @JoinColumn(name = "counsellor_registration_id")
    private CounsellorRegistration counsellorRegistration;

    @ManyToOne
    @JoinColumn(name = "mandal_id")
    private Mandal allotedMandal;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "date_of_allotment")
    private Date dateOfAllotment;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Date updatedOn;
}