package com.metaverse.workflow.enquiry.service;

import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnquiryResponse {

    private Long id;
    private String walkInName;
    private String whatsappNumber;
    private String address;
    private String email;
    private String startupOrCompanyName;
    private String innovationDescription;
    private List<String> sectors;
    private String stage;
    private Boolean incubationSupportRequired;
    private String incubationSupportDescription;
    private List<String> supportServicesRequired;
    private String applicantSignature;
    private String formReceivedDate;
    private String officialName;
    private String discussionSummary;
    private String enquiryFromFilePath;
}