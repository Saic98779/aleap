package com.metaverse.workflow.counsellor.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CounsellorAllotmentsResponse {
    private Long counsellorAllotmentId;
    private String counsellorName;
    private Integer mandalId;
    private String mandalName;
    private String startDate;
    private String endDate;
    private String dateOfAllotment;
}
