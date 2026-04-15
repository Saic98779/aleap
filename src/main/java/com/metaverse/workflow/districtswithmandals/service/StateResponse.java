package com.metaverse.workflow.districtswithmandals.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateResponse {
    private Integer stateId;
    private String stateName;
}
