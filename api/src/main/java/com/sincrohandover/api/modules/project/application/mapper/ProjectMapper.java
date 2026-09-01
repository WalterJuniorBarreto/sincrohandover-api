package com.sincrohandover.api.modules.project.application.mapper;

import com.sincrohandover.api.modules.project.application.dto.ProjectRequest;
import com.sincrohandover.api.modules.project.application.dto.ProjectResponse;
import com.sincrohandover.api.modules.project.domain.model.Project;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper estandarizado para la entidad Project.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface ProjectMapper {

    ProjectResponse toResponse(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Project toEntity(ProjectRequest request);
}