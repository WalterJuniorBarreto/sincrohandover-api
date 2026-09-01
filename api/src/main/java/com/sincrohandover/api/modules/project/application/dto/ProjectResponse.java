package com.sincrohandover.api.modules.project.application.dto;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO de Salida para Project.
 * Expone de forma segura, inmutable y esbelta los datos del proyecto.
 */
public record ProjectResponse(
        UUID id,
        String name,
        Instant createdAt
) {}