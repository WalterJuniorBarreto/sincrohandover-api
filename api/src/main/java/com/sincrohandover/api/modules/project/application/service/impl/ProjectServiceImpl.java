package com.sincrohandover.api.modules.project.application.service.impl;


import com.sincrohandover.api.modules.project.application.service.ProjectService;
import com.sincrohandover.api.modules.project.application.dto.ProjectRequest;
import com.sincrohandover.api.modules.project.application.dto.ProjectResponse;
import com.sincrohandover.api.modules.project.application.mapper.ProjectMapper;
import com.sincrohandover.api.modules.project.domain.model.Project;
import com.sincrohandover.api.modules.project.infrastructure.persistence.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request){
        Project project = projectMapper.toEntity(request);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponse(savedProject);
    }
}
