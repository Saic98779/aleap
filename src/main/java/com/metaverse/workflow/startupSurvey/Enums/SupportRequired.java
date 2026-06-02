package com.metaverse.workflow.startupSurvey.Enums;

public enum SupportRequired {
    BRANDING_MARKETING("Branding & Marketing"),
    DIGITAL_PRESENCE("Digital Presence (Website, SEO, Ads)"),
    CUSTOMER_ACQUISITION("Customer Acquisition"),
    EXPORT_READINESS("Export Readiness"),
    B2B_B2C_B2G_LINKAGES("B2B/B2C/B2G Linkages");

    private final String displayName;

    SupportRequired(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
