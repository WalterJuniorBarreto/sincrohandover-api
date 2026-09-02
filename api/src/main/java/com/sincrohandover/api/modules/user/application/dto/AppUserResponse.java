package com.sincrohandover.api.modules.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO de Salida para AppUser.
 * Contrato inmutable que expone la información pública del usuario.
 */
public record AppUserResponse(
        UUID id,
        String email,
        String timezone,
        String workStart,
        String workEnd,
        Instant createdAt
) {}