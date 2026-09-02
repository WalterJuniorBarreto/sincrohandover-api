package com.sincrohandover.api.modules.handover.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO de Entrada para Handover (Transaccional).
 * Garantiza inmutabilidad y valida los identificadores de relación
 * antes de interactuar con los repositorios y la base de datos.
 */
public record HandoverRequest(

        @NotNull(message = "El identificador del autor es obligatorio")
        UUID authorId,

        @NotNull(message = "El identificador del proyecto es obligatorio")
        UUID projectId,

        @NotNull(message = "El identificador de la categoría es obligatorio")
        UUID categoryId,

        @NotBlank(message = "El payload JSON no puede estar vacío")
        String payload

) {}