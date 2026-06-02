package com.metaverse.workflow.startupSurvey.Enums;

public enum BusinessMentoring {
    BUSINESS_MODEL_REFINEMENT("Business Model Refinement"),
    PRODUCT_MARKET_FIT("Product-Market Fit"),
    PRICING_STRATEGY("Pricing Strategy"),
    BUSINESS_EXPANSION_PLANNING("Business Expansion Planning"),
    COMPETITIVE_STRATEGY("Competitive Strategy");

    private final String displayName;

    BusinessMentoring(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
