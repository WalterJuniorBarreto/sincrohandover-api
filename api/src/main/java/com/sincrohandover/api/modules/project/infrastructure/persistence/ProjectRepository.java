package com.sincrohandover.api.modules.project.infrastructure.persistence;

import com.sincrohandover.api.modules.project.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
