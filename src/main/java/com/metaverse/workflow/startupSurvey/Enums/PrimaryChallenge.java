package com.metaverse.workflow.startupSurvey.Enums;

public enum PrimaryChallenge {
    AWARENESS("Awareness"),
    PRICING("Pricing"),
    COMPETITION("Competition"),
    DIGITAL_MARKETING("Digital Marketing"),
    LOGISTICS("Logistics"),
    OTHERS("Others");

    private final String displayName;

    PrimaryChallenge(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

