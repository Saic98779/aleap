package com.metaverse.workflow.startupSurvey.Enums;

public enum SupportNeeded {
    HIRING_STRATEGY("Hiring Strategy"),
    SKILL_DEVELOPMENT_TRAINING("Skill Development / Training"),
    FOUNDERS_CAPACITY_BUILDING("Founders' Capacity Building"),
    TEAM_PERFORMANCE_MANAGEMENT("Team Performance Management"),
    HR_PAYROLL_SETUP("HR/Payroll Setup");

    private final String displayName;

    SupportNeeded(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

