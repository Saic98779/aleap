package com.metaverse.workflow.startupSurvey.Enums;

public enum RegistrationType {
    PVT_LTD("Pvt Ltd"),
    LLP("LLP"),
    PARTNERSHIP("Partnership"),
    OPC("OPC"),
    OTHER("Other");

    private final String displayName;

    RegistrationType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

