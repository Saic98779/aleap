package com.metaverse.workflow.startupSurvey.Enums;

public enum RevenueModel {
    SUBSCRIPTION("Subscription"),
    COMMISSION("Commission"),
    LICENSING("Licensing"),
    ONE_TIME_SALE("One-time Sale"),
    FREEMIUM("Freemium"),
    OTHERS("Others");

    private final String displayName;

    RevenueModel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

