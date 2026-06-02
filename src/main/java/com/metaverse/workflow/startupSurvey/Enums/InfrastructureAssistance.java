package com.metaverse.workflow.startupSurvey.Enums;

public enum InfrastructureAssistance {
    INCUBATION_SPACE("Incubation Space"),
    MANUFACTURING_SETUP("Manufacturing Setup"),
    EQUIPMENT_PROCUREMENT("Equipment Procurement"),
    REGULATORY_CERTIFICATIONS("Regulatory Certifications (e.g., FSSAI, ISO, etc.)");

    private final String displayName;

    InfrastructureAssistance(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

