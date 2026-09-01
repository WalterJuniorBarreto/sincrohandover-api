package com.sincrohandover.api.modules.category.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de Entrada para Categorías.
 * Implementado como Record para garantizar inmutabilidad absoluta.
 * La validación de Jakarta asegura la integridad del payload antes de tocar el dominio.
 */
public record CategoryRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name

) {}
