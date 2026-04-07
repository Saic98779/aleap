package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.Data;

//@Entity
//@Table(name = "startup_scores")
@Data
public class StartupScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // Business, HR, Market etc.
    private Double score;
    private Double totalMarks;

    @ManyToOne
    @JoinColumn(name = "startup_id")
    private StartupNeedsAssessment startup;
}
