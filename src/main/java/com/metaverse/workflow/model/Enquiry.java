package com.metaverse.workflow.model;

import com.metaverse.workflow.common.enums.Stage;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "enquiry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Details
    private String walkInName;

    private String whatsappNumber;

    private String state;
    private String district;
    private String mandal;
    private String village;
    private String streetOrBlock;
    private String houseNoOrDoorNo;
    private String pinCode;

    private String email;

    private String startupOrCompanyName;

    // Idea / Product
    @Column(columnDefinition = "TEXT")
    private String innovationDescription;

    // Sector (multi-select possible)
    @ElementCollection
    @CollectionTable(name = "enquiry_sectors", joinColumns = @JoinColumn(name = "enquiry_id"))
    @Column(name = "sector")
    private List<String> sectors;

    // Stage of Idea
    @Enumerated(EnumType.STRING)
    private Stage stage;

    // Incubation Support
    private Boolean incubationSupportRequired;

    @Column(columnDefinition = "TEXT")
    private String incubationSupportDescription;

    // Support Services Required (multi-select)
    @ElementCollection
    @CollectionTable(name = "enquiry_support_services", joinColumns = @JoinColumn(name = "enquiry_id"))
    @Column(name = "service")
    private List<String> supportServicesRequired;

    // Signature (optional - can store file path or base64)
    private String applicantSignature;

    // Office Use
    private String formReceivedDate;

    private String officialName;

    private String enquiryFromFilePath;

    @Column(columnDefinition = "TEXT")
    private String discussionSummary;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
}
