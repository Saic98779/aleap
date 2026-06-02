package com.metaverse.workflow.startupSurvey.Enums;

public enum StartupStage {
    IDEA("Idea"),
    PROTOTYPE("Prototype"),
    MVP("MVP (Minimum Viable Product)"),
    EARLY_REVENUE("Early Revenue"),
    GROWTH("Growth"),
    SCALEUP("Scaleup");

    private final String displayName;

    StartupStage(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
