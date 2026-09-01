package com.sincrohandover.api.modules.category.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * PoC: Java Record Inmutable con Jakarta Validation.
 * Los registros procesan las anotaciones directamente en sus componentes.
 * Cuando este DTO pase por un @RestController con un @Valid, Spring Boot
 * auditará estos campos antes de ejecutar el código del controlador.
 */
public record PingRequestRecord(

        @NotNull(message = "El ID de correlacion no puede ser nulo")
        Long correlationId,

        @NotBlank(message = "El mensaje de ping no puede estar en blanco ni contener solo espacios")
        @Size(min = 3, max = 50, message = "El mensaje debe tener entre 3 y 50 caracteres")
        String message
) { }
