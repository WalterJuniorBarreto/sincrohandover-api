package com.sincrohandover.api.modules.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO de Entrada para AppUser.
 * Valida formatos estandarizados (como el email) de forma nativa.
 */
public record AppUserRequest(

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser una dirección de correo electrónico con formato válido")
        String email,

        String timezone,

        String workStart,

        String workEnd

) {}
