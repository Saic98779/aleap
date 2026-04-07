package com.metaverse.workflow.model;
import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "startup_founders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FounderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private Integer age;
    private String qualification;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "startup_id")
    private StartupNeedsAssessment startup;
}
