package com.sincrohandover.api.modules.project.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de Entrada para Project.
 * Garantiza inmutabilidad y validación estricta del payload antes de procesar el negocio.
 */
public record ProjectRequest(

        @NotBlank(message = "El nombre del proyecto es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
        String name

) {}