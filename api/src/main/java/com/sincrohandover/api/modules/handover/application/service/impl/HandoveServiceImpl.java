package com.sincrohandover.api.modules.handover.application.service.impl;

import com.sincrohandover.api.modules.handover.application.service.HandoverService;
import com.sincrohandover.api.modules.category.domain.model.Category;
import com.sincrohandover.api.modules.category.infrastructure.persistence.CategoryRepository;
import com.sincrohandover.api.modules.handover.application.dto.HandoverRequest;
import com.sincrohandover.api.modules.handover.application.dto.HandoverResponse;
import com.sincrohandover.api.modules.handover.application.mapper.HandoverMapper;
import com.sincrohandover.api.modules.handover.domain.model.Handover;
import com.sincrohandover.api.modules.handover.infrastructure.persistence.HandoverRepository;
import com.sincrohandover.api.modules.project.domain.model.Project;
import com.sincrohandover.api.modules.project.infrastructure.persistence.ProjectRepository;
import com.sincrohandover.api.modules.user.domain.model.AppUser;
import com.sincrohandover.api.modules.user.infrastructure.persistence.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandoveServiceImpl implements HandoverService {


    private final HandoverRepository handoverRepository;
    private final HandoverMapper handoverMapper;

    private final AppUserRepository appUserRepository;
    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public HandoverResponse createHandover(HandoverRequest request){
        log.debug("Iniciando orquestancion de handove, Autor: {}, proyecto: {}, categoria: {}",
                request.authorId(), request.projectId(), request.categoryId());

        Handover handover = handoverMapper.toEntity(request);

        AppUser author = appUserRepository.findById(request.authorId())
                .orElseThrow(() -> {
                    log.error("Violacion de integridad: Autor no encontrado [{}]", request.authorId());
                    return new RuntimeException("Autor no encontrado");
                });

        Project project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> {
                    log.error("VIolacion de integridad: Proyecto no encontrado [{}]", request.projectId());
                    return new RuntimeException("PRoyecto no encontrado");
                });

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> {
                    log.error("Violacion de integeidad: Categoria no encontrada [{}]", request.categoryId());
                    return new RuntimeException("Categoria no encontrada");
                });

        handover.setAuthor(author);
        handover.setProject(project);
        handover.setCategory(category);

        Handover savedHandover = handoverRepository.save(handover);
        log.info("Handover orquestado y persistido con exito (ID: {})", savedHandover.getId());

        return handoverMapper.toResponse(savedHandover);
    }
}
