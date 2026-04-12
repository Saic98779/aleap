package com.metaverse.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "esdp_training")
public class ESDPTraining {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "esdp_training_id")
    private Long ESDPTrainingApplicationId;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "participant_id", referencedColumnName = "participant_id")
    private Participant participantId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id")
    private Organization organizationId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agency_id", referencedColumnName = "agency_id")
    private Agency agency;

    @Column(name = "dateofawareness_program")
    private Date dateOfAwarenessProgram;

    @Column(name = "interested_inAttending_15days")
    private String interestedInAttending15Days;

    @Column(name = "dateOfApplicationReceived")
    private Date dateOfApplicationReceived;

    @Column(name = "selectedfor_training")
    private String selectedForTraining;

    @ElementCollection
    @CollectionTable(name = "interested_sectors_for_esdp",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "sector")
    private List<String> interestedSectorsForEsdp;

    @Column(name="created_on", insertable = true, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on", insertable = false, updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
}
