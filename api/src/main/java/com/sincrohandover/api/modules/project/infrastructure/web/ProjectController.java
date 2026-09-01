package com.sincrohandover.api.modules.project.infrastructure.web;

import com.sincrohandover.api.modules.project.application.dto.ProjectRequest;
import com.sincrohandover.api.modules.project.application.dto.ProjectResponse;
import com.sincrohandover.api.modules.project.application.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Crea un nuevo proyecto en el sistema.
     *
     * @param request Payload auditado para prevenir inyección de datos nulos o malformados.
     * @return 201 Created con URI absoluta en cabecera Location.
     */
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request){
        log.info("HTTP POST /api/v1/projects - Iniciando creacion de proyecto");

        ProjectResponse response = projectService.createProject(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        log.debug("Proyecto creado. LOcation {} ", location);
        return ResponseEntity.created(location).body(response);

    }
}
