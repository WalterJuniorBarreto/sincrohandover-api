package com.sincrohandover.api.modules.handover.application.mapper;

import com.sincrohandover.api.modules.category.application.mapper.CategoryMapper;
import com.sincrohandover.api.modules.handover.application.dto.HandoverRequest;
import com.sincrohandover.api.modules.handover.application.dto.HandoverResponse;
import com.sincrohandover.api.modules.handover.domain.model.Handover;
import com.sincrohandover.api.modules.project.application.mapper.ProjectMapper;
import com.sincrohandover.api.modules.user.application.mapper.AppUserMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper transaccional para la entidad Handover.
 *
 * Utiliza composición de Mappers (uses = {...}) para delegar la transformación
 * de las entidades anidadas (author, project, category) a sus respectivos
 * componentes de Spring, construyendo un grafo enriquecido e inmutable en el Response.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        uses = {AppUserMapper.class, ProjectMapper.class, CategoryMapper.class}
)
public interface HandoverMapper {

    /**
     * Mapea la entidad Handover hacia su DTO de salida enriquecido.
     * MapStruct invocará automáticamente los mappers satélites para resolver
     * las relaciones (AppUser -> AppUserResponse, etc.).
     */
    HandoverResponse toResponse(Handover handover);

    /**
     * Mapea el DTO de entrada hacia la entidad Handover.
     *
     * NOTA ARQUITECTÓNICA:
     * Se ignoran explícitamente los campos relacionales (author, project, category)
     * y de auditoría. El Request solo transporta UUIDs; la capa de Servicio será
     * la responsable exclusiva de ejecutar los 'findById' e inyectar las entidades
     * gestionadas (Persistent) antes de guardar en la base de datos.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "category", ignore = true)
    Handover toEntity(HandoverRequest request);
}


