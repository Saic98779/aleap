package com.metaverse.workflow.startupSurvey.Enums;

public enum BusinessModelType {
    B2B("B2B"),
    B2C("B2C"),
    B2G("B2G"),
    OTHER("Other");

    private final String displayName;

    BusinessModelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

