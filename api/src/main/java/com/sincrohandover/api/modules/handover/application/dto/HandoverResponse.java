package com.sincrohandover.api.modules.handover.application.dto;

import com.sincrohandover.api.modules.category.application.dto.CategoryResponse;
import com.sincrohandover.api.modules.handover.domain.model.HandoverStatus;
import com.sincrohandover.api.modules.project.application.dto.ProjectResponse;
import com.sincrohandover.api.modules.user.application.dto.AppUserResponse;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO de Salida para Handover.
 * Agrupa la información core de la entrega y anida los DTOs de soporte
 * para entregar un grafo completo e inmutable, ideal para el Dashboard en Angular.
 */
public record HandoverResponse(
        UUID id,
        HandoverStatus status,
        String payload,
        Instant createdAt,

        // DTOs Anidados de Soporte
        AppUserResponse author,
        ProjectResponse project,
        CategoryResponse category
) {}