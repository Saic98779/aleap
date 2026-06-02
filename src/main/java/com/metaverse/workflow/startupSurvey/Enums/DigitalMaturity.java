package com.metaverse.workflow.startupSurvey.Enums;

public enum DigitalMaturity {
    CRM("CRM"),
    INVENTORY_MANAGEMENT("Inventory Mgmt"),
    PAYMENT_GATEWAY("Payment Gateway"),
    WEBSITE_APP("Website/App"),
    ERP("ERP"),
    NONE("None");

    private final String displayName;

    DigitalMaturity(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

