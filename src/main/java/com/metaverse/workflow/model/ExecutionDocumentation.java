package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "execution_documentation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutionDocumentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long executionDocumentationId;

    private Long programId;

    private Long eventId;

    private String programSchedulePath;

    private String participantListPath;

    private String participantAttendancePath;

    private String resourcePersonsPath;

    private String completionReportPath;

    private String completionReportPhotographPath;

    private String socialMediaLink1;

    private String socialMediaLink2;

    private String expenditureDocumentPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}