package com.metaverse.workflow.startupSurvey.Enums;

public enum ProductDevelopmentSupport {
    PRODUCT_DEVELOPMENT("Product Development"),
    PROTOTYPING("Prototyping"),
    TECH_VALIDATION("Tech Validation"),
    R_AND_D_COLLABORATION("R&D Collaboration"),
    DESIGN_AND_PACKAGING("Design and Packaging"),
    QUALITY_TESTING_CERTIFICATION("Quality Testing & Certification");

    private final String displayName;

    ProductDevelopmentSupport(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

