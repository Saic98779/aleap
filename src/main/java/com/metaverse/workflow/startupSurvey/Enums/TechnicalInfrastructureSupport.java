package com.metaverse.workflow.startupSurvey.Enums;

public enum TechnicalInfrastructureSupport {
    IT_SYSTEMS_SETUP("IT Systems Setup"),
    CYBERSECURITY("Cybersecurity"),
    CLOUD_SERVICES("Cloud Services"),
    AI_ML_ADOPTION("AI/ML Adoption"),
    APP_WEB_DEVELOPMENT("App/Web Development"),
    UI_UX_IMPROVEMENT("UI/UX Improvement");

    private final String displayName;

    TechnicalInfrastructureSupport(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

