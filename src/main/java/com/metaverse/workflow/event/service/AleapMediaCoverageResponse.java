package com.metaverse.workflow.event.service;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AleapMediaCoverageResponse {

    private Long mediaCoverageId;

    private Long eventId;

    private String mediaCoverageType;

    private String image1;

    private String image2;

    private String image3;

    private String mediaCoverageUrl;

    private Date date;

    private Date createdOn;

    private Date updatedOn;
}
