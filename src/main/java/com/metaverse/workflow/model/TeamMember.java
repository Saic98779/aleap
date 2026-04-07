package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "startup_team_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String designation;

    @Column(length = 1000)
    private String keyTasks;

    @ManyToOne
    @JoinColumn(name = "startup_id")
    private StartupNeedsAssessment startup;
}