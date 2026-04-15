package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "aleap_participant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AleapParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String participantName;
    private String gender;
    private String category;
    private String disability;

    private Date dob;
    private Integer age;

    private String aadharNo;
    private Long contactNo;
    private String email;

    private String organizationName;
    private String designation;

    private String state;
    private String district;
    private String mandal;
    private String village;
    private String streetOrBlock;
    private String houseNoOrDoorNo;
    private String pinCode;


    private String otherInformation;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
