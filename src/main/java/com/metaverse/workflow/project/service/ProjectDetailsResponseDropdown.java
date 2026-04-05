package com.metaverse.workflow.project.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectDetailsResponseDropdown {
    private Long project_id;
    private String titleOfProject;
}
