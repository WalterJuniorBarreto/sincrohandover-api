package com.sincrohandover.api.modules.project.application.service;

import com.sincrohandover.api.modules.project.application.dto.ProjectRequest;
import com.sincrohandover.api.modules.project.application.dto.ProjectResponse;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request);
}
