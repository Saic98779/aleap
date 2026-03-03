package com.metaverse.workflow.counsellor.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CounsellorAllotmentsRequest {

    private Long counsellorRegistrationId;
    private Integer mandalId;
    private String startDate;
    private String endDate;
    private String dateOfAllotment;
}
