package com.metaverse.workflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="sub_activity")
public class SubActivity {
    @Id
    @Column(name="sub_activity_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long subActivityId;
    @Column(name="sub_activity_name")
    private String subActivityName;
    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "activity_id")  // FK column
    @JsonBackReference
    private Activity activity;
}
