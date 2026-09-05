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
import com.sincrohandover.api.shared.domain.exception.OutsideWorkingHoursException;
import com.sincrohandover.api.shared.domain.exception.ResourceNotFoundException;
import com.sincrohandover.api.shared.domain.model.ShiftBoundaries;
import com.sincrohandover.api.shared.domain.service.ShiftTimeCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandoveServiceImpl implements HandoverService {


    private final HandoverRepository handoverRepository;
    private final HandoverMapper handoverMapper;

    private final AppUserRepository appUserRepository;
    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;

    private final ShiftTimeCalculator shiftTimeCalculator;

    @Override
    @Transactional
    public HandoverResponse createHandover(HandoverRequest request){

        log.debug("Iniciando orquestacion de Handove. Autor: {}", request.authorId());


        AppUser author = appUserRepository.findById(request.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author no encontrado"));

        Instant nowUtc = Instant.now();

        ShiftBoundaries boundaries = shiftTimeCalculator.calculateUtcBoundaries(
                author.getTimezone(),
                author.getWorkStart(),
                author.getWorkEnd()
        );


        if(nowUtc.isBefore(boundaries.startInstant()) || nowUtc.isAfter(boundaries.endInstant())){
            log.warn("Alerta de seguridad/negocio: Usuario {} intento crear un Handover fuera de su horario laboral. Servidor {}, Turno UTC: {} - {}", author.getEmail(), nowUtc, boundaries.startInstant(), boundaries.endInstant());
            throw new OutsideWorkingHoursException("No es posible registrar el turno. Te encuentras fuera de tu horario laboral asignado");
        }

        Handover handover = handoverMapper.toEntity(request);

        Project project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> {
                    log.error("VIolacion de integridad: Proyecto no encontrado [{}]", request.projectId());
                    return new ResourceNotFoundException("PRoyecto no encontrado");
                });

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> {
                    log.error("Violacion de integeidad: Categoria no encontrada [{}]", request.categoryId());
                    return new ResourceNotFoundException("Categoria no encontrada");
                });

        handover.setAuthor(author);
        handover.setProject(project);
        handover.setCategory(category);

        Handover savedHandover = handoverRepository.save(handover);
        log.info("Handover orquestado y persistido con exito (ID: {})", savedHandover.getId());

        return handoverMapper.toResponse(savedHandover);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<HandoverResponse> getHandovers(UUID projectId, String status, Pageable pageable){
        log.debug("EJecutando consulta paginada de Handovers. FIltros -> Proyecto: {}, Estado: {}", projectId, status);

        return handoverRepository.findByFiltersDynamically(projectId, status, pageable)
                .map(handoverMapper::toResponse);
    }

}
