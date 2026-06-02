package com.metaverse.workflow.startupSurvey.Enums;

public enum ProductReadinessLevel {
    IDEA("Idea"),
    UNDER_DEVELOPMENT("Under Development"),
    LAUNCHED("Launched"),
    IMPROVING("Improving");

    private final String displayName;

    ProductReadinessLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

