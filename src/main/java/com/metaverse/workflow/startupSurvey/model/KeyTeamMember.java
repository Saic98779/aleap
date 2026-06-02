package com.metaverse.workflow.startupSurvey.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "key_team_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyTeamMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "startup_survey_id", nullable = false)
    private StartupSurvey startupSurvey;

    private String name;

    private String designation;

    @Column(columnDefinition = "TEXT")
    private String keyTasks;
}

