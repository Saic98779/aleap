package com.metaverse.workflow.event.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AleapMediaCoverageRequest {

    private Long mediaCoverageId;

    private Long eventId;

    private String mediaCoverageType;

    private String mediaCoverageUrl;

    private String date;

    private String image1;

    private String image2;

    private String image3;
}