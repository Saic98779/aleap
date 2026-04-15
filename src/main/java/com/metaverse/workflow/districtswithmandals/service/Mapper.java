package com.metaverse.workflow.districtswithmandals.service;

import com.metaverse.workflow.model.District;
import com.metaverse.workflow.model.Mandal;
import com.metaverse.workflow.model.State;

public class Mapper {

    public static StateResponse mapStates(State state) {
        return StateResponse.builder()
                .stateId(state.getStateId())
                .stateName(state.getStateName())
                .build();

    }
    public static DistrictResponse mapDistricts(District district) {
        return DistrictResponse.builder()
                .districtId(district.getDistrictId())
                .districtName(district.getDistrictName())
                .build();

    }

    public static MandalResponse mapMandals(Mandal mandal) {
        return MandalResponse.builder()
                .mandalId(mandal.getMandalId())
                .mandalName(mandal.getMandalName())
                .build();
    }
}
